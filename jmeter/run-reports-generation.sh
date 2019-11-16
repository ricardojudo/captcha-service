OUT_DIR="out/$1"
RESULTS_FILE="${OUT_DIR}/results.jtl"

JMeterPluginsCMD.sh --generate-csv $OUT_DIR/Syntesis.csv --input-jtl ${RESULTS_FILE} --plugin-type SynthesisReport


#,"HitsPerSecond","ResponseTimesDistribution",
#         "ThroughputVsThreads","TimesVsThreads"

PLOTS = ["ResponseTimesOverTime"]

#for p in PLOTS 
#do
#JMeterPluginsCMD.sh --generate-png $OUT_DIR/${p}.png --input-jtl ${RESULTS_FILE} --plugin-type ${p} --width 800 --height 600
#done


JMeterPluginsCMD.sh --generate-png $OUT_DIR/ResponseTimesOverTime.png --input-jtl ${RESULTS_FILE} --plugin-type ResponseTimesOverTime --width 800 --height 600
JMeterPluginsCMD.sh --generate-png $OUT_DIR/HitsPerSecond.png --input-jtl ${RESULTS_FILE} --plugin-type HitsPerSecond --width 800 --height 600
JMeterPluginsCMD.sh --generate-png $OUT_DIR/ResponseTimesDistribution.png --input-jtl ${RESULTS_FILE} --plugin-type ResponseTimesDistribution --width 800 --height 600
JMeterPluginsCMD.sh --generate-png $OUT_DIR/ThroughputVsThreads.png --input-jtl ${RESULTS_FILE} --plugin-type ThroughputVsThreads --width 800 --height 600
JMeterPluginsCMD.sh --generate-png $OUT_DIR/TimesVsThreads.png --input-jtl ${RESULTS_FILE} --plugin-type TimesVsThreads --width 800 --height 600
#JMeterPluginsCMD.sh --generate-png $OUT_DIR/${p}.png --input-jtl ${RESULTS_FILE} --plugin-type ${p} --width 800 --height 600



tar czvf $OUT_DIR/results.tag.gz $OUT_DIR/*