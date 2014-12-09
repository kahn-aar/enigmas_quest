$( document ).ready(function() {
    //Adresse du web service
    var url = "http://localhost:8080/EnigmaRest/rest/enigma/";

	//Affichage de la liste des joueurs
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

    //details joueur, au clique sur un joueur dans la liste
    $('body').on('click', '.joueur', function () {
        //clean
        $('.titre-joueur').remove();
        $('.itemStat').remove();
        $('.pourcentBon').remove();

        //titre joueur
        var loginJ = $(this).attr('id');
        $('#statjoueur').append("<h2 class=\"titre-joueur\">"+loginJ+"</h2>");

        //pourcentage de bonne réponse
        $.ajax({
            url: url+"stat",
            data: { login: loginJ },
            dataType: 'xml',
            type: "GET",
            success: function(xml){
                var pourcentageBon = $(xml).find('pourcentQuestion').text();
                $('#statjoueur').append('<div class=\"pourcentBon\"><p>Pourcentage de bonne reponse : '+pourcentageBon+' %</p></div>');
            },
            error: function(xhr, msg){
                console.log("error");
            }
        });

        //détails des réponses aux questions
        $.ajax({
            url: url+"details",
            data: { login: loginJ },
            dataType: 'xml',
            type: "GET",
            success: function(xml){
                $(xml).find('reponse').each(function(){
                    var question = $(this).find('question').text();
                    var reponseJ = $(this).find('reponseJoueur').text();
                    var solution = $(this).find('solution').text();
                    var juste = $(this).find('juste').text();
                    if(juste == 'true'){
                        $('#statjoueur').append('<div class=\"itemStat\"><h3>'+question+'</h3><p class=\"reponse-juste\">'+reponseJ+'</p></div>');
                    }else{
                        $('#statjoueur').append('<div class=\"itemStat\"><h3>'+question+'</h3><p class=\"reponse-fausse\">'+reponseJ+'</p><p>Solution : '+solution+'</p></div>');

                    }
                });
            },
            error: function(xhr, msg){
                console.log("error");
            }
        });
    });

});