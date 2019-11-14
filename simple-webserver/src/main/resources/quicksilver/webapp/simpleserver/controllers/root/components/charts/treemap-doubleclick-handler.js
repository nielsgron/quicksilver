var clickCounter = 0;
var timer = null;
var timerURL = null;

targetName.on('plotly_treemapclick', function (data) {
    var key = data.points[0].id;
    var data = data.points[0].data;
    var index = data.ids.indexOf(key);
    var targetURL = data.ids[index];
    //console.log(key + "-" + targetURL);
    //console.log(data);
    clickCounter++;
    if (clickCounter == 2) {
        if (targetURL != timerURL) {
            //restart counter for this url/cell which will also clear previous timer out
            clickCounter = 1;
        }
    }
    timerURL = targetURL;
    if (clickCounter == 1) {
        if (timer != null) {
            clearTimeout(timer);
        }
        if (timerURL === undefined || timerURL == '') {
            clickCounter = 0;
            return;
        }
        timer = setTimeout(function () {
            if (clickCounter == 1) {
                window.location = 'quote?ticker=' + timerURL;
                //window.open('quote?ticker='+timerURL, '_blank');
                //window.event.preventDefault();
                //window.event.stopPropagation();
            }
            clickCounter = 0;
        }, 500 /*ms*/);
    }
});