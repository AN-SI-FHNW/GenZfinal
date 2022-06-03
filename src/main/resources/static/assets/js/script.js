/*
* Authors: Andrea Alex Simonek, Carla Kaufmann, Moana Kleiner, Kevin Pini
* Date: 03.06.2022
* Inspired by Documentation of Andreas Martin (Lecturer FHNW): https://github.com/DigiPR/acrm-sandbox
*/

$('#confirm-modal').on('show.bs.modal', function(e) {
    $(this).find('#deleteData').attr('data-id', $(e.relatedTarget).data('id'));
});