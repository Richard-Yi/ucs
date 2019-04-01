var strategies = {
    isNotEmpty: function ( value, errorMsg ) {
        if ( value === "" ) {
            return errorMsg;
        }
    },
    isMobile: function ( value, errorMsg ) {
        if ( !/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test( value ) ) {
            return errorMsg;
        }
    },
    minLength: function ( value, length, errorMsg ) {
        if ( value.length < length ) {
            return errorMsg;
        }
    },
    maxLength: function (value, length, errorMsg) {
        if (value.length > length) {
            return errorMsg;
        }
    },
    isChecked: function ( value, errorMsg) {
        if( !value ) {
            return errorMsg;
        }
    },
    isCode: function (value, errorMsg) {
      if ( !/^\d{6}$/.test(value) ) {
          return errorMsg;
      }
    },
    sendCheck: function ( value, errorMsg) {
        if( !value ){
            return errorMsg;
        }
    },
    equal: function (value, errorMsg, equalWith) {
        if (value !== equalWith) {
            return errorMsg;
        }
    }
};

var Validator = function(){
    this.cache = [];
};
Validator.prototype.add = function( dom, rules ) {
    var self = this;
    for ( var i = 0, rule; rule = rules[i++]; ) {
        (function( rule ){
            var strategyAry = rule.strategy.split( ":" );
            var errorMsg = rule.errorMsg;
            self.cache.push(function(){
                var strategy = strategyAry.shift();
                if ( rule.strategy === 'isChecked' ) {
                    strategyAry.unshift( dom.checked );
                } else if ( rule.strategy === 'sendCheck' ) {
                    strategyAry.unshift( ifClickSend );
                } else {
                    strategyAry.unshift( dom.value );
                }
                strategyAry.push( errorMsg );
                strategyAry.push( rule.value );
                return strategies[strategy].apply( dom, strategyAry );
            });
        })(rule);
    }
};

Validator.prototype.start = function(){
    for ( var i = 0, validatorFunc; validatorFunc = this.cache[ i++ ]; ){
        var errorMsg = validatorFunc();
        if( errorMsg ){
            return errorMsg;
        }
    }
};