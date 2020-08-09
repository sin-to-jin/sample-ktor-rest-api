# sample-ktor-rest-api

## Started
1. Build gradle
2. Run Kotlin `Application.kt`
3. Access to `http://127.0.0.1:5001/`

## API access
`curl http://127.0.0.1:5001/api/v1/users`

## Hot Reload
After editing the code and completing the compilation using gradle build, it is automatically hot reloaded. However, you need to insert the compiled file directory in `resources/application.conf` under `ktor.deployment.watch` in the Editor.
