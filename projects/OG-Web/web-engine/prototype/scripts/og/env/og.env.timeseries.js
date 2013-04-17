/*
 * Copyright 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * Please see distribution for license.
 */
$.register_module({
    name: 'og.env.timeseries',
    dependencies: [],
    obj: function () {
        return function (selector) {
            new og.common.gadgets.TimeseriesPlot({
                selector: selector,
                datapoints_link: false,
                editable: false,
                external_links: true,
                menu: false,
                data: [{
                    "template_data": {
                        "data_field": "Historical Time Series",
                        "observation_time": "Historical Time Series"
                    },
                    "timeseries": {
                        "fieldLabels": ["Time","Value"],
                        "data": [[1301270400000,20.34],[1301356800000,20.28],[1301443200000,20.46],[1301529600000,20.18],[1301616000000,19.72],[1301875200000,19.49],[1301961600000,19.71],[1302048000000,19.95],[1302134400000,20.03],[1302220800000,20.02],[1302480000000,20.12],[1302566400000,19.76],[1302652800000,19.78],[1302739200000,19.58],[1302825600000,19.75],[1303084800000,19.62],[1303171200000,19.86],[1303257600000,21.41],[1303344000000,21.46],[1303430400000,21.46],[1303689600000,21.94],[1303776000000,22.48],[1303862400000,22.63],[1303948800000,22.8],[1304035200000,23.15],[1304294400000,22.91],[1304380800000,23.23],[1304467200000,23.5],[1304553600000,23.61],[1304640000000,23.25],[1304899200000,22.76],[1304985600000,23.03],[1305072000000,23.41],[1305158400000,23.705],[1305244800000,23.41],[1305504000000,23.64],[1305590400000,23.55],[1305676800000,23.88],[1305763200000,23.54],[1305849600000,23.22],[1306108800000,22.86],[1306195200000,22.61],[1306281600000,22.68],[1306368000000,22.5],[1306454400000,22.21],[1306713600000,22.21],[1306800000000,22.51],[1306886400000,22],[1306972800000,22.085],[1307059200000,21.73],[1307318400000,21.829],[1307404800000,22.06],[1307491200000,21.82],[1307577600000,21.76],[1307664000000,21.38],[1307923200000,21.385],[1308009600000,21.82],[1308096000000,21.42],[1308182400000,21.42],[1308268800000,21.19],[1308528000000,21.325],[1308614400000,21.65],[1308700800000,21.395],[1308787200000,21.71],[1308873600000,21.2],[1309132800000,21.34],[1309219200000,21.49],[1309305600000,21.39],[1309392000000,22.16],[1309478400000,22.53],[1309737600000,22.53],[1309824000000,22.44],[1309910400000,22.75],[1309996800000,23.23],[1310083200000,23.09],[1310342400000,22.85],[1310428800000,22.45],[1310515200000,22.48],[1310601600000,22.27],[1310688000000,22.37],[1310947200000,22.28],[1311033600000,23.06],[1311120000000,22.99],[1311206400000,22.81],[1311292800000,23.13],[1311552000000,23.03],[1311638400000,22.895],[1311724800000,22.53],[1311811200000,22.55],[1311897600000,22.33],[1312156800000,22.24],[1312243200000,21.72],[1312329600000,21.81],[1312416000000,20.85],[1312502400000,20.79],[1312761600000,20.11],[1312848000000,20.6],[1312934400000,19.93],[1313020800000,20.76],[1313107200000,20.65],[1313366400000,20.89],[1313452800000,20.79],[1313539200000,20.67],[1313625600000,19.77],[1313712000000,19.19],[1313971200000,19.38],[1314057600000,19.71],[1314144000000,19.8],[1314230400000,19.42],[1314316800000,19.77],[1314576000000,20.295],[1314662400000,20.24],[1314748800000,20.13],[1314835200000,19.99],[1314921600000,19.64],[1315180800000,19.64],[1315267200000,19.54],[1315353600000,20.08],[1315440000000,19.895],[1315526400000,19.7],[1315785600000,20.28],[1315872000000,20.76],[1315958400000,21.115],[1316044800000,21.54],[1316131200000,21.97],[1316390400000,21.93],[1316476800000,22.2],[1316563200000,21.94],[1316649600000,21.615],[1316736000000,22.16],[1316995200000,22.24],[1317081600000,22.54],[1317168000000,22.31],[1317254400000,22.21],[1317340800000,21.335],[1317600000000,20.62],[1317686400000,21.22],[1317772800000,21.85],[1317859200000,22.03],[1317945600000,22.29],[1318204800000,22.88],[1318291200000,22.99],[1318377600000,23.12],[1318464000000,23.39],[1318550400000,23.5],[1318809600000,23.28],[1318896000000,23.4],[1318982400000,24.24],[1319068800000,23.61],[1319155200000,24.03],[1319414400000,24.59],[1319500800000,24.63],[1319587200000,24.7],[1319673600000,25.13],[1319760000000,24.98],[1320019200000,24.3239],[1320105600000,23.6896],[1320192000000,23.64],[1320278400000,24.2],[1320364800000,23.74],[1320624000000,24.28],[1320710400000,24.75],[1320796800000,23.84],[1320883200000,24.06],[1320969600000,24.85],[1321228800000,24.63],[1321315200000,25.34],[1321401600000,24.94],[1321488000000,24.34],[1321574400000,24.29],[1321833600000,23.57],[1321920000000,23.24],[1322006400000,22.7],[1322092800000,22.7],[1322179200000,22.73],[1322438400000,23.46],[1322524800000,23.58],[1322611200000,24.91],[1322697600000,24.92],[1322784000000,24.64],[1323043200000,25.01],[1323129600000,25.35],[1323216000000,25.66],[1323302400000,24.71],[1323388800000,25.01],[1323648000000,24],[1323734400000,23.56],[1323820800000,23.31],[1323907200000,23.31],[1323993600000,23.23],[1324252800000,23.095],[1324339200000,23.84],[1324425600000,23.68],[1324512000000,24.02],[1324598400000,24.4],[1324857600000,24.4],[1324944000000,24.56],[1325030400000,24.225],[1325116800000,24.55],[1325203200000,24.25],[1325462400000,24.25],[1325548800000,24.54],[1325635200000,25.11],[1325721600000,25.4],[1325808000000,25.25],[1326067200000,25.465],[1326153600000,25.59],[1326240000000,25.8],[1326326400000,25.75],[1326412800000,25.14],[1326672000000,25.14],[1326758400000,25.04],[1326844800000,25.39],[1326931200000,25.63],[1327017600000,26.38],[1327276800000,26.71],[1327363200000,26.895],[1327449600000,26.9],[1327536000000,26.75],[1327622400000,26.73],[1327881600000,26.74],[1327968000000,26.42],[1328054400000,26.55],[1328140800000,26.49],[1328227200000,26.74],[1328486400000,26.72],[1328572800000,26.64],[1328659200000,26.85],[1328745600000,26.86],[1328832000000,26.695],[1329091200000,26.7],[1329177600000,26.78],[1329264000000,26.58],[1329350400000,26.825],[1329436800000,27.37],[1329696000000,27.37],[1329782400000,27.16],[1329868800000,26.73],[1329955200000,26.66],[1330041600000,26.7],[1330300800000,26.89],[1330387200000,27.24],[1330473600000,26.88],[1330560000000,26.86],[1330646400000,26.915],[1330905600000,26.54],[1330992000000,26.605],[1331078400000,26.91],[1331164800000,26.84],[1331251200000,27.07],[1331510400000,26.985],[1331596800000,27.49],[1331683200000,27.46],[1331769600000,27.75],[1331856000000,27.73],[1332115200000,27.74],[1332201600000,27.75],[1332288000000,27.775],[1332374400000,27.895],[1332460800000,27.875],[1332720000000,28.19],[1332806400000,28.19],[1332892800000,27.8],[1332979200000,28.16],[1333065600000,28.115],[1333324800000,28.38],[1333411200000,28.11],[1333497600000,27.93],[1333584000000,28.07],[1333670400000,28.07],[1333929600000,27.76],[1334016000000,27.45],[1334102400000,27.85],[1334188800000,28.48],[1334275200000,28.09],[1334534400000,28.405],[1334620800000,28.47],[1334707200000,27.95],[1334793600000,27.69],[1334880000000,27.6],[1335139200000,27.45],[1335225600000,27.31],[1335312000000,27.86],[1335398400000,28.22],[1335484800000,28.38],[1335744000000,28.395],[1335830400000,28.95],[1335916800000,29.18],[1336003200000,28.56],[1336089600000,27.9],[1336348800000,27.76],[1336435200000,27.37],[1336521600000,27.19],[1336608000000,27.24],[1336694400000,27.63],[1336953600000,27.015],[1337040000000,26.88],[1337126400000,26.495],[1337212800000,26.19],[1337299200000,26.07],[1337558400000,26.15],[1337644800000,26.03],[1337731200000,25.44],[1337817600000,25.65],[1337904000000,25.74],[1338163200000,25.74],[1338249600000,26.09],[1338336000000,26.13],[1338422400000,25.84],[1338508800000,25.14],[1338768000000,25.04],[1338854400000,25.43],[1338940800000,26.07],[1339027200000,25.94],[1339113600000,26.41],[1339372800000,25.985],[1339459200000,26.52],[1339545600000,26.54],[1339632000000,26.98],[1339718400000,27.34],[1339977600000,27.42],[1340064000000,27.51],[1340150400000,27.64],[1340236800000,26.71],[1340323200000,26.935],[1340582400000,26.05],[1340668800000,26.005],[1340755200000,26.22],[1340841600000,25.83],[1340928000000,26.65],[1341187200000,26.665],[1341273600000,26.86],[1341360000000,26.86],[1341446400000,26.55],[1341532800000,26.155],[1341792000000,26.17],[1341878400000,25.56],[1341964800000,25.39],[1342051200000,24.74],[1342137600000,25.25],[1342396800000,25.13],[1342483200000,25.38],[1342569600000,26.21],[1342656000000,26.06],[1342742400000,25.52],[1343001600000,25.26],[1343088000000,25.005],[1343174400000,25.13],[1343260800000,25.5],[1343347200000,26.02],[1343606400000,25.76],[1343692800000,25.7],[1343779200000,25.93],[1343865600000,25.91],[1343952000000,26.23],[1344211200000,26.31],[1344297600000,26.5],[1344384000000,26.6],[1344470400000,26.7],[1344556800000,26.88],[1344816000000,26.69],[1344902400000,26.48],[1344988800000,26.27],[1345075200000,26.59],[1345161600000,26.33],[1345420800000,26.23],[1345507200000,26.11],[1345593600000,25.73],[1345680000000,25.04],[1345766400000,24.91],[1346025600000,24.84],[1346112000000,25],[1346198400000,24.668],[1346284800000,24.27],[1346371200000,24.83],[1346630400000,24.83],[1346716800000,24.415],[1346803200000,24.39],[1346889600000,25.095],[1346976000000,24.19],[1347235200000,23.26],[1347321600000,23.34],[1347408000000,23.19],[1347494400000,23.355],[1347580800000,23.37],[1347840000000,23.31],[1347926400000,23.37],[1348012800000,23.15],[1348099200000,23.1788],[1348185600000,23.125],[1348444800000,22.795],[1348531200000,22.535],[1348617600000,22.645],[1348704000000,23.085],[1348790400000,22.655],[1349049600000,22.755],[1349136000000,22.84],[1349222400000,22.55],[1349308800000,22.465],[1349395200000,22.68],[1349654400000,22.51],[1349740800000,21.9],[1349827200000,21.76],[1349913600000,21.68],[1350000000000,21.4803],[1350259200000,21.73],[1350345600000,22.35],[1350432000000,21.79],[1350518400000,21.67],[1350604800000,21.265],[1350864000000,21.46],[1350950400000,21.59],[1351036800000,21.46],[1351123200000,21.69],[1351209600000,21.95],[1351468800000,21.95],[1351555200000,21.95],[1351641600000,21.63],[1351728000000,22.26],[1351814400000,22.06],[1352073600000,21.8391],[1352160000000,21.73],[1352246400000,20.91],[1352332800000,20.83],[1352419200000,20.8],[1352678400000,20.765],[1352764800000,20.28],[1352851200000,19.96],[1352937600000,20.03],[1353024000000,20.19],[1353283200000,20.25],[1353369600000,19.5132],[1353456000000,19.36],[1353542400000,19.36],[1353628800000,19.72],[1353888000000,19.885],[1353974400000,19.93],[1354060800000,20.09],[1354147200000,19.53],[1354233600000,19.565],[1354492800000,19.54],[1354579200000,19.9694],[1354665600000,19.85],[1354752000000,20.16],[1354838400000,20.155],[1355097600000,20.08],[1355184000000,20.65],[1355270400000,20.67],[1355356800000,20.49],[1355443200000,20.526699999999998],[1355702400000,20.57],[1355788800000,20.96],[1355875200000,21.1],[1355961600000,21.0293],[1356048000000,20.765],[1356307200000,20.64],[1356393600000,20.64],[1356480000000,20.65],[1356566400000,20.51],[1356652800000,20.23],[1356912000000,20.62],[1356998400000,20.62],[1357084800000,21.38],[1357171200000,21.32],[1357257600000,21.16],[1357516800000,21.25],[1357603200000,21.09],[1357689600000,21.45],[1357776000000,21.8],[1357862400000,22],[1358121600000,21.999],[1358208000000,21.88],[1358294400000,22.11],[1358380800000,22.68],[1358467200000,21.25],[1358726400000,21.25],[1358812800000,21.17],[1358899200000,21.11],[1358985600000,20.95],[1359072000000,20.96],[1359331200000,21.05],[1359417600000,21.28],[1359504000000,21.37],[1359590400000,21.04],[1359676800000,21.355],[1359936000000,21.16],[1360022400000,21.1784],[1360108800000,20.99],[1360195200000,20.81],[1360281600000,21],[1360540800000,21.03],[1360627200000,21.19],[1360713600000,21.25],[1360800000000,21.23],[1360886400000,21.115],[1361145600000,21.115],[1361232000000,21.085],[1361318400000,20.73],[1361404800000,20.25],[1361491200000,20.42],[1361750400000,20.23],[1361836800000,20.58],[1361923200000,20.93],[1362009600000,20.88],[1362096000000,21.03],[1362355200000,21.27],[1362441600000,21.51],[1362528000000,21.75],[1362614400000,21.89],[1362700800000,21.58],[1362960000000,21.58],[1363046400000,21.58],[1363132800000,21.58],[1363219200000,21.58],[1363305600000,21.58],[1363564800000,21.58],[1363651200000,21.58],[1363737600000,21.58],[1363824000000,21.58],[1363910400000,21.58],[1364169600000,21.58],[1364256000000,21.58],[1364342400000,21.58],[1364428800000,21.58]]
                    }
                }]
            });
        };
    }
});