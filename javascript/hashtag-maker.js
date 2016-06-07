/**
 * hashtag maker
 * 2016.05 hk-cho
 * 
 * # NECESSARINESS #
 * - jQuery , common-validate.js, common-util.js
 */
;(function($){
	
	var delimiter = [',', ' '];			// ',' 또는 ' '으로 해쉬태그가 구분 됨
	var nth = 0;
	var tags = new Array();				// 태그들
	var byteLimit = 20;					// 해쉬태그 바이트 제한
	
	var PREFIX = '#';
	var SUFFIX = ', ';
	
	$.fn.addTag = function(e, v) {
		var $this = $(this);
		// focus event
		if(e.type == 'focus' && paramValidate.isEmpty($this.val())) $this.val("#");
		
		// key up event
		if(e.type == 'keyup'){
			v = checkText($this,v);
			if(_checkDelimiter(v)){
				tags = convertToArr(v);
				if(tags.length == 5) alert("해시태그는 5개까지 등록됩니다");
				$(this).val(setHashStr(tags, $this));
			};
		};
		
		$this.off('blur').on('blur', function(){
			var inputArr = convertToArr(v);
			// 입력 후 바로 포커스 아웃시
			var same = $(tags).not(inputArr).length === 0 && $(inputArr).not(tags).length === 0
			if(!same) tags = convertToArr(v);
			
			var thisVal = setHashStr(tags, $this);
			var endSet = thisVal.substr(thisVal.length - 3);
			if(endSet == (SUFFIX + PREFIX)) {
				thisVal = thisVal.slice(0, -3);
			}
			$(this).val(thisVal);
		});
	};
	
	$.fn.getTags = function(){
		var str = $(this).val();
		return convertToArr(str); 
	}
	
	///////////////////////////////////////////////////
	//	UTILITIES
	///////////////////////////////////////////////////	
	
	// 텍스트 체크
	function checkText($this, v){
		v = _captureBackspace($this,v);
		return v;
	}
	
	// 백스페이스 이벤트 처리
	/*
	 * 안드로이드 모바일 웹에서는 event.keycode || event.which로 백스페이스 이벤트 감지안됨
	 * 해쉬태그 패턴으로 백스페이스 이벤트처리
	 */
	function _captureBackspace($this, v){
		var target = v.substr(v.length - 2);
		if(target == SUFFIX) {
			v = v.slice(0, -2);
			$this.val(v);
		}
		return v;
	};
	
	// 구분자 여부 체크
	function _checkDelimiter(v){
		var found = false;
		var lastChar = v.substr(v.length - 1);
		if($.inArray(lastChar, delimiter) >= 0) {
			found = true;
		}
		return found;
	};
	
	// 태그 문자 배열 만들기
	function convertToArr(v){
		var result = new Array();
		var inputs = v.split(/#| |,/);
		var tempArr = inputs.filter(function(v){return v !== '';});		// 공백 제거
		
		// 중복제거
		$.each(tempArr, function(i, el){
			if($.inArray(el, result) === -1) result.push(el);
		});
		return result;
	}
	
	// 해시 문자열 만들기, 문자열 만들면서 바이트 체크
	function setHashStr(tags, $input){
		var tempTags = tags;
		var s, byte = 0;
		var strs = '';
		
		// 해시 내용 검사
		for(var i=0; i < tags.length; i++){
			if(tags.length > 5) {
				return false;
			}
			var str = new String(escapeSymbols(tempTags[i]));
			byte = str.getByte();
			if(byte > 20) {
				alert("해시태그 내용은 20byte를 넘을 수 없습니다.");
				s = str.cutByte(byteLimit);
				tempTags[i] = s;
			}
			tags = tempTags;
			var tag = PREFIX + tempTags[i];
			strs += (i == 0) ? tag : (SUFFIX + tag);
		}
		if(tags.length < 5) strs += (SUFFIX + PREFIX);
		
		return strs;
	};
	
	/** 특수문자 적용 안함 */
	function escapeSymbols(value) {
		return value.replace(/[-!@#$%^&*()_+|~=`{}\[\]:";'<>?,.\/]/g, "");
	}
	
//		return false;
})(jQuery);
