var getTag = function (tag, context, results) {
    results = results || [];
    context = context || document;
    results.push.apply(results, context.getElementsByTagName(tag));
    return results;
};

var getId = function (id, context, results) {
    results = results || [];
    context = context || document;
    results.push(context.getElementById(id));
    return results;
};

var getClass = function (className, context, results) {
    results = results || [];
    context = context || document;
    results.push(context.getElementsByClassName(className));
    return results;
};


var get = function (selector, context, results) {
    results = results || [];
    context = context || document;

    // reg = /^(?: #([\w-]+) | \.([\w-]+) | ([\w]+) | ([\w]+) | (\*) )$/; 正则表达式

    var rQuickExpr = /^(?: #([\w-]+)|\.([\w-]+)|([\w]+)|([\w]+)|(\*) )$/,
        m = rQuickExpr.exec(selector);

    if (m) {
        if (m[1]) {
            results = getId(m[1], results);
        } else if (m[2]) {
            results = getClass(m[2], context, results);
        } else if (m[3] || m[4]) {
            results = getTag(m[3], context, results);
        } else if (m[4]) {
            results = getTag(m[4], context, results);
        }
    }
    return results;
};


//封装for循环
var each = function (arr, fn) {
    for (var i = 0; i < arr.length; i++) {

        //call()方法的语法格式是,函数引用.call(调用者,参数1,参数2,参数3)
        if (fn.call(arr[i], i, arr[i]) === false) {
            //调用传进来的函数，如果返回的不是false，那么就一直执行

            break;
        }
    }
};


// 原生字符串的方法
/*
    var firstChar = selector.charAt(0);

    switch(firstChar){
        case '#':
            selector.slice(1); //取一号元素后面的所有元素
            break;

        case '.':
            selector.slice(1);
            break;

        default:
            selector.slice(1);
            break;
    }

 */

// get('选择器',父元素)
// 如果要实现这个功能，那么应该先做什么？
// 1,首先假定父元素就是dom对象
// 首先应该考虑修改父元素的三个get方法，然后再修改通用的get方法

var myTrim = function (str) {
    if (String.prototype.trim) {
        return str.trim();
    } else {
        return str.replace(/^\s+|\s+$/g, ''); //去空格
    }
};

var select = function (selector, context, results) {
    var newSelectors = selector.split(',');
    each(newSelectors, function (i, v) {
        results.push.apply(get(myTrim(v), context));
    });

    return results;
};


/*****************************************/

var list1 = select('#dv');
var list2 = select('.dv');
var list3 = select('div');
var list4 = select('#dv, .c, p');
var list5 = select('#dv .c, p .c,div');

//最简单的做法
//我们已经知道里面可以有组合，可能有后代，但是最终是转换成基本版本
var select = function (selector) {
    var selectors = selector.split(',');


    for (var i = 0; i < selector.length; i++) {
        var singleSelector = selectors[i]; //可能包含后代的

        var subSelectors = singleSelector.split(' ');

        //在subSelectors[j]中找后代元素
        for (var j = 0; j < subSelectors.length; j++) {
            var s = subSelectors[j];
            res = get(s,res);
        }

        push.apply(results,res);

    }
};
