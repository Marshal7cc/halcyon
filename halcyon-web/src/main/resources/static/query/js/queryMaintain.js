var person = [
    {
        name: '江哲',
        src: 'img/1.png',
        motto: '须交有道之人，莫结无义之友。饮清静之茶，莫贪花色之酒。开方便之门，闲是非之口。',
        sex: 'boys'
    },
    {
        name: '欧阳锋',
        src: 'img/2.png',
        motto: '“我欲”是贫穷的标志。事能常足，心常惬，人到无求品自高。',
        sex: 'boys'
    },
    {
        name: '江小白',
        src: 'img/3.png',
        motto: '势不可使尽，福不可享尽，便宜不可占尽，聪明不可用尽。',
        sex: 'boys'
    },
    {
        name: '程浩然',
        src: 'img/4.png',
        motto: '做事不必与俗同，亦不宜与俗异。做事不必令人喜，亦不可令人憎。',
        sex: 'boys'
    },
    {
        name: '李志成',
        src: 'img/5.png',
        motto: '人之心胸，多欲则窄，寡欲则宽。',
        sex: 'boys'
    },
    {
        name: '王嫣然',
        src: 'img/6.png',
        motto: '人的一生也可以是那一杯香醇的酒，慢慢地享受，细细地品味，自然也可以韵出生命的味道。',
        sex: 'girls'
    },
    {
        name: '程美',
        src: 'img/7.png',
        motto: '大千世界，人生百态。如何面对人生，是快乐，是悲伤？不能让你的人生去决定，也不是任由命运摆布着你，应该自己把握！',
        sex: 'girls'
    },
    {
        name: '李梅芳',
        src: 'img/8.png',
        motto: '人的一生是由色彩交织而成的，有善良的白，沉静的蓝，热情的红，希望的绿和温柔的紫。它们散发出的光彩，则是我们的生命。！',
        sex: 'girls'
    },
    {
        name: '南宫小婉',
        src: 'img/9.png',
        motto: '生活就是一块调色板，你选择了你喜欢的色彩，那么其色就更加美丽，人生就似一碗汤，你选择了你喜欢的味道，你才感觉有滋有味……',
        sex: 'girls'
    },
    {
        name: '江苏颖',
        src: 'img/10.png',
        motto: '势不可使尽，福不可享尽，便宜不可占尽，聪明不可用尽。',
        sex: 'girls'
    }
]




var inp = document.getElementsByClassName('inp')[0],
    btnList = document.getElementsByClassName('btn-list')[0],
    prompt = document.getElementsByClassName('prompt')[0],
    user = document.getElementsByClassName('user')[0];


function createList(list) {
    var str = '';
    list.forEach(function (ele, index) {
        str += '<li>\
            <div class="head-img">\
            <img src='+ ele.src + ' alt="">\
            </div>\
            <div class="tit">\
            <h1 class="name">'+ ele.name + '</h1>\
            <p class="motto">'+ ele.motto + '</p>\
            </div>\
            </li>'
    })

    user.innerHTML = str
}

createList(person);

var state = createStore({ text: '', sex: 'all' })

state.addFn(function () {
    createList(perArr());
    anti(show());
})

inp.oninput = anti(getInput, 1000)

function getInput() {
    state.change({ type: 'text', value: this.value });
}

function anti(hander, delay) {
    var timer = null;
    return function () {
         var  _self = this,
            args = arguments;
        clearTimeout(timer)
        timer = setTimeout(function () {
            hander.apply(_self, args)
        }, delay)
    }
}

function show() {
    if (user.children.length == 0) {
        prompt.style.display = 'block'
    } else {
        prompt.style.display = 'none'
    }
}

function addFn(obj, arr) {
    return function () {
        var lastArr = arr;
        for (prop in obj) {
            lastArr = obj[prop](state.getState()[prop], lastArr)
        }
        return lastArr
    }
}

var perArr = addFn({ text: filterText, sex: filterSex }, person)

btnList.onclick = function (e) {
    var tar = e.target;
    if (tar.tagName == 'LI') {
        document.getElementsByClassName('active')[0].className = '';
        tar.className = 'active';
        state.change({ type: 'sex', value: tar.getAttribute('sex') })
    }
}

function filterText(text, arr) {
    return arr.filter(function (ele, index) {
        if (ele.name.indexOf(text) != -1 || ele.motto.indexOf(text) != -1) {
            return true
        }
    })
}

function filterSex(sex, arr) {
    if (sex == 'all') {
        return arr
    } else {
        return arr.filter(function (ele, index) {
            if (ele.sex == sex) {
                return true
            }
        })
    }
}