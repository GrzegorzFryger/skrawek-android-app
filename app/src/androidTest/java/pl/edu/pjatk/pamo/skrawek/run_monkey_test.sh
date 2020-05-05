#!/bin/bash
LG='\033[1;32m' # LIGHT GREEN
LY='\033[1;33m' # LIGHT YELLOW
NC='\033[0m'    # NO COLOR
ACTIONS=1000
CURRENT_DIR=$PWD
OUTPUT_DIR=${CURRENT_DIR}/test_log.txt
PLATFORM_TOOLS_DIR="AppData/Local/Android/Sdk/platform-tools"

printf "Starting script execution from directory:\n${LY}${CURRENT_DIR}${NC}\n\n"
cd ~/${PLATFORM_TOOLS_DIR}
printf "Moved to directory:\n${LY}${PWD}${NC}\n\n"
printf "Running monkey tests. Logs will be provided to:\n${LY}${OUTPUT_DIR}${NC}\n\n"
./adb -e shell monkey --ignore-crashes -p pl.edu.pjatk.pamo.skrawek -v ${ACTIONS} >> ${OUTPUT_DIR}
