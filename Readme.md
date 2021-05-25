Sample Architecture app with DFM and library modules integrated with dagger
=============================

## Can be tested in two ways

First, Internal App sharing:

Need google developer account for this.
`https://play.google.com/console/internal-app-sharing/`

Second, Using Bundle tool. Steps are below.

- brew install bundletool (If bundletool is not installed yet)
- ./gradlew bundleDebug
- bundletool build-apks --overwrite --local-testing --bundle path/to/bundle.aab --output path/to/apkset.apks
- bundletool install-apks --apks path/to/apkset.apks

