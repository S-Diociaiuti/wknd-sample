$(function(){
    function myFunction() {
        $("div").css("background-color",'#' + Math.floor(Math.random()*16777215).toString(16));
    }
    $(".quellochevuoi").on("click", myFunction);

    $('#mandastaservlet').on('click', function(){
        let url = location.href.replaceAll('.html', '.mysampleservlet.json');
        let data = {"firstName" : $('.firstName').val(), "lastName" : $('.lastName').val(), "email" : $('.email').val()};
        let $servletResponse = $(".servlet-response");
        $.post(url, data, function(response){
            if(response){
                $servletResponse.find('.firstName').html(response.firstName);
                $servletResponse.find('.lastName').html(response.lastName);
                $servletResponse.find('.email').html(response.email);
            } else {
                alert("ha risposto male");
            }
        }).fail(function() {
            alert( "error" );
        })
  });
  let url2 = location.href.replaceAll('.html', '.searchutilsservlet.json');
  let data2 = {"path" : "/content/wknd/language-masters", "propertyName" : "cq:template", "propertyValue" : "/conf/wknd/settings/wcm/templates/landing-page-template", "isXPath" : "false"};
  $.post(url2, data2, function(response) {
    console.log("Result: ", response);
  });
});