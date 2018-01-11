test();
function test(){
	var EndPoint = "/GlobalMessage";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0,path.indexOf('/',1));
	var endPointURL = "ws://"+host+webCtx+EndPoint;
	var globalWebSocket = new WebSocket(endPointURL+"/"+"test"); 

	console.log(member_id);
	console.log(globalWebSocket);
}