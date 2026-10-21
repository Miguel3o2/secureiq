# SecureIQ

A framework-free marketing site and security-operations dashboard prototype for an integrated **ICT, AV and physical-security** capability platform.

The site presents 16 system families across three domains — ICT, audio-visual and security — and pairs them with a live-looking operations console that consolidates access-control, intrusion, video and alarm events into a single 8-zone view.

## Pages

| Page | Path | Purpose |
| --- | --- | --- |
| Capability platform | `src/index.html` | ICT / AV / security domains, scope, delivery model, standards and contact |
| Operations dashboard | `src/platform.html` | Zone risk scores, alert feed, timeline, event volume, heatmap and KPI tiles |

## Run locally

No build step, no dependencies. From the repository root:

```bash
python -m http.server 8080 --directory src
```

Then open <http://localhost:8080>. Any static file server works.

## Structure

```
src/
  index.html          # capability platform
  platform.html       # operations dashboard
  assets/
    css/style.css
    js/main.js        # nav, reveal animations, dashboard simulation
    fonts/            # self-hosted Barlow + JetBrains Mono
    img/              # cropped capability tiles and dashboard capture

site-java/
  src/iq/secureiq/    # early Java 17 server-rendered port (work in progress)
  assets/             # staged styles and images for the port
```

## Stack

- Semantic HTML, hand-written CSS, vanilla JavaScript
- Self-hosted variable-free WOFF2 fonts (Barlow, Barlow Semi Condensed, JetBrains Mono)
- Responsive from 360 px upward; mobile navigation uses native `<details>`
- No framework, no bundler, no runtime dependencies

## Status and limitations

- **Dashboard data is simulated in the browser.** It is a UI and operations-workflow prototype, not a live feed and not connected to real cameras, controllers or access panels.
- The **Java 17 server-rendered port is in progress**. Only the content model and event record exist so far; there is no HTTP server or build script yet.
- The dashboard's anomaly figures are demonstration values. They are not a validated model result.

## Imagery

The capability tile imagery is cropped from reference deck material supplied for this project. Rights remain with the original owner. Replace or clear permission before any commercial or public-facing use.

## Author

Built by **Umar Ahmad** — ML engineer working across ICT systems, network telemetry, security analytics and MLOps.

- GitHub: [github.com/Miguel3o2](https://github.com/Miguel3o2)
- LinkedIn: [linkedin.com/in/umar-ahmad-263b77209](https://linkedin.com/in/umar-ahmad-263b77209)
- Portfolio: [miguel3o2.github.io](https://miguel3o2.github.io)
