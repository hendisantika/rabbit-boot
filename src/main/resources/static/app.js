(function app() {

  function msg(message) {
    return { message: message };
  }

  $(document).ready(function ready() {

    var base = $('meta[name=base]').attr("content");

    $('#send-message-to-all-form').on('submit', function submitToAllForm(event) {
      event.preventDefault();

      var $input = $(this).find('input[name=message]');

      $.post(base + '/message/send', msg($input.val()))
        .always(function alwaysToAll() {
          $input.val('');
        })
        .fail(function failToAll(data) {
          console.info(data.statusText, data.status);
        });
    });

    $('#send-message-to-form').on('submit', function submitToForm(event) {
      event.preventDefault();

      var $input = $(this).find('input[name=message]');
      var $dst = $(this).find('input[name=dst]');
      var destination = $dst.val() || 'all';

      $.post(base + '/message/send/to/' + destination, msg($input.val()))
        .always(function alwaysTo() {
          $input.val('');
          $dst.val('');
        })
        .fail(function failTo(data) {
          console.info(data.statusText, data.status, data);
        });
    });

  });

})();
