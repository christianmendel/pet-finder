export function DataFormatada(data) {
  return data.split("-").reverse().toString().replaceAll(",", "/");
}

export function DataEHoraFormatada(data) {
  return data
    .split(".")
    .reverse()
    .pop()
    .replace("T", "-")
    .split("-")
    .reverse()
    .toString()
    .replaceAll(",", "/")
    .replace("/", " ");
}