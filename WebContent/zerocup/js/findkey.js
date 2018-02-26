
function adjustWidth(width){
	var width=getElementById('bg1').style.width;
	if (width>=1000) {
		getElementById('bg1').style.width=device-width,initial-scale=1;
	}
	else{
		getElementById('bg1').style.width=1000;
		getElementById('bg1').style.height=1000;
	}


}