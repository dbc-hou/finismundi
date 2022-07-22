const d1Endings = ["ae", "am", "ā", "ae", "ārum", "īs", "ās", "īs"];
const d2MascREndings = ["ō", "um", "ō", "ī", "ōrum", "īs", "ōs", "īs"];
const d2MascUSEndings = ["ō", "um", "ō", "ī", "ōrum", "īs", "ōs", "īs"];
const d2NeutEndings = ["ō", "um", "ō", "a", "ōrum", "īs", "a", "īs"];
const d3MFEndings = ["ī", "em", "e", "ēs", "um", "ibus", "ēs", "ibus"];
const d3NeutEndings = ["ī", "", "e", "a", "um", "ibus", "a", "ibus"];
const d3IMFEndings = ["ī", "em", "e", "ēs", "ium", "ibus", "ēs", "ibus"];
const d3INeutEndings = ["ī", "", "ī", "ia", "ium", "ibus", "ia", "ibus"];
const d4MFEndings = ["uī", "um", "ū", "ūs", "uum", "ibus", "ūs", "ibus"];
const d4NeutEndings = ["ū", "ū", "ū", "ua", "uum", "ibus", "ua", "ibus"];
const d5LongEndings = ["ēī", "em", "ē", "ēs", "ērum", "ēbus", "ēs", "ēbus"];
const d5ShortEndings = ["eī", "em", "ē", "ēs", "ērum", "ēbus", "ēs", "ēbus"];

function declensionArray (nom, gen, gender, decl) {
  var endings = [];
  var forms = [nom, gen];
  var stem, len;

  if (decl === "II") {
    len = gen.length - 1;
  } else {
    len = gen.length - 2;
  }
  stem = gen.substring(0,len);

  switch (decl) {
    case "I":
      endings = d1Endings;
      break;
    case "II":
      if (gender === "n") {
        endings = d2NeutEndings;
      } else if (nom.substring(0,len - 2) === "us") {
        endings = d2MascUSEndings;
      } else {
        endings = d2MascREndings;
      }
      break;
    case "III":
      if (gender === "n") {
        endings = d3NeutEndings;
      } else {
        endings = d3MFEndings;
      }
      break;
    case "III-i":
      if (gender === "n") {
        endings = d3INeutEndings;
      } else {
        endings = d3IMFEndings;
      }
      break;
    case "IV":
      if (gender === "n") {
        endings = d4NeutEndings;
      } else {
        endings = d4MFEndings;
      }
      break;
    case "V-long":
      endings = d5LongEndings;
      break;
    case "V-short":
      endings = d5ShortEndings;
  }

  for (i = 0; i < 3; i++) {
    forms.push(stem + endings[i]);
  }

  if(gender === "n") {
    forms[3] = forms[0];
  }

  if (decl === "II") {
    if (nom.substring(nom.length - 3) === "ius" || nom.substring(nom.length - 3) === "eus") {
      forms.push(nom.substring(0, nom.length - 3) + "ī");
    } else if (nom.substring(nom.length - 2) === "us") {
      forms.push(stem + "e");
    } else {
      forms.push(nom);
    }
  }
  forms.push(nom);

  for (i = 3; i < endings.length; i++) {
    forms.push(stem + endings[i]);
  }

  forms.push(stem + endings[3]);

  return forms;
}

console.log(declensionArray("honor", "honōris", "m", "III"));
