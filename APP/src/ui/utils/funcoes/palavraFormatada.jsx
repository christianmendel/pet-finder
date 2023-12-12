export function PalavraFormatada(palara) {
  return palara.toLowerCase().replace(/(?:^|\s)\S/g, function (a) {
    return a.toUpperCase();
  });
}
