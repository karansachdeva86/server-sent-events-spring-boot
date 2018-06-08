<!DOCTYPE html>
<html lang="en" class=" js">

  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Demo for Server Sent Events</title>
  <link rel="stylesheet" href="css/base.min.css">
  <style>
.sprite-survey{background-image:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAJcAAACfCAMAAADDErsmAAACFlBMVEVMaXHk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27n////////////////////////k27nk27nk27nk27nk27nk27nk27ns5s3////k27nk27nk27nk27nk27nk27nk27nk27nx7Nn////k27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27n////////k27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27n////////////////////////k27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27n////////////////////////////////////////////k27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27n////////////////////////////////////////////////////////k27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27nk27n////////////////////////////////////////////////////////////////////////////////////w69jk27n////jzwsCAAAAsHRSTlMAwf4/DuYE8fKiHF2VwH/SMfqG+/x/mJ4C7AoU+fwGKQjdYs0kKPblBOgXUrgZONyDNu/wPBCZtf3bFbI6ajlDZrpUmw+k9CTs9A7vEmwh30lQ2LT4eEbP1cZyAS/pfH74BiWSzjQNK+bkiT4RgVaHhslLl2ciq21Ni+PgjaTZiL9TP9TGCSF5IN8YcGBEJnWAyh++klw1qEGhOGyhO6ZDast81TBHlU/yE42bt0pdCyb6UTYAAATiSURBVHja7dz3VxN5EADwoQt4KAKmgZAQWqKUEMCAdCSxUkRBmkbAyt15epa707Niu5Pr/fSq5c7C7n/oSwzJ97slCcuWwfedH+C9Zcsnm2V2ZlIAVhDN6+JGMxgUO7m4sdMoV3Z8VzZzMZcKLvsGQdhxuGqFy2txuNKEy9OYa025euO7enVBNKwXxXB817B4iwb1XQ7ZPCXnkgiH+q5cNVy5zMVcK3V1Clft1MDV8p4oWqyEy5QmiibhPprE65gIl1XqGIlc28UPcnsf4Tqo7OEeJFx9UsdItIMs8TZZ6YQrT5krj3ClSx2DuZiLuda0q6RPXVdfyWpc/d6NkZg0q+syTy7v+YACV2Om5P1RBVcsTApcb68qkWtamWta0pWtnutKjqK4orUrVWFo7VpdMBdzybgcargc6rsOpagQh9R3aTsIZS7mYi7mYi7mYi7mYi7mYi7meqddX5w1Y3S5i7nzW/C5fpziOO5iEzZXRV14t90uXC7XaGS/5U5MLmtblDA2icdlNhFDnamHaFzvU9OmHiyuLvIV2tR2LM+je4Q8W/VYrvtaC8kymZG4nHUka+sSkvxlKydZ5S4k+b7sAMkaWERyfzT3kixLB5Z6op5kVbqx1F9d1Euz+7DUhZcrSdZZLPVqh4dKXBNIXE47lbisSOp720dU4tqGpO8oayNZn1Vg6YeaSZbHi6VPoxPXEJb+8WOqEGzH0tee6ydZH2LptztkKy5DXc4BktVtRTKfECQuF5K5ibWFZNkXscxzzlOJ6wyWOVMeySp2Y5l/JZe41Ha5VlZxndZpXuiu+yH+1jvoxJWpj2vQw43E/YTGfqpV7F7SZ77aGToZJZfkt50/nLDi0sB1xpLgne5WqlW0O/WZRzdUJbgNm5OpuFR3xVgc973k9fw1yRrpAV1cJIvjTFskWsXUuK2iRq4v6feX/SSqEc5RiesT0MkFjmIK1jYvmHF5kqu41L/ue6gSlPvORv7xQsJWUbs8cbmRgqXkEBUXlbhGc0BPF/ycQcE2OGVaxUXQ1wVeGjYW6VUnqMSVtQP0dsHkMAUbqBUP55NLXGrXORVjFKwqlNS7OOnhvJ4uuPANBbMMwuCKKi7N6tVtKRTMM01dc9mZRrnAtlX+bcZJJy4t6vv0i3KsURcY6IIyme9R+LQCDHVB9XopVqMXDHbBxGmJTz99AIa7BGO3cFwCDC4YF3yYqx5wuGCc/k6aaiwu+IpsFZUNDbSZT7THSh4bIHJBbqXSxKXxPGcovLbFC8hc0JmhLHFpPv/qqOPGAaELvh0ClK5VBXMxF3MxF3MxF3OtZdfnRwvy6Sg4emu38a5dt49soqPorz0IXAVHRCv9ff+48a7CovCvhV+XVwnembvpr8bimuFvRhb49/h8gXtIXJv4x8HIgtKae/4aP+la0t71i7SriP99mQWlJ/e2Bu6+IlzzmZqH9Pk6tot/FKWX1vx3/OX9QMyVOpWheVRKn68/Avk3Yq4HACdOSX3fkB5BX18nfPxvGF3Qep2/Fn0eF17cDiBxQfAqf3Uhet3//xqLC+Aaf701fL72tp70Ax4X/Mn7joX+OWdnn9/B5IIbp+YAgv8+eTYXNN5F3rffzmzv+kL37X5jXP3Ldc7szOZoPA39mLkVrnOq1hkSVdG6sLCAjsL8f3YDvAHoEP9nSX6d9gAAAABJRU5ErkJggg==)}
.sprite-survey-icon{width:24px;height:18px;background-position:-127px -141px;margin-bottom:-3px}
.sprite-survey-clip{width:120px;height:159px;float:left;margin-right:15px}
.survey-tab{position:fixed;right:0;bottom:225px;padding:7px 10px;background:#3c9adc;border:5px solid rgba(60,154,220,0.3);background-clip:padding-box;border-radius:5px 5px 0 0;border-bottom:0;transform:rotate(-90deg);transform-origin:right bottom 0;cursor:pointer}
.survey-tab h3{font-size:20px;color:#fff}
.survey-note{position:fixed;right:20px;bottom:20px;max-width:450px}
.survey-note h3{border:0;padding:0;font-size:20px}
.survey-note-buttons{text-align:right}
  </style>
<!--[if lt IE 9]>
  <script src="/static/r148/js/html5shiv.min.js"></script>
<![endif]-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript">
var files = [];
$(document)
        .on(
                "change",
                "#fileLoader",
                function(event) {
                 files=event.target.files;
                })

$(document)
        .on(
                "click",
                "#fileSubmit",
                function() {
                processUpload();
                })

function processUpload()
          {
              var oMyForm = new FormData();
              oMyForm.append("file", files[0]);
             $
                .ajax({dataType : 'json',
                    url : "upload",
                    data : oMyForm,
                    type : "POST",
                    enctype: 'multipart/form-data',
                    processData: false,
                    contentType:false,
                    success : function(result) {
                        //...;

                        //alert(result);
                        var response = $.parseJSON(JSON.stringify(result));
                       // alert();


                       $('#dynamic_text').text(response['msg']);
                      //  document.getElementById("dynamic_text").innerHTML += response['msg'];
                        waitForResponseReady(response['id']);
                    },
                    error : function(result){
                        //...;
                    }
                });
          }

function waitForResponseReady(idVal){
    if(typeof(EventSource) !== "undefined") {
        var source = new EventSource("/listen-for-response?id="+idVal);
        source.addEventListener('error', function(e) {
            if (e.currentTarget.readyState == EventSource.CLOSED) {
                // Connection was closed.
            } else {
                // Close it yourself
                source.close();
            }
        });
        source.onmessage = function(event) {
           // document.getElementById("dynamic_text").innerHTML += event.data + " - ";
            $('#dynamic_text').text(event.data);
            var requestId = (event.data).split(':')[1];

            $(location).attr('href', '/show-file-details?id='+ requestId);


        };
    } else {
        document.getElementById("dynamic_text").innerHTML =
            "Your browser does not support server-sent events.";
    }
}

</script>
</head>
<body class="page-home">
  <div class="page-wrapper">
    <noscript>
      &lt;div class="no-script-info"&gt;
        &lt;div class="box box-info"&gt;
          &lt;h3&gt;You must have JavaScript enabled to use GTmetrix&lt;/h3&gt;
          &lt;p&gt;GTmetrix requires JavaScript to function properly.  Please enable JavaScript in your browser and refresh the page to ensure the best GTmetrix experience.&lt;/p&gt;
        &lt;/div&gt;
      &lt;/div&gt;
    </noscript>


    <main class="clear page-home-content layout-zebra">
<article>
  <section class="layout-zebra-section home-section-analyze">
    <div class="layout-zebra-section-content">
      <h1>Demo for Server Sent Events</h1>
      <h2>Upload a file below</h2>

      <p1 id="dynamic_text"></p1>
<form method="POST" enctype="multipart/form-data" action="/reandroid/upload" class="analyze-form js-form-analyze" novalidate="novalidate">
  <div class="analyze-form-inputs">
    <div class="analyze-form-input"><div class="clearable-input">
    <!--<input type="file" name="file" value="" class="js-aanalyze-form-url" placeholder="Select APK File..." maxlength="1048" autofocus="" required="" aria-required="true" style="padding-right: 42.4px;"><button class="clearable-input-button hide" type="button" tabindex="-1" style="line-height: 53px; width: 53px; font-size: 34.125px;"><span>×</span></button></div></div>-->

    <input type="file" name="file" id="fileLoader" />
    <input type="button" id="fileSubmit" value="Upload"/>


    <!--<div class="analyze-form-button"><button type="button">Submit</button></div>-->
  </div>
</form>
</div>
  </section>




<!-- Mirrored from gtmetrix.com/ by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 13 Mar 2017 10:06:02 GMT -->

<div class="menu-overlay"></div>

</body>

</html>