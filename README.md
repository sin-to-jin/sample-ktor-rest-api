# sample-ktor-rest-api

## Version
- Kotlin 1.3.7
- Ktor 1.3.2
- Koin 2.1.6
- Exposed 0.25.1

## Started
1. Build gradle
2. Run Kotlin `Application.kt`
3. Access to `http://127.0.0.1:5001/`

## API access
`curl http://127.0.0.1:5001/api/v1/users`

## Hot Reload
After editing the code and completing the compilation using gradle build, it is automatically hot reloaded. However, you need to insert the compiled file directory in `resources/application.conf` under `ktor.deployment.watch` in the Editor.

## PR
If there's something wrong with the way it's written, please PR it. I mean to know the code that should be there, but I hope it's useful for others to see.
（書き方がおかしいとかあれば、是非PRお願いします。あるべきコードを知りたいという意味もありますが、他の方が見た時に有益だと良いな、と思います。）
