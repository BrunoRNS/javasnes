### Project Overview

javasnes — a rolling-release Java library for building SNES games. Continuously updated and evolving, this project aims to make SNES development in Java fast, modular, and CI-friendly. Works smoothly with SNES-IDE to compile the generated C code and ship playable ROMs.

### Current Release Model

**Rolling release** — no fixed versions for most changes; the library is updated frequently with improvements, fixes, and new features. Use the Maven/Gradle coordinates or the wrapper to pin a specific Maven/Gradle run if you need reproducible builds. Expect nightly-ish updates and incremental improvements.

### Quick Status

- **Stability:** APIs and functional core features.  
- **Update cadence:** High — small, frequent commits and improvements.  
- **Recommended for:** Developers who want the latest features and can adapt to API changes; contributors and experimental game projects.

### Prerequisites

- **Java 8 or newer**
- **SNES-IDE or PVSNESLIB** (installed and configured to compile the generated C output)

### Getting Started

1. Clone the repo:

   ```sh
   git clone https://github.com/BrunoRNS/javasnes.git
   ```

2. Build with Gralde Wrapper:
   - Unix/macOS:
        ```sh
        ./gradlew build
        ```
   - Windows:
        ```sh
        gradlew.bat
        ```
3. Move generated library and example outputs to your SNES-IDE project and follow SNES-IDE build steps to produce the final ROM.

### Usage Tips

- Use the latest snapshot for new features; pin a commit or artifact if you need stability for releases.
- Keep your SNES-IDE workflow isolated per project to avoid accidental global changes.
- Check the examples folder for sample game setups and minimal main-class templates.

### Contributing

Contributions are welcome. Preferred workflow:
1. Fork the repository.
2. Open a topic branch for your change.
3. Keep changes focused and add tests where appropriate.
4. Submit a pull request with a clear summary of the change and any migration notes.

Please follow the code style and add a short entry to the changelog for non-trivial changes.

### Release & Versioning

This project uses continuous delivery of improvements. Releases are published as rolling snapshots; tags will be created for major milestones only. If you need semantic versioning for distribution, pin a tagged release or use your CI to snapshot a reproducible artifact.

### Communication

Open issues for bugs, feature requests, and design discussions. Use PR comments for implementation details. Maintainers will triage frequently but prioritize breaking-change coordination and CI green builds.

### License

Released under **GPL v3**. See COPYING for full terms.
