/**
 * BookInfo.js
 * - Interpark Mobile Web Characteritic Script Source.
 * - Object of book-product informations.
 * 
 * INTERPARK BOOK MOBILE
 * 201603 hk-cho
 * 
 * # NECESSARINESS #
 * - jQuery , common-validate.js, common-util.js
 */
var BookInfo = function(properties){
	// PROPERTIES
	var p			= $.extend({}, BookInfo.defauIts, properties);
	
	// INNER ASSETS
	var _book				= this,
		ofrtpList			= [],
		OFRTP_LOADED		= false,
		OFRTP_LIST_URL		= "/api/my/bookdiary/getOfferTypeInfo"
	;
	
	/////////////////////////////////////////
	// PUBLIC FUNCTIONS
	/////////////////////////////////////////
	
	/* 도서 상품번호 */
	_book.getPrdNo = function(){
		return p.prdNo;
	};
	_book.setPrdNo = function(prdNo){
		p.prdNo = prdNo;
	};
	
	/* 도서 타입 */
	_book.getType = function(){
		return p.type;
	};
	_book.setType = function(type){
		setType(type);
	};
	
	/* 도서 이미지 */
	_book.getImg = function(){
		return p.img;
	};
	_book.setImg = function(src){
		setImg(src);
	};
	
	/* 도서 제목 */
	_book.getPrdTitle = function(){
		return p.prdTitle;
	};
	_book.setPrdTitle = function(prdTitle){
		p.prdTitle = prdTitle;
	};
	
	/* 도서 작가 */
	_book.getWriter = function(){
		return p.writer;
	};
	_book.setWriter = function(writer){
		p.writer = writer;
	};
	
	///////////////////////////////////
	
	/* 내 서재에 공개 여부 (Y/N) */
	_book.getLibryDispYn = function(){
		return p.libryDispYn;
	};
	_book.setLibryDispYn = function(libryDispYn){
		setLibryDispYn(libryDispYn);
	};
	_book.isLibryDisp = function(){
		return p.libryDispYn == "Y";
	}
	
	/* 다이어리 번호*/
	_book.getDiaryNo = function(){
		return p.diaryNo;
	};
	_book.setDiaryNo = function(diaryNo){
		p.diaryNo = diaryNo;
	};
	
	/* 다이어리 시퀀스 */
	_book.getDiarySeq = function(){
		return p.diarySeq;
	};
	_book.setDiarySeq = function(diarySeq){
		p.diarySeq = diarySeq;
	};
	
	/* 도서상태 */
	_book.getPrdStat = function(){
		return p.prdStat;
	};
	_book.setPrdStat = function(prdStat){
		p.prdStat = prdStat;
	};
	_book.getPrdStatOpts = function(){
		return getPrdStatOpts();
	}
	
	/* 읽은 페이지 수 */
	_book.getCurPage = function(){
		return p.curPage;
	};
	_book.setCurPage = function(curPage){
		setPage(curPage,'curPage');
	};
	
	/* 전체 페이지 수 */
	_book.getTotPage = function(){
		return p.totPage;
	};
	_book.setTotPage = function(totPage){
		setPage(totPage,'totPage');
	};
	
	/* 독서 진행률 */
	_book.getCompletion = function(){
		return getCompletion();
	}
	
	/* 구매처 */
	_book.getOfrTp = function(){
		return p.ofrTp;
	};
	_book.setOfrTp = function(ofrTp){
		p.ofrTp = ofrTp;
	};
	
	/* 중복 여부*/
	_book.getIsDup = function(){
		return p.isDup;
	};
/**
	_book.setIsDup = function(stat){
		setIsDup(stat);
	};
*/
	_book.isDup = function(){
		return p.isDup == 'Y';
	}
	
	/////////////////////////////////////////
	// UTILITIES
	/////////////////////////////////////////

	/* 구매처 정보 가져오기 */
	_book.getOfrTpList = function(){
		return getOfrTpList();
	};
	
	/* 도서정보 Properties 반환, 서비스 때는 주석처리 */
	_book.getInfo = function(){
		return p;
	};
	
	/* 데이터 초기화 */
	_book.clear = function(){
		p = BookInfo.defauIts;
	};
	
	/***/return init();/***/
	
	/////////////////////////////////////////
	// PRIVATE FUNCTIONS
	/////////////////////////////////////////
	function init(){  };
	/***/
	function setType(type){
		if(type !== 'ebook') type = 'book';
		p.type = type;
	}
	/***/
	function setLibryDispYn(stat){
		switch(true){
			case(typeof stat == 'boolean') : stat = stat ? 'Y' : 'N';
			case(typeof stat == 'string') : if(stat != 'Y' && stat != 'N') return false;
				break;
			default : try{console.error("setLibryDispYn - NOT APPROPRIATE TYPE , " + stat + ' : ' + typeof stat);}catch(e){}; return false;
		}
		p.libryDispYn = stat;
	};
	/***/
	function setImg(src){
		if(paramValidate.isEmpty(src)){
			try{console.error("setImg - EMPTY OR NULL IS NOT ACCEPTABLE");}catch(e){};
		}else p.img = src;
	};
	/***/
	function getPrdStatOpts(){
		var pStat = p.prdStat;
		var statSet = {};
		if(p.prdStat == '10'){
			statSet.text = '읽고싶어요';
			statSet.clazz = 'readWant';
		} else if(p.prdStat == '20'){
			statSet.text = '읽기예정';
			statSet.clazz = 'readWill';
		} else if(p.prdStat == '30'){
			statSet.text = '읽는 중';
			statSet.clazz = 'reading';
		} else if(p.prdStat == '40'){
			statSet.text = '읽기완료';
			statSet.clazz = 'readComplete';
		}
		return statSet;
	};
	/***/
	function setPage(page, type){
		if(type == 'totPage'){
			p.totPage = paramValidate.isNum(page) ? page : 0;
		} else if(type == 'curPage'){
			p.curPage = paramValidate.isNum(page) ? page : 0;
		}
	};
	/***/
	function getCompletion(){
		if(p.totPage === 0) return 0;
		var percent = (p.curPage / p.totPage * 100);
		return percent - (percent % 1);
	};
	/***/
	function getOfrTpList(){
		var retryCnt = 0;
		return OFRTP_LOADED ? loadFromLocal() : loadFromServer();
		/***/
		/** 서버에서 가져오기 */
		function loadFromServer(){
			var ofrtpList_ = [];
			requestAsync({
				url		: OFRTP_LIST_URL,
				method	: "POST",
				async	: false
			}, function(data){
				OFRTP_LOADED = true;
				ofrtpList_ = data.resData;
				ofrtpList = ofrtpList_;
			}, function(){
				if(retryCnt < 3) {
					setTimeout(function(){loadFromServer(); retryCnt++;}, 1500);
					try{console.error("Server Error, retry loadOfrTpList() - try : " + retryCnt);}catch(e){};
				} else ofrtpList_ = [{text:"서버오류",value:"00"}];
			});
			return ofrtpList;
		};
		/** 변수에 저장된 값 */
		function loadFromLocal(){return ofrtpList;};
	};
};// end

// 기본값
BookInfo.defauIts = {
		/** PRODUCT INFORMATIONS */
		prdNo			: ""								// 상품 번호
		, type			: "book"							// 상품 타입, "book": 도서, "ebook": 전자도서
		, img			: "/images/thumbnail/book_none.png"	// 도서 디폴트 이미지
		, prdTitle		: ""								// 도서 제목
		, writer		: ""								// 작가
		
		/** DATA OPTIONS */
		, libryDispYn	: "Y"				// 내 서재에 공개여부 , 'Y' : 공개(default) , 'N' : 비공개
		, diaryNo		: "DRY001"			// 다이어리번호, default "DRY001", 현황 2016.03)DRY001, DRY002, DRY003으로 fix
		, diarySeq		: ""				// 다이어리에 속한 도서정보의 sequence
		, prdStat		: "10"				// 도서 상태, 10 : 읽고싶어요(default), 20 : 읽기예정, 30 : 읽는 중, 40 : 읽기완료
			
		, curPage		: 0					// 읽은 페이지 수
		, totPage		: 0					// 전체 페이지 수
			
		, ofrTp			: "01"				// 구매처, default "01" 
		, bestPrdTp		: "02"				// 최고의 책 여부, 01 : 최고의 책, 02 : default, 03 : 최고의책 아님
		, isDup			: "N"				// 중복여부, 'Y' : 이미 북다이어리에 추가됨, 'N' : 신규
};
