# Running tests
Make sure that backend REST API is turned off when running these tests

# Running monkey tests
a) Make sure that emulator is running  
b) Open command prompt (you should start from home directory)  
c) Move to Android SDK folder  
`cd AppData\Local\Android\Sdk\platform-tools`  
d) Run below command:  
`adb -e shell monkey --ignore-crashes -p pl.edu.pjatk.pamo.skrawek 1000 -v > test_log.txt`  

As an alternative if you are using Git Bash - you may try to run `run_monkey_test.sh` script.