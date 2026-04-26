FROM eclipse-temurin:25-jre

ENV TZ 'Europe/Kyiv'
ENV JAR_NAME="bu-journal.jar"
ENV APP_HOME="/app"

WORKDIR "$APP_HOME"

COPY "${JAR_NAME}" "${APP_HOME}"/"${JAR_NAME}"

CMD java -jar -Xms512M -Xmx512M -XX:-OmitStackTraceInFastThrow "${JAR_NAME}"

EXPOSE 80
