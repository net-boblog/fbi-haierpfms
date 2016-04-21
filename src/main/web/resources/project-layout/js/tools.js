var Pbmp = {
    checkBrowser: function () {
        var userAgent = navigator.userAgent;
        var browser;
        var version;
        var ua = userAgent.toLowerCase();

        var browserMatch = this.uaMatch(userAgent.toLowerCase());
        if (browserMatch.browser) {
            browser = browserMatch.browser;
            version = browserMatch.version;
        }


        var input = window.document.getElementById("form:browserinfo");
        //var UserAgent = navigator.userAgent.toLowerCase();
        input.value = browser + version + "-" + window.screen.width + "-" + window.screen.height;
        if (browser == 'IE' && version <= 8) {
            //document.getElementById('form:download').click();
        }
    },
    
    uaMatch : function (ua) {
        var  rMsie = /(msie\s|trident\/7)([\w.]+)/,
            rTrident = /(trident)\/([\w.]+)/,
            rFirefox = /(firefox)\/([\w.]+)/,
            rOpera = /(opera).+version\/([\w.]+)/,
            rNewOpera = /(opr)\/(.+)/,
            rChrome = /(chrome)\/([\w.]+)/,
            rSafari = /version\/([\w.]+).*(safari)/;
        var matchBS, matchBS2;
        matchBS = rMsie.exec(ua);
        if (matchBS != null) {
            matchBS2 = rTrident.exec(ua);
            if (matchBS2 != null) {
                switch (matchBS2[2]) {
                    case "4.0":
                        return {browser: "IE", version: "8"};
                        break;
                    case "5.0":
                        return {browser: "IE", version: "9"};
                        break;
                    case "6.0":
                        return {browser: "IE", version: "10"};
                        break;
                    case "7.0":
                        return {browser: "IE", version: "11"};
                        break;
                    default:
                        return {browser: "IE", version: "undefined"};
                }
            }
            else
                return {browser: "IE", version: matchBS[2] || "0"};
        }
        matchBS = rFirefox.exec(ua);
        if ((matchBS != null) && (!(window.attachEvent)) && (!(window.chrome)) && (!(window.opera))) {
            return {browser: matchBS[1] || "", version: matchBS[2] || "0"};
        }
        matchBS = rOpera.exec(ua);
        if ((matchBS != null) && (!(window.attachEvent))) {
            return {browser: matchBS[1] || "", version: matchBS[2] || "0"};
        }
        matchBS = rChrome.exec(ua);
        if ((matchBS != null) && (!!(window.chrome)) && (!(window.attachEvent))) {
            matchBS2 = rNewOpera.exec(ua);
            if (matchBS2 == null)
                return {browser: matchBS[1] || "", version: matchBS[2] || "0"};
            else
                return {browser: "Opera", version: matchBS2[2] || "0"};
        }
        matchBS = rSafari.exec(ua);
        if ((matchBS != null) && (!(window.attachEvent)) && (!(window.chrome)) && (!(window.opera))) {
            return {browser: matchBS[2] || "", version: matchBS[1] || "0"};
        }
        if (matchBS != null) {
            return {browser: "undefined", version: " browser"};
        }
    }

};

$(function () {
    Pbmp.checkBrowser();
});

