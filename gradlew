#!/usr/bin/env sh
##############################################################################
#
#  Gradle start-up script for UNIX-based OSes.
#
##############################################################################

# set -e

# Determine directory of script
PRG="$0"
while [ -h "$PRG" ]; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done

SAVED="`pwd`"
cd "`dirname \"$PRG\"`/" >/dev/null
APP_HOME="`pwd -P`"
cd "$SAVED" >/dev/null

DEFAULT_JVM_OPTS=""

exec "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" "$@"
