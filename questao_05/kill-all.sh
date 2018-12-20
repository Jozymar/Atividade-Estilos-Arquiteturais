docker stop node2
docker stop node1
docker stop node0
docker rm node2
docker rm node1
docker rm node0
docker rmi -f node2
docker rmi -f node1
docker rmi -f node0
