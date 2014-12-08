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
    $(".joueur").click(function(){
        var loginJ = $(this).attr('id');

        $.ajax({
            url: url+"details",
            data: { login: loginJ },
            dataType: 'xml',
            type: "GET",
            success: function(xml){
                $(xml).find('question').each(function(){
                    console.log($(this));
                    var question = $(this).find('question').text();
                    console.log(question);
                });
            },
            error: function(xhr, msg){
                console.log("error");
            }
        });
    });

});