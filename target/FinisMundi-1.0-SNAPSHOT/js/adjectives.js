const d1Endings = ["ae", "ae", "am", "ā", "a", "ae", "ārum", "īs", "ās", "īs", "ae"];
const d2MascREndings = ["ī", "ō", "um", "ō", "", "ī", "ōrum", "īs", "ōs", "īs", "ī"];
const d2MascUSEndings = ["ī", "ō", "um", "ō", "e", "ī", "ōrum", "īs", "ōs", "īs", "ī"];
const d2NeutEndings = ["ī", "ō", "um", "ō", "um", "a", "ōrum", "īs", "a", "īs", "a"];
const d3MFEndings = ["is", "ī", "em", "e", "ēs", "um", "ibus", "ēs", "ibus", "ēs"];
const d3NeutEndings = ["is", "ī", "", "e", "a", "um", "ibus", "a", "ibus", "a"];
const d3IMFEndings = ["is", "ī", "em", "ī", "", "ēs", "ium", "ibus", "ēs", "ibus", "ēs"];
const d3INeutEndings = ["is", "ī", "", "ī", "", "ia", "ium", "ibus", "ia", "ibus", "ia"];

function declensionArray (mascNom, femNom, neutNom, decl) {
  var mascEndings = [];
  var femEndings = [];
  var neutEndings = [];
  var forms = [mascNom, femNom, neutNom];
  var stem, len
  var femLength = femNom.length;
  var mascLength = mascNom.length;

  if (decl === "I-II") {
    len = femLength - 1;
    stem = femNom.substring(0,len);
  } else if (decl === "III") {
    switch (femNom.substring(femLength - 2)) {
      case "is":
        len = femLength - 2;
        stem = femNom.substring(0, len);
        break;
      case "ns":
        len = femLength - 1;
        stem = femNom.substring(0, len) + "t";
        break;
      case "ex":
        len = femLength - 2;
        stem = femNom.substring(0, len) + "ic";
        break;
      case "āx":
        len = femLength - 1;
        stem = femNom.substring(0, len) + "c";
        break;
      case "ōx":
        len = femLength - 1;
        stem = femNom.substring(0, len) + "c";
      case "us":
        len = femLength - 2;
        stem = femNom.substring(0, len) + "er";
    }
  }


  switch (decl) {
    case "I-II":
      if (mascNom.substring(mascNom.length - 2) === "us") {
        mascEndings = d2MascUSEndings;
      } else {
        mascEndings = d2MascREndings;
      }
      femEndings = d1Endings;
      neutEndings = d2NeutEndings;
      break;
    case "III":
        mascEndings = d3IMFEndings;
        femEndings = d3IMFEndings;
        neutEndings = d3INeutEndings;
  }

  for (i = 0; i <= 3; i++) {
    forms.push(stem + mascEndings[i]);
    forms.push(stem + femEndings[i]);
    forms.push(stem + neutEndings[i]);
  }
  forms[11] = forms[2];

  if (mascNom.substring(mascLength - 3) === "ius" || mascNom.substring(mascLength - 3) === "eus") {
    forms.push(mascNom.substring(0, mascLength - 3) + "ī");
  } else if (mascNom.substring(mascLength - 2) === "us") {
    forms.push(stem + "e");
  } else {
    forms.push(mascNom);
  }
  forms.push(femNom);
  forms.push(neutNom);

  for (i = 5; i <= 10; i++) {
    forms.push(stem + mascEndings[i]);
    forms.push(stem + femEndings[i]);
    forms.push(stem + neutEndings[i]);
  }

  return forms;
}

console.log(declensionArray("acer", "acris", "acre", "III"));
