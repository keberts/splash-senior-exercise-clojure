# Payment Summary Exercise (Clojure)

A small Clojure project with tests already configured. See `PROMPT.md` for the
task. The exercise has a second part that your interviewer will give you partway
through the session.

## Requirements
- JDK 17 or newer on your PATH (`java -version` to check).
- The Clojure CLI (`clojure -version` to check). Install:
  https://clojure.org/guides/install_clojure
- Git, and internet access on first run. The first `clojure -X:test` downloads
  Clojure and the test runner, then caches them.

## Running the tests
```
clojure -X:test
```

## Starting state
On a fresh checkout `clojure -X:test` runs two tests:
- `empty-input-produces-empty-result` PASSES against the stub.
- `worked-example` FAILS until you implement `summarize`. This is expected; it
  shows you the happy-path target and confirms the harness works.

## Layout
```
deps.edn                        :test alias runs the suite
src/payments/summary.clj        <-- implement summarize here
test/payments/summary_test.clj  add your own tests here
```

A REPL, if you want one: `clojure -M:test -r`, or connect however you normally
do. Working in the REPL is entirely up to you.
