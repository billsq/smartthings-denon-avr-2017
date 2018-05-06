metadata {
    definition (name: "Denon AV Receiver", namespace: "billsq", author: "Qian Sheng") {
        capability "Actuator"
        capability "Switch"
        capability "Polling"
        capability "Refresh"
        capability "Sensor"
        capability "Music Player"
        capability "Switch Level"

        attribute "phono", "string"
        attribute "cd", "string"
        attribute "dvd", "string"
        attribute "bd", "string"
        attribute "tv", "string"
        attribute "satcbl", "string"
        attribute "mplay", "string"
        attribute "game", "string"
        attribute "tuner", "string"
        attribute "aux1", "string"
        attribute "aux2", "string"
        attribute "net", "string"
        attribute "bt", "string"

        command "phono"
        command "cd"
        command "dvd"
        command "bd"
        command "tv"
        command "satcbl"
        command "mplay"
        command "game"
        command "tuner"
        command "aux1"
        command "aux2"
        command "net"
        command "bt"
    }

    simulator {}

    tiles(scale: 2) {
        multiAttributeTile(name: "mediaMulti", type:"mediaPlayer", width:6, height:4) {
            tileAttribute("device.status", key: "PRIMARY_CONTROL") {
                attributeState("paused", label:"Paused",)
                attributeState("playing", label:"Playing")
                attributeState("stopped", label:"Stopped")
            }

            tileAttribute("device.status", key: "MEDIA_STATUS") {
                attributeState("paused", label:"Paused", action:"music Player.play", nextState: "playing")
                attributeState("playing", label:"Playing", action:"music Player.pause", nextState: "paused")
                attributeState("stopped", label:"Stopped", action:"music Player.play", nextState: "playing")
            }

            tileAttribute("device.status", key: "PREVIOUS_TRACK") {
                attributeState("status", action:"music Player.previousTrack", defaultState: true)
            }

            tileAttribute("device.status", key: "NEXT_TRACK") {
                attributeState("status", action:"music Player.nextTrack", defaultState: true)
            }

            tileAttribute ("device.level", key: "SLIDER_CONTROL") {
                attributeState("level", action:"music Player.setLevel")
            }

            tileAttribute ("device.mute", key: "MEDIA_MUTED") {
                attributeState("unmuted", action:"music Player.mute", nextState: "muted")
                attributeState("muted", action:"music Player.unmute", nextState: "unmuted")
            }

            tileAttribute("device.trackDescription", key: "MARQUEE") {
                attributeState("trackDescription", label:"${currentValue}", defaultState: true)
            }
        }

        standardTile("switch", "device.switch", width: 2, height: 2, decoration: "flat", canChangeIcon: true) {
            state "on", label:'${name}', action:"switch.off", icon:"st.Electronics.electronics19", backgroundColor:"#00A0DC", nextState:"turningOff"
            state "off", label:'${name}', action:"switch.on", icon:"st.Electronics.electronics19", backgroundColor:"#ffffff", nextState:"turningOn"
            state "turningOn", label:'${name}', icon:"st.Electronics.electronics19", backgroundColor:"#00A0DC", nextState:"on"
            state "turningOff", label:'${name}', icon:"st.Electronics.electronics19", backgroundColor:"#ffffff", nextState:"off"
        }

        standardTile("phono", "device.phono", width: 2, height: 1, decoration: "flat"){
            state "off", label:'Phono', action:"phono", icon:"st.Electronics.electronics4" , backgroundColor:"#ffffff", nextState:"on"
            state "on", label:'Phono', action:"phono", icon:"st.Electronics.electronics4", backgroundColor:"#E6E6E6", nextState:"off"
        }

        standardTile("cd", "device.cd", width: 2, height: 1, decoration: "flat"){
            state "off", label:'CD', action:"cd", icon:"st.Electronics.electronics1" , backgroundColor:"#ffffff", nextState:"on"
            state "on", label:'CD', action:"cd", icon:"st.Electronics.electronics1", backgroundColor:"#E6E6E6", nextState:"off"
        }

        standardTile("dvd", "device.dvd", width: 2, height: 1, decoration: "flat"){
            state "off", label:'DVD', action:"dvd", icon:"st.Electronics.electronics8" , backgroundColor:"#ffffff", nextState:"on"
            state "on", label:'DVD', action:"dvd", icon:"st.Electronics.electronics8", backgroundColor:"#E6E6E6", nextState:"off"
        }

        standardTile("bd", "device.bd", width: 2, height: 1, decoration: "flat"){
            state "off", label:'Blu-ray', action:"bd", icon:"st.Electronics.electronics9" , backgroundColor:"#ffffff", nextState:"on"
            state "on", label:'Blu-ray', action:"bd", icon:"st.Electronics.electronics9", backgroundColor:"#E6E6E6", nextState:"off"
        }

        standardTile("tv", "device.tv", width: 2, height: 1, decoration: "flat"){
            state "off", label:'TV', action:"tv", icon:"st.Electronics.electronics3" , backgroundColor:"#ffffff", nextState:"on"
            state "on", label:'TV', action:"tv", icon:"st.Electronics.electronics3", backgroundColor:"#E6E6E6", nextState:"off"
        }

        standardTile("satcbl", "device.satcbl", width: 2, height: 1, decoration: "flat"){
            state "off", label:'SAT/CBL', action:"satcbl", icon:"st.Electronics.electronics18" , backgroundColor:"#ffffff", nextState:"on"
            state "on", label:'SAT/CBL', action:"satcbl", icon:"st.Electronics.electronics18", backgroundColor:"#E6E6E6", nextState:"off"
        }

        standardTile("mplay", "device.mplay", width: 2, height: 1, decoration: "flat"){
            state "off", label:'Media Player', action:"mplay", icon:"st.Electronics.electronics13" , backgroundColor:"#ffffff", nextState:"on"
            state "on", label:'Media Player', action:"mplay", icon:"st.Electronics.electronics13", backgroundColor:"#E6E6E6", nextState:"off"
        }

        standardTile("game", "device.game", width: 2, height: 1, decoration: "flat"){
            state "off", label:'Game', action:"game", icon:"st.Electronics.electronics5" , backgroundColor:"#ffffff", nextState:"on"
            state "on", label:'Game', action:"game", icon:"st.Electronics.electronics5", backgroundColor:"#E6E6E6", nextState:"off"
        }

        standardTile("tuner", "device.tuner", width: 2, height: 1, decoration: "flat"){
            state "off", label:'Tuner', action:"tuner", icon:"st.Electronics.electronics10" , backgroundColor:"#ffffff", nextState:"on"
            state "on", label:'Tuner', action:"tuner", icon:"st.Electronics.electronics10", backgroundColor:"#E6E6E6", nextState:"off"
        }

        standardTile("aux1", "device.aux1", width: 2, height: 1, decoration: "flat"){
            state "off", label:'AUX1', action:"aux1", icon:"st.Electronics.electronics6" , backgroundColor:"#ffffff", nextState:"on"
            state "on", label:'AUX1', action:"aux1", icon:"st.Electronics.electronics6", backgroundColor:"#E6E6E6", nextState:"off"
        }

        standardTile("aux2", "device.aux2", width: 2, height: 1, decoration: "flat"){
            state "off", label:'AUX2', action:"aux2", icon:"st.Electronics.electronics6" , backgroundColor:"#ffffff", nextState:"on"
            state "on", label:'AUX2', action:"aux2", icon:"st.Electronics.electronics6", backgroundColor:"#E6E6E6", nextState:"off"
        }

        standardTile("net", "device.net", width: 2, height: 1, decoration: "flat"){
            state "off", label:'NET', action:"net", icon:"st.Entertainment.entertainment2" , backgroundColor:"#ffffff", nextState:"on"
            state "on", label:'NET', action:"net", icon:"st.Entertainment.entertainment2", backgroundColor:"#E6E6E6", nextState:"off"
        }

        standardTile("bt", "device.bt", width: 2, height: 1, decoration: "flat"){
            state "off", label:'Bluetooth', action:"bt", icon:"st.Electronics.electronics2" , backgroundColor:"#ffffff", nextState:"on"
            state "on", label:'Bluetooth', action:"bt", icon:"st.Electronics.electronics2", backgroundColor:"#E6E6E6", nextState:"off"
        }

        standardTile("refresh", "device.refresh", inactiveLabel: false, decoration: "flat", width: 1, height: 1) {
            state "default", label:"", action:"refresh.refresh", icon:"st.secondary.refresh"
        }

        main "switch"
        details(["mediaMulti", "switch", "phono", "cd", "dvd", "bd", "tv", "satcbl", "mplay", "game", "tuner", "aux1", "aux2", "net", "bt", "refresh"])
    }
}

// parse events into attributes
def parse(String description) {
    //log.debug "Parsing ${description}"

    def msg = parseLanMessage(description)
    def headerString = msg.header
    def events = []

    def bodyString = msg.body
    if (bodyString) {
        def body = new XmlSlurper().parseText(bodyString.replaceAll("[^\\x20-\\x7e]", ""))
        def parsed = false

        //log.debug "bodyString ${bodyString}"

        if (body?.property?.LastChange?.text()) {
            def lastChange = body?.property?.LastChange?.text()
            def event = new XmlSlurper().parseText(lastChange)

            //log.debug "lastChange ${lastChange}"
            /*
            if (event?.InstanceID?.Volume?.size()) {
                def volumes = event?.InstanceID?.Volume
                volumes.find {
                    if (it.@channel == "Zone1" || it.@channel == "Master") {
                        def volume = it.@val?.toInteger()
                        if (volume != null) {
                            log.trace "Got volume Zone1=${volume}"
                            events << createEvent(name: "level", value: volume)
                            parsed = true
                            return true
                        }
                    }
                    return false
                }
            }

            if (event?.InstanceID?.Mute?.size()) {
                def mutes = event?.InstanceID?.Mute
                mutes.find {
                    if (it.@channel == "Zone1" || it.@channel == "Master") {
                        def mute = it.@val?.toBoolean()
                        if (mute != null) {
                            log.trace "Got mute Zone1=${mute}"
                            events << createEvent(name: "mute", value: mute ? "muted" : "unmuted")
                            parsed = true
                            return true
                        }
                    }
                    return false
                }
            }
            */

            if (event?.InstanceID?.TransportState?.size()) {
                def transportState = event?.InstanceID?.TransportState?.@val
                def status = "stopped"

                if (transportState == "PLAYING") {
                    status = "playing"
                } else if (transportState == "PAUSED_PLAYBACK") {
                    status = "paused"
                }

                parsed = true
                log.trace "Got TransportState=${transportState} status=${status}"
                events << createEvent(name: "status", value: status)
            }

            if (event?.InstanceID?.CurrentTrackMetaData?.size()) {
                def metaData = event?.InstanceID?.CurrentTrackMetaData?.@val.text()
                //log.debug "metadata ${metaData}"

                def item = new XmlSlurper().parseText(metaData)

                if (item?.item?.size()) {
                    def trackData = [:]

                    trackData["name"] = item?.item?.title?.text()
                    trackData["artist"] = item?.item?.artist?.text()
                    trackData["album"] = item?.item?.album?.text()
                    trackData["creator"] = item?.item?.creator?.text()

                    if (trackData["artist"]?.trim() == null || trackData["artist"] == '""') {
                        trackData["artist"] = trackData["creator"]
                    }

                    log.info "name=${trackData["name"]} artist=${trackData["artist"]} album=${trackData["album"]}"

                    parsed = true
                    state.trackData = trackData
                    events << createEvent(name: "trackData", value: trackData)
                }
            }
        }

        if (!parsed) {
            log.debug "Unparsed body ${bodyString}"
        }
    }

    runIn(1.5, sendPollCmd)
    return events
}

private Integer convertHexToInt(hex) {
    Integer.parseInt(hex,16)
}

private String convertHexToIP(hex) {
    [convertHexToInt(hex[0..1]),convertHexToInt(hex[2..3]),convertHexToInt(hex[4..5]),convertHexToInt(hex[6..7])].join(".")
}

private String getHostAddress() {
    def ip = convertHexToIP(getDataValue("ip"))
    def port = convertHexToInt(getDataValue("port"))
    def host = "${ip}:${port}"

    //log.debug "Using SOAP host: ${host} for device: ${device.label}"

    return host
}

private String getApiAddress() {
    def ip = convertHexToIP(getDataValue("ip"))
    def host = "${ip}:${getDataValue("apiPort")}"

    //log.debug "Using API host: ${host} for device: ${device.label}"

    return host
}

private String getCallBackAddress() {
    def address = "${device.hub.getDataValue("localIP")}:${device.hub.getDataValue("localSrvPortTCP")}"

    //log.debug "callbackAddress ${address}"

    return address
}

private Map getSourceNames() {
    if (state.sourceName == null) {
        state.sourceName = [:]
    }

    state.sourceName
}

def installed() {
    log.debug "Executing installed() for ${device.label}"
    state.allSources = ["phono", "cd", "dvd", "bd", "tv", "satcbl", "mplay", "game", "tuner", "aux1", "aux2", "net", "bt"]
    initialize()
}

def updated() {
    log.debug "Executing updated() for ${device.label}"
    initialize()
}

def initialize() {
    log.debug "Executing initialize() for ${device.label}"
    refresh()
}

def sync(ip, port) {
    def existingIp = getDataValue("ip")
    def existingPort = getDataValue("port")
    if (ip && ip != existingIp) {
        updateDataValue("ip", ip)
    }
    if (port && port != existingPort) {
        updateDataValue("port", port)
    }
}

def sendPollCmd() {
    log.debug "Executing sendPollCmd() for ${device.label}"

    sendHubCommand(new physicalgraph.device.HubAction([
            path: "/goform/AppCommand0300.xml",
            method: "POST",
            headers: [Host: getApiAddress()],
            body:"""<?xml version="1.0" encoding="utf-8"?>
<tx>
  <cmd id="3">
    <name>GetSourceRename</name>
    <list/>
  </cmd>
</tx>
"""
        ],
        getDataValue("mac"),
        [callback: getSourceRenameCallback]
    ))

    sendHubCommand(new physicalgraph.device.HubAction([
            path: "/goform/AppCommand.xml",
            method: "POST",
            headers: [Host: getApiAddress()],
            body:"""<?xml version="1.0" encoding="utf-8"?>
<tx>
  <cmd id="1">GetAllZonePowerStatus</cmd>
  <cmd id="2">GetAllZoneVolume</cmd>
  <cmd id="3">GetAllZoneMuteStatus</cmd>
  <cmd id="4">GetAllZoneSource</cmd>
</tx>
"""
        ],
        getDataValue("mac"),
        [callback: pollCallback]
    ))
}

def refresh() {
    log.debug "Executing refresh() for ${device.label}"
    state.allSources = ["phono", "cd", "dvd", "bd", "tv", "satcbl", "mplay", "game", "tuner", "aux1", "aux2", "net", "bt"]

    unschedule(resubscribeRenderingControl)
    unschedule(resubscribeAVTransport)

    subscribe(["RenderingControl", "AVTransport"])

    sendPollCmd()
}

def poll() {
    log.debug "Executing poll() for ${device.label}"

    refresh()
}

def on() {
    log.debug "Executing on() for ${device.label}"

    new physicalgraph.device.HubAction([
            path: "/goform/formiPhoneAppDirect.xml?ZMON",
            method: "GET",
            headers: [Host: getApiAddress()],
        ]
    )
/*
    new physicalgraph.device.HubAction([
            path: "/goform/AppCommand.xml",
            method: "POST",
            headers: [Host: getApiAddress()],
            body:"""<?xml version="1.0" encoding="utf-8"?>
<tx>
  <cmd id="1">SetPower</cmd>
  <zone>Main</zone>
  <value>ON</value>
</tx>
"""
        ],
        getDataValue("mac"),
        [callback: setCmdCallback]
    )
*/
}

def off() {
    log.debug "Executing off() for ${device.label}"

    new physicalgraph.device.HubAction([
            path: "/goform/formiPhoneAppDirect.xml?PWSTANDBY",
            method: "GET",
            headers: [Host: getApiAddress()],
        ]
    )
/*
    new physicalgraph.device.HubAction([
            path: "/goform/AppCommand.xml",
            method: "POST",
            headers: [Host: getApiAddress()],
            body:"""<?xml version="1.0" encoding="utf-8"?>
<tx>
  <cmd id="1">SetPower</cmd>
  <zone>Main</zone>
  <value>STANDBY</value>
</tx>
"""
        ],
        getDataValue("mac"),
        [callback: setCmdCallback]
    )
*/
}

def setLevel(level) {
    log.debug "Executing setLevel() for ${device.label} level=${level}"

    if (level > 98) {
        level = 98
    }

    new physicalgraph.device.HubAction([
            path: "/goform/formiPhoneAppDirect.xml?MV" + String.format("%02d", level),
            method: "GET",
            headers: [Host: getApiAddress()],
        ]
    )
}

def mute() {
    log.debug "Executing mute() for ${device.label}"

    new physicalgraph.device.HubAction([
            path: "/goform/formiPhoneAppDirect.xml?MUON",
            method: "GET",
            headers: [Host: getApiAddress()],
        ]
    )
/*
    new physicalgraph.device.HubSoapAction(
        path:    getDataValue("RenderingControlControlPath"),
        urn:     "urn:schemas-upnp-org:service:RenderingControl:1",
        action:  "SetMute",
        body:    [InstanceID: 0, Channel: "Master", DesiredMute: 1],
        headers: [Host: getHostAddress()]
    )
*/
}

def unmute() {
    log.debug "Executing unmute() for ${device.label}"

    new physicalgraph.device.HubAction([
            path: "/goform/formiPhoneAppDirect.xml?MUOFF",
            method: "GET",
            headers: [Host: getApiAddress()],
        ]
    )
/*
    new physicalgraph.device.HubSoapAction(
        path:    getDataValue("RenderingControlControlPath"),
        urn:     "urn:schemas-upnp-org:service:RenderingControl:1",
        action:  "SetMute",
        body:    [InstanceID: 0, Channel: "Master", DesiredMute: 0],
        headers: [Host: getHostAddress()]
    )
*/
}

def play() {
    log.debug "Executing play() for ${device.label}"

    new physicalgraph.device.HubSoapAction(
        path:    getDataValue("AVTransportControlPath"),
        urn:     "urn:schemas-upnp-org:service:AVTransport:1",
        action:  "Play",
        body:    [InstanceID: 0, Speed: 1],
        headers: [Host: getHostAddress()]
    )
}

def pause() {
    log.debug "Executing pause() for ${device.label}"

    new physicalgraph.device.HubSoapAction(
        path:    getDataValue("AVTransportControlPath"),
        urn:     "urn:schemas-upnp-org:service:AVTransport:1",
        action:  "Pause",
        body:    [InstanceID: 0],
        headers: [Host: getHostAddress()]
    )
}

def stop() {
    log.debug "Executing stop() for ${device.label}"

    new physicalgraph.device.HubSoapAction(
        path:    getDataValue("AVTransportControlPath"),
        urn:     "urn:schemas-upnp-org:service:AVTransport:1",
        action:  "Stop",
        body:    [InstanceID: 0],
        headers: [Host: getHostAddress()]
    )
}

def nextTrack() {
    log.debug "Executing nextTrack() for ${device.label}"

    new physicalgraph.device.HubSoapAction(
        path:    getDataValue("AVTransportControlPath"),
        urn:     "urn:schemas-upnp-org:service:AVTransport:1",
        action:  "Next",
        body:    [InstanceID: 0],
        headers: [Host: getHostAddress()]
    )
}

def previousTrack() {
    log.debug "Executing previousTrack() for ${device.label}"

    new physicalgraph.device.HubSoapAction(
        path:    getDataValue("AVTransportControlPath"),
        urn:     "urn:schemas-upnp-org:service:AVTransport:1",
        action:  "Previous",
        body:    [InstanceID: 0],
        headers: [Host: getHostAddress()]
    )
}

def playTrack(trackToPlay) {
    log.debug "Executing previousTrack() for ${device.label} track=${trackToPlay}"
}

def restoreTrack(trackToRestore) {
    log.debug "Executing restoreTrack() for ${device.label} track=${trackToRestore}"
}

def resumeTrack(trackToResume) {
    log.debug "Executing resumeTrack() for ${device.label} track=${trackToResume}"
}

def setTrack(trackToSet) {
    log.debug "Executing setTrack() for ${device.label} track=${trackToSet}"
}

def phono() {
    log.debug "Executing phono() for ${device.label}"

    new physicalgraph.device.HubAction([
            path: "/goform/formiPhoneAppDirect.xml?SIPHONO",
            method: "GET",
            headers: [Host: getApiAddress()],
        ]
    )
}

def cd() {
    log.debug "Executing cd() for ${device.label}"

    new physicalgraph.device.HubAction([
            path: "/goform/formiPhoneAppDirect.xml?SICD",
            method: "GET",
            headers: [Host: getApiAddress()],
        ]
    )
}

def dvd() {
    log.debug "Executing dvd() for ${device.label}"

    new physicalgraph.device.HubAction([
            path: "/goform/formiPhoneAppDirect.xml?SIDVD",
            method: "GET",
            headers: [Host: getApiAddress()],
        ]
    )
}

def bd() {
    log.debug "Executing bd() for ${device.label}"

    new physicalgraph.device.HubAction([
            path: "/goform/formiPhoneAppDirect.xml?SIBD",
            method: "GET",
            headers: [Host: getApiAddress()],
        ]
    )
}

def tv() {
    log.debug "Executing tv() for ${device.label}"

    new physicalgraph.device.HubAction([
            path: "/goform/formiPhoneAppDirect.xml?SITV",
            method: "GET",
            headers: [Host: getApiAddress()],
        ]
    )
}

def satcbl() {
    log.debug "Executing satcbl() for ${device.label}"

    new physicalgraph.device.HubAction([
            path: "/goform/formiPhoneAppDirect.xml?SISAT%2FCBL",
            method: "GET",
            headers: [Host: getApiAddress()],
        ]
    )
}

def mplay() {
    log.debug "Executing mplay() for ${device.label}"

    new physicalgraph.device.HubAction([
            path: "/goform/formiPhoneAppDirect.xml?SIMPLAY",
            method: "GET",
            headers: [Host: getApiAddress()],
        ]
    )
}

def game() {
    log.debug "Executing game() for ${device.label}"

    new physicalgraph.device.HubAction([
            path: "/goform/formiPhoneAppDirect.xml?SIGAME",
            method: "GET",
            headers: [Host: getApiAddress()],
        ]
    )
}

def tuner() {
    log.debug "Executing tuner() for ${device.label}"

    new physicalgraph.device.HubAction([
            path: "/goform/formiPhoneAppDirect.xml?SITUNER",
            method: "GET",
            headers: [Host: getApiAddress()],
        ]
    )
}

def aux1() {
    log.debug "Executing aux1() for ${device.label}"

    new physicalgraph.device.HubAction([
            path: "/goform/formiPhoneAppDirect.xml?SIAUX1",
            method: "GET",
            headers: [Host: getApiAddress()],
        ]
    )
}

def aux2() {
    log.debug "Executing aux2() for ${device.label}"

    new physicalgraph.device.HubAction([
            path: "/goform/formiPhoneAppDirect.xml?SIAUX2",
            method: "GET",
            headers: [Host: getApiAddress()],
        ]
    )
}

def net() {
    log.debug "Executing net() for ${device.label}"

    new physicalgraph.device.HubAction([
            path: "/goform/formiPhoneAppDirect.xml?SINET",
            method: "GET",
            headers: [Host: getApiAddress()],
        ]
    )
}

def bt() {
    log.debug "Executing bt() for ${device.label}"

    new physicalgraph.device.HubAction([
            path: "/goform/formiPhoneAppDirect.xml?SIBT",
            method: "GET",
            headers: [Host: getApiAddress()],
        ]
    )
}

// subscription
def subscribe(List events) {
    def address = getCallBackAddress()
    log.debug "Executing subscribe() on ${address} for events ${events}"

    events.each {
        sendHubCommand(new physicalgraph.device.HubAction([
                path: getDataValue("${it}EventPath"),
                method: "SUBSCRIBE",
                headers: [
                    "Host": "${getHostAddress()}",
                    "CALLBACK": "<http://${address}/>",
                    "NT": "upnp:event",
                    "TIMEOUT": "Second-86400",
                    "Accept-Language": "zh-cn"
                ]
            ],
            getDataValue("mac"),
            [callback: "subscribeHandler${it}"]
        ))
    }
}

def resubscribe(event, sid) {
    def address = getCallBackAddress()
    log.debug "Executing resubscribe() on ${address} for event ${event} sid ${sid}"

    sendHubCommand(new physicalgraph.device.HubAction([
            path: getDataValue("${it}EventPath"),
            method: "SUBSCRIBE",
            headers: [
                "Host": "${getHostAddress()}",
                "SID": "uuid:${sid}",
                "TIMEOUT": "Second-86400"
            ]
        ],
        getDataValue("mac"),
        [callback: "subscribeHandler${event}"]
    ))
}

def getSidAndTimeout(physicalgraph.device.HubResponse hubResponse) {
    def body = hubResponse.xml
    def headers = hubResponse.headers

    log.debug "Executing getSidAndTimeout() header=${headers} body=${hubResponse.body}"

    def sid = headers["SID"]
    sid -= "uuid:".trim()

    def timeoutString = headers["Timeout"]
    timeoutString -= "Second-".trim()
    def timeout = Integer.parseInt(timeoutString, 10)

    log.info "sid=${sid} timeout=${timeout}"

    return [sid: sid, timeout: timeout]
}

// RenderingControl
def resubscribeRenderingControl() {
    def sid = state.renderingControlSid
    log.debug "Executing resubscribeRenderingControl() sid=${sid}"

	if (sid) {
		resubscribe("RenderingControl", sid)
    } else {
    	subscribe(["RenderingControl"])
    }
}

void subscribeHandlerRenderingControl(physicalgraph.device.HubResponse hubResponse) {
    def result = getSidAndTimeout(hubResponse)
    log.debug "Executing subscribeHandlerRenderingControl() sid=${result.sid} timeout=${result.timeout}"

    state.renderingControlSid = result.sid
    def timeout = result.timeout - 13 + (Math.random() * 6)

    log.info "Renew RenderingControl subscription in ${timeout} seconds"

    unschedule(resubscribeRenderingControl)
    runIn(timeout, resubscribeRenderingControl)
}

// AVTransport
def resubscribeAVTransport() {
    def sid = state.avTransportSid
    log.debug "Executing resubscribeAVTransport() sid=${sid}"

	if (sid) {
		resubscribe("AVTransport", sid)
    } else {
    	subscribe(["AVTransport"])
    }
}

void subscribeHandlerAVTransport(physicalgraph.device.HubResponse hubResponse) {
    def result = getSidAndTimeout(hubResponse)
    log.debug "Executing subscribeHandlerAVTransport() sid=${result.sid} timeout=${result.timeout}"

    state.avTransportSid = result.sid
    def timeout = result.timeout - 13 + (Math.random() * 6)

    log.info "Renew AVTransport subscription in ${timeout} seconds"

    unschedule(resubscribeAVTransport)
    runIn(timeout, resubscribeAVTransport)
}

// Callbacks
void setCmdCallback(physicalgraph.device.HubResponse hubResponse) {
    //log.debug "Executing setCmdCallback() body=${hubResponse.body}"
    log.debug "Executing setCmdCallback()"

    def body = hubResponse.xml
    def headers = hubResponse.headers

    if (body?.error?.text() || !body?.cmd?.size()) {
        log.error "Some set cmd failed! error=${body?.error?.text()}"
    } else {
        def cmds = body?.cmd
        def doPoll = false

        cmds.each { cmd ->
            if (cmd.text() == "OK") {
                doPoll = true
            } else {
                log.error "Some set cmd failed! response=${cmd.text()}"
            }
        }

        if (doPoll) {
            sendPollCmd()
        }
    }
}

void getSourceRenameCallback(physicalgraph.device.HubResponse hubResponse) {
    //log.debug "Executing getSourceRenameCallback() body=${hubResponse.body}"
    log.debug "Executing getSourceRenameCallback()"

    def headers = hubResponse.headers
    def params = hubResponse.xml?.cmd?.list?.param

    if (params.size()) {
        def sourceNames = getSourceNames()

        params.each {
            def source = it.@name?.text()
            def name = it.text()

            switch (source) {
                case "CBL/SAT":
                    sourceNames["satcbl"] = name
                    break
                case "DVD":
                    sourceNames["dvd"] = name
                    break
                case "Blu-ray":
                    sourceNames["bd"] = name
                    break
                case "GAME":
                    sourceNames["game"] = name
                    break
                case "Media Player":
                    sourceNames["mplay"] = name
                    break
                case "TV AUDIO":
                    sourceNames["tv"] = name
                    break
                case "AUX1":
                    sourceNames["aux1"] = name
                    break
                case "AUX2":
                    sourceNames["aux2"] = name
                    break
                case "CD":
                    sourceNames["cd"] = name
                    break
                case "PHONO":
                    sourceNames["phono"] = name
                    break
                case "TUNER":
                    sourceNames["tuner"] = name
                    break
                default:
                    log.error "Unknown source ${source} name ${name}!"
            }
        }

        //log.debug "sourceNames ${sourceNames}"
    }
}

void pollCallback(physicalgraph.device.HubResponse hubResponse) {
    //log.debug "Executing pollCallback() body=${hubResponse.body}"
    log.debug "Executing pollCallback()"

    def headers = hubResponse.headers
    def cmds = hubResponse.xml?.cmd

    if (cmds.size() != 4) {
        log.error "Malformed poll() response!"
    } else {
        // GetAllZonePowerStatus
        def powerStatus = cmds[0].zone1?.text()
        if (powerStatus == "ON" || powerStatus == "OFF") {
            log.trace "Got GetAllZonePowerStatus zone1=${powerStatus}"

            sendEvent(name: "switch", value: (powerStatus == "ON") ? "on" : "off")
            if (powerStatus != "ON") {
                sendEvent(name: "trackDescription", value: "")
            }
        } else {
            log.error "Malformed GetAllZonePowerStatus response!"
        }

        // GetAllZoneVolume
        def volume = cmds[1].zone1?.dispvalue?.text()
        if (volume) {
            log.trace "Got GetAllZoneVolume zone1=${volume}"

            sendEvent(name: "level", value: volume)
        } else {
            log.error "Malformed GetAllZoneVolume response!"
        }

        // GetAllZoneMuteStatus
        def mute = cmds[2].zone1?.text()
        if (mute) {
            log.trace "Got GetAllZoneMuteStatus zone1=${mute}"

            sendEvent(name: "mute", value: mute == "on" ? "muted" : "unmuted")
        } else {
            log.error "Malformed GetAllZoneMuteStatus response!"
        }

        // GetAllZoneSource
        def source = cmds[3].zone1?.source?.text()
        if (source) {
            source = source.toLowerCase().replaceAll("/", "")
            log.trace "Got GetAllZoneSource zone1=${source}"

            state.allSources.each {
                if (it == source) {
                    if (device.currentValue(it) != "on") {
                        sendEvent(name: it, value: "on")
                    }

                    def trackDesc = ""

                    if (powerStatus == "ON") {
                        if (it == "net") {
                            if (state.trackData != null) {
                                //log.debug "trackData ${state.trackData}"

                                if (state.trackData.name?.trim() && state.trackData.name != '""') {
                                    trackDesc += "${state.trackData.name}"
                                }

                                if (state.trackData.artist?.trim() && state.trackData.artist != '""') {
                                    trackDesc += "\n${state.trackData.artist}"
                                }

                                if (state.trackData.album?.trim() && state.trackData.album != '""') {
                                    trackDesc += "\n${state.trackData.album}"
                                }
                            }

                            //log.trace "trackDesc ${trackDesc}"
                        } else {
                            def sourceNames = getSourceNames()
                            def name = sourceNames[it]

                            if (name != null) {
                                trackDesc = name
                            }

                            //log.trace "trackDesc ${name}"
                        }

                        sendEvent(name: "trackDescription", value: trackDesc)
                    }
                } else if (device.currentValue(it) == "on") {
                    log.trace "Switching source ${it} off"
                    sendEvent(name: it, value: "off")
                }
            }
        } else {
            log.error "Malformed GetAllZoneSource response!"
        }
    }
}
