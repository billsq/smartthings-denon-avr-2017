definition(
    name: "Denon AVR Connect",
    namespace: "billsq",
    author: "Qian Sheng",
    description: "Manage Denon AV Receivers.",

    category: "My Apps",
    iconUrl: "https://raw.githubusercontent.com/billsq/smartthings-denon-avr-2017/master/smartapps/denon.png",
    iconX2Url: "https://raw.githubusercontent.com/billsq/smartthings-denon-avr-2017/master/smartapps/denon@2x.png",
    iconX3Url: "https://raw.githubusercontent.com/billsq/smartthings-denon-avr-2017/master/smartapps/denon@3x.png"
)

preferences {
    page(name: "deviceDiscovery", title: "Device Setup", content: "deviceDiscovery")
}

def deviceDiscovery() {
    def options = [:]
    def devices = getVerifiedDevices()
    devices.each { ssdpUSN, deviceData ->
        def title = "${deviceData["name"]} [Model: ${deviceData["model"]}"
        options[ssdpUSN] = title
        log.debug "Adding device to discovery page key=${ssdpUSN} title=${title}"
    }

    ssdpSubscribe()
    ssdpDiscover()

    return dynamicPage(name: "deviceDiscovery", title: "Discovering your Denon AV receivers...", nextPage: "", refreshInterval: 5, install: true, uninstall: true) {
        section {
            input "selectedDevices", "enum", required: false, title: "Select Devices (${options.size() ?: 0} found)", multiple: true, options: options
        }
    }
}

def installed() {
    log.debug "Installed with settings: ${settings}"

    initialize()
}

def updated() {
    log.debug "Updated with settings: ${settings}"

    unsubscribe()
    initialize()
}

def initialize() {
    unsubscribe()
    unschedule()

    ssdpSubscribe()

    if (selectedDevices) {
        addDevices()
    }

    runEvery5Minutes("ssdpDiscover")
}

void ssdpSubscribe() {
    subscribe(location, "ssdpTerm.urn:schemas-denon-com:device:AiosDevice:1", ssdpHandler)
}

void ssdpDiscover() {
    sendHubCommand(new physicalgraph.device.HubAction("lan discovery urn:schemas-denon-com:device:AiosDevice:1", physicalgraph.device.Protocol.LAN))
}

Map getVerifiedDevices() {
    getDevices().findAll { it.value.verified == true }
}

Map getDevices() {
    if (!state.devices) {
        state.devices = [:]
    }
    state.devices
}

void verifyDevices() {
    def devices = getDevices()
    devices.each { ssdpUSN, deviceData ->
        int port = convertHexToInt(deviceData["deviceAddress"])
        String ip = convertHexToIP(deviceData["networkAddress"])
        String host = "${ip}:${port}"

        log.debug("verifyDevices host=${host} path=${deviceData["ssdpPath"]}")

        sendHubCommand(new physicalgraph.device.HubAction([
                path: deviceData["ssdpPath"],
                method: "GET",
                headers: [Host: host]
            ],
            host,
            [callback: deviceDescriptionHandler]
        ))
    }
}

def addDevices() {
	log.debug "Add devices"
    def devices = getDevices()

    selectedDevices.each { dni ->
        def deviceData = devices[dni]
        
        if (deviceData) {
            def childDevice = getChildDevice(dni)

            if (!childDevice) {
                log.debug "Creating child device with DNI: ${dni}"
                addChildDevice("billsq", "Denon AV Receiver", dni, deviceData["hub"], [
                    "label": deviceData["name"],
                    "data": deviceData
                ])
            }
        }
    }
}

def ssdpHandler(evt) {
    def description = evt.description
    def hub = evt?.hubId

    def parsedEvent = parseLanMessage(description)
    parsedEvent << ["hub": hub]

    log.debug "parsedEvent ${parsedEvent}"

    def devices = getDevices()
    String ssdpUSN = parsedEvent.ssdpUSN.toString()
    if (devices."${ssdpUSN}") {
        def d = devices."${ssdpUSN}"
        d << parsedEvent
    } else {
        devices << ["${ssdpUSN}": parsedEvent]
    }
    
    verifyDevices()
}

void deviceDescriptionHandler(physicalgraph.device.HubResponse hubResponse) {
    def body = hubResponse.xml
    def devices = getDevices()
    def device = devices.find { it?.key?.contains(body?.device?.UDN?.text()) }
    if (device) {
        def verified = 0
        device.value << [name: body?.device?.friendlyName?.text(), model:body?.device?.modelName?.text(), serialNumber:body?.device?.serialNum?.text(), apiPort:body?.device?.X_WebAPIPort?.text()]

        if (body?.device?.deviceList?.device?.size()) {
            def devs = body?.device?.deviceList?.device

            devs.each { dev ->
                if (dev.deviceType?.text().contains("MediaRenderer")) {
                    if (dev.serviceList?.service?.size()) {
                        def services = dev.serviceList?.service

                        services.each {
                            if (it.serviceType?.text().contains("RenderingControl")) {
                                def control = it.controlURL?.text()
                                def event = it.eventSubURL?.text()

                                if (!control.startsWith("/")) {
                                    control = "/" + control
                                }

                                if (!event.startsWith("/")) {
                                    event = "/" + event
                                }

                                log.info "Got RenderingControl control=${control} event=${event} for device ${device.value["name"]}"
                                device.value << [RenderingControlControlPath: control, RenderingControlEventPath: event]
                                verified += 1
                            } else if (it.serviceType?.text().contains("AVTransport")) {
                                def control = it.controlURL?.text()
                                def event = it.eventSubURL?.text()

                                if (!control.startsWith("/")) {
                                    control = "/" + control
                                }

                                if (!event.startsWith("/")) {
                                    event = "/" + event
                                }

                                log.info "Got AVTransport control=${control} event=${event} for device ${device.value["name"]}"
                                device.value << [AVTransportControlPath: control, AVTransportEventPath: event]
                                verified += 1
                            }
                        }
                    }
                }
            }
        }

        if (verified == 2) {
            device.value << [verified: true]
            
            def child = getChildDevice(device.key)
            if (child) {
            	log.debug "Found existing child, sync..."
                child.sync(device.value)
            }
        } else {
            device.value << [verified: false]
        }

        log.debug "deviceDescriptionHandler device.value=${device.value}"
    }
}

private Integer convertHexToInt(hex) {
    Integer.parseInt(hex,16)
}

private String convertHexToIP(hex) {
    [convertHexToInt(hex[0..1]),convertHexToInt(hex[2..3]),convertHexToInt(hex[4..5]),convertHexToInt(hex[6..7])].join(".")
}
