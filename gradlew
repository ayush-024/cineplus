#!/usr/bin/env sh

# Set environment
APP_HOME="$(cd "$(dirname "$0")" && pwd)"

# JVM options (optional)
DEFAULT_JVM_OPTS=""

# Run Gradle Wrapper
exec java $DEFAULT_JVM_OPTS -jar "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" "$@"
