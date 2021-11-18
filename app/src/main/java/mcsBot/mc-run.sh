
java -Xmx1024M -Xms1024M -jar /home/alien/Desktop/mc-server/server.jar gui &
mcpid=$!
sleep 3600
kill -KILL $mcpid