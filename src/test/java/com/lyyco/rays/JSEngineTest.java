package com.lyyco.rays;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Author liyangyang
 * 2018/9/26
 */
public class JSEngineTest {
    public static void main(String...args){
        ScriptEngineManager scriptEngineManager =  new ScriptEngineManager();
        ScriptEngine engine = scriptEngineManager.getEngineByExtension("js");
        try{
            Double hour = (Double)engine.eval("var date = new Date();"+"date.getHours();");
            String  key = (String)engine.eval("var condition = '{\"SearchKey\":\"E0A28CB6479DFB642D66084451A04E68153D9F2265ACB919FDC4D40D32807C64FE0790D3293DB034\"}';\n" +
                    "        !function($, w) {validateQuery = function() {return ! 1},simpleLoader = function(cond){}}(this.cQuery,this);\n" +
                    "        (function(u,r,k,t){var l10=1,p10l=1;l10=l10*=eval(function(p,a,c,k,e,d){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\\\w+'};c=1;};while(c--)if(k[c])p=p.replace(new RegExp('\\\\b'+e(c)+'\\\\b','g'),k[c]);return p;}('1(0.2(4)*3)',5,5,'Math|parseInt|log|0xa|7'.split('|'),0,{}));l10=l10+=eval(function(p,a,c,k,e,d){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\\\w+'};c=1;};while(c--)if(k[c])p=p.replace(new RegExp('\\\\b'+e(c)+'\\\\b','g'),k[c]);return p;}('1(0.2(4)*3)',5,5,'Math|parseInt|cos|0xa|7'.split('|'),0,{}));l10=l10+=eval(function(p,a,c,k,e,d){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\\\w+'};c=1;};while(c--)if(k[c])p=p.replace(new RegExp('\\\\b'+e(c)+'\\\\b','g'),k[c]);return p;}('1(0.2(4)*3)',5,5,'Math|parseInt|sin|0xa|7'.split('|'),0,{}));if(l10<0)l10=-l10; while(l10>30)l10=l10%10;p10l=p10l+=eval(function(p,a,c,k,e,d){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\\\w+'};c=1;};while(c--)if(k[c])p=p.replace(new RegExp('\\\\b'+e(c)+'\\\\b','g'),k[c]);return p;}('1(0.2(4)*3)',5,5,'Math|parseInt|cos|0xa|8'.split('|'),0,{}));if(p10l<0)p10l=-p10l; while(p10l>30)p10l=p10l%10;(function(r, w, y, zz, x) {eval(function(p,a,c,k,e,d){e=function(c){return(c<a?\"\":e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\\\w+'};c=1;};while(c--)if(k[c])p=p.replace(new RegExp('\\\\b'+e(c)+'\\\\b','g'),k[c]);return p;}('m 5$=[\\'\\',\\'b\\',\\'f\\',\\'e\\',\\'h\\'],l,7;g(6[5$[1]]){l=7.9(5$[0]);c=l.8(d,a);l.8(i,j,c);6[5$[4]]=6[5$[1]](6[5$[2]]=6[5$[2]][5$[3]](7,l.k(5$[0])))}',23,23,'|||||_|w|r|splice|split|0x1|simpleLoader||y|replace|condition|if|flightLoader|x|0x0|join||var'.split('|'),0,{}))})(eval(function(p,a,c,k,e,d){e=function(c){return(c<a?\"\":e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\\\w+'};c=1;};while(c--)if(k[c])p=p.replace(new RegExp('\\\\b'+e(c)+'\\\\b','g'),k[c]);return p;}('(1.0(/\"3\":\"(.+?)\"/)||[\\'\\']).2();',4,4,'match|condition|pop|SearchKey'.split('|'),0,{})), this, p10l, eval(function(p,a,c,k,e,d){e=function(c){return c.toString(36)};if(!''.replace(/^/,String)){while(c--){d[c.toString(a)]=k[c]||c.toString(a)}k=[function(e){return d[e]}];e=function(){return'\\\\w+'};c=1};while(c--){if(k[c]){p=p.replace(new RegExp('\\\\b'+e(c)+'\\\\b','g'),k[c])}}return p}('!0(a){a||($=n=i=h=e)}(0(a,b,c){f g[6.7(2*a+b,2*a+c,2*a-1,2*a-3,2*a+9,2*a+5,2*a+c,2*a+c-1)][6.7(2*a+4,a+m,l,j)]}(k,8,d,9,5));',24,24,'function||||||String|fromCharCode||16||||11|null|return|this|simpleLoader|this|102|50|101|64|module'.split('|'),0,{})), l10)})();\n" +
                    "    ");
            System.out.println(key);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        HtmlUnitDriver webDriver = new HtmlUnitDriver();
        webDriver.setJavascriptEnabled(true);

    }
}
