#!/usr/bin/env bash

SBT_OPTS="-Xms512M -Xmx1024M -Xss2M -XX:MaxMetaspaceSize=1024M" \
./sbt-dist/bin/sbt "$@"