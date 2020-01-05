# Funny Crash

A simple Crash detector for Android apps that allows you to receive crashes from different devices or permit user to send a bug or feedback with screenshot when he shake his device.

* API 21+


<img alt="Card sample" width="360" height="600" src="funnyCrash_record.gif" />


## Download

Add the JitPack repository to the build.gradle file:

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Add the Gradle dependency:

```groovy
    implementation 'com.github.minageorge5080:Funny-Crash:1.1.2'

```

## Usage

### Setup in your application class

```java
FunnyCrash.initialize(this)
FunnyCrash.showExcuseDialog = false // default is true.

```
### Setup the listener to receive crashes or reports in your mainActivity or mainViewModel

```java

 FunnyCrash.reportListener = object : FunnyCrashReportListener {
            override fun onReceiveReport(reportModel: ReportModel) {
                Toast.makeText(this@MainActivity, reportModel.info, Toast.LENGTH_SHORT).show()
                if (reportModel.reportType == FunnyCrashConstants.REPORT)
                    screenShot_imageView.setImageBitmap(BitmapFactory.decodeFile(reportModel.file?.absolutePath))
            }
        }
```

## License
    Copyright (C) 2020 Mina George

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.