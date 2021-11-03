//参考Web: https://moewe-net.com/nodejs/node-java
//必要なもの: node-java (npm install java)
//          XMLHttpRequest
const java = require('java');
const XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;
//カレントディレクトリ
console.log("Current dir = " + process.cwd());

//Javaの実行ファイルをパスに追加(jarファイルはカレントディレクトリに配置する)
java.classpath.push("OptimThinningJNI-1.0.jar");

//使いたいクラスをオブジェクトにする
//cuOptimThinning.dllは.\node_modules\に配置. (失敗してもエラーメッセージでどこに配置すれば良いか示される)
const javaObject = java.import('thinningoptim.SA2021');

//以下はテストコード

//ファイル読み込み
const xhr = new XMLHttpRequest();
xhr.open("GET", 'file://E:/Git/JavaScript/Java2Js/Nagano_Cedar.json',false);
xhr.send();
let input  = xhr.responseText;
xhr.abort();
//読み込んだファイルの確認
console.log(input);

//Javaに送って実行
let result = javaObject.runSync(input);
console.log(result);