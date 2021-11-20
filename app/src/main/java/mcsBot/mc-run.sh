
java -Xmx2048M -Xms2048M -jar /home/alien/Desktop/mc-server/server.jar gui &
mcpid=$!
sleep 3600
kill -KILL $mcpid
