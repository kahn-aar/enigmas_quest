$( document ).ready(function() {
    //Adresse du web service
    var url = "http://localhost:8080/EnigmaRest/rest/enigma/";

	//Liste des joueurs
	$.ajax({
        url: url+"player",
        dataType: 'xml',
        type: "GET",
        success: function(xml){
        	$(xml).find('player').each(function(){
                var login = $(this).find('login').text();
                $('#listeJoueur ul').append("<li class=\"joueur\" id=\""+login+"\">"+login+"</li>");
            });
        },
        error: function(xhr, msg){
        	console.log("error");
        }
    });

    //details joueur
    $('body').on('click', '.joueur', function () {
         var loginJ = $(this).attr('id');
         $('#statjoueur').append("<h2>"+loginJ+"</h2>");

        $.ajax({
            url: url+"details",
            data: { login: loginJ },
            dataType: 'xml',
            type: "GET",
            success: function(xml){
                $(xml).find('question').each(function(){
                    var question = $(this).find('question').text();
                    var reponse = $(this).find('reponse').text();
                    $('#statjoueur').append("<div class=\"questionJoueur\"><h3>"+question+"</h3><p>"+reponse+"</p></div>");
                });
            },
            error: function(xhr, msg){
                console.log("error");
            }
        });
    });

});