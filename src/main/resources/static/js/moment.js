"use strict";

// const dates = document.querySelectorAll(".createdAt");
// for (let date of dates) {
//     date.innerText = moment(date.innerText).format("LLLL");
// }

$(function(){
    function formatDate(dates) {
        dates.each(function(){
            //get date
            let formattedDate = $(this).text();

            //format it
            let d = moment(formattedDate, "YYYY-MM-DDTHH:mm:ss");

            //replace it
            $(this).html(d.format("dddd, MMMM Do YYYY, h:mm:ss a"));
        });
    };

    formatDate($('.createdAt'));
});