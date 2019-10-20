# Android group chat using CometChat and [ChatKit](https://github.com/stfalcon-studio/ChatKit)

This demo app is showing how to create a chat app in <1 hour using CometChat pro SDK and ChatKit for the UI

![The screenshot](https://github.com/cometchat-pro-tutorials/quick-group-chat-java/blob/master/screenshots/Screenshot_1571563081.png "Screenshot of the app in action")

## Technology
- CometChat pro 1.8.8
- Java

## Running the demo locally:
1. Install the latest Android studio
2. Clone the project
3. Open the project in Android studio

    3.1. Possibly install missing libraries
4. Launch the project on the emulator or on the actual device
5. [Head over to CometChat Pro and create an account](https://www.cometchat.com/pro?utm_source=github&utm_medium=example-code-readme)
6. From the [dashboard](https://app.cometchat.com/?utm_source=github&utm_medium=example-code-readme), create a new app called "Quick Group Chat"
7. Once created, click Explore
8. Go to the API Keys tab and click Create API Key
9. Create an API key called "Group Chat Key" with Full Access
10. Update `API_KEY` and `APP_ID` in `Constants` file with the new values

## Testing the functionality of the app:
- Install on 1 emulator (or physical device) and log in as "SUPERHERO1" (which is the test user for CometChat added by default)
- Install the app again on the second device and log in as some other user ("SUPERHERO2","SUPERHERO3","SUPERHERO4" or "SUPERHERO5")
- Upon logging in, you'll see a list of groups, there will be one called "Comic Heros' Hangout"
- Tap on the group to enter
- Start chatting

## Useful links
- [Android quick start docs](https://prodocs.cometchat.com/docs/android-quick-start)
- [Android messaging docs](https://prodocs.cometchat.com/docs/android-messaging)