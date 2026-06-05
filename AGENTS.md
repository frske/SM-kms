# AGENTS.md

## Build And Test
- Run Maven from the repo root; there is no wrapper.
- Full build/test: `mvn test`.
- Focus one module and its deps: `mvn -pl <module> -am test`.
- Run one test class: `mvn -pl <module> -Dtest=<ClassName> test`.
- Package a module for local verification: `mvn -pl <module> -am package`.

## Module Map
- `jhotdraw-api`, `jhotdraw-utils`, `jhotdraw-xml`, `jhotdraw-datatransfer`, and `jhotdraw-actions` are library modules.
- `jhotdraw-core` depends on `api`, `utils`, `xml`, `datatransfer`, and `actions`.
- `jhotdraw-app` depends on `api`, `actions`, and `gui`.
- `jhotdraw-samples` contains the runnable demos; `jhotdraw-samples-misc` includes the `exec-maven-plugin` and several `main` entrypoints.

## Test Quirks
- Test frameworks are mixed: `jhotdraw-core` and `jhotdraw-utils` use TestNG, while `jhotdraw-samples-misc` has JUnit 4 test scope.
- Tests in this repo often end with `NGTest`; do not assume JUnit-only conventions.

## Repo Conventions
- Java source/target is 1.8 and encoding is UTF-8.
- Keep edits inside the owning Maven module; module dependencies are enforced by the POMs.
- Ignore build output and generated artifacts such as `target/`, `*.class`, and `*.patch`.
