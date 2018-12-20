docker run --name app-node2 -d -v ${PWD}/temp:/opt/shared -t node2
docker run --name app-node1 -d -v ${PWD}/temp:/opt/shared -t node1
docker run --name app-node0 -d -t node0