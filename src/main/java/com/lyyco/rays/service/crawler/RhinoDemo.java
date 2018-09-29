package com.lyyco.rays.service.crawler;

import com.google.common.base.Throwables;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

/**
 * Author liyangyang
 * 2018/9/28
 */
public class RhinoDemo {


    public static void main(String...args){
        Context cx = Context.enter();
        try {
            Scriptable scope = cx.initStandardObjects();
            Object result = cx.evaluateString(scope, "var condition = '{\"SearchKey\":\"B73F91AB9CE2E33531E59886C2F715F99D97CA7E18BDA2C0F6058D1037C2DDFB\"}'; \n" +
                    "(function(u, r, k, t) {\n" +
                    "    var N23 = 1,\n" +
                    "    B23N = 1;\n" +
                    "    N23 = N23 -= eval(function(p, a, c, k, e, d) {\n" +
                    "        e = function(c) {\n" +
                    "            return (c < a ? '': e(parseInt(c / a))) + ((c = c % a) > 35 ? String.fromCharCode(c + 29) : c.toString(36))\n" +
                    "        };\n" +
                    "        if (!''.replace(/^/, String)) {\n" +
                    "            while (c--) d[e(c)] = k[c] || e(c);\n" +
                    "            k = [function(e) {\n" +
                    "                return d[e]\n" +
                    "            }];\n" +
                    "            e = function() {\n" +
                    "                return '\\\\w+'\n" +
                    "            };\n" +
                    "            c = 1;\n" +
                    "        };\n" +
                    "        while (c--) if (k[c]) p = p.replace(new RegExp('\\\\b' + e(c) + '\\\\b', 'g'), k[c]);\n" +
                    "        return p;\n" +
                    "    } ('1(0.2(4)*3)', 5, 5, 'Math|parseInt|log|0xa|5'.split('|'), 0, {}));\n" +
                    "    if (N23 < 0) N23 = -N23;\n" +
                    "    while (N23 > 30) N23 = N23 % 10;\n" +
                    "    B23N = B23N += eval(function(p, a, c, k, e, d) {\n" +
                    "        e = function(c) {\n" +
                    "            return (c < a ? '': e(parseInt(c / a))) + ((c = c % a) > 35 ? String.fromCharCode(c + 29) : c.toString(36))\n" +
                    "        };\n" +
                    "        if (!''.replace(/^/, String)) {\n" +
                    "            while (c--) d[e(c)] = k[c] || e(c);\n" +
                    "            k = [function(e) {\n" +
                    "                return d[e]\n" +
                    "            }];\n" +
                    "            e = function() {\n" +
                    "                return '\\\\w+'\n" +
                    "            };\n" +
                    "            c = 1;\n" +
                    "        };\n" +
                    "        while (c--) if (k[c]) p = p.replace(new RegExp('\\\\b' + e(c) + '\\\\b', 'g'), k[c]);\n" +
                    "        return p;\n" +
                    "    } ('1(0.2(4)*3)', 5, 5, 'Math|parseInt|sin|0xa|3'.split('|'), 0, {}));\n" +
                    "    B23N = B23N += eval(function(p, a, c, k, e, d) {\n" +
                    "        e = function(c) {\n" +
                    "            return (c < a ? '': e(parseInt(c / a))) + ((c = c % a) > 35 ? String.fromCharCode(c + 29) : c.toString(36))\n" +
                    "        };\n" +
                    "        if (!''.replace(/^/, String)) {\n" +
                    "            while (c--) d[e(c)] = k[c] || e(c);\n" +
                    "            k = [function(e) {\n" +
                    "                return d[e]\n" +
                    "            }];\n" +
                    "            e = function() {\n" +
                    "                return '\\\\w+'\n" +
                    "            };\n" +
                    "            c = 1;\n" +
                    "        };\n" +
                    "        while (c--) if (k[c]) p = p.replace(new RegExp('\\\\b' + e(c) + '\\\\b', 'g'), k[c]);\n" +
                    "        return p;\n" +
                    "    } ('1(0.2(4)*3)', 5, 5, 'Math|parseInt|log|0xa|3'.split('|'), 0, {}));\n" +
                    "    B23N = B23N -= eval(function(p, a, c, k, e, d) {\n" +
                    "        e = function(c) {\n" +
                    "            return (c < a ? '': e(parseInt(c / a))) + ((c = c % a) > 35 ? String.fromCharCode(c + 29) : c.toString(36))\n" +
                    "        };\n" +
                    "        if (!''.replace(/^/, String)) {\n" +
                    "            while (c--) d[e(c)] = k[c] || e(c);\n" +
                    "            k = [function(e) {\n" +
                    "                return d[e]\n" +
                    "            }];\n" +
                    "            e = function() {\n" +
                    "                return '\\\\w+'\n" +
                    "            };\n" +
                    "            c = 1;\n" +
                    "        };\n" +
                    "        while (c--) if (k[c]) p = p.replace(new RegExp('\\\\b' + e(c) + '\\\\b', 'g'), k[c]);\n" +
                    "        return p;\n" +
                    "    } ('1(0.2(4)*3)', 5, 5, 'Math|parseInt|sin|0xa|3'.split('|'), 0, {}));\n" +
                    "    if (B23N < 0) B23N = -B23N;\n" +
                    "    while (B23N > 30) B23N = B23N % 10; (function(r, w, y, zz, x) {\n" +
                    "        eval(function(p, a, c, k, e, d) {\n" +
                    "            e = function(c) {\n" +
                    "                return (c < a ? \"\": e(parseInt(c / a))) + ((c = c % a) > 35 ? String.fromCharCode(c + 29) : c.toString(36))\n" +
                    "            };\n" +
                    "            if (!''.replace(/^/, String)) {\n" +
                    "                while (c--) d[e(c)] = k[c] || e(c);\n" +
                    "                k = [function(e) {\n" +
                    "                    return d[e]\n" +
                    "                }];\n" +
                    "                e = function() {\n" +
                    "                    return '\\\\w+'\n" +
                    "                };\n" +
                    "                c = 1;\n" +
                    "            };\n" +
                    "            while (c--) if (k[c]) p = p.replace(new RegExp('\\\\b' + e(c) + '\\\\b', 'g'), k[c]);\n" +
                    "            return p;\n" +
                    "        } ('m 5$=[\\'\\',\\'b\\',\\'f\\',\\'e\\',\\'h\\'],l,7;g(6[5$[1]]){l=7.9(5$[0]);c=l.8(d,a);l.8(i,j,c);6[5$[4]]=6[5$[1]](6[5$[2]]=6[5$[2]][5$[3]](7,l.k(5$[0])))}', 23, 23, '|||||_|w|r|splice|split|0x1|simpleLoader||y|replace|condition|if|flightLoader|x|0x0|join||var'.split('|'), 0, {}))\n" +
                    "    })(eval(function(p, a, c, k, e, d) {\n" +
                    "        e = function(c) {\n" +
                    "            return (c < a ? \"\": e(parseInt(c / a))) + ((c = c % a) > 35 ? String.fromCharCode(c + 29) : c.toString(36))\n" +
                    "        };\n" +
                    "        if (!''.replace(/^/, String)) {\n" +
                    "            while (c--) d[e(c)] = k[c] || e(c);\n" +
                    "            k = [function(e) {\n" +
                    "                return d[e]\n" +
                    "            }];\n" +
                    "            e = function() {\n" +
                    "                return '\\\\w+'\n" +
                    "            };\n" +
                    "            c = 1;\n" +
                    "        };\n" +
                    "        while (c--) if (k[c]) p = p.replace(new RegExp('\\\\b' + e(c) + '\\\\b', 'g'), k[c]);\n" +
                    "        return p;\n" +
                    "    } ('(1.0(/\"3\":\"(.+?)\"/)||[\\'\\']).2();', 4, 4, 'match|condition|pop|SearchKey'.split('|'), 0, {})), window, B23N, eval(function(p, a, c, k, e, d) {\n" +
                    "        e = function(c) {\n" +
                    "            return c.toString(36)\n" +
                    "        };\n" +
                    "        if (!''.replace(/^/, String)) {\n" +
                    "            while (c--) {\n" +
                    "                d[c.toString(a)] = k[c] || c.toString(a)\n" +
                    "            }\n" +
                    "            k = [function(e) {\n" +
                    "                return d[e]\n" +
                    "            }];\n" +
                    "            e = function() {\n" +
                    "                return '\\\\w+'\n" +
                    "            };\n" +
                    "            c = 1\n" +
                    "        };\n" +
                    "        while (c--) {\n" +
                    "            if (k[c]) {\n" +
                    "                p = p.replace(new RegExp('\\\\b' + e(c) + '\\\\b', 'g'), k[c])\n" +
                    "            }\n" +
                    "        }\n" +
                    "        return p\n" +
                    "    } ('!0(a){a||($=n=i=h=e)}(0(a,b,c){f g[6.7(2*a+b,2*a+c,2*a-1,2*a-3,2*a+9,2*a+5,2*a+c,2*a+c-1)][6.7(2*a+4,a+m,l,j)]}(k,8,d,9,5));', 24, 24, 'function||||||String|fromCharCode||16||||11|null|return|this|simpleLoader|window|102|50|101|64|module'.split('|'), 0, {})), N23)\n" +
                    "})();", "", 1, null);
            System.out.println(cx.toString(result));
        }catch (Exception e){
            System.out.println(e.getMessage());
            Throwables.propagateIfPossible(e);
        }finally {
            Context.exit();
        }
    }
}
