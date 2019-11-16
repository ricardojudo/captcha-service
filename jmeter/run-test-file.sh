 #!/bin/bash


TEST_FILE=jmx/$1.jmx
HOST=$2
PORT=$3 
USERS=$4 
PERIOD=$5 
LOOP=$6

HOSTNAME=`hostname`
TIMESTAMPT=`date +"%F_%T"`

OUT_DIR="out/$1/${HOSTNAME}/${TIMESTAMPT}"
RESULTS_FILE="${OUT_DIR}/results.jtl"

mkdir -p ${OUT_DIR}




echo ">>JMETER PARAMS:"

JMETER_PARAMS_OUT="File: $TEST_FILE Host: $HOST Port: $PORT Users: $USERS Period: $PERIOD Loop: $LOOP"

echo JMETER_PARAMS_OUT
echo ">>> Exec" >>  "${OUT_DIR}/README"
echo $JMETER_PARAMS_OUT >> "${OUT_DIR}/README"
echo ">>> uname -a" >> "${OUT_DIR}/README"
uname -a >> "${OUT_DIR}/README"
echo ">>> CPU" >> "${OUT_DIR}/README"
lscpu >> "${OUT_DIR}/README"
echo ">>> RAM (MB)" >> "${OUT_DIR}/README"
free -m -t >> "${OUT_DIR}/README"


echo ">>JMETER PARAMS:"
JMETER_PARAMS="-t ${TEST_FILE} -l ${RESULTS_FILE} -n -JHost=${HOST} -JPort=${PORT} -JUsers=${USERS} -JPeriod=${PERIOD} -JLoop=${LOOP}"
echo $JMETER_PARAMS

#jmeter.sh -n -t ${TEST_FILE} -l ${} -JHost=54.167.237.253 -JPort=7001 -JUsers=1000 
jmeter.sh $JMETER_PARAMS


