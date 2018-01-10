var moneyInMachine = 0;
var quarters = 0;
var dimes = 0;
var nickels = 0;
var pennies = 0;

$(document).ready(function () {
    moneyInMachine = 0;
    loadInventory();
    updateMoneyInMachine();

    $('#dollar-button').click(function (event) { 
        moneyInMachine += 1;
        quarters += 4;
        updateMoneyInMachine();
        makeChange();
    });

    $('#quarter-button').click(function (event) {
        moneyInMachine += .25;
        quarters += 1;
        updateMoneyInMachine();
        makeChange();
    });

    $('#dime-button').click(function (event) {
        moneyInMachine += .1;
        dimes += 1;
        updateMoneyInMachine();
        makeChange();
    });

    $('#nickel-button').click(function (event) {
        moneyInMachine += .05;
        nickels += 1
        updateMoneyInMachine();
        makeChange();
    });

    $('#make-purchase-button').click(function (event) {
        var test = $('#item-box').find('input');
        var haveValidationErrors = checkAndDisplayValidationErrors($('#item-box').find('input'));
        if (haveValidationErrors) {
            return false;
        }
        makePurchase();

    });


    $('#change-button').click(function (event) {
        loadInventory();
         moneyInMachine = 0;
         updateMoneyInMachine();
        $('#item-box').val('');
        quarters = 0;
        dimes = 0;
        nickels = 0;
        pennies = 0;
        makeChange();

    });

});



function loadInventory() {
    $('#vendingItems').empty();
    $('#errorMessage').empty();
    $('.messageBox').empty();

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/ContactListSpringMVC/items',
        success: function (data, status) {
            var count = 0;
            var newDivID;
            var idName;
            var newSpanID;
            var newP1ID;
            var newP2ID;
            var newP3ID;
            var number;
            var thisItem;
            var thisPrice;
            var thisQuantity;
            $.each(data, function (index, item) {
                number = item.id;
                thisItem = item.name;
                thisPrice = item.price;
                thisQuantity = item.quantity

                newDivID = 'DIV' + number;
                idName = '#' + newDivID;
                $('#vendingItems').append('<div class="vendItem square col-md-4" id=' + newDivID + '></div>');

                newSpanID = 'SPAN' + number;
                $(idName).append('<span class="itemNumber" id=' + newSpanID + '>' + number + '</span> <br> <br>');

                newP1ID = 'P1' + number;
                $(idName).append('<p class="itemName" id=' + newP1ID + '>' + thisItem + '</p>');

                newP2ID = 'P2' + number;
                $(idName).append('<p class="itemPrice" id=' + newP2ID + '>$' + thisPrice.toFixed(2) + '</p>');

                newP3ID = 'P3' + number;
                $(idName).append('<p class="itemQuantity" id=' + newP3ID + '>Quantity Left: ' + thisQuantity + '</p>');

                count++;
            });

            $('.vendItem').click(function () {
                var test = $(this).children('.itemNumber').text();
                $('#item-box').val(test);
            })
            $('#item-box').attr(max = 'count');
        },
        error: function () {
            $('#errorMessages').append($('<li>').attr({ class: 'list-group-item list-group-item-danger' })
                .text('Error calling web service. Please try again later.'));
        }
    })
};


function updateMoneyInMachine() {
    moneyToDisplay = moneyInMachine.toFixed(2);
    $("div.currentBalance").replaceWith('<div id="money-in-machine" class="currentBalance">' + moneyToDisplay + '<div>');
};

function makePurchase() {
    $('#errorMessage').empty();
    $('.messageBox').empty();

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/ContactListSpringMVC/money/' + moneyInMachine + '/item/' + $('#item-box').val(),
        success: function (data, status) {


            quarters = data.quarters;
            dimes = data.dimes;
            nickels = data.nickels;
            pennies = data.pennies;

            moneyInMachine = (.25*quarters) + (.1*dimes) + (.05*nickels) + (.01*pennies);

            $('#vendingItems').empty();
            loadInventory();
            makeChange();
            updateMoneyInMachine();
            $('.messageBox').append($('<p>').attr({ class: 'blink' })
                .text('Thank You!!!'));


        },
        error: function (message) {
            if (message.status != 500 && message.status != 404){
            $('.messageBox').append($('<p>').attr({ class: 'blink' })
                .text(message.responseJSON.message));
            }
        },
        statusCode:{
        500: function(){
            $('.messageBox').append($('<p>').attr({ class: 'blink' })
                .text('Not an Item'));
        },
        404: function(){
            $('.messageBox').append($('<p>').attr({ class: 'blink' })
                .text('Make A Selection'));
        }}
    });
};

function makeChange() {
    $('.change-box').empty();
    if (quarters != 0) {
        $('.change-box').append('Quarters: ' + quarters + '<br>');
    }
    if (dimes != 0) {
        $('.change-box').append('  Dimes: ' + dimes + '<br>');
    }
    if (nickels != 0) {
        $('.change-box').append('  Nickels: ' + nickels + '<br>');
    }
    if (pennies != 0) {
        $('.change-box').append('  Pennies: ' + pennies + '<br>');
    }
};



function checkAndDisplayValidationErrors(input) {
    $('#errorMessages').empty();

    var errorMessages = [];
    input.each(function () {
        if (!this.validity.valid) {
            var errorField = $('label[for=' + this.id + ']').text();
            errorMessages.push(errorField + ' ' + this.validationMessage);
        };
    });

    if (errorMessages.length > 0) {
        $.each(errorMessages, function (index, message) {
            $('#errorMessages').append($('<li>').attr({ class: 'list-group-item list-group-item-danger' }).text(message));
        });
        return true;
    } else {
        return false;
    }

};












function originalItemProcessingProcess() {
    var newDivID;
    var idName;
    var newSpanID;
    var spanIdName;
    var newP1ID;
    var p1IdName;
    var newP2ID;
    var p2IdName;
    var newP3ID;
    var p3IdName;
    var number;
    var thisItem;
    var thisPrice;
    var thisQuantity;
    $.each(data, function (index, item) {
        number = item.id;
        thisItem = item.name;
        thisPrice = item.price;
        thisQuantity = item.quantity

        $('#vendingItems').append('<div class="square col-md-4" id="temp"></div>');
        newDivID = 'DIV' + number;
        $('#temp').attr("id", newDivID);
        var idName = '#' + newDivID;

        $(idName).append('<span class="itemNumber" id="temp"></span> <br>');
        newSpanID = 'SPAN' + number;
        spanIdName = '#' + newSpanID;
        $('#temp').attr("id", newSpanID);
        $(spanIdName).append(number);

        $(idName).append('<p class="itemName" id="temp"></p>');
        newP1ID = 'P1' + number;
        p1IdName = '#' + newP1ID;
        $('#temp').attr("id", newP1ID);
        $(p1IdName).append(thisItem);

        $(idName).append('<p class="itemPrice" id="temp">$</p>');
        newP2ID = 'P2' + number;
        p2IdName = '#' + newP2ID;
        $('#temp').attr("id", newP2ID);
        $(p2IdName).append(thisPrice.toFixed(2));

        $(idName).append('<p class="itemQuantity" id="temp">Quantity Left: </p>');
        newP3ID = 'P3' + number;
        p3IdName = '#' + newP3ID;
        $('#temp').attr("id", newP3ID);
        $(p3IdName).append(thisQuantity);


    });
}
