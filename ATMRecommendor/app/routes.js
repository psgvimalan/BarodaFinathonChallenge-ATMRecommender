var dateFormat = require('dateformat');
//Consume REST APIs
var Client = require('node-rest-client').Client;
// direct way 
var client = new Client();

/*
var p1 = Promise.resolve(3);
var p2 = 1337;
var p3 = new Promise((resolve, reject) => {
setTimeout(resolve, 100, 'foo');
}); 

Promise.all([p1, p2, p3]).then(values => { 
console.log(values); // [3, 1337, "foo"] 
});
*/

//ATMRecommendor();
//function ATMRecommendor()
function ATMRecommendor(req, res)
{

var lan=req.query.latitude;
var lat=req.query.longitude;


var dataMatrix =[];
var datarow =[];

var dataDistanceMatrix =[];
var dataDistanceRow =[];

var dataDurationMatrix =[];
var dataDurationRow =[];

var dataATMUpMatrix =[];
var dataATMUpRow =[];

var dataATMCashMatrix =[];
var dataATMCashRow =[];

var dataATMTrafficQueueMatrix =[];
var dataATMTrafficQueueRow =[];

var dataATMDTMatrix =[];
var dataATMDTMatrixRow =[];
//Micro services
var atmsFromBank="http://10.50.6.173:8090/BankApiCall/rest/atmRecommender/atmsFromBank"
var distanceAndDuration="http://10.50.6.173:8090/GoogleApi/rest/atmRecommender/distanceAndDurationWithTrafic"
var atmUpDownStatus="http://10.50.6.173:8090/AtmUpDown/rest/atmRecommender/atmUpDownStatus"
var atmCashNoCashStatus="http://10.50.6.173:8090/CashNoCash/rest/atmRecommender/atmCashNoCashStatus"
var waitTimePrediction="http://10.50.6.173:8090/WaitTime/rest/rService/waitTimePrediction"
var recommender="http://10.50.6.173:8090/Recommender/rest/rService/recommender"
//Micro service -1
var currentLocationArgs = {
//path: { "id": 120 },
parameters: { coordinators: "19.066,72.8777" },
//parameters: { coordinators: lan+','+lat },
//headers: { "apikey": "384M9075I79182CG" },
headers:{"Content-Type": "application/json"}
};

console.log('start sync');
client.post(atmsFromBank, currentLocationArgs, function (data, response) {
console.log('start async');
if(Buffer.isBuffer(data)){
		  data = data.toString('utf8');
}
//console.log(data);
var myJsonString = JSON.parse(data);

for(var c=0;c<myJsonString.length;c++)
{
datarow =[];
datarow.push(myJsonString[c].id);
		  datarow.push(myJsonString[c].Name);
		  datarow.push(myJsonString[c].address);
dataMatrix.push(datarow);
}
console.log('---List ATM near by 5KM redius----');
//console.log(dataMatrix);
console.log('---End of List ATM near by 5KM redius----');

//Micro service -2
var locationDistanceCords = {
//path: { "id": 120 },
parameters: { source: '19.066,72.8777', 
										destination: myJsonString[0].latitude+','+myJsonString[0].longitude+'|'+myJsonString[1].latitude+','+myJsonString[1].longitude+'|'+myJsonString[2].latitude+','+myJsonString[2].longitude+'|'+myJsonString[3].latitude+','+myJsonString[3].longitude+'|'+myJsonString[4].latitude+','+myJsonString[4].longitude+'|'+myJsonString[5].latitude+','+myJsonString[5].longitude+'|'+myJsonString[6].latitude+','+myJsonString[6].longitude+'|'+myJsonString[7].latitude+','+myJsonString[7].longitude+'|'+myJsonString[8].latitude+','+myJsonString[8].longitude+'|'+myJsonString[9].latitude+','+myJsonString[9].longitude},
//data: "<xml><arg1>hello</arg1><arg2>world</arg2></xml>"
};

client.post(distanceAndDuration, locationDistanceCords, function (data, response) {
		  if(Buffer.isBuffer(data)){
						 data = data.toString('utf8');
		  }
		  var ATMDist = JSON.parse(data);
		  var vdist = ATMDist.rows;
		  vdist= JSON.stringify(vdist);
		  var tempData = vdist;                     
		  console.log("tempData::"+tempData);
		  tempData = JSON.stringify(tempData);
		  for (var key in tempData) {
						 //console.log(JSON.stringify(tempData[key].elements));
						 //console.log(JSON.stringify(tempData[key].elements[0].duration.text));
		  }
		  
		  var vLocationDistance = [{"elements":
			   [{"distance":{"text":"57.5 km","value":57485},"duration":{"text":"2 hours 47 mins","value":10025},"status":"OK"},{"distance":{"text":"66.6 km","value":66610},"duration":{"text":"3 hours 18 mins","value":11869},"status":"OK"},{"distance":{"text":"36.6 km","value":36647},"duration":{"text":"2 hours 29 mins","value":8943},"status":"OK"},{"distance":{"text":"38.8 km","value":38837},"duration":{"text":"2 hours 37 mins","value":9398},"status":"OK"},{"distance":{"text":"70.6 km","value":70611},"duration":{"text":"3 hours 27 mins","value":12449},"status":"OK"},{"distance":{"text":"38.0 km","value":38011},"duration":{"text":"2 hours 30 mins","value":8989},"status":"OK"},{"distance":{"text":"55.7 km","value":55717},"duration":{"text":"2 hours 47 mins","value":9991},"status":"OK"},{"distance":{"text":"59.5 km","value":59492},"duration":{"text":"3 hours 0 mins","value":10804},"status":"OK"},{"distance":{"text":"45.9 km","value":45868},"duration":{"text":"2 hours 43 mins","value":9768},"status":"OK"},{"distance":{"text":"59.2 km","value":59196},"duration":{"text":"3 hours 0 mins","value":10778},"status":"OK"}]}];
																												   
		  for (var key in vLocationDistance) { 
						 //var dataDistanceMatrix =[];
						 for(var i=0;i<dataMatrix.length;i++)  
						 {
										dataDistanceRow =[];
										dataDistanceRow.push(dataMatrix[i][0]);
										dataDistanceRow.push(vLocationDistance[key].elements[i].distance.text);
										dataDistanceMatrix.push(dataDistanceRow);
						 }                                    
						 
						 //var dataDurationMatrix =[];
						 for(var i=0;i<dataMatrix.length;i++)  
						 {                                            
										dataDurationRow =[];                                           
										dataDurationRow.push(dataMatrix[i][0]);
										dataDurationRow.push(vLocationDistance[key].elements[i].duration.text);
										dataDurationMatrix.push(dataDurationRow);
						 } 

						 console.log('---ATM Distance----');
						 //console.log(dataDistanceMatrix);
						 console.log('---End of ATM Distance----');

						 console.log('---ATM Duration----');
						 //console.log(dataDurationMatrix);
						 console.log('---End of ATM Duration----');
		  }

});
		  

var _vAMTs = '';
for(var m4=0;m4<dataMatrix.length; m4++)
{
_vAMTs=_vAMTs+dataMatrix[m4][0]+',';
}

//Micro service -3
var atmUp = {       
parameters: { atmIds: _vAMTs},
//headers: { "apikey": "384M9075I79182CG" },
//data: "<xml><arg1>hello</arg1><arg2>world</arg2></xml>"
};

client.post(atmUpDownStatus, atmUp, function (data, response) {
if(Buffer.isBuffer(data)){
data = data.toString('utf8');
}           
var myATMUp = JSON.parse(data);
//console.log(data);
//var dataATMUpMatrix =[];                     
for(var m31=0;m31<myATMUp.length;m31++)
{
dataATMUpRow =[];
dataATMUpRow.push(myATMUp[m31].atmid);
dataATMUpRow.push(myATMUp[m31].status);
dataATMUpMatrix.push(dataATMUpRow);
}  
console.log('---ATM Up----');        
console.log(dataATMUpMatrix);
console.log('---End of ATM Up----');
});

//Micro service -4  
var atmsCashAvailable = {       
parameters: { atmIds: _vAMTs},
//headers: { "apikey": "384M9075I79182CG" },
//data: "<xml><arg1>hello</arg1><arg2>world</arg2></xml>"
};

client.post(atmCashNoCashStatus, atmsCashAvailable, function (data, response) {
if(Buffer.isBuffer(data)){
data = data.toString('utf8');
}

var myATMCash = JSON.parse(data);
//console.log(data);
//var dataATMCashMatrix =[];           
for(var m41=0;m41<myATMCash.length;m41++)
{
dataATMCashRow =[];
dataATMCashRow.push(myATMCash[m41].atmid);
dataATMCashRow.push(myATMCash[m41].status);
dataATMCashMatrix.push(dataATMCashRow);
}
console.log('---ATM Cash Up----');
//console.log(dataATMCashMatrix);
console.log('---End of ATM Cash Up----');
});

//Micro service -5 
var _trafficQInput ='';
var CurrentDt = new Date();//"2016-11-08 03:05:20.0";
var year = dateFormat(CurrentDt, "yyyy");
var date = dateFormat(CurrentDt, "yyyy/mm/dd");
//var MonthOfFile = dateFormat(CurrentDt, "mmm");
var MonthforSearch = dateFormat(CurrentDt, "mm"); 
var dayOfTheWeek = dateFormat(CurrentDt, "ddd");
var hour =dateFormat(CurrentDt, "hh");
for(var m5=0;m5<dataMatrix.length; m5++)
{
_trafficQInput=_trafficQInput+dataMatrix[m5][0]+','+date+','+dayOfTheWeek.toLowerCase()+','+hour+'\n';
}
console.log("dataMatrix.length:"+_trafficQInput)

var atmsTrafficQueue = {       
parameters: { atmList: _trafficQInput,},
//headers: { "apikey": "384M9075I79182CG" },
//data: "<xml><arg1>hello</arg1><arg2>world</arg2></xml>"
};

		  /*
for(var m41=0;m41<dataMatrix.length;m41++)
{
dataATMTrafficQueueRow =[];
dataATMTrafficQueueRow.push(dataMatrix[m41]);
dataATMTrafficQueueRow.push('3.6');
dataATMTrafficQueueMatrix.push(dataATMTrafficQueueRow);
}
console.log('---ATM Traffic Queue----');
//console.log(dataATMTrafficQueueMatrix);
console.log('---End of ATM Traffic Queue----');
		  */

client.post(waitTimePrediction, atmsTrafficQueue, function (data, response) {
if(Buffer.isBuffer(data)){
data = data.toString('utf8');
}

var myATMTrafficQueue = data;
						 console.log(myATMTrafficQueue);
						 var vQT = myATMTrafficQueue.split("\n");
						 for(var m511=0;m511<vQT.length; m511++)
						 {
										var vQTrow = [];
										vQTrow = vQT[m511].split(',');
										dataATMTrafficQueueRow =[];
										if(vQTrow[0]!=undefined && vQTrow[1]!=undefined)
										{
													   dataATMTrafficQueueRow.push(vQTrow[0]);
													   var vtime = vQTrow[1].substring(0, 4);
													   dataATMTrafficQueueRow.push(vtime);
													   dataATMTrafficQueueMatrix.push(dataATMTrafficQueueRow);
										}

						 }
console.log('---ATM Traffic Queue----');
console.log(dataATMTrafficQueueMatrix);
console.log('---End of ATM Traffic Queue----');
});

console.log('end async');
		  
		  
		  
var finalData = [
[  '4304','5', '2', 1,1, '3.6' ],
[ '3077','6', '3' , 1,1, '3.6' ],
[ '43040' ,'3', '4', 1,1, '3.6' ],
[  '43041','3', '8', 1,0 , '3.6' ],
[  '43042' ,'7', '3', 0,1, '3.6' ],
[ '43043' ,'3', '80',1,1, '3.6' ],
[ '43044' ,'5','6', 0,1, '3.6' ],
[ '43045' ,'15','5', 1,1, '3.6' ],
[ '43046' , '19','7', 1,1, '3.6' ],
[ '43047' ,'2', '9',1,1, '3.6' ] 
];

//Micro service -6  
var finalCall ='';
for(var m7=0;m7<finalData.length;m7++)
{
		  var tempRow =[];
		  tempRow =finalData[m7];  
		  finalCall = finalCall+tempRow[0]+','+tempRow[3]+','+tempRow[4]+','+tempRow[1]+','+tempRow[2]+','+tempRow[5]+'\n';
}
//console.log(finalCall);
var atmsDecisionTree = {       
parameters: { atmList: finalCall},
//headers: { "apikey": "384M9075I79182CG" },
//data: "<xml><arg1>hello</arg1><arg2>world</arg2></xml>"
};
		  
		  dataATMDTMatrix =[];
client.post(recommender, atmsDecisionTree, function (data, response) {
if(Buffer.isBuffer(data)){
data = data.toString('utf8');
}
						 
						 var myATMDT = data;
						 var vDT = myATMDT.split("\r\n");
						 var tempdataATMDTMatrix =[];
						 
						 for(var m711=0;m711<vDT.length; m711++)
						 {
										var vDTrow = [];
										vDTrow = vDT[m711].split(',');
										var dataDTRow =[];
										if(vDTrow[0]!=undefined && vDTrow[1]!=undefined)
										{
													   dataDTRow.push(m711+1);
													   dataDTRow.push(vDTrow[0]);
													   tempdataATMDTMatrix.push(dataDTRow);
										}
						 }
						 
						 var counter =1;
						 var vDTrowTemp = [];
						 str=""
						 var str = "["
						 for(var z=0;z<dataMatrix.length; z++)
						 {
										for(var x=0;x<tempdataATMDTMatrix.length; x++)
										{
													   if(dataMatrix[z][0]==tempdataATMDTMatrix[x][1])
													   {
																	  str=str+"{'id':'"+counter+"','atmId':'"+dataMatrix[z][0]+"','Name':'"+dataMatrix[z][1]+"','address':'"+dataMatrix[z][2]+"'},"
//                                                                                       str=str+"{\"id\":\""+counter+"\",\"atmId\":\""+dataMatrix[z][0]+"\",\"Name\":\""+dataMatrix[z][1]+"\",\"address\":\""+dataMatrix[z][2]+"\"},"

																	  counter++;                                                                                     
																	  //vDTrowTemp= '{"id":'+counter+',"atmId":'+dataMatrix[z][0]+',"Name":'+dataMatrix[z][1]+',"address":'+dataMatrix[z][2]+'}';
																	  
																	  //dataATMDTMatrix.push(vDTrowTemp);
													   }
										}
						 }
str=str+"]"
console.log('---Decision Tree----');
console.log(str);
console.log('---End of Decision Tree----');
						 
						 res.json(str); 
		  });
		  
		  
});
		  
console.log('end sync');


}
		  

module.exports = function (app) {
// api ---------------------------------------------------------------------
//"18.9220,72.8347"
//Method:POST; parameter:lan=18.9220, lat=72.8347
app.post('/BobMplus/ATMRecommender', function (req, res) {
var lan=req.query.latitude;
var lat=req.query.longitude;
console.log(lan);
console.log(lat);
						 
ATMRecommendor(req, res);
});

// application -------------------------------------------------------------
/*
app.get('*', function (req, res) {
res.sendFile(__dirname + '/public/index.html'); // load the single view file (angular will handle the page changes on the front-end)
});
*/
};
