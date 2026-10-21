package iq.secureiq;

import java.util.List;

public final class Content {

    public record Tile(String img, String alt, String label) {}

    public record Domain(String id, String kicker, String title, String accent,
                         String p1, String p2, String scope, List<Tile> tiles, int cols) {}

    public record Scope(String title, String img, String alt, String copy, String href, String link) {}

    public record Step(String num, String title, String copy) {}

    private Content() {}

    public static final List<Domain> DOMAINS = List.of(
        new Domain(
            "ict", "01 — ICT",
            "Connectivity that is ", "designed to last",
            "We develop end-to-end ICT solutions that ensure smooth digital communication while integrating future readiness, resilience, and innovation.",
            "Our approach is centred on improving the experience, functionality, and operational efficiency of contemporary facilities — from passive infrastructure and ISP design to the systems guests and staff touch every day.",
            "Passive &amp; active networking · FTTX / ISP · wireless · GSM / TETRA · IP telephony · IPTV · GRMS",
            List.of(
                new Tile("ict-isp.png", "Fibre and telecom infrastructure reaching a building", "ISP &amp; Telecom Infrastructure Networks (FTTX)"),
                new Tile("ict-passive.png", "Patch panels and structured cabling in a communications rack", "Passive &amp; Active Networking"),
                new Tile("ict-wireless.png", "Wireless signal icon", "Wireless Networking"),
                new Tile("ict-gsm.png", "Digital network infrastructure visualisation", "GSM / TETRA Network Infrastructure"),
                new Tile("ict-telephony.png", "IP desk telephone", "IP Telephony &amp; IPTV"),
                new Tile("ict-grms.png", "Hotel guest room with integrated room controls", "Guest Room Management System (GRMS)")
            ), 3),
        new Domain(
            "av", "02 — AV",
            "Spaces that ", "communicate",
            "We design intelligent audio-visual systems that enhance communications, collaboration, and engagement across spaces. Our solutions seamlessly integrate technology to deliver immersive and reliable user experiences.",
            "From a single meeting room to a ballroom, we coordinate acoustics, displays, control and content so every space behaves predictably on the day it matters.",
            "BGM · PA · video conferencing · ballroom projection · digital signage · room control",
            List.of(
                new Tile("av-bgm.png", "Ceiling speakers in a glass-walled lounge", "Background Music System (BGM)"),
                new Tile("av-vc.png", "Video conferencing room with a multi-participant display", "Video Conferencing System"),
                new Tile("av-projection.png", "Ballroom projection and stage setup", "Ballroom Projection System"),
                new Tile("av-signage.png", "Digital signage displays in a retail concourse", "Digital Signage System")
            ), 2),
        new Domain(
            "security", "03 — Security",
            "Layers, not ", "gadgets",
            "We develop and implement extensive security strategies using integrated, advanced systems designed to safeguard individuals and ensure multi-layered protection of people, property, and critical assets.",
            "Detection, delay and response are designed as one system — so video, access control, intrusion and alarms share a single operating picture instead of running in isolation.",
            "VSS · ACAMS · ISMS · HVM · intrusion detection · DTA · security intercom",
            List.of(
                new Tile("sec-vss.png", "Dome CCTV camera at night", "Video Surveillance System (VSS)"),
                new Tile("sec-access.png", "Fingerprint access control at a door", "Access Control &amp; Alarm Monitoring (ACAMS)"),
                new Tile("sec-intrusion.png", "Intrusion detector with a shadow on a wall", "Intrusion Detection System"),
                new Tile("sec-isms.png", "Security operations control room with video walls", "Integrated Security Management (ISMS)"),
                new Tile("sec-hvm.png", "Hostile vehicle mitigation bollards on a city street", "Hostile Vehicle Mitigation (HVM)"),
                new Tile("sec-dta.png", "Accessible washroom with a disabled toilet alarm pull cord", "Disabled Toilet Alarm Systems (DTA)")
            ), 3)
    );

    public static final List<Scope> SCOPE = List.of(
        new Scope("ICT Systems", "cat-ict.png", "Connected city representing ICT infrastructure",
            "Technology design including passive &amp; active networking (structured cabling and data networking), wireless networking, FTTX / ISP design, GSM infrastructure, TETRA / two-way radio, IP telephony, IPTV, guest room management (GRMS) and home automation.",
            "#ict", "View ICT systems"),
        new Scope("AV Systems", "cat-av.png", "Audio mixing console representing AV systems",
            "Audio-visual system design primarily including background music (BGM), public address (PA), digital signage, video conferencing, ballroom projection and venue control — specified for the acoustics and sightlines of each space.",
            "#av", "View AV systems"),
        new Scope("Security Systems", "cat-security.png", "City skyline protected by security icons",
            "Video surveillance (VSS), access control and alarm monitoring (ACAMS), integrated security management (ISMS), hostile vehicle mitigation (HVM), intrusion detection, disabled toilet alarms (DTA) and security intercom — layered around your risk profile.",
            "#security", "View security systems")
    );

    public static final List<Step> STEPS = List.of(
        new Step("01 / ASSESS", "Site survey",
            "Walk the facility, audit existing systems and map risk, coverage, cable routes and constraints before a single line is drawn."),
        new Step("02 / DESIGN", "Coordinated design",
            "ICT, AV and security drawings, schedules and bills of quantities — reviewed together to avoid clashes and rework."),
        new Step("03 / DEPLOY", "Build &amp; commission",
            "Structured cabling, device installation, integration and end-to-end testing against an agreed acceptance plan."),
        new Step("04 / OPERATE", "Monitor &amp; tune",
            "Dashboards, SLA support and analytics tuning — keeping false alarms down and the operating picture current.")
    );

    public static final List<String> STANDARDS = List.of(
        "ONVIF", "RTSP", "SIP", "TETRA", "PoE / PoE+", "IEEE 802.3af/at", "Wiegand", "OSDP",
        "BACnet", "Modbus TCP", "MQTT", "SNMP", "NTP", "VLAN / QoS", "IPv4 / IPv6", "TLS 1.3",
        "LDAP / AD", "REST / Webhooks"
    );

    public static final List<String> TICKER = List.of(
        "FTTX / FITH", "Structured cabling", "Wi-Fi 6E", "GSM / TETRA", "IP telephony", "IPTV", "GRMS",
        "Home automation", "BGM / PA", "Digital signage", "Video conferencing", "VSS / CCTV", "ACAMS",
        "ISMS", "HVM", "Intrusion detection", "DTA"
    );

    public static final List<String> ZONES = List.of(
        "Server Room", "Executive Floor", "Main Entrance", "Car Park B",
        "CCTV Room", "Lobby West", "Loading Bay", "Stairwell C"
    );
}
