$( function() {
	$.validator.addMethod( "letter", function(theValue) { // 字母
		if ( ( "" + theValue ).length == 0 ) return true;
		var reg = /^[A-Za-z]+$/;
		return reg.test( theValue );
	}, '只能输入大小写字母' );

	$.validator.addMethod( "upcase", function(theValue) { // 大写字母
		if ( ( "" + theValue ).length == 0 ) return true;
		var reg = /^[A-Z]+$/;
		return reg.test( theValue );
	}, '只能输入大写字母' );
	
	$.validator.addMethod( "lowercase", function(theValue) { // 小写字母
		if ( ( "" + theValue ).length == 0 ) return true;
		var reg = /^[a-z]+$/;
		return reg.test( theValue );
	}, '只能输入小写字母' );
	
	$.validator.addMethod( "english", function(theValue) { // 英文字符
		if ( ( "" + theValue ).length == 0 ) return true;
		var reg = /^[^\u4E00-\u9FA5]+$/;
		return reg.test( theValue );
	}, '只能输入英文字符' );
	
	$.validator.addMethod( "chinese", function(theValue) { // 中文字符
		if ( ( "" + theValue ).length == 0 ) return true;
		var reg = /^[\u4E00-\u9FA5]+$/;
		return reg.test( theValue );
	}, '只能输入中文字符' );
	
	$.validator.addMethod( "letternumber", function(theValue) { // 文本字符(字母/数字)
		if ( ( "" + theValue ).length == 0 ) return true;
		var reg = reg = /^[A-Za-z0-9]+$/;
		return reg.test( theValue );
	}, '只能输入字母/数字字符' );
	
	$.validator.addMethod( "letternumberunderline", function(theValue) { // 文本字符(字母/数字/下划线)
		if ( ( "" + theValue ).length == 0 ) return true;
		var reg = reg = /^[A-Za-z0-9_]+$/;
		return reg.test( theValue );
	}, '只能输入字母/数字字符' );
	
	$.validator.addMethod( "text", function(theValue) { // 文本字符(字母/数字/下划线/中文)
		if ( ( "" + theValue ).length == 0 ) return true;
		var reg = reg = /^[A-Za-z0-9_\u4E00-\u9FA5]+$/;
		return reg.test( theValue );
	}, '只能输入字母/数字/下划线字符/中文' );
	
	$.validator.addMethod( "exclude", function(theValue) { // 文本字符(字母/数字/下划线/中文)
		if ( ( "" + theValue ).length == 0 ) return true;
		var reg = reg = /^[@#$<>\^&\?"'/\\~`]+$/;
		return !reg.test( theValue );
	}, '不能使用特殊字符' );
	
	$.validator.addMethod( "print", function(theValue) { // 打印字符(除控制字符如Tab以外的字符)
		if ( ( "" + theValue ).length == 0 ) return true;
		var reg = reg = /^[\s\S]+$/;
		return reg.test( theValue );
	}, '只能输入打印字符' );
	
	$.validator.addMethod( "telephone", function(theValue) { // 电话
		if ( ( "" + theValue ).length == 0 ) return true;
		var reg =/^0\d{2,3}-?\d{7,8}$/; 
		return reg.test( theValue );
	}, '不是有效的电话号码' );
	
	$.validator.addMethod( "mobile", function(theValue) { // 手机
		if ( ( "" + theValue ).length == 0 ) return true;
		var reg =/^1[345789]\d{9}$/; 
		return reg.test( theValue );
	}, '不是有效的手机号码' );
	
/*	$.validator.addMethod( "longitude", function(theValue) { // 经度
		if ( ( "" + theValue ).length == 0 ) return true;
		var reg ==/^[-]?(\d|([1-9]\d)|(1[0-7]\d)|(180))(\.\d*)?$/g;
		return reg.test( theValue );
	}, '不是有效的经度' );
	
	$.validator.addMethod( "latitude", function(theValue) { // 纬度
		if ( ( "" + theValue ).length == 0 ) return true;
		var reg =/^[-]?(\d|([1-8]\d)|(90))(\.\d*)?$/g;
		return reg.test( theValue );
	}, '不是有效的纬度' );
*/
	$.validator.addMethod( "image", function(theValue) { // 图片
		if ( ( "" + theValue ).length == 0 ) return true;
		var reg =/\.(bmp|gif|jpg|jpeg|png|BMP|GIF|JPEG|JPG|PNG)$/; 
		return reg.test( theValue );
	}, '不是有效的图片' );
	
	$.validator.addMethod( "excel", function(theValue) { // 图片
		if ( ( "" + theValue ).length == 0 ) return true;
		var reg =/\.(xlsx|xls|XLSX|XLS)$/; 
		return reg.test( theValue );
	}, '不是有效的excel' );
	
} );
