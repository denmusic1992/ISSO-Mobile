package com.ais.admin.isso_s;


import android.content.Context;

public class TimeZoneMapper {

    public Context context;

    public TimeZoneMapper(Context context) {
        this.context = context;
    }

    public  String latLngToTimezoneString(double lat, double lng)
    {
        return timezoneStrings[kdLookupRoot(lat,lng)];
    }

	private  String[] timezoneStrings = {
	"unknown",
	"America/Dominica",
	"America/St_Vincent",
	"Australia/Lord_Howe",
	"Asia/Kashgar",
	"Pacific/Wallis",
	"Europe/Berlin",
	"America/Manaus",
	"Asia/Jerusalem",
	"America/Phoenix",
	"Australia/Darwin",
	"Asia/Seoul",
	"Africa/Gaborone",
	"Indian/Chagos",
	"America/Argentina/Mendoza",
	"Asia/Hong_Kong",
	"America/Godthab",
	"Africa/Dar_es_Salaam",
	"Pacific/Majuro",
	"America/Port-au-Prince",
	"America/Montreal",
	"Atlantic/Reykjavik",
	"America/Panama",
	"America/Sitka",
	"Asia/Ho_Chi_Minh",
	"America/Danmarkshavn",
	"Asia/Jakarta",
	"America/Boise",
	"Asia/Baghdad",
	"Africa/El_Aaiun",
	"Europe/Zagreb",
	"America/Santiago",
	"America/Merida",
	"Africa/Nouakchott",
	"America/Bahia_Banderas",
	"Australia/Perth",
	"Asia/Sakhalin",
	"Asia/Vladivostok",
	"Africa/Bissau",
	"America/Los_Angeles",
	"Asia/Rangoon",
	"America/Belize",
	"Asia/Harbin",
	"Australia/Currie",
	"Pacific/Pago_Pago",
	"America/Vancouver",
	"Asia/Magadan",
	"Asia/Tbilisi",
	"Asia/Yerevan",
	"Europe/Tallinn",
	"Pacific/Johnston",
	"Asia/Baku",
	"America/North_Dakota/New_Salem",
	"Europe/Vilnius",
	"America/Indiana/Petersburg",
	"Asia/Tehran",
	"America/Inuvik",
	"Europe/Lisbon",
	"Europe/Vatican",
	"Pacific/Chatham",
	"Antarctica/Macquarie",
	"America/Araguaina",
	"Asia/Thimphu",
	"Atlantic/Madeira",
	"America/Coral_Harbour",
	"Pacific/Funafuti",
	"Indian/Mahe",
	"Australia/Adelaide",
	"Africa/Freetown",
	"Atlantic/South_Georgia",
	"Africa/Accra",
	"America/North_Dakota/Beulah",
	"America/Jamaica",
	"America/Scoresbysund",
	"America/Swift_Current",
	"Europe/Tirane",
	"Asia/Ashgabat",
	"America/Moncton",
	"Europe/Vaduz",
	"Australia/Eucla",
	"America/Montserrat",
	"America/Glace_Bay",
	"Atlantic/Stanley",
	"Africa/Bujumbura",
	"Africa/Porto-Novo",
	"America/Argentina/Rio_Gallegos",
	"America/Grenada",
	"Asia/Novokuznetsk",
	"America/Argentina/Catamarca",
	"America/Indiana/Indianapolis",
	"America/Indiana/Tell_City",
	"America/Curacao",
	"America/Miquelon",
	"America/Detroit",
	"America/Menominee",
	"Asia/Novosibirsk",
	"Africa/Lagos",
	"Indian/Cocos",
	"America/Yakutat",
	"Europe/Volgograd",
	"Asia/Qatar",
	"Indian/Antananarivo",
	"Pacific/Marquesas",
	"America/Grand_Turk",
	"Asia/Khandyga",
	"America/North_Dakota/Center",
	"Pacific/Guam",
	"Pacific/Pitcairn",
	"America/Cambridge_Bay",
	"Asia/Bahrain",
	"America/Kentucky/Monticello",
	"Arctic/Longyearbyen",
	"Africa/Cairo",
	"Australia/Hobart",
	"Pacific/Galapagos",
	"Asia/Oral",
	"America/Dawson_Creek",
	"Africa/Mbabane",
	"America/Halifax",
	"Pacific/Tongatapu",
	"Asia/Aqtau",
	"Asia/Hovd",
	"uninhabited",
	"Africa/Nairobi",
	"Asia/Ulaanbaatar",
	"Indian/Christmas",
	"Asia/Taipei",
	"Australia/Melbourne",
	"America/Argentina/Salta",
	"Australia/Broken_Hill",
	"America/Argentina/Tucuman",
	"America/Kentucky/Louisville",
	"Asia/Jayapura",
	"Asia/Macau",
	"America/Ojinaga",
	"America/Nome",
	"Pacific/Wake",
	"Europe/Andorra",
	"America/Iqaluit",
	"America/Kralendijk",
	"Europe/Jersey",
	"Asia/Ust-Nera",
	"Asia/Yakutsk",
	"America/Yellowknife",
	"America/Fortaleza",
	"Asia/Irkutsk",
	"America/Tegucigalpa",
	"Europe/Zaporozhye",
	"Pacific/Fiji",
	"Pacific/Tarawa",
	"Africa/Asmara",
	"Asia/Dhaka",
	"Asia/Pyongyang",
	"Europe/Athens",
	"America/Resolute",
	"Africa/Brazzaville",
	"Africa/Libreville",
	"Atlantic/St_Helena",
	"Europe/Samara",
	"America/Adak",
	"America/Argentina/Jujuy",
	"America/Chicago",
	"Africa/Sao_Tome",
	"Europe/Bratislava",
	"Asia/Riyadh",
	"America/Lima",
	"America/New_York",
	"America/Pangnirtung",
	"Asia/Samarkand",
	"America/Port_of_Spain",
	"Africa/Johannesburg",
	"Pacific/Port_Moresby",
	"America/Bahia",
	"Europe/Zurich",
	"America/St_Barthelemy",
	"Asia/Nicosia",
	"Europe/Kaliningrad",
	"America/Anguilla",
	"Europe/Ljubljana",
	"Asia/Yekaterinburg",
	"Africa/Kampala",
	"America/Rio_Branco",
	"Africa/Bamako",
	"America/Goose_Bay",
	"Europe/Moscow",
	"Africa/Conakry",
	"America/Chihuahua",
	"Europe/Warsaw",
	"Pacific/Palau",
	"Europe/Mariehamn",
	"Africa/Windhoek",
	"America/La_Paz",
	"America/Recife",
	"America/Mexico_City",
	"Asia/Amman",
	"America/Tijuana",
	"America/Metlakatla",
	"Pacific/Midway",
	"Europe/Simferopol",
	"Europe/Budapest",
	"Pacific/Apia",
	"America/Paramaribo",
	"Africa/Malabo",
	"Africa/Ndjamena",
	"Asia/Choibalsan",
	"America/Antigua",
	"Europe/Istanbul",
	"Africa/Blantyre",
	"Australia/Sydney",
	"Asia/Dushanbe",
	"Europe/Belgrade",
	"Asia/Karachi",
	"Europe/Luxembourg",
	"Europe/Podgorica",
	"Australia/Lindeman",
	"Africa/Bangui",
	"Asia/Aden",
	"Pacific/Chuuk",
	"Asia/Brunei",
	"Indian/Comoro",
	"America/Asuncion",
	"Europe/Prague",
	"America/Cayman",
	"Pacific/Pohnpei",
	"America/Atikokan",
	"Pacific/Norfolk",
	"Africa/Dakar",
	"America/Argentina/Buenos_Aires",
	"America/Edmonton",
	"America/Barbados",
	"America/Santo_Domingo",
	"Asia/Bishkek",
	"Asia/Kuwait",
	"Pacific/Efate",
	"Indian/Mauritius",
	"America/Aruba",
	"Australia/Brisbane",
	"Indian/Kerguelen",
	"Pacific/Kiritimati",
	"America/Toronto",
	"Asia/Qyzylorda",
	"Asia/Aqtobe",
	"America/Eirunepe",
	"Europe/Isle_of_Man",
	"America/Blanc-Sablon",
	"Pacific/Honolulu",
	"America/Montevideo",
	"Asia/Tashkent",
	"Pacific/Kosrae",
	"America/Indiana/Winamac",
	"America/Argentina/La_Rioja",
	"Africa/Mogadishu",
	"Asia/Phnom_Penh",
	"Africa/Banjul",
	"America/Creston",
	"Europe/Brussels",
	"Asia/Gaza",
	"Atlantic/Bermuda",
	"America/Indiana/Knox",
	"America/El_Salvador",
	"America/Managua",
	"Africa/Niamey",
	"Europe/Monaco",
	"Africa/Ouagadougou",
	"Pacific/Easter",
	"Atlantic/Canary",
	"Asia/Vientiane",
	"Europe/Bucharest",
	"Africa/Lusaka",
	"Asia/Kathmandu",
	"Africa/Harare",
	"Asia/Bangkok",
	"Europe/Rome",
	"Africa/Lome",
	"America/Denver",
	"Indian/Reunion",
	"Europe/Kiev",
	"Europe/Vienna",
	"America/Guadeloupe",
	"America/Argentina/Cordoba",
	"Asia/Manila",
	"Asia/Tokyo",
	"America/Nassau",
	"Pacific/Enderbury",
	"Atlantic/Azores",
	"America/Winnipeg",
	"Europe/Dublin",
	"Asia/Kuching",
	"America/Argentina/Ushuaia",
	"Asia/Colombo",
	"Asia/Krasnoyarsk",
	"America/St_Johns",
	"Asia/Shanghai",
	"Pacific/Kwajalein",
	"Africa/Kigali",
	"Europe/Chisinau",
	"America/Noronha",
	"Europe/Guernsey",
	"Europe/Paris",
	"America/Guyana",
	"Africa/Luanda",
	"Africa/Abidjan",
	"America/Tortola",
	"Europe/Malta",
	"Europe/London",
	"Pacific/Guadalcanal",
	"Pacific/Gambier",
	"America/Thule",
	"America/Rankin_Inlet",
	"America/Regina",
	"America/Indiana/Vincennes",
	"America/Santarem",
	"Africa/Djibouti",
	"Pacific/Tahiti",
	"Europe/San_Marino",
	"America/Argentina/San_Luis",
	"Africa/Ceuta",
	"Asia/Singapore",
	"America/Campo_Grande",
	"Africa/Tunis",
	"Europe/Copenhagen",
	"Asia/Pontianak",
	"Asia/Dubai",
	"Africa/Khartoum",
	"Europe/Helsinki",
	"America/Whitehorse",
	"America/Maceio",
	"Africa/Douala",
	"Asia/Kuala_Lumpur",
	"America/Martinique",
	"America/Sao_Paulo",
	"America/Dawson",
	"Africa/Kinshasa",
	"Europe/Riga",
	"Africa/Tripoli",
	"Europe/Madrid",
	"America/Nipigon",
	"Pacific/Fakaofo",
	"Europe/Skopje",
	"America/St_Thomas",
	"Africa/Maseru",
	"Europe/Sofia",
	"America/Porto_Velho",
	"America/St_Kitts",
	"Africa/Casablanca",
	"Asia/Hebron",
	"Asia/Dili",
	"America/Argentina/San_Juan",
	"Asia/Almaty",
	"Europe/Sarajevo",
	"America/Boa_Vista",
	"Africa/Addis_Ababa",
	"Indian/Mayotte",
	"Africa/Lubumbashi",
	"Atlantic/Cape_Verde",
	"America/Lower_Princes",
	"Europe/Oslo",
	"Africa/Monrovia",
	"Asia/Muscat",
	"America/Thunder_Bay",
	"America/Juneau",
	"Pacific/Rarotonga",
	"Atlantic/Faroe",
	"America/Cayenne",
	"America/Cuiaba",
	"Africa/Maputo",
	"Asia/Anadyr",
	"Asia/Kabul",
	"America/Santa_Isabel",
	"Asia/Damascus",
	"Pacific/Noumea",
	"America/Anchorage",
	"Asia/Kolkata",
	"Pacific/Niue",
	"Asia/Kamchatka",
	"America/Matamoros",
	"Europe/Stockholm",
	"America/Havana",
	"Pacific/Auckland",
	"America/Rainy_River",
	"Asia/Omsk",
	"Africa/Algiers",
	"America/Guayaquil",
	"Indian/Maldives",
	"Asia/Makassar",
	"America/Monterrey",
	"Europe/Amsterdam",
	"America/St_Lucia",
	"Europe/Uzhgorod",
	"America/Indiana/Marengo",
	"Pacific/Saipan",
	"America/Bogota",
	"America/Indiana/Vevay",
	"America/Guatemala",
	"America/Puerto_Rico",
	"America/Marigot",
	"Africa/Juba",
	"America/Costa_Rica",
	"America/Caracas",
	"Pacific/Nauru",
	"Europe/Minsk",
	"America/Belem",
	"America/Cancun",
	"America/Hermosillo",
	"Asia/Chongqing",
	"Asia/Beirut",
	"Europe/Gibraltar",
	"Asia/Urumqi",
	"America/Mazatlan"
	};

	private  int kdLookup0(double lat, double lng)
	{
	 if (lng < -168.75) {
	  if (lat < 56.25) {
	   if (lng < -174.5) {
	    return 159;
	   } else {
	    if (lat < 50.5) {
	     return 0;
	    } else {
	     if (lng < -171.75) {
	      return 0;
	     } else {
	      if (lat < 53.25) {
	       if (lng < -170.25) {
	        return 159;
	       } else {
	        if (lat < 51.75) {
	         return 0;
	        } else {
	         if (lng < -169.5) {
	          return 159;
	         } else {
	          if (lat < 52.5) {
	           return 0;
	          } else {
	           if (lng < -169.25) {
	            return 159;
	           } else {
	            return 135;
	           }
	          }
	         }
	        }
	       }
	      } else {
	       return 159;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < -174.5) {
	    return 366;
	   } else {
	    if (lat < 61.75) {
	     return 135;
	    } else {
	     if (lng < -171.75) {
	      if (lat < 64.5) {
	       if (lng < -173.25) {
	        return 366;
	       } else {
	        if (lat < 63.0) {
	         return 0;
	        } else {
	         if (lng < -172.5) {
	          return 366;
	         } else {
	          if (lat < 63.75) {
	           return 135;
	          } else {
	           return 366;
	          }
	         }
	        }
	       }
	      } else {
	       return 366;
	      }
	     } else {
	      if (lat < 64.5) {
	       return 135;
	      } else {
	       return 366;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 56.25) {
	   if (lng < -163.25) {
	    return 135;
	   } else {
	    if (lat < 50.5) {
	     return 0;
	    } else {
	     if (lng < -160.5) {
	      if (lat < 53.25) {
	       return 0;
	      } else {
	       if (lat < 54.75) {
	        return 135;
	       } else {
	        if (lng < -162.0) {
	         return 135;
	        } else {
	         return 371;
	        }
	       }
	      }
	     } else {
	      return 371;
	     }
	    }
	   }
	  } else {
	   if (lng < -163.25) {
	    return 135;
	   } else {
	    if (lat < 61.75) {
	     if (lng < -161.75) {
	      if (lat < 59.0) {
	       return 371;
	      } else {
	       if (lat < 60.25) {
	        if (lng < -162.25) {
	         return 135;
	        } else {
	         return 371;
	        }
	       } else {
	        if (lng < -162.0) {
	         return 135;
	        } else {
	         return 371;
	        }
	       }
	      }
	     } else {
	      return 371;
	     }
	    } else {
	     if (lng < -161.0) {
	      if (lat < 64.5) {
	       if (lat < 63.0) {
	        if (lng < -162.0) {
	         return 135;
	        } else {
	         return 371;
	        }
	       } else {
	        if (lng < -162.25) {
	         return 135;
	        } else {
	         if (lat < 63.75) {
	          if (lng < -161.75) {
	           if (lat < 63.25) {
	            if (lng < -162.0) {
	             return 135;
	            } else {
	             return 371;
	            }
	           } else {
	            if (lng < -162.0) {
	             return 135;
	            } else {
	             return 371;
	            }
	           }
	          } else {
	           return 371;
	          }
	         } else {
	          if (lng < -161.75) {
	           return 135;
	          } else {
	           return 371;
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < 66.0) {
	        if (lng < -162.0) {
	         return 135;
	        } else {
	         return 371;
	        }
	       } else {
	        if (lng < -162.25) {
	         return 135;
	        } else {
	         if (lat < 66.75) {
	          if (lng < -161.75) {
	           if (lat < 66.25) {
	            if (lng < -162.0) {
	             return 135;
	            } else {
	             return 371;
	            }
	           } else {
	            if (lng < -162.0) {
	             return 135;
	            } else {
	             return 371;
	            }
	           }
	          } else {
	           return 371;
	          }
	         } else {
	          if (lng < -162.0) {
	           return 135;
	          } else {
	           return 371;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      return 371;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup1(double lat, double lng)
	{
	 if (lat < 61.75) {
	  if (lng < -138.0) {
	   if (lat < 59.0) {
	    return 0;
	   } else {
	    if (lng < -139.5) {
	     if (lat < 60.25) {
	      return 98;
	     } else {
	      if (lng < -140.25) {
	       if (lat < 60.5) {
	        return 98;
	       } else {
	        return 325;
	       }
	      } else {
	       if (lat < 60.5) {
	        if (lng < -140.0) {
	         return 98;
	        } else {
	         if (lng < -139.75) {
	          return 325;
	         } else {
	          return 98;
	         }
	        }
	       } else {
	        return 325;
	       }
	      }
	     }
	    } else {
	     if (lat < 60.25) {
	      if (lng < -138.75) {
	       if (lat < 59.5) {
	        return 98;
	       } else {
	        if (lng < -139.0) {
	         return 98;
	        } else {
	         if (lat < 60.0) {
	          return 98;
	         } else {
	          return 325;
	         }
	        }
	       }
	      } else {
	       if (lat < 59.75) {
	        return 98;
	       } else {
	        if (lng < -138.5) {
	         if (lat < 60.0) {
	          return 98;
	         } else {
	          return 325;
	         }
	        } else {
	         if (lng < -138.25) {
	          if (lat < 60.0) {
	           return 45;
	          } else {
	           return 325;
	          }
	         } else {
	          if (lat < 60.0) {
	           return 45;
	          } else {
	           return 325;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < -139.0) {
	       if (lat < 60.5) {
	        return 98;
	       } else {
	        return 325;
	       }
	      } else {
	       return 325;
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 59.0) {
	    if (lng < -136.5) {
	     return 360;
	    } else {
	     if (lat < 57.5) {
	      return 23;
	     } else {
	      if (lng < -135.75) {
	       if (lat < 58.0) {
	        return 23;
	       } else {
	        return 360;
	       }
	      } else {
	       if (lat < 58.0) {
	        return 23;
	       } else {
	        return 360;
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < -136.5) {
	     if (lat < 60.0) {
	      if (lng < -137.25) {
	       if (lat < 59.5) {
	        if (lng < -137.5) {
	         return 98;
	        } else {
	         if (lat < 59.25) {
	          return 98;
	         } else {
	          return 45;
	         }
	        }
	       } else {
	        return 45;
	       }
	      } else {
	       if (lat < 59.25) {
	        return 360;
	       } else {
	        return 45;
	       }
	      }
	     } else {
	      return 325;
	     }
	    } else {
	     if (lat < 60.0) {
	      if (lng < -135.75) {
	       if (lat < 59.5) {
	        return 360;
	       } else {
	        if (lng < -136.25) {
	         return 45;
	        } else {
	         if (lng < -136.0) {
	          if (lat < 59.75) {
	           return 360;
	          } else {
	           return 45;
	          }
	         } else {
	          if (lat < 59.75) {
	           return 360;
	          } else {
	           return 45;
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < 59.75) {
	        return 360;
	       } else {
	        if (lng < -135.5) {
	         return 45;
	        } else {
	         if (lng < -135.25) {
	          return 360;
	         } else {
	          return 45;
	         }
	        }
	       }
	      }
	     } else {
	      return 325;
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < -138.0) {
	   if (lat < 64.5) {
	    if (lng < -140.75) {
	     if (lat < 64.25) {
	      return 325;
	     } else {
	      return 371;
	     }
	    } else {
	     return 325;
	    }
	   } else {
	    if (lng < -140.75) {
	     return 371;
	    } else {
	     return 325;
	    }
	   }
	  } else {
	   if (lat < 67.25) {
	    return 325;
	   } else {
	    if (lng < -136.0) {
	     return 325;
	    } else {
	     return 143;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup2(double lat, double lng)
	{
	 if (lng < -115.5) {
	  if (lat < 30.75) {
	   return 368;
	  } else {
	   if (lat < 32.25) {
	    if (lng < -117.0) {
	     return 0;
	    } else {
	     if (lng < -116.25) {
	      if (lat < 31.5) {
	       return 368;
	      } else {
	       if (lng < -116.75) {
	        return 195;
	       } else {
	        if (lat < 32.0) {
	         return 368;
	        } else {
	         return 195;
	        }
	       }
	      }
	     } else {
	      return 368;
	     }
	    }
	   } else {
	    if (lng < -117.0) {
	     return 39;
	    } else {
	     if (lng < -116.25) {
	      if (lat < 32.75) {
	       return 195;
	      } else {
	       return 39;
	      }
	     } else {
	      if (lat < 32.75) {
	       if (lng < -116.0) {
	        if (lat < 32.5) {
	         return 368;
	        } else {
	         return 195;
	        }
	       } else {
	        if (lng < -115.75) {
	         if (lat < 32.5) {
	          return 368;
	         } else {
	          return 195;
	         }
	        } else {
	         if (lat < 32.5) {
	          return 368;
	         } else {
	          return 195;
	         }
	        }
	       }
	      } else {
	       return 39;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 30.75) {
	   if (lng < -114.0) {
	    if (lat < 29.25) {
	     if (lng < -114.75) {
	      if (lat < 28.5) {
	       if (lng < -115.0) {
	        return 368;
	       } else {
	        if (lat < 28.25) {
	         return 408;
	        } else {
	         return 368;
	        }
	       }
	      } else {
	       return 368;
	      }
	     } else {
	      if (lat < 28.25) {
	       return 408;
	      } else {
	       return 368;
	      }
	     }
	    } else {
	     return 368;
	    }
	   } else {
	    if (lat < 29.25) {
	     if (lng < -113.25) {
	      if (lat < 28.25) {
	       return 408;
	      } else {
	       return 368;
	      }
	     } else {
	      return 368;
	     }
	    } else {
	     if (lng < -113.25) {
	      return 368;
	     } else {
	      if (lat < 29.75) {
	       return 368;
	      } else {
	       return 403;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < -114.0) {
	    if (lat < 32.25) {
	     if (lng < -114.75) {
	      if (lat < 32.0) {
	       return 368;
	      } else {
	       if (lng < -115.0) {
	        return 368;
	       } else {
	        return 403;
	       }
	      }
	     } else {
	      if (lat < 31.5) {
	       return 368;
	      } else {
	       if (lng < -114.5) {
	        if (lat < 31.75) {
	         return 368;
	        } else {
	         return 403;
	        }
	       } else {
	        return 403;
	       }
	      }
	     }
	    } else {
	     if (lng < -114.75) {
	      if (lat < 32.75) {
	       if (lng < -115.25) {
	        if (lat < 32.5) {
	         return 368;
	        } else {
	         return 195;
	        }
	       } else {
	        if (lng < -115.0) {
	         if (lat < 32.5) {
	          return 368;
	         } else {
	          return 195;
	         }
	        } else {
	         if (lat < 32.5) {
	          return 368;
	         } else {
	          return 195;
	         }
	        }
	       }
	      } else {
	       return 39;
	      }
	     } else {
	      if (lat < 33.0) {
	       if (lng < -114.5) {
	        if (lat < 32.5) {
	         return 403;
	        } else {
	         if (lat < 32.75) {
	          return 9;
	         } else {
	          return 39;
	         }
	        }
	       } else {
	        if (lat < 32.5) {
	         return 403;
	        } else {
	         return 9;
	        }
	       }
	      } else {
	       if (lng < -114.5) {
	        return 39;
	       } else {
	        if (lat < 33.25) {
	         if (lng < -114.25) {
	          return 39;
	         } else {
	          return 9;
	         }
	        } else {
	         return 9;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 32.25) {
	     if (lng < -113.0) {
	      return 403;
	     } else {
	      if (lat < 32.0) {
	       return 403;
	      } else {
	       return 9;
	      }
	     }
	    } else {
	     return 9;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup3(double lat, double lng)
	{
	 if (lng < -101.25) {
	  if (lat < 11.25) {
	   return 0;
	  } else {
	   if (lng < -107.0) {
	    return 408;
	   } else {
	    if (lat < 16.75) {
	     return 0;
	    } else {
	     if (lng < -104.25) {
	      if (lat < 19.5) {
	       return 193;
	      } else {
	       if (lat < 21.0) {
	        if (lng < -105.75) {
	         return 0;
	        } else {
	         if (lng < -105.0) {
	          if (lat < 20.75) {
	           return 193;
	          } else {
	           return 408;
	          }
	         } else {
	          return 193;
	         }
	        }
	       } else {
	        if (lng < -105.75) {
	         return 0;
	        } else {
	         if (lng < -104.75) {
	          return 408;
	         } else {
	          if (lat < 21.25) {
	           if (lng < -104.5) {
	            return 193;
	           } else {
	            return 408;
	           }
	          } else {
	           return 408;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 17.5) {
	       return 0;
	      } else {
	       if (lat < 21.0) {
	        return 193;
	       } else {
	        if (lng < -104.0) {
	         if (lat < 22.0) {
	          return 408;
	         } else {
	          return 193;
	         }
	        } else {
	         return 193;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 11.25) {
	   return 114;
	  } else {
	   if (lng < -95.75) {
	    return 193;
	   } else {
	    if (lat < 16.75) {
	     if (lng < -92.0) {
	      return 193;
	     } else {
	      if (lat < 14.0) {
	       return 393;
	      } else {
	       if (lat < 15.75) {
	        return 393;
	       } else {
	        if (lng < -91.0) {
	         if (lng < -91.5) {
	          if (lat < 16.25) {
	           if (lng < -91.75) {
	            return 193;
	           } else {
	            return 393;
	           }
	          } else {
	           return 193;
	          }
	         } else {
	          if (lat < 16.25) {
	           return 393;
	          } else {
	           return 193;
	          }
	         }
	        } else {
	         if (lng < -90.5) {
	          if (lat < 16.25) {
	           return 393;
	          } else {
	           return 193;
	          }
	         } else {
	          if (lat < 16.25) {
	           return 393;
	          } else {
	           if (lng < -90.25) {
	            if (lat < 16.5) {
	             return 193;
	            } else {
	             return 393;
	            }
	           } else {
	            return 393;
	           }
	          }
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -93.0) {
	      return 193;
	     } else {
	      if (lat < 19.5) {
	       if (lng < -91.5) {
	        if (lat < 18.0) {
	         return 193;
	        } else {
	         if (lng < -92.25) {
	          return 193;
	         } else {
	          if (lat < 18.75) {
	           if (lng < -92.0) {
	            return 193;
	           } else {
	            if (lat < 18.25) {
	             if (lng < -91.75) {
	              return 193;
	             } else {
	              return 32;
	             }
	            } else {
	             return 32;
	            }
	           }
	          } else {
	           if (lng < -92.0) {
	            return 193;
	           } else {
	            return 32;
	           }
	          }
	         }
	        }
	       } else {
	        if (lat < 18.0) {
	         if (lng < -90.75) {
	          if (lat < 17.25) {
	           if (lng < -91.0) {
	            return 193;
	           } else {
	            if (lat < 17.0) {
	             return 193;
	            } else {
	             return 393;
	            }
	           }
	          } else {
	           if (lng < -91.25) {
	            return 193;
	           } else {
	            if (lat < 17.5) {
	             return 393;
	            } else {
	             return 193;
	            }
	           }
	          }
	         } else {
	          return 393;
	         }
	        } else {
	         if (lng < -91.0) {
	          if (lat < 18.75) {
	           if (lat < 18.25) {
	            return 193;
	           } else {
	            return 32;
	           }
	          } else {
	           return 32;
	          }
	         } else {
	          return 32;
	         }
	        }
	       }
	      } else {
	       return 32;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup4(double lat, double lng)
	{
	 if (lat < 28.0) {
	  if (lng < -109.75) {
	   if (lat < 25.25) {
	    return 408;
	   } else {
	    if (lng < -111.25) {
	     return 408;
	    } else {
	     if (lat < 26.5) {
	      return 408;
	     } else {
	      if (lng < -111.0) {
	       return 408;
	      } else {
	       return 403;
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 25.25) {
	    return 408;
	   } else {
	    if (lng < -108.5) {
	     if (lat < 26.5) {
	      return 408;
	     } else {
	      if (lat < 27.25) {
	       if (lng < -108.75) {
	        return 403;
	       } else {
	        if (lat < 26.75) {
	         return 408;
	        } else {
	         return 403;
	        }
	       }
	      } else {
	       if (lng < -108.75) {
	        return 403;
	       } else {
	        if (lat < 27.75) {
	         return 403;
	        } else {
	         return 186;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 26.5) {
	      if (lng < -107.75) {
	       return 408;
	      } else {
	       if (lat < 26.0) {
	        return 408;
	       } else {
	        if (lng < -107.5) {
	         if (lat < 26.25) {
	          return 408;
	         } else {
	          return 186;
	         }
	        } else {
	         if (lng < -107.25) {
	          if (lat < 26.25) {
	           return 408;
	          } else {
	           return 186;
	          }
	         } else {
	          return 186;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < -107.75) {
	       if (lat < 27.25) {
	        if (lng < -108.25) {
	         if (lat < 27.0) {
	          return 408;
	         } else {
	          return 186;
	         }
	        } else {
	         if (lat < 27.0) {
	          return 408;
	         } else {
	          if (lng < -108.0) {
	           return 408;
	          } else {
	           return 186;
	          }
	         }
	        }
	       } else {
	        return 186;
	       }
	      } else {
	       return 186;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 30.75) {
	   if (lng < -109.75) {
	    return 403;
	   } else {
	    if (lng < -108.5) {
	     if (lat < 28.5) {
	      if (lng < -109.0) {
	       return 403;
	      } else {
	       if (lng < -108.75) {
	        return 186;
	       } else {
	        if (lat < 28.25) {
	         return 186;
	        } else {
	         return 403;
	        }
	       }
	      }
	     } else {
	      return 403;
	     }
	    } else {
	     return 186;
	    }
	   }
	  } else {
	   if (lng < -109.75) {
	    if (lat < 32.0) {
	     if (lng < -111.25) {
	      if (lng < -112.0) {
	       if (lat < 31.75) {
	        return 403;
	       } else {
	        if (lng < -112.25) {
	         return 403;
	        } else {
	         return 9;
	        }
	       }
	      } else {
	       if (lat < 31.5) {
	        return 403;
	       } else {
	        if (lng < -111.75) {
	         if (lat < 31.75) {
	          return 403;
	         } else {
	          return 9;
	         }
	        } else {
	         if (lng < -111.5) {
	          if (lat < 31.75) {
	           return 403;
	          } else {
	           return 9;
	          }
	         } else {
	          return 9;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < -110.5) {
	       if (lat < 31.5) {
	        return 403;
	       } else {
	        return 9;
	       }
	      } else {
	       if (lat < 31.5) {
	        return 403;
	       } else {
	        return 9;
	       }
	      }
	     }
	    } else {
	     return 9;
	    }
	   } else {
	    if (lat < 32.25) {
	     if (lng < -108.5) {
	      if (lat < 31.5) {
	       if (lng < -108.75) {
	        return 403;
	       } else {
	        if (lat < 31.0) {
	         return 403;
	        } else {
	         if (lat < 31.25) {
	          return 186;
	         } else {
	          return 134;
	         }
	        }
	       }
	      } else {
	       if (lng < -109.0) {
	        return 9;
	       } else {
	        return 274;
	       }
	      }
	     } else {
	      if (lng < -107.75) {
	       if (lat < 31.5) {
	        if (lng < -108.25) {
	         if (lat < 31.25) {
	          return 186;
	         } else {
	          return 134;
	         }
	        } else {
	         if (lat < 31.25) {
	          return 186;
	         } else {
	          return 134;
	         }
	        }
	       } else {
	        if (lng < -108.0) {
	         return 274;
	        } else {
	         if (lat < 32.0) {
	          return 134;
	         } else {
	          return 274;
	         }
	        }
	       }
	      } else {
	       if (lat < 31.75) {
	        return 186;
	       } else {
	        if (lng < -107.5) {
	         if (lat < 32.0) {
	          return 134;
	         } else {
	          return 274;
	         }
	        } else {
	         if (lng < -107.25) {
	          if (lat < 32.0) {
	           return 134;
	          } else {
	           return 274;
	          }
	         } else {
	          if (lat < 32.0) {
	           return 134;
	          } else {
	           return 274;
	          }
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -109.0) {
	      return 9;
	     } else {
	      return 274;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup5(double lat, double lng)
	{
	 if (lng < -104.25) {
	  if (lat < 30.75) {
	   if (lng < -105.25) {
	    return 186;
	   } else {
	    if (lat < 29.5) {
	     return 186;
	    } else {
	     if (lat < 30.0) {
	      if (lng < -104.75) {
	       return 186;
	      } else {
	       if (lng < -104.5) {
	        if (lat < 29.75) {
	         return 186;
	        } else {
	         return 134;
	        }
	       } else {
	        if (lat < 29.75) {
	         return 134;
	        } else {
	         return 161;
	        }
	       }
	      }
	     } else {
	      if (lng < -104.75) {
	       if (lat < 30.25) {
	        return 186;
	       } else {
	        if (lng < -105.0) {
	         if (lat < 30.5) {
	          return 186;
	         } else {
	          return 134;
	         }
	        } else {
	         return 134;
	        }
	       }
	      } else {
	       if (lat < 30.25) {
	        if (lng < -104.5) {
	         return 134;
	        } else {
	         return 161;
	        }
	       } else {
	        if (lng < -104.5) {
	         if (lat < 30.5) {
	          return 134;
	         } else {
	          return 161;
	         }
	        } else {
	         return 161;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 32.25) {
	    if (lng < -105.75) {
	     if (lat < 31.5) {
	      if (lng < -106.25) {
	       return 186;
	      } else {
	       if (lat < 31.25) {
	        return 186;
	       } else {
	        return 134;
	       }
	      }
	     } else {
	      if (lng < -106.5) {
	       if (lat < 31.75) {
	        return 186;
	       } else {
	        if (lng < -106.75) {
	         if (lat < 32.0) {
	          return 134;
	         } else {
	          return 274;
	         }
	        } else {
	         if (lat < 32.0) {
	          return 134;
	         } else {
	          return 274;
	         }
	        }
	       }
	      } else {
	       if (lng < -106.25) {
	        if (lat < 32.0) {
	         return 134;
	        } else {
	         return 274;
	        }
	       } else {
	        if (lat < 31.75) {
	         if (lng < -106.0) {
	          return 134;
	         } else {
	          return 274;
	         }
	        } else {
	         return 274;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -105.0) {
	      if (lat < 31.25) {
	       if (lng < -105.5) {
	        if (lat < 31.0) {
	         return 186;
	        } else {
	         return 134;
	        }
	       } else {
	        if (lng < -105.25) {
	         if (lat < 31.0) {
	          return 134;
	         } else {
	          return 274;
	         }
	        } else {
	         if (lat < 31.0) {
	          return 134;
	         } else {
	          return 274;
	         }
	        }
	       }
	      } else {
	       return 274;
	      }
	     } else {
	      if (lat < 31.5) {
	       if (lng < -104.75) {
	        return 274;
	       } else {
	        return 161;
	       }
	      } else {
	       if (lng < -104.75) {
	        return 274;
	       } else {
	        return 161;
	       }
	      }
	     }
	    }
	   } else {
	    return 274;
	   }
	  }
	 } else {
	  if (lat < 30.75) {
	   if (lng < -102.75) {
	    if (lat < 29.25) {
	     if (lng < -103.5) {
	      if (lat < 28.5) {
	       if (lng < -103.75) {
	        return 186;
	       } else {
	        if (lat < 28.25) {
	         return 385;
	        } else {
	         return 186;
	        }
	       }
	      } else {
	       if (lng < -103.75) {
	        return 186;
	       } else {
	        if (lat < 29.0) {
	         return 186;
	        } else {
	         return 134;
	        }
	       }
	      }
	     } else {
	      if (lat < 28.75) {
	       return 385;
	      } else {
	       if (lng < -103.25) {
	        if (lat < 29.0) {
	         return 186;
	        } else {
	         return 134;
	        }
	       } else {
	        if (lng < -103.0) {
	         if (lat < 29.0) {
	          return 375;
	         } else {
	          return 161;
	         }
	        } else {
	         if (lat < 29.0) {
	          return 385;
	         } else {
	          return 375;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -103.75) {
	      if (lat < 29.75) {
	       if (lng < -104.0) {
	        return 134;
	       } else {
	        if (lat < 29.5) {
	         return 134;
	        } else {
	         return 161;
	        }
	       }
	      } else {
	       return 161;
	      }
	     } else {
	      return 161;
	     }
	    }
	   } else {
	    if (lat < 29.25) {
	     if (lng < -102.5) {
	      if (lat < 29.0) {
	       return 385;
	      } else {
	       return 375;
	      }
	     } else {
	      return 385;
	     }
	    } else {
	     if (lng < -102.0) {
	      if (lat < 30.0) {
	       if (lng < -102.5) {
	        if (lat < 29.75) {
	         return 375;
	        } else {
	         return 161;
	        }
	       } else {
	        if (lat < 29.5) {
	         return 385;
	        } else {
	         if (lng < -102.25) {
	          return 375;
	         } else {
	          if (lat < 29.75) {
	           return 385;
	          } else {
	           return 375;
	          }
	         }
	        }
	       }
	      } else {
	       return 161;
	      }
	     } else {
	      if (lat < 30.0) {
	       if (lng < -101.75) {
	        if (lat < 29.75) {
	         return 385;
	        } else {
	         return 375;
	        }
	       } else {
	        if (lat < 29.5) {
	         return 385;
	        } else {
	         return 375;
	        }
	       }
	      } else {
	       return 161;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < -103.0) {
	    if (lat < 32.25) {
	     return 161;
	    } else {
	     return 274;
	    }
	   } else {
	    return 161;
	   }
	  }
	 }
	}

	private  int kdLookup6(double lat, double lng)
	{
	 if (lng < -107.0) {
	  return kdLookup4(lat,lng);
	 } else {
	  if (lat < 28.0) {
	   if (lng < -104.25) {
	    if (lat < 25.25) {
	     if (lng < -105.75) {
	      if (lat < 23.75) {
	       return 408;
	      } else {
	       if (lat < 24.5) {
	        if (lng < -106.0) {
	         return 408;
	        } else {
	         if (lat < 24.25) {
	          return 408;
	         } else {
	          return 385;
	         }
	        }
	       } else {
	        if (lng < -106.5) {
	         if (lat < 24.75) {
	          return 408;
	         } else {
	          if (lng < -106.75) {
	           if (lat < 25.0) {
	            return 408;
	           } else {
	            return 385;
	           }
	          } else {
	           return 385;
	          }
	         }
	        } else {
	         return 385;
	        }
	       }
	      }
	     } else {
	      if (lat < 23.75) {
	       if (lng < -105.0) {
	        if (lat < 23.25) {
	         return 408;
	        } else {
	         if (lng < -105.5) {
	          return 408;
	         } else {
	          return 385;
	         }
	        }
	       } else {
	        if (lat < 23.0) {
	         if (lng < -104.75) {
	          return 408;
	         } else {
	          if (lng < -104.5) {
	           if (lat < 22.75) {
	            return 408;
	           } else {
	            return 385;
	           }
	          } else {
	           return 385;
	          }
	         }
	        } else {
	         return 385;
	        }
	       }
	      } else {
	       return 385;
	      }
	     }
	    } else {
	     if (lng < -105.75) {
	      if (lat < 26.5) {
	       if (lng < -106.5) {
	        if (lat < 25.75) {
	         return 385;
	        } else {
	         return 186;
	        }
	       } else {
	        if (lat < 26.25) {
	         return 385;
	        } else {
	         if (lng < -106.25) {
	          return 186;
	         } else {
	          return 385;
	         }
	        }
	       }
	      } else {
	       if (lat < 27.0) {
	        if (lng < -106.0) {
	         return 186;
	        } else {
	         return 385;
	        }
	       } else {
	        return 186;
	       }
	      }
	     } else {
	      if (lat < 26.5) {
	       return 385;
	      } else {
	       if (lng < -105.25) {
	        if (lat < 26.75) {
	         return 385;
	        } else {
	         return 186;
	        }
	       } else {
	        return 186;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 25.25) {
	     if (lng < -102.75) {
	      if (lat < 23.75) {
	       if (lng < -104.0) {
	        if (lat < 22.75) {
	         return 193;
	        } else {
	         return 385;
	        }
	       } else {
	        return 193;
	       }
	      } else {
	       if (lng < -103.5) {
	        if (lat < 24.25) {
	         if (lng < -103.75) {
	          return 385;
	         } else {
	          return 193;
	         }
	        } else {
	         return 385;
	        }
	       } else {
	        if (lat < 24.5) {
	         return 193;
	        } else {
	         return 385;
	        }
	       }
	      }
	     } else {
	      if (lat < 24.5) {
	       return 193;
	      } else {
	       if (lng < -102.0) {
	        if (lng < -102.5) {
	         return 385;
	        } else {
	         return 193;
	        }
	       } else {
	        if (lng < -101.75) {
	         return 193;
	        } else {
	         if (lat < 25.0) {
	          return 193;
	         } else {
	          return 385;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -103.5) {
	      if (lat < 26.75) {
	       return 385;
	      } else {
	       if (lat < 27.25) {
	        return 186;
	       } else {
	        if (lng < -103.75) {
	         return 186;
	        } else {
	         return 385;
	        }
	       }
	      }
	     } else {
	      return 385;
	     }
	    }
	   }
	  } else {
	   return kdLookup5(lat,lng);
	  }
	 }
	}

	private  int kdLookup7(double lat, double lng)
	{
	 if (lng < -107.0) {
	  if (lat < 37.25) {
	   if (lng < -109.75) {
	    if (lat < 35.5) {
	     if (lng < -111.0) {
	      return 9;
	     } else {
	      if (lat < 35.25) {
	       return 9;
	      } else {
	       return 274;
	      }
	     }
	    } else {
	     if (lng < -111.25) {
	      if (lat < 36.25) {
	       if (lng < -111.5) {
	        return 9;
	       } else {
	        if (lat < 36.0) {
	         return 9;
	        } else {
	         return 274;
	        }
	       }
	      } else {
	       if (lng < -111.75) {
	        return 9;
	       } else {
	        if (lat < 36.75) {
	         return 274;
	        } else {
	         if (lng < -111.5) {
	          return 9;
	         } else {
	          if (lat < 37.0) {
	           return 274;
	          } else {
	           return 9;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 36.25) {
	       if (lng < -110.5) {
	        if (lng < -111.0) {
	         if (lat < 35.75) {
	          return 9;
	         } else {
	          return 274;
	         }
	        } else {
	         if (lat < 35.75) {
	          return 274;
	         } else {
	          return 9;
	         }
	        }
	       } else {
	        if (lng < -110.25) {
	         if (lat < 35.75) {
	          return 274;
	         } else {
	          return 9;
	         }
	        } else {
	         if (lat < 35.75) {
	          return 274;
	         } else {
	          if (lng < -110.0) {
	           if (lat < 36.0) {
	            return 9;
	           } else {
	            return 274;
	           }
	          } else {
	           return 274;
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < -110.5) {
	        if (lat < 36.75) {
	         if (lng < -111.0) {
	          return 274;
	         } else {
	          if (lng < -110.75) {
	           return 9;
	          } else {
	           if (lat < 36.5) {
	            return 9;
	           } else {
	            return 274;
	           }
	          }
	         }
	        } else {
	         return 274;
	        }
	       } else {
	        if (lat < 36.5) {
	         if (lng < -110.25) {
	          return 9;
	         } else {
	          return 274;
	         }
	        } else {
	         return 274;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 35.5) {
	     if (lng < -109.0) {
	      if (lat < 35.25) {
	       return 9;
	      } else {
	       if (lng < -109.5) {
	        return 274;
	       } else {
	        if (lng < -109.25) {
	         return 9;
	        } else {
	         return 274;
	        }
	       }
	      }
	     } else {
	      return 274;
	     }
	    } else {
	     return 274;
	    }
	   }
	  } else {
	   if (lat < 42.0) {
	    return 274;
	   } else {
	    if (lng < -111.0) {
	     if (lat < 43.5) {
	      if (lng < -111.75) {
	       if (lat < 42.25) {
	        if (lng < -112.25) {
	         return 274;
	        } else {
	         return 27;
	        }
	       } else {
	        return 27;
	       }
	      } else {
	       if (lat < 42.25) {
	        return 274;
	       } else {
	        return 27;
	       }
	      }
	     } else {
	      if (lng < -111.75) {
	       if (lat < 44.5) {
	        return 27;
	       } else {
	        if (lng < -112.25) {
	         return 274;
	        } else {
	         if (lng < -112.0) {
	          if (lat < 44.75) {
	           return 27;
	          } else {
	           return 274;
	          }
	         } else {
	          if (lat < 44.75) {
	           return 27;
	          } else {
	           return 274;
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < 44.75) {
	        return 27;
	       } else {
	        return 274;
	       }
	      }
	     }
	    } else {
	     return 274;
	    }
	   }
	  }
	 } else {
	  if (lat < 38.75) {
	   if (lng < -103.0) {
	    return 274;
	   } else {
	    if (lat < 37.0) {
	     return 161;
	    } else {
	     if (lng < -102.0) {
	      return 274;
	     } else {
	      if (lat < 37.75) {
	       return 161;
	      } else {
	       if (lat < 38.25) {
	        if (lng < -101.5) {
	         return 274;
	        } else {
	         return 161;
	        }
	       } else {
	        if (lng < -101.5) {
	         return 274;
	        } else {
	         return 161;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 44.75) {
	    if (lat < 44.5) {
	     if (lng < -102.0) {
	      return 274;
	     } else {
	      if (lat < 44.25) {
	       if (lat < 44.0) {
	        if (lat < 43.75) {
	         if (lat < 43.5) {
	          if (lat < 43.25) {
	           if (lat < 43.0) {
	            if (lat < 42.75) {
	             if (lat < 42.5) {
	              if (lat < 42.25) {
	               if (lat < 42.0) {
	                if (lat < 41.75) {
	                 if (lat < 41.5) {
	                  if (lat < 40.0) {
	                   if (lat < 39.75) {
	                    return 274;
	                   } else {
	                    return 161;
	                   }
	                  } else {
	                   if (lat < 40.25) {
	                    return 161;
	                   } else {
	                    return 274;
	                   }
	                  }
	                 } else {
	                  return 274;
	                 }
	                } else {
	                 return 274;
	                }
	               } else {
	                return 274;
	               }
	              } else {
	               return 274;
	              }
	             } else {
	              return 274;
	             }
	            } else {
	             return 274;
	            }
	           } else {
	            return 274;
	           }
	          } else {
	           return 274;
	          }
	         } else {
	          return 274;
	         }
	        } else {
	         return 274;
	        }
	       } else {
	        return 274;
	       }
	      } else {
	       return 274;
	      }
	     }
	    } else {
	     return 274;
	    }
	   } else {
	    return 274;
	   }
	  }
	 }
	}

	private  int kdLookup8(double lat, double lng)
	{
	 if (lat < 25.25) {
	  if (lng < -99.0) {
	   if (lat < 23.75) {
	    if (lng < -100.25) {
	     return 193;
	    } else {
	     if (lng < -99.75) {
	      if (lat < 23.25) {
	       return 193;
	      } else {
	       return 385;
	      }
	     } else {
	      if (lat < 22.75) {
	       if (lng < -99.25) {
	        return 193;
	       } else {
	        return 385;
	       }
	      } else {
	       return 385;
	      }
	     }
	    }
	   } else {
	    if (lng < -100.25) {
	     if (lat < 24.5) {
	      if (lng < -100.5) {
	       return 193;
	      } else {
	       if (lat < 24.0) {
	        return 193;
	       } else {
	        return 385;
	       }
	      }
	     } else {
	      if (lng < -100.75) {
	       if (lat < 24.75) {
	        return 193;
	       } else {
	        if (lng < -101.0) {
	         if (lat < 25.0) {
	          return 193;
	         } else {
	          return 385;
	         }
	        } else {
	         return 385;
	        }
	       }
	      } else {
	       return 385;
	      }
	     }
	    } else {
	     return 385;
	    }
	   }
	  } else {
	   return 385;
	  }
	 } else {
	  if (lng < -99.0) {
	   if (lat < 26.5) {
	    if (lng < -99.25) {
	     return 385;
	    } else {
	     if (lat < 26.25) {
	      return 385;
	     } else {
	      return 375;
	     }
	    }
	   } else {
	    if (lng < -100.0) {
	     return 385;
	    } else {
	     if (lat < 27.25) {
	      if (lng < -99.5) {
	       return 385;
	      } else {
	       if (lat < 26.75) {
	        if (lng < -99.25) {
	         return 385;
	        } else {
	         return 375;
	        }
	       } else {
	        if (lng < -99.25) {
	         return 375;
	        } else {
	         if (lat < 27.0) {
	          return 375;
	         } else {
	          return 161;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < -99.5) {
	       if (lat < 27.5) {
	        if (lng < -99.75) {
	         return 385;
	        } else {
	         return 375;
	        }
	       } else {
	        if (lng < -99.75) {
	         if (lat < 27.75) {
	          return 385;
	         } else {
	          return 375;
	         }
	        } else {
	         if (lat < 27.75) {
	          return 375;
	         } else {
	          return 161;
	         }
	        }
	       }
	      } else {
	       if (lat < 27.5) {
	        if (lng < -99.25) {
	         return 375;
	        } else {
	         return 161;
	        }
	       } else {
	        return 161;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 26.5) {
	    if (lng < -98.0) {
	     if (lat < 26.0) {
	      return 385;
	     } else {
	      if (lng < -98.5) {
	       if (lng < -98.75) {
	        if (lat < 26.25) {
	         return 385;
	        } else {
	         return 375;
	        }
	       } else {
	        if (lat < 26.25) {
	         return 385;
	        } else {
	         return 375;
	        }
	       }
	      } else {
	       if (lng < -98.25) {
	        if (lat < 26.25) {
	         return 375;
	        } else {
	         return 161;
	        }
	       } else {
	        if (lat < 26.25) {
	         return 375;
	        } else {
	         return 161;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -97.5) {
	      if (lat < 25.75) {
	       return 385;
	      } else {
	       if (lat < 26.0) {
	        if (lng < -97.75) {
	         return 385;
	        } else {
	         return 375;
	        }
	       } else {
	        if (lng < -97.75) {
	         if (lat < 26.25) {
	          return 375;
	         } else {
	          return 161;
	         }
	        } else {
	         if (lat < 26.25) {
	          return 375;
	         } else {
	          return 161;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 25.75) {
	       return 385;
	      } else {
	       if (lng < -97.25) {
	        if (lat < 26.0) {
	         return 375;
	        } else {
	         return 161;
	        }
	       } else {
	        if (lat < 26.0) {
	         return 375;
	        } else {
	         return 161;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return 161;
	   }
	  }
	 }
	}

	private  int kdLookup9(double lat, double lng)
	{
	 if (lng < -112.5) {
	  if (lat < 22.5) {
	   return 0;
	  } else {
	   if (lng < -123.75) {
	    return 39;
	   } else {
	    if (lat < 33.75) {
	     if (lng < -118.25) {
	      return 39;
	     } else {
	      if (lat < 28.0) {
	       return 408;
	      } else {
	       return kdLookup2(lat,lng);
	      }
	     }
	    } else {
	     if (lng < -118.0) {
	      return 39;
	     } else {
	      if (lat < 39.25) {
	       if (lng < -114.5) {
	        return 39;
	       } else {
	        if (lat < 36.5) {
	         if (lat < 36.25) {
	          if (lat < 36.0) {
	           if (lat < 35.75) {
	            if (lng < -114.0) {
	             if (lat < 35.5) {
	              if (lat < 34.5) {
	               if (lat < 34.0) {
	                return 9;
	               } else {
	                if (lng < -114.25) {
	                 return 39;
	                } else {
	                 if (lat < 34.25) {
	                  return 9;
	                 } else {
	                  return 39;
	                 }
	                }
	               }
	              } else {
	               if (lat < 34.75) {
	                if (lng < -114.25) {
	                 return 39;
	                } else {
	                 return 9;
	                }
	               } else {
	                return 9;
	               }
	              }
	             } else {
	              return 9;
	             }
	            } else {
	             return 9;
	            }
	           } else {
	            return 9;
	           }
	          } else {
	           return 9;
	          }
	         } else {
	          if (lng < -114.0) {
	           return 39;
	          } else {
	           return 9;
	          }
	         }
	        } else {
	         if (lat < 37.75) {
	          if (lng < -113.5) {
	           if (lat < 37.0) {
	            if (lng < -114.0) {
	             return 39;
	            } else {
	             return 9;
	            }
	           } else {
	            if (lng < -114.0) {
	             return 39;
	            } else {
	             if (lat < 37.25) {
	              return 9;
	             } else {
	              return 274;
	             }
	            }
	           }
	          } else {
	           if (lat < 37.25) {
	            return 9;
	           } else {
	            return 274;
	           }
	          }
	         } else {
	          if (lng < -114.0) {
	           return 39;
	          } else {
	           return 274;
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < 42.0) {
	        if (lng < -114.0) {
	         return 39;
	        } else {
	         return 274;
	        }
	       } else {
	        if (lng < -115.25) {
	         if (lat < 43.5) {
	          if (lng < -116.75) {
	           if (lat < 42.5) {
	            if (lng < -117.0) {
	             return 39;
	            } else {
	             if (lat < 42.25) {
	              return 39;
	             } else {
	              return 27;
	             }
	            }
	           } else {
	            return 27;
	           }
	          } else {
	           return 27;
	          }
	         } else {
	          if (lng < -117.0) {
	           if (lat < 44.5) {
	            return 27;
	           } else {
	            return 39;
	           }
	          } else {
	           return 27;
	          }
	         }
	        } else {
	         if (lat < 44.5) {
	          return 27;
	         } else {
	          if (lng < -113.0) {
	           return 27;
	          } else {
	           return 274;
	          }
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 22.5) {
	   return kdLookup3(lat,lng);
	  } else {
	   if (lng < -101.25) {
	    if (lat < 33.75) {
	     return kdLookup6(lat,lng);
	    } else {
	     return kdLookup7(lat,lng);
	    }
	   } else {
	    if (lat < 33.75) {
	     if (lng < -96.75) {
	      if (lat < 28.0) {
	       return kdLookup8(lat,lng);
	      } else {
	       if (lat < 29.75) {
	        if (lng < -99.75) {
	         if (lat < 28.75) {
	          if (lng < -100.5) {
	           return 385;
	          } else {
	           if (lng < -100.25) {
	            if (lat < 28.25) {
	             return 385;
	            } else {
	             return 375;
	            }
	           } else {
	            if (lat < 28.25) {
	             return 375;
	            } else {
	             return 161;
	            }
	           }
	          }
	         } else {
	          if (lng < -100.5) {
	           if (lat < 29.25) {
	            if (lng < -100.75) {
	             return 385;
	            } else {
	             return 375;
	            }
	           } else {
	            if (lng < -101.0) {
	             return 375;
	            } else {
	             if (lng < -100.75) {
	              if (lat < 29.5) {
	               return 375;
	              } else {
	               return 161;
	              }
	             } else {
	              return 161;
	             }
	            }
	           }
	          } else {
	           return 161;
	          }
	         }
	        } else {
	         return 161;
	        }
	       } else {
	        return 161;
	       }
	      }
	     } else {
	      return 161;
	     }
	    } else {
	     if (lng < -100.25) {
	      if (lat < 40.75) {
	       return 161;
	      } else {
	       if (lat < 42.75) {
	        if (lat < 41.75) {
	         if (lng < -101.0) {
	          if (lat < 41.25) {
	           return 274;
	          } else {
	           return 161;
	          }
	         } else {
	          return 161;
	         }
	        } else {
	         if (lng < -100.75) {
	          return 274;
	         } else {
	          return 161;
	         }
	        }
	       } else {
	        if (lat < 43.75) {
	         if (lng < -100.75) {
	          if (lat < 43.25) {
	           if (lng < -101.0) {
	            return 274;
	           } else {
	            if (lat < 43.0) {
	             return 274;
	            } else {
	             return 161;
	            }
	           }
	          } else {
	           if (lng < -101.0) {
	            return 274;
	           } else {
	            return 161;
	           }
	          }
	         } else {
	          return 161;
	         }
	        } else {
	         if (lat < 44.25) {
	          if (lng < -101.0) {
	           return 274;
	          } else {
	           return 161;
	          }
	         } else {
	          if (lng < -100.5) {
	           return 274;
	          } else {
	           if (lat < 44.5) {
	            return 274;
	           } else {
	            return 161;
	           }
	          }
	         }
	        }
	       }
	      }
	     } else {
	      return 161;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup10(double lat, double lng)
	{
	 if (lat < 61.75) {
	  if (lng < -132.25) {
	   if (lat < 59.0) {
	    if (lng < -133.75) {
	     if (lat < 57.5) {
	      if (lng < -134.5) {
	       return 23;
	      } else {
	       if (lat < 57.25) {
	        return 23;
	       } else {
	        return 360;
	       }
	      }
	     } else {
	      if (lat < 57.75) {
	       if (lng < -134.5) {
	        return 23;
	       } else {
	        return 360;
	       }
	      } else {
	       return 360;
	      }
	     }
	    } else {
	     if (lat < 57.5) {
	      if (lng < -133.0) {
	       if (lat < 57.25) {
	        return 23;
	       } else {
	        return 360;
	       }
	      } else {
	       return 23;
	      }
	     } else {
	      if (lng < -133.0) {
	       if (lat < 58.25) {
	        return 360;
	       } else {
	        if (lng < -133.5) {
	         if (lat < 58.75) {
	          return 360;
	         } else {
	          return 45;
	         }
	        } else {
	         if (lat < 58.5) {
	          if (lng < -133.25) {
	           return 360;
	          } else {
	           return 45;
	          }
	         } else {
	          return 45;
	         }
	        }
	       }
	      } else {
	       if (lat < 58.0) {
	        if (lng < -132.75) {
	         return 360;
	        } else {
	         if (lng < -132.5) {
	          if (lat < 57.75) {
	           return 360;
	          } else {
	           return 45;
	          }
	         } else {
	          return 45;
	         }
	        }
	       } else {
	        return 45;
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < -133.75) {
	     if (lat < 60.0) {
	      if (lng < -134.5) {
	       if (lat < 59.5) {
	        return 360;
	       } else {
	        return 45;
	       }
	      } else {
	       if (lat < 59.25) {
	        if (lng < -134.25) {
	         return 360;
	        } else {
	         return 45;
	        }
	       } else {
	        return 45;
	       }
	      }
	     } else {
	      return 325;
	     }
	    } else {
	     if (lat < 60.0) {
	      return 45;
	     } else {
	      return 325;
	     }
	    }
	   }
	  } else {
	   if (lat < 59.0) {
	    if (lng < -131.0) {
	     if (lat < 57.25) {
	      if (lng < -131.75) {
	       if (lat < 57.0) {
	        return 23;
	       } else {
	        if (lng < -132.0) {
	         return 23;
	        } else {
	         return 45;
	        }
	       }
	      } else {
	       if (lat < 56.75) {
	        if (lng < -131.25) {
	         return 23;
	        } else {
	         if (lat < 56.5) {
	          return 23;
	         } else {
	          return 45;
	         }
	        }
	       } else {
	        return 45;
	       }
	      }
	     } else {
	      return 45;
	     }
	    } else {
	     if (lat < 56.5) {
	      if (lng < -130.5) {
	       return 23;
	      } else {
	       return 45;
	      }
	     } else {
	      return 45;
	     }
	    }
	   } else {
	    if (lng < -131.0) {
	     if (lat < 60.0) {
	      return 45;
	     } else {
	      return 325;
	     }
	    } else {
	     if (lat < 60.0) {
	      return 45;
	     } else {
	      return 325;
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 64.5) {
	   if (lng < -130.75) {
	    return 325;
	   } else {
	    if (lat < 63.25) {
	     return 325;
	    } else {
	     if (lng < -130.25) {
	      if (lat < 64.0) {
	       return 325;
	      } else {
	       return 143;
	      }
	     } else {
	      if (lat < 63.75) {
	       if (lng < -130.0) {
	        return 325;
	       } else {
	        if (lng < -129.75) {
	         if (lat < 63.5) {
	          return 143;
	         } else {
	          return 325;
	         }
	        } else {
	         return 143;
	        }
	       }
	      } else {
	       if (lng < -130.0) {
	        if (lat < 64.0) {
	         return 325;
	        } else {
	         return 143;
	        }
	       } else {
	        return 143;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < -132.25) {
	    if (lat < 66.0) {
	     if (lng < -132.5) {
	      return 325;
	     } else {
	      if (lat < 65.25) {
	       return 325;
	      } else {
	       if (lat < 65.5) {
	        return 143;
	       } else {
	        return 325;
	       }
	      }
	     }
	    } else {
	     if (lng < -133.75) {
	      if (lat < 67.25) {
	       return 325;
	      } else {
	       return 143;
	      }
	     } else {
	      if (lng < -133.0) {
	       if (lat < 66.75) {
	        if (lng < -133.5) {
	         return 325;
	        } else {
	         if (lat < 66.25) {
	          if (lng < -133.25) {
	           return 143;
	          } else {
	           return 325;
	          }
	         } else {
	          return 143;
	         }
	        }
	       } else {
	        return 143;
	       }
	      } else {
	       if (lat < 66.25) {
	        if (lng < -132.75) {
	         return 325;
	        } else {
	         return 143;
	        }
	       } else {
	        return 143;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 65.0) {
	     if (lng < -131.5) {
	      if (lng < -132.0) {
	       return 325;
	      } else {
	       if (lng < -131.75) {
	        if (lat < 64.75) {
	         return 325;
	        } else {
	         return 143;
	        }
	       } else {
	        if (lat < 64.75) {
	         return 325;
	        } else {
	         return 143;
	        }
	       }
	      }
	     } else {
	      return 143;
	     }
	    } else {
	     return 143;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup11(double lat, double lng)
	{
	 if (lat < 56.25) {
	  if (lng < -129.5) {
	   if (lat < 50.5) {
	    return 0;
	   } else {
	    if (lat < 53.25) {
	     return 45;
	    } else {
	     if (lng < -132.25) {
	      if (lat < 54.75) {
	       if (lng < -133.75) {
	        return 0;
	       } else {
	        if (lng < -133.0) {
	         return 45;
	        } else {
	         if (lat < 54.5) {
	          return 45;
	         } else {
	          if (lng < -132.75) {
	           return 45;
	          } else {
	           return 23;
	          }
	         }
	        }
	       }
	      } else {
	       return 23;
	      }
	     } else {
	      if (lat < 54.75) {
	       if (lng < -131.0) {
	        if (lat < 54.0) {
	         return 45;
	        } else {
	         if (lng < -131.75) {
	          if (lat < 54.5) {
	           return 45;
	          } else {
	           return 23;
	          }
	         } else {
	          return 45;
	         }
	        }
	       } else {
	        return 45;
	       }
	      } else {
	       if (lng < -130.5) {
	        if (lng < -131.5) {
	         return 23;
	        } else {
	         if (lat < 55.5) {
	          if (lng < -131.25) {
	           return 196;
	          } else {
	           return 23;
	          }
	         } else {
	          return 23;
	         }
	        }
	       } else {
	        if (lat < 55.5) {
	         if (lng < -130.0) {
	          if (lat < 55.0) {
	           return 45;
	          } else {
	           return 23;
	          }
	         } else {
	          return 45;
	         }
	        } else {
	         if (lng < -130.0) {
	          return 23;
	         } else {
	          return 45;
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 48.75) {
	    if (lng < -126.75) {
	     return 0;
	    } else {
	     if (lat < 48.25) {
	      return 39;
	     } else {
	      if (lng < -125.25) {
	       return 0;
	      } else {
	       if (lng < -124.5) {
	        if (lng < -125.0) {
	         return 0;
	        } else {
	         if (lng < -124.75) {
	          return 45;
	         } else {
	          return 39;
	         }
	        }
	       } else {
	        if (lng < -124.25) {
	         return 39;
	        } else {
	         if (lng < -124.0) {
	          if (lat < 48.5) {
	           return 39;
	          } else {
	           return 45;
	          }
	         } else {
	          return 45;
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return 45;
	   }
	  }
	 } else {
	  if (lng < -129.5) {
	   return kdLookup10(lat,lng);
	  } else {
	   if (lat < 61.75) {
	    if (lng < -126.75) {
	     if (lat < 60.0) {
	      return 45;
	     } else {
	      if (lng < -127.25) {
	       return 325;
	      } else {
	       if (lat < 61.25) {
	        return 325;
	       } else {
	        if (lng < -127.0) {
	         if (lat < 61.5) {
	          return 325;
	         } else {
	          return 143;
	         }
	        } else {
	         return 143;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 60.0) {
	      return 45;
	     } else {
	      if (lng < -125.25) {
	       if (lat < 61.0) {
	        return 325;
	       } else {
	        return 143;
	       }
	      } else {
	       if (lat < 60.75) {
	        if (lng < -124.25) {
	         return 325;
	        } else {
	         if (lat < 60.25) {
	          if (lng < -124.0) {
	           return 325;
	          } else {
	           return 143;
	          }
	         } else {
	          if (lng < -124.0) {
	           if (lat < 60.5) {
	            return 325;
	           } else {
	            return 143;
	           }
	          } else {
	           return 143;
	          }
	         }
	        }
	       } else {
	        if (lng < -124.5) {
	         if (lat < 61.0) {
	          return 325;
	         } else {
	          return 143;
	         }
	        } else {
	         return 143;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < -128.0) {
	     if (lat < 62.75) {
	      if (lng < -128.75) {
	       if (lat < 62.25) {
	        return 325;
	       } else {
	        if (lng < -129.25) {
	         return 325;
	        } else {
	         if (lng < -129.0) {
	          if (lat < 62.5) {
	           return 143;
	          } else {
	           return 325;
	          }
	         } else {
	          return 143;
	         }
	        }
	       }
	      } else {
	       if (lat < 62.25) {
	        if (lng < -128.25) {
	         return 325;
	        } else {
	         if (lat < 62.0) {
	          return 325;
	         } else {
	          return 143;
	         }
	        }
	       } else {
	        return 143;
	       }
	      }
	     } else {
	      return 143;
	     }
	    } else {
	     return 143;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup12(double lat, double lng)
	{
	 if (lat < 50.5) {
	  if (lng < -121.0) {
	   if (lat < 48.5) {
	    return 39;
	   } else {
	    if (lng < -122.5) {
	     if (lat < 49.25) {
	      if (lng < -123.0) {
	       return 45;
	      } else {
	       if (lat < 48.75) {
	        return 39;
	       } else {
	        if (lng < -122.75) {
	         return 45;
	        } else {
	         return 39;
	        }
	       }
	      }
	     } else {
	      return 45;
	     }
	    } else {
	     if (lat < 49.25) {
	      return 39;
	     } else {
	      return 45;
	     }
	    }
	   }
	  } else {
	   if (lat < 49.0) {
	    return 39;
	   } else {
	    if (lng < -119.75) {
	     if (lat < 49.25) {
	      if (lng < -120.75) {
	       return 45;
	      } else {
	       return 39;
	      }
	     } else {
	      return 45;
	     }
	    } else {
	     if (lng < -119.0) {
	      if (lat < 49.25) {
	       return 39;
	      } else {
	       return 45;
	      }
	     } else {
	      if (lat < 49.25) {
	       return 39;
	      } else {
	       return 45;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 53.25) {
	   if (lng < -118.5) {
	    return 45;
	   } else {
	    if (lat < 52.25) {
	     return 45;
	    } else {
	     if (lat < 52.75) {
	      if (lat < 52.5) {
	       return 228;
	      } else {
	       return 45;
	      }
	     } else {
	      if (lat < 53.0) {
	       return 45;
	      } else {
	       return 228;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < -121.0) {
	    if (lat < 54.75) {
	     return 45;
	    } else {
	     if (lng < -122.5) {
	      if (lat < 55.5) {
	       return 45;
	      } else {
	       if (lng < -123.25) {
	        return 45;
	       } else {
	        if (lng < -123.0) {
	         if (lat < 55.75) {
	          return 45;
	         } else {
	          return 116;
	         }
	        } else {
	         if (lat < 55.75) {
	          if (lng < -122.75) {
	           return 45;
	          } else {
	           return 116;
	          }
	         } else {
	          return 116;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < -121.75) {
	       if (lat < 55.5) {
	        if (lng < -122.25) {
	         return 45;
	        } else {
	         if (lat < 55.25) {
	          return 45;
	         } else {
	          return 116;
	         }
	        }
	       } else {
	        return 116;
	       }
	      } else {
	       if (lat < 55.0) {
	        if (lng < -121.25) {
	         return 45;
	        } else {
	         return 116;
	        }
	       } else {
	        return 116;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 54.75) {
	     if (lng < -119.75) {
	      if (lat < 54.0) {
	       return 45;
	      } else {
	       if (lng < -120.5) {
	        if (lat < 54.5) {
	         return 45;
	        } else {
	         return 116;
	        }
	       } else {
	        if (lng < -120.25) {
	         if (lat < 54.25) {
	          return 45;
	         } else {
	          return 116;
	         }
	        } else {
	         if (lat < 54.25) {
	          if (lng < -120.0) {
	           return 116;
	          } else {
	           return 228;
	          }
	         } else {
	          if (lng < -120.0) {
	           return 116;
	          } else {
	           return 228;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < -119.25) {
	       if (lat < 53.5) {
	        return 45;
	       } else {
	        return 228;
	       }
	      } else {
	       return 228;
	      }
	     }
	    } else {
	     if (lng < -120.0) {
	      return 116;
	     } else {
	      return 228;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup13(double lat, double lng)
	{
	 if (lng < -115.5) {
	  if (lat < 47.75) {
	   if (lng < -116.75) {
	    return 39;
	   } else {
	    if (lat < 46.0) {
	     if (lng < -116.25) {
	      if (lat < 45.5) {
	       if (lng < -116.5) {
	        if (lat < 45.25) {
	         return 27;
	        } else {
	         return 39;
	        }
	       } else {
	        return 27;
	       }
	      } else {
	       if (lng < -116.5) {
	        return 39;
	       } else {
	        return 27;
	       }
	      }
	     } else {
	      if (lat < 45.5) {
	       return 27;
	      } else {
	       return 39;
	      }
	     }
	    } else {
	     return 39;
	    }
	   }
	  } else {
	   if (lng < -117.0) {
	    if (lat < 49.25) {
	     return 39;
	    } else {
	     return 45;
	    }
	   } else {
	    if (lat < 49.0) {
	     if (lng < -116.0) {
	      return 39;
	     } else {
	      if (lat < 48.0) {
	       if (lng < -115.75) {
	        return 39;
	       } else {
	        return 274;
	       }
	      } else {
	       return 274;
	      }
	     }
	    } else {
	     if (lng < -116.25) {
	      if (lat < 49.75) {
	       if (lng < -116.75) {
	        return 45;
	       } else {
	        if (lat < 49.25) {
	         return 39;
	        } else {
	         if (lng < -116.5) {
	          if (lat < 49.5) {
	           return 45;
	          } else {
	           return 254;
	          }
	         } else {
	          if (lat < 49.5) {
	           return 254;
	          } else {
	           return 228;
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < -116.75) {
	        return 45;
	       } else {
	        if (lat < 50.0) {
	         if (lng < -116.5) {
	          return 254;
	         } else {
	          return 228;
	         }
	        } else {
	         if (lng < -116.5) {
	          return 45;
	         } else {
	          if (lat < 50.25) {
	           return 45;
	          } else {
	           return 228;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 49.5) {
	       if (lng < -116.0) {
	        if (lat < 49.25) {
	         return 39;
	        } else {
	         return 254;
	        }
	       } else {
	        if (lng < -115.75) {
	         if (lat < 49.25) {
	          return 274;
	         } else {
	          return 228;
	         }
	        } else {
	         if (lat < 49.25) {
	          return 274;
	         } else {
	          return 228;
	         }
	        }
	       }
	      } else {
	       return 228;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 47.75) {
	   if (lng < -114.0) {
	    if (lat < 46.25) {
	     if (lng < -114.75) {
	      if (lat < 45.5) {
	       return 27;
	      } else {
	       if (lng < -115.25) {
	        return 39;
	       } else {
	        if (lat < 45.75) {
	         if (lng < -115.0) {
	          return 27;
	         } else {
	          return 39;
	         }
	        } else {
	         return 39;
	        }
	       }
	      }
	     } else {
	      if (lat < 45.5) {
	       return 27;
	      } else {
	       if (lng < -114.5) {
	        return 39;
	       } else {
	        if (lat < 45.75) {
	         return 27;
	        } else {
	         if (lng < -114.25) {
	          if (lat < 46.0) {
	           return 274;
	          } else {
	           return 39;
	          }
	         } else {
	          return 274;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -114.75) {
	      if (lat < 47.0) {
	       return 39;
	      } else {
	       if (lng < -115.25) {
	        if (lat < 47.5) {
	         return 39;
	        } else {
	         return 274;
	        }
	       } else {
	        if (lat < 47.25) {
	         if (lng < -115.0) {
	          return 39;
	         } else {
	          return 274;
	         }
	        } else {
	         return 274;
	        }
	       }
	      }
	     } else {
	      if (lat < 46.75) {
	       if (lng < -114.25) {
	        return 39;
	       } else {
	        return 274;
	       }
	      } else {
	       return 274;
	      }
	     }
	    }
	   } else {
	    if (lat < 45.75) {
	     if (lng < -113.25) {
	      if (lng < -113.75) {
	       return 27;
	      } else {
	       if (lat < 45.25) {
	        return 27;
	       } else {
	        if (lng < -113.5) {
	         if (lat < 45.5) {
	          return 27;
	         } else {
	          return 274;
	         }
	        } else {
	         return 274;
	        }
	       }
	      }
	     } else {
	      return 274;
	     }
	    } else {
	     return 274;
	    }
	   }
	  } else {
	   if (lng < -114.0) {
	    if (lat < 49.0) {
	     return 274;
	    } else {
	     if (lng < -115.25) {
	      if (lat < 49.25) {
	       return 274;
	      } else {
	       return 228;
	      }
	     } else {
	      return 228;
	     }
	    }
	   } else {
	    if (lat < 49.0) {
	     return 274;
	    } else {
	     return 228;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup14(double lat, double lng)
	{
	 if (lat < 56.25) {
	  if (lng < -118.25) {
	   return kdLookup12(lat,lng);
	  } else {
	   if (lat < 50.5) {
	    return kdLookup13(lat,lng);
	   } else {
	    if (lng < -116.5) {
	     if (lat < 52.0) {
	      if (lng < -117.5) {
	       if (lat < 51.5) {
	        return 45;
	       } else {
	        if (lng < -118.0) {
	         return 45;
	        } else {
	         if (lng < -117.75) {
	          if (lat < 51.75) {
	           return 45;
	          } else {
	           return 228;
	          }
	         } else {
	          return 228;
	         }
	        }
	       }
	      } else {
	       if (lat < 51.25) {
	        if (lng < -117.0) {
	         return 45;
	        } else {
	         if (lat < 50.75) {
	          return 45;
	         } else {
	          if (lng < -116.75) {
	           if (lat < 51.0) {
	            return 45;
	           } else {
	            return 228;
	           }
	          } else {
	           return 228;
	          }
	         }
	        }
	       } else {
	        if (lng < -117.25) {
	         if (lat < 51.5) {
	          return 45;
	         } else {
	          return 228;
	         }
	        } else {
	         return 228;
	        }
	       }
	      }
	     } else {
	      return 228;
	     }
	    } else {
	     return 228;
	    }
	   }
	  }
	 } else {
	  if (lng < -118.25) {
	   if (lat < 60.0) {
	    if (lng < -121.0) {
	     if (lat < 57.25) {
	      if (lng < -123.5) {
	       if (lat < 56.75) {
	        return 45;
	       } else {
	        if (lat < 57.0) {
	         return 116;
	        } else {
	         return 45;
	        }
	       }
	      } else {
	       if (lng < -122.25) {
	        return 116;
	       } else {
	        if (lng < -121.75) {
	         if (lat < 57.0) {
	          return 116;
	         } else {
	          return 45;
	         }
	        } else {
	         return 116;
	        }
	       }
	      }
	     } else {
	      return 45;
	     }
	    } else {
	     if (lat < 58.0) {
	      if (lng < -120.0) {
	       if (lat < 57.25) {
	        return 116;
	       } else {
	        if (lng < -120.5) {
	         if (lat < 57.5) {
	          return 116;
	         } else {
	          return 45;
	         }
	        } else {
	         if (lat < 57.5) {
	          if (lng < -120.25) {
	           return 116;
	          } else {
	           return 45;
	          }
	         } else {
	          return 45;
	         }
	        }
	       }
	      } else {
	       return 228;
	      }
	     } else {
	      if (lng < -120.0) {
	       return 45;
	      } else {
	       return 228;
	      }
	     }
	    }
	   } else {
	    return 143;
	   }
	  } else {
	   if (lat < 61.75) {
	    if (lng < -115.5) {
	     if (lat < 60.0) {
	      return 228;
	     } else {
	      return 143;
	     }
	    } else {
	     if (lat < 60.0) {
	      return 228;
	     } else {
	      return 143;
	     }
	    }
	   } else {
	    if (lng < -115.5) {
	     if (lat < 66.5) {
	      return 143;
	     } else {
	      if (lng < -117.0) {
	       if (lng < -117.75) {
	        if (lat < 67.25) {
	         return 143;
	        } else {
	         return 108;
	        }
	       } else {
	        if (lat < 67.0) {
	         return 143;
	        } else {
	         if (lng < -117.5) {
	          if (lat < 67.25) {
	           return 143;
	          } else {
	           return 108;
	          }
	         } else {
	          return 108;
	         }
	        }
	       }
	      } else {
	       if (lng < -116.25) {
	        if (lat < 66.75) {
	         return 143;
	        } else {
	         return 108;
	        }
	       } else {
	        return 108;
	       }
	      }
	     }
	    } else {
	     if (lat < 65.5) {
	      return 143;
	     } else {
	      if (lng < -114.0) {
	       if (lat < 66.25) {
	        if (lng < -114.75) {
	         return 143;
	        } else {
	         if (lng < -114.5) {
	          if (lat < 66.0) {
	           return 143;
	          } else {
	           return 108;
	          }
	         } else {
	          if (lat < 66.0) {
	           return 143;
	          } else {
	           return 108;
	          }
	         }
	        }
	       } else {
	        return 108;
	       }
	      } else {
	       if (lat < 65.75) {
	        if (lng < -113.5) {
	         return 143;
	        } else {
	         return 108;
	        }
	       } else {
	        return 108;
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup15(double lat, double lng)
	{
	 if (lat < 67.5) {
	  if (lng < -123.75) {
	   return kdLookup11(lat,lng);
	  } else {
	   return kdLookup14(lat,lng);
	  }
	 } else {
	  if (lng < -123.75) {
	   return 143;
	  } else {
	   if (lat < 78.75) {
	    if (lng < -118.25) {
	     if (lat < 73.0) {
	      if (lng < -121.0) {
	       if (lat < 70.25) {
	        if (lng < -122.5) {
	         return 143;
	        } else {
	         if (lat < 68.75) {
	          if (lng < -121.75) {
	           return 143;
	          } else {
	           if (lat < 68.5) {
	            return 143;
	           } else {
	            return 108;
	           }
	          }
	         } else {
	          if (lng < -121.75) {
	           return 143;
	          } else {
	           if (lat < 69.75) {
	            return 108;
	           } else {
	            return 143;
	           }
	          }
	         }
	        }
	       } else {
	        return 143;
	       }
	      } else {
	       if (lat < 70.25) {
	        if (lng < -119.75) {
	         if (lat < 68.75) {
	          if (lng < -120.5) {
	           if (lat < 68.25) {
	            return 143;
	           } else {
	            return 108;
	           }
	          } else {
	           if (lat < 68.0) {
	            return 143;
	           } else {
	            return 108;
	           }
	          }
	         } else {
	          return 108;
	         }
	        } else {
	         if (lat < 68.75) {
	          if (lng < -119.0) {
	           if (lat < 67.75) {
	            return 143;
	           } else {
	            return 108;
	           }
	          } else {
	           return 108;
	          }
	         } else {
	          return 108;
	         }
	        }
	       } else {
	        return 143;
	       }
	      }
	     } else {
	      return 143;
	     }
	    } else {
	     if (lat < 70.5) {
	      if (lng < -115.5) {
	       if (lat < 69.25) {
	        return 108;
	       } else {
	        if (lng < -117.0) {
	         if (lng < -117.75) {
	          return 108;
	         } else {
	          return 143;
	         }
	        } else {
	         if (lng < -116.25) {
	          if (lat < 69.75) {
	           if (lng < -116.75) {
	            return 143;
	           } else {
	            return 108;
	           }
	          } else {
	           if (lng < -116.75) {
	            if (lat < 70.0) {
	             return 143;
	            } else {
	             return 108;
	            }
	           } else {
	            return 108;
	           }
	          }
	         } else {
	          return 108;
	         }
	        }
	       }
	      } else {
	       if (lng < -114.0) {
	        if (lat < 69.0) {
	         return 108;
	        } else {
	         if (lng < -114.75) {
	          if (lat < 70.25) {
	           return 108;
	          } else {
	           return 143;
	          }
	         } else {
	          if (lat < 69.75) {
	           return 108;
	          } else {
	           if (lng < -114.5) {
	            if (lat < 70.25) {
	             return 108;
	            } else {
	             return 143;
	            }
	           } else {
	            if (lat < 70.25) {
	             return 108;
	            } else {
	             return 143;
	            }
	           }
	          }
	         }
	        }
	       } else {
	        if (lat < 70.0) {
	         return 108;
	        } else {
	         if (lng < -113.25) {
	          if (lng < -113.75) {
	           if (lat < 70.25) {
	            return 108;
	           } else {
	            return 143;
	           }
	          } else {
	           if (lng < -113.5) {
	            if (lat < 70.25) {
	             return 108;
	            } else {
	             return 143;
	            }
	           } else {
	            if (lat < 70.25) {
	             return 108;
	            } else {
	             return 143;
	            }
	           }
	          }
	         } else {
	          if (lng < -113.0) {
	           if (lat < 70.25) {
	            return 108;
	           } else {
	            return 143;
	           }
	          } else {
	           if (lng < -112.75) {
	            return 108;
	           } else {
	            return 143;
	           }
	          }
	         }
	        }
	       }
	      }
	     } else {
	      return 143;
	     }
	    }
	   } else {
	    return 0;
	   }
	  }
	 }
	}

	private  int kdLookup16(double lat, double lng)
	{
	 if (lat < 50.5) {
	  if (lng < -104.25) {
	   if (lat < 49.0) {
	    return 274;
	   } else {
	    return 309;
	   }
	  } else {
	   if (lat < 47.75) {
	    if (lng < -102.0) {
	     return 274;
	    } else {
	     if (lat < 46.75) {
	      return 274;
	     } else {
	      if (lat < 47.25) {
	       if (lng < -101.75) {
	        if (lat < 47.0) {
	         return 52;
	        } else {
	         return 71;
	        }
	       } else {
	        if (lng < -101.5) {
	         if (lat < 47.0) {
	          return 52;
	         } else {
	          return 105;
	         }
	        } else {
	         if (lat < 47.0) {
	          return 52;
	         } else {
	          return 105;
	         }
	        }
	       }
	      } else {
	       return 71;
	      }
	     }
	    }
	   } else {
	    if (lng < -102.75) {
	     if (lat < 49.0) {
	      if (lng < -103.75) {
	       if (lat < 48.25) {
	        if (lng < -104.0) {
	         return 274;
	        } else {
	         if (lat < 48.0) {
	          return 274;
	         } else {
	          return 161;
	         }
	        }
	       } else {
	        if (lat < 48.5) {
	         if (lng < -104.0) {
	          return 274;
	         } else {
	          return 161;
	         }
	        } else {
	         if (lng < -104.0) {
	          return 274;
	         } else {
	          return 161;
	         }
	        }
	       }
	      } else {
	       return 161;
	      }
	     } else {
	      return 309;
	     }
	    } else {
	     if (lat < 49.0) {
	      return 161;
	     } else {
	      return 309;
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < -102.5) {
	   return 309;
	  } else {
	   if (lat < 53.25) {
	    if (lat < 51.75) {
	     if (lng < -101.5) {
	      return 309;
	     } else {
	      if (lat < 50.75) {
	       return 309;
	      } else {
	       return 285;
	      }
	     }
	    } else {
	     if (lat < 52.5) {
	      if (lng < -101.5) {
	       return 309;
	      } else {
	       return 285;
	      }
	     } else {
	      if (lng < -101.5) {
	       return 309;
	      } else {
	       return 285;
	      }
	     }
	    }
	   } else {
	    if (lat < 54.75) {
	     if (lat < 54.0) {
	      if (lng < -101.75) {
	       return 309;
	      } else {
	       if (lat < 53.5) {
	        if (lng < -101.5) {
	         return 309;
	        } else {
	         return 285;
	        }
	       } else {
	        if (lng < -101.5) {
	         if (lat < 53.75) {
	          return 309;
	         } else {
	          return 285;
	         }
	        } else {
	         return 285;
	        }
	       }
	      }
	     } else {
	      if (lng < -102.0) {
	       if (lat < 54.25) {
	        return 309;
	       } else {
	        if (lng < -102.25) {
	         if (lat < 54.5) {
	          return 309;
	         } else {
	          return 285;
	         }
	        } else {
	         return 285;
	        }
	       }
	      } else {
	       if (lng < -101.75) {
	        if (lat < 54.25) {
	         return 309;
	        } else {
	         return 285;
	        }
	       } else {
	        return 285;
	       }
	      }
	     }
	    } else {
	     if (lat < 55.5) {
	      if (lng < -102.0) {
	       if (lat < 55.0) {
	        return 285;
	       } else {
	        if (lng < -102.25) {
	         return 309;
	        } else {
	         if (lat < 55.25) {
	          return 285;
	         } else {
	          return 309;
	         }
	        }
	       }
	      } else {
	       if (lng < -101.75) {
	        if (lat < 55.25) {
	         return 285;
	        } else {
	         return 309;
	        }
	       } else {
	        return 285;
	       }
	      }
	     } else {
	      if (lng < -102.0) {
	       return 309;
	      } else {
	       if (lng < -101.75) {
	        if (lat < 56.0) {
	         return 309;
	        } else {
	         return 285;
	        }
	       } else {
	        return 285;
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup17(double lat, double lng)
	{
	 if (lat < 56.25) {
	  if (lng < -107.0) {
	   if (lat < 50.5) {
	    if (lng < -109.75) {
	     if (lat < 49.0) {
	      return 274;
	     } else {
	      if (lng < -111.25) {
	       return 228;
	      } else {
	       if (lng < -110.5) {
	        if (lat < 49.25) {
	         return 274;
	        } else {
	         return 228;
	        }
	       } else {
	        if (lat < 49.75) {
	         if (lng < -110.0) {
	          return 228;
	         } else {
	          return 309;
	         }
	        } else {
	         if (lng < -110.0) {
	          return 228;
	         } else {
	          return 309;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 49.0) {
	      return 274;
	     } else {
	      return 309;
	     }
	    }
	   } else {
	    if (lat < 53.25) {
	     if (lng < -109.75) {
	      if (lng < -110.0) {
	       return 228;
	      } else {
	       if (lat < 52.75) {
	        return 309;
	       } else {
	        return 228;
	       }
	      }
	     } else {
	      if (lng < -108.75) {
	       if (lat < 52.75) {
	        return 309;
	       } else {
	        if (lng < -109.25) {
	         return 228;
	        } else {
	         if (lng < -109.0) {
	          if (lat < 53.0) {
	           return 309;
	          } else {
	           return 228;
	          }
	         } else {
	          if (lat < 53.0) {
	           return 309;
	          } else {
	           return 228;
	          }
	         }
	        }
	       }
	      } else {
	       return 309;
	      }
	     }
	    } else {
	     if (lng < -109.75) {
	      if (lat < 54.75) {
	       if (lng < -110.0) {
	        return 228;
	       } else {
	        if (lat < 53.75) {
	         return 228;
	        } else {
	         return 309;
	        }
	       }
	      } else {
	       if (lng < -110.0) {
	        return 228;
	       } else {
	        if (lat < 55.75) {
	         return 309;
	        } else {
	         if (lat < 56.0) {
	          return 228;
	         } else {
	          return 309;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 53.75) {
	       if (lng < -109.0) {
	        if (lng < -109.5) {
	         return 228;
	        } else {
	         if (lng < -109.25) {
	          if (lat < 53.5) {
	           return 228;
	          } else {
	           return 309;
	          }
	         } else {
	          if (lat < 53.5) {
	           return 228;
	          } else {
	           return 309;
	          }
	         }
	        }
	       } else {
	        return 309;
	       }
	      } else {
	       return 309;
	      }
	     }
	    }
	   }
	  } else {
	   return kdLookup16(lat,lng);
	  }
	 } else {
	  if (lng < -107.0) {
	   if (lat < 61.75) {
	    if (lng < -109.75) {
	     if (lat < 59.0) {
	      if (lng < -110.0) {
	       return 228;
	      } else {
	       return 309;
	      }
	     } else {
	      if (lng < -111.25) {
	       if (lat < 60.0) {
	        return 228;
	       } else {
	        return 143;
	       }
	      } else {
	       if (lat < 60.0) {
	        if (lng < -110.0) {
	         return 228;
	        } else {
	         return 309;
	        }
	       } else {
	        return 143;
	       }
	      }
	     }
	    } else {
	     if (lat < 60.0) {
	      return 309;
	     } else {
	      return 143;
	     }
	    }
	   } else {
	    if (lat < 64.75) {
	     return 143;
	    } else {
	     if (lng < -110.25) {
	      if (lat < 65.5) {
	       if (lng < -111.0) {
	        return 143;
	       } else {
	        if (lng < -110.75) {
	         if (lat < 65.25) {
	          return 143;
	         } else {
	          return 108;
	         }
	        } else {
	         if (lat < 65.0) {
	          return 143;
	         } else {
	          return 108;
	         }
	        }
	       }
	      } else {
	       return 108;
	      }
	     } else {
	      return 108;
	     }
	    }
	   }
	  } else {
	   if (lat < 61.75) {
	    if (lng < -104.25) {
	     if (lat < 60.0) {
	      return 309;
	     } else {
	      return 143;
	     }
	    } else {
	     if (lat < 59.0) {
	      if (lng < -101.75) {
	       return 309;
	      } else {
	       return 285;
	      }
	     } else {
	      if (lng < -102.75) {
	       if (lat < 60.0) {
	        return 309;
	       } else {
	        return 143;
	       }
	      } else {
	       if (lat < 60.25) {
	        if (lng < -102.0) {
	         if (lat < 60.0) {
	          return 309;
	         } else {
	          return 143;
	         }
	        } else {
	         if (lat < 60.0) {
	          return 285;
	         } else {
	          return 308;
	         }
	        }
	       } else {
	        if (lng < -102.0) {
	         return 143;
	        } else {
	         return 308;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < -104.25) {
	     if (lat < 64.5) {
	      return 143;
	     } else {
	      if (lat < 64.75) {
	       if (lng < -106.75) {
	        return 143;
	       } else {
	        return 108;
	       }
	      } else {
	       return 108;
	      }
	     }
	    } else {
	     if (lat < 64.5) {
	      if (lng < -102.0) {
	       return 143;
	      } else {
	       return 308;
	      }
	     } else {
	      if (lng < -102.0) {
	       return 108;
	      } else {
	       if (lat < 67.0) {
	        return 308;
	       } else {
	        return 108;
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup18(double lat, double lng)
	{
	 if (lng < -95.75) {
	  if (lat < 49.0) {
	   if (lng < -100.25) {
	    if (lat < 47.0) {
	     if (lat < 46.0) {
	      return 274;
	     } else {
	      if (lng < -100.75) {
	       if (lat < 46.5) {
	        if (lng < -101.0) {
	         if (lat < 46.25) {
	          return 161;
	         } else {
	          return 274;
	         }
	        } else {
	         return 161;
	        }
	       } else {
	        return 52;
	       }
	      } else {
	       if (lat < 46.5) {
	        return 161;
	       } else {
	        if (lng < -100.5) {
	         if (lat < 46.75) {
	          return 52;
	         } else {
	          return 161;
	         }
	        } else {
	         return 161;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 47.5) {
	      if (lng < -100.75) {
	       return 105;
	      } else {
	       return 161;
	      }
	     } else {
	      return 161;
	     }
	    }
	   } else {
	    return 161;
	   }
	  } else {
	   return 285;
	  }
	 } else {
	  if (lat < 49.5) {
	   if (lng < -93.0) {
	    if (lat < 48.75) {
	     return 161;
	    } else {
	     if (lng < -94.5) {
	      if (lng < -95.25) {
	       if (lat < 49.0) {
	        return 161;
	       } else {
	        return 285;
	       }
	      } else {
	       if (lng < -95.0) {
	        if (lat < 49.0) {
	         return 161;
	        } else {
	         return 285;
	        }
	       } else {
	        if (lat < 49.25) {
	         return 161;
	        } else {
	         if (lng < -94.75) {
	          return 161;
	         } else {
	          return 285;
	         }
	        }
	       }
	      }
	     } else {
	      return 285;
	     }
	    }
	   } else {
	    if (lat < 47.25) {
	     return 161;
	    } else {
	     if (lng < -91.5) {
	      if (lat < 48.25) {
	       return 161;
	      } else {
	       if (lng < -92.25) {
	        if (lat < 48.75) {
	         if (lng < -92.5) {
	          return 161;
	         } else {
	          if (lat < 48.5) {
	           return 161;
	          } else {
	           return 285;
	          }
	         }
	        } else {
	         return 285;
	        }
	       } else {
	        if (lat < 48.75) {
	         if (lng < -92.0) {
	          if (lat < 48.5) {
	           return 161;
	          } else {
	           return 285;
	          }
	         } else {
	          return 224;
	         }
	        } else {
	         if (lng < -91.75) {
	          return 285;
	         } else {
	          if (lat < 49.0) {
	           return 224;
	          } else {
	           return 285;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 48.25) {
	       return 161;
	      } else {
	       if (lng < -90.75) {
	        if (lat < 49.0) {
	         return 224;
	        } else {
	         if (lng < -91.0) {
	          return 285;
	         } else {
	          return 239;
	         }
	        }
	       } else {
	        if (lat < 48.5) {
	         return 224;
	        } else {
	         return 239;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 56.0) {
	    if (lat < 55.75) {
	     if (lat < 55.5) {
	      if (lat < 55.25) {
	       if (lng < -90.75) {
	        return 285;
	       } else {
	        if (lat < 55.0) {
	         if (lat < 54.75) {
	          if (lat < 54.5) {
	           if (lat < 54.25) {
	            if (lat < 51.75) {
	             if (lat < 51.0) {
	              return 285;
	             } else {
	              if (lng < -90.5) {
	               if (lat < 51.25) {
	                return 285;
	               } else {
	                return 239;
	               }
	              } else {
	               return 239;
	              }
	             }
	            } else {
	             if (lat < 52.0) {
	              if (lng < -90.5) {
	               return 285;
	              } else {
	               return 239;
	              }
	             } else {
	              return 285;
	             }
	            }
	           } else {
	            return 285;
	           }
	          } else {
	           return 285;
	          }
	         } else {
	          return 285;
	         }
	        } else {
	         return 285;
	        }
	       }
	      } else {
	       return 285;
	      }
	     } else {
	      return 285;
	     }
	    } else {
	     return 285;
	    }
	   } else {
	    return 285;
	   }
	  }
	 }
	}

	private  int kdLookup19(double lat, double lng)
	{
	 if (lng < -95.75) {
	  if (lat < 73.0) {
	   if (lng < -98.5) {
	    return 108;
	   } else {
	    if (lat < 71.25) {
	     return 108;
	    } else {
	     if (lng < -97.25) {
	      return 108;
	     } else {
	      if (lat < 72.0) {
	       return 108;
	      } else {
	       if (lng < -96.5) {
	        if (lat < 72.75) {
	         return 108;
	        } else {
	         return 308;
	        }
	       } else {
	        return 108;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 75.75) {
	    if (lng < -98.5) {
	     if (lng < -100.0) {
	      if (lat < 73.25) {
	       return 108;
	      } else {
	       return 308;
	      }
	     } else {
	      if (lat < 73.25) {
	       if (lng < -99.0) {
	        return 108;
	       } else {
	        return 308;
	       }
	      } else {
	       return 308;
	      }
	     }
	    } else {
	     if (lng < -97.25) {
	      return 308;
	     } else {
	      if (lat < 74.25) {
	       return 308;
	      } else {
	       if (lng < -96.5) {
	        return 308;
	       } else {
	        if (lat < 75.0) {
	         return 154;
	        } else {
	         if (lng < -96.25) {
	          if (lat < 75.25) {
	           return 154;
	          } else {
	           return 308;
	          }
	         } else {
	          if (lat < 75.5) {
	           return 154;
	          } else {
	           return 308;
	          }
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return 308;
	   }
	  }
	 } else {
	  if (lat < 73.0) {
	   if (lng < -93.0) {
	    if (lat < 71.75) {
	     return 108;
	    } else {
	     if (lng < -94.5) {
	      if (lng < -95.25) {
	       return 308;
	      } else {
	       if (lat < 72.5) {
	        return 108;
	       } else {
	        return 308;
	       }
	      }
	     } else {
	      if (lng < -93.75) {
	       if (lat < 72.5) {
	        return 108;
	       } else {
	        return 308;
	       }
	      } else {
	       if (lat < 72.25) {
	        return 108;
	       } else {
	        return 308;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 70.25) {
	     return 108;
	    } else {
	     if (lng < -91.5) {
	      if (lat < 71.5) {
	       return 108;
	      } else {
	       if (lng < -92.75) {
	        if (lat < 72.25) {
	         return 108;
	        } else {
	         return 308;
	        }
	       } else {
	        return 308;
	       }
	      }
	     } else {
	      if (lat < 71.5) {
	       return 108;
	      } else {
	       return 308;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < -93.0) {
	    if (lat < 75.75) {
	     if (lng < -94.5) {
	      if (lat < 74.5) {
	       return 308;
	      } else {
	       return 154;
	      }
	     } else {
	      if (lat < 74.5) {
	       return 308;
	      } else {
	       return 154;
	      }
	     }
	    } else {
	     if (lat < 77.25) {
	      if (lng < -94.5) {
	       if (lat < 76.25) {
	        if (lng < -95.25) {
	         return 154;
	        } else {
	         if (lng < -95.0) {
	          if (lat < 76.0) {
	           return 154;
	          } else {
	           return 308;
	          }
	         } else {
	          if (lng < -94.75) {
	           if (lat < 76.0) {
	            return 154;
	           } else {
	            return 308;
	           }
	          } else {
	           return 154;
	          }
	         }
	        }
	       } else {
	        return 308;
	       }
	      } else {
	       if (lng < -94.0) {
	        if (lat < 76.25) {
	         return 154;
	        } else {
	         return 308;
	        }
	       } else {
	        return 308;
	       }
	      }
	     } else {
	      return 308;
	     }
	    }
	   } else {
	    return 308;
	   }
	  }
	 }
	}

	private  int kdLookup20(double lat, double lng)
	{
	 if (lat < 67.5) {
	  if (lng < -101.25) {
	   return kdLookup17(lat,lng);
	  } else {
	   if (lat < 56.25) {
	    return kdLookup18(lat,lng);
	   } else {
	    if (lng < -95.75) {
	     if (lat < 61.75) {
	      if (lng < -98.5) {
	       if (lat < 60.0) {
	        return 285;
	       } else {
	        return 308;
	       }
	      } else {
	       if (lat < 60.0) {
	        return 285;
	       } else {
	        return 308;
	       }
	      }
	     } else {
	      if (lat < 67.0) {
	       return 308;
	      } else {
	       return 108;
	      }
	     }
	    } else {
	     if (lat < 61.75) {
	      if (lng < -93.0) {
	       if (lat < 59.25) {
	        return 285;
	       } else {
	        if (lng < -94.5) {
	         if (lat < 60.0) {
	          return 285;
	         } else {
	          return 308;
	         }
	        } else {
	         return 308;
	        }
	       }
	      } else {
	       return 285;
	      }
	     } else {
	      if (lng < -93.0) {
	       if (lat < 67.0) {
	        return 308;
	       } else {
	        return 108;
	       }
	      } else {
	       if (lat < 64.5) {
	        return 308;
	       } else {
	        if (lng < -91.5) {
	         if (lat < 67.0) {
	          return 308;
	         } else {
	          return 108;
	         }
	        } else {
	         if (lat < 67.0) {
	          return 308;
	         } else {
	          return 108;
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < -101.25) {
	   if (lat < 78.75) {
	    if (lng < -107.0) {
	     if (lat < 73.0) {
	      if (lng < -109.75) {
	       if (lat < 70.25) {
	        if (lng < -111.25) {
	         if (lat < 70.0) {
	          return 108;
	         } else {
	          if (lng < -112.25) {
	           return 143;
	          } else {
	           return 108;
	          }
	         }
	        } else {
	         return 108;
	        }
	       } else {
	        return 143;
	       }
	      } else {
	       return 108;
	      }
	     } else {
	      if (lat < 75.75) {
	       if (lng < -109.75) {
	        return 143;
	       } else {
	        return 108;
	       }
	      } else {
	       if (lng < -109.75) {
	        return 143;
	       } else {
	        return 108;
	       }
	      }
	     }
	    } else {
	     if (lat < 73.0) {
	      return 108;
	     } else {
	      if (lng < -104.25) {
	       return 108;
	      } else {
	       if (lat < 75.75) {
	        if (lng < -102.75) {
	         return 108;
	        } else {
	         if (lat < 74.25) {
	          if (lng < -102.0) {
	           return 108;
	          } else {
	           if (lat < 73.5) {
	            if (lng < -101.75) {
	             return 108;
	            } else {
	             if (lng < -101.5) {
	              return 108;
	             } else {
	              if (lat < 73.25) {
	               return 108;
	              } else {
	               return 308;
	              }
	             }
	            }
	           } else {
	            return 308;
	           }
	          }
	         } else {
	          return 308;
	         }
	        }
	       } else {
	        if (lng < -102.75) {
	         return 108;
	        } else {
	         if (lat < 77.25) {
	          if (lng < -102.0) {
	           if (lat < 76.5) {
	            if (lng < -102.25) {
	             return 108;
	            } else {
	             if (lat < 76.0) {
	              return 308;
	             } else {
	              if (lat < 76.25) {
	               return 108;
	              } else {
	               return 308;
	              }
	             }
	            }
	           } else {
	            return 108;
	           }
	          } else {
	           return 308;
	          }
	         } else {
	          if (lng < -102.0) {
	           return 108;
	          } else {
	           return 308;
	          }
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < -107.0) {
	     if (lat < 84.25) {
	      if (lng < -109.75) {
	       return 143;
	      } else {
	       return 108;
	      }
	     } else {
	      return 0;
	     }
	    } else {
	     if (lat < 84.25) {
	      if (lng < -104.25) {
	       return 108;
	      } else {
	       if (lat < 81.5) {
	        if (lng < -102.75) {
	         return 108;
	        } else {
	         if (lat < 80.0) {
	          if (lng < -102.0) {
	           return 108;
	          } else {
	           return 308;
	          }
	         } else {
	          return 0;
	         }
	        }
	       } else {
	        return 0;
	       }
	      }
	     } else {
	      return 0;
	     }
	    }
	   }
	  } else {
	   if (lat < 78.75) {
	    return kdLookup19(lat,lng);
	   } else {
	    return 308;
	   }
	  }
	 }
	}

	private  int kdLookup21(double lat, double lng)
	{
	 if (lat < 0.0) {
	  if (lng < -135.0) {
	   if (lat < -45.0) {
	    return 122;
	   } else {
	    if (lng < -157.5) {
	     if (lat < -22.5) {
	      return 59;
	     } else {
	      if (lng < -168.75) {
	       if (lat < -11.25) {
	        if (lng < -174.5) {
	         return 148;
	        } else {
	         if (lat < -17.0) {
	          return 0;
	         } else {
	          if (lng < -171.75) {
	           return 200;
	          } else {
	           if (lat < -14.25) {
	            return 44;
	           } else {
	            if (lng < -170.25) {
	             return 200;
	            } else {
	             return 44;
	            }
	           }
	          }
	         }
	        }
	       } else {
	        return 0;
	       }
	      } else {
	       return 361;
	      }
	     }
	    } else {
	     if (lat < -22.5) {
	      return 0;
	     } else {
	      if (lng < -146.25) {
	       return 313;
	      } else {
	       if (lat < -11.25) {
	        return 313;
	       } else {
	        return 102;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < -45.0) {
	    return 122;
	   } else {
	    return 114;
	   }
	  }
	 } else {
	  if (lng < -135.0) {
	   if (lat < 45.0) {
	    if (lng < -157.5) {
	     return 245;
	    } else {
	     if (lat < 22.5) {
	      if (lng < -146.25) {
	       if (lat < 11.25) {
	        return 238;
	       } else {
	        return 245;
	       }
	      } else {
	       return 0;
	      }
	     } else {
	      return 0;
	     }
	    }
	   } else {
	    if (lng < -157.5) {
	     if (lat < 67.5) {
	      if (lng < -168.75) {
	       if (lat < 56.25) {
	        if (lng < -174.5) {
	         return 159;
	        } else {
	         if (lat < 50.5) {
	          return 0;
	         } else {
	          if (lng < -171.75) {
	           return 0;
	          } else {
	           if (lat < 53.25) {
	            if (lng < -170.25) {
	             return 159;
	            } else {
	             if (lat < 51.75) {
	              return 0;
	             } else {
	              if (lng < -169.5) {
	               return 159;
	              } else {
	               if (lat < 52.5) {
	                return 0;
	               } else {
	                if (lng < -169.25) {
	                 return 159;
	                } else {
	                 return 135;
	                }
	               }
	              }
	             }
	            }
	           } else {
	            return 159;
	           }
	          }
	         }
	        }
	       } else {
	        if (lng < -174.5) {
	         return 366;
	        } else {
	         if (lat < 61.75) {
	          return 135;
	         } else {
	          if (lng < -171.75) {
	           if (lat < 64.5) {
	            if (lng < -173.25) {
	             return 366;
	            } else {
	             if (lat < 63.0) {
	              return 0;
	             } else {
	              if (lng < -172.5) {
	               return 366;
	              } else {
	               if (lat < 63.75) {
	                return 135;
	               } else {
	                return 366;
	               }
	              }
	             }
	            }
	           } else {
	            return 366;
	           }
	          } else {
	           if (lat < 64.5) {
	            return 135;
	           } else {
	            return 366;
	           }
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < 56.25) {
	        if (lng < -163.25) {
	         return 135;
	        } else {
	         if (lat < 50.5) {
	          return 0;
	         } else {
	          if (lng < -160.5) {
	           if (lat < 53.25) {
	            return 0;
	           } else {
	            if (lat < 54.75) {
	             return 135;
	            } else {
	             if (lng < -162.0) {
	              return 135;
	             } else {
	              return 371;
	             }
	            }
	           }
	          } else {
	           return 371;
	          }
	         }
	        }
	       } else {
	        if (lng < -163.25) {
	         return 135;
	        } else {
	         if (lat < 61.75) {
	          if (lng < -161.75) {
	           if (lat < 59.0) {
	            return 371;
	           } else {
	            if (lat < 60.25) {
	             if (lng < -162.25) {
	              return 135;
	             } else {
	              return 371;
	             }
	            } else {
	             if (lng < -162.0) {
	              return 135;
	             } else {
	              return 371;
	             }
	            }
	           }
	          } else {
	           return 371;
	          }
	         } else {
	          if (lng < -161.0) {
	           if (lat < 64.5) {
	            if (lat < 63.0) {
	             if (lng < -162.0) {
	              return 135;
	             } else {
	              return 371;
	             }
	            } else {
	             if (lng < -162.25) {
	              return 135;
	             } else {
	              if (lat < 63.75) {
	               if (lng < -161.75) {
	                if (lat < 63.25) {
	                 if (lng < -162.0) {
	                  return 135;
	                 } else {
	                  return 371;
	                 }
	                } else {
	                 if (lng < -162.0) {
	                  return 135;
	                 } else {
	                  return 371;
	                 }
	                }
	               } else {
	                return 371;
	               }
	              } else {
	               if (lng < -161.75) {
	                return 135;
	               } else {
	                return 371;
	               }
	              }
	             }
	            }
	           } else {
	            if (lat < 66.0) {
	             if (lng < -162.0) {
	              return 135;
	             } else {
	              return 371;
	             }
	            } else {
	             if (lng < -162.25) {
	              return 135;
	             } else {
	              if (lat < 66.75) {
	               if (lng < -161.75) {
	                if (lat < 66.25) {
	                 if (lng < -162.0) {
	                  return 135;
	                 } else {
	                  return 371;
	                 }
	                } else {
	                 if (lng < -162.0) {
	                  return 135;
	                 } else {
	                  return 371;
	                 }
	                }
	               } else {
	                return 371;
	               }
	              } else {
	               if (lng < -162.0) {
	                return 135;
	               } else {
	                return 371;
	               }
	              }
	             }
	            }
	           }
	          } else {
	           return 371;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < -168.75) {
	       return 366;
	      } else {
	       if (lat < 78.75) {
	        if (lng < -163.25) {
	         return 135;
	        } else {
	         if (lat < 73.0) {
	          if (lng < -160.5) {
	           if (lat < 70.25) {
	            if (lng < -162.0) {
	             return 135;
	            } else {
	             return 371;
	            }
	           } else {
	            if (lng < -162.0) {
	             return 135;
	            } else {
	             return 371;
	            }
	           }
	          } else {
	           return 371;
	          }
	         } else {
	          return 0;
	         }
	        }
	       } else {
	        return 0;
	       }
	      }
	     }
	    } else {
	     if (lat < 67.5) {
	      if (lng < -141.0) {
	       return 371;
	      } else {
	       if (lat < 56.25) {
	        return 0;
	       } else {
	        return kdLookup1(lat,lng);
	       }
	      }
	     } else {
	      if (lng < -146.25) {
	       return 371;
	      } else {
	       if (lat < 78.75) {
	        if (lng < -140.75) {
	         return 371;
	        } else {
	         if (lat < 73.0) {
	          if (lng < -138.0) {
	           return 325;
	          } else {
	           if (lat < 70.25) {
	            if (lng < -136.5) {
	             return 325;
	            } else {
	             if (lat < 68.75) {
	              if (lng < -136.0) {
	               if (lat < 68.0) {
	                if (lng < -136.25) {
	                 return 325;
	                } else {
	                 if (lat < 67.75) {
	                  return 325;
	                 } else {
	                  return 143;
	                 }
	                }
	               } else {
	                if (lat < 68.25) {
	                 if (lng < -136.25) {
	                  return 325;
	                 } else {
	                  return 143;
	                 }
	                } else {
	                 if (lng < -136.25) {
	                  return 325;
	                 } else {
	                  return 143;
	                 }
	                }
	               }
	              } else {
	               return 143;
	              }
	             } else {
	              if (lng < -135.75) {
	               if (lat < 69.5) {
	                if (lng < -136.25) {
	                 return 325;
	                } else {
	                 return 143;
	                }
	               } else {
	                return 143;
	               }
	              } else {
	               return 143;
	              }
	             }
	            }
	           } else {
	            return 0;
	           }
	          }
	         } else {
	          return 0;
	         }
	        }
	       } else {
	        return 0;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 45.0) {
	    return kdLookup9(lat,lng);
	   } else {
	    if (lng < -112.5) {
	     return kdLookup15(lat,lng);
	    } else {
	     return kdLookup20(lat,lng);
	    }
	   }
	  }
	 }
	}

	private  int kdLookup22(double lat, double lng)
	{
	 if (lat < -50.75) {
	  if (lng < -70.5) {
	   if (lat < -53.5) {
	    return 31;
	   } else {
	    if (lng < -72.0) {
	     if (lat < -51.5) {
	      return 31;
	     } else {
	      if (lng < -72.25) {
	       return 31;
	      } else {
	       if (lat < -51.0) {
	        return 85;
	       } else {
	        return 31;
	       }
	      }
	     }
	    } else {
	     if (lat < -52.0) {
	      return 31;
	     } else {
	      if (lng < -71.25) {
	       if (lat < -51.75) {
	        if (lng < -71.5) {
	         return 31;
	        } else {
	         return 85;
	        }
	       } else {
	        return 85;
	       }
	      } else {
	       if (lat < -51.75) {
	        return 31;
	       } else {
	        return 85;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < -53.5) {
	    if (lng < -68.5) {
	     return 31;
	    } else {
	     if (lat < -55.0) {
	      return 31;
	     } else {
	      if (lat < -54.75) {
	       return 31;
	      } else {
	       return 288;
	      }
	     }
	    }
	   } else {
	    if (lng < -69.0) {
	     if (lat < -52.25) {
	      return 31;
	     } else {
	      if (lng < -69.75) {
	       if (lat < -52.0) {
	        return 31;
	       } else {
	        return 85;
	       }
	      } else {
	       if (lat < -52.0) {
	        return 31;
	       } else {
	        return 85;
	       }
	      }
	     }
	    } else {
	     if (lat < -52.25) {
	      if (lng < -68.25) {
	       if (lat < -53.0) {
	        if (lng < -68.5) {
	         return 31;
	        } else {
	         return 288;
	        }
	       } else {
	        if (lng < -68.75) {
	         return 31;
	        } else {
	         if (lat < -52.75) {
	          if (lng < -68.5) {
	           return 31;
	          } else {
	           return 288;
	          }
	         } else {
	          if (lng < -68.5) {
	           return 31;
	          } else {
	           return 85;
	          }
	         }
	        }
	       }
	      } else {
	       return 288;
	      }
	     } else {
	      if (lng < -68.25) {
	       if (lat < -51.5) {
	        if (lng < -68.75) {
	         if (lat < -52.0) {
	          return 31;
	         } else {
	          return 85;
	         }
	        } else {
	         return 85;
	        }
	       } else {
	        return 85;
	       }
	      } else {
	       return 85;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < -70.5) {
	   if (lat < -48.0) {
	    if (lng < -72.25) {
	     if (lat < -49.5) {
	      if (lat < -50.5) {
	       return 31;
	      } else {
	       return 85;
	      }
	     } else {
	      if (lat < -48.75) {
	       if (lng < -72.75) {
	        if (lat < -49.0) {
	         return 85;
	        } else {
	         return 31;
	        }
	       } else {
	        return 85;
	       }
	      } else {
	       if (lng < -72.5) {
	        return 31;
	       } else {
	        if (lat < -48.25) {
	         return 85;
	        } else {
	         return 31;
	        }
	       }
	      }
	     }
	    } else {
	     return 85;
	    }
	   } else {
	    if (lat < -46.5) {
	     if (lng < -72.0) {
	      if (lat < -47.25) {
	       if (lng < -72.25) {
	        return 31;
	       } else {
	        return 85;
	       }
	      } else {
	       return 31;
	      }
	     } else {
	      if (lng < -71.5) {
	       if (lat < -47.0) {
	        return 85;
	       } else {
	        if (lng < -71.75) {
	         return 31;
	        } else {
	         if (lat < -46.75) {
	          return 85;
	         } else {
	          return 31;
	         }
	        }
	       }
	      } else {
	       return 85;
	      }
	     }
	    } else {
	     if (lng < -71.75) {
	      return 31;
	     } else {
	      if (lat < -45.75) {
	       if (lng < -71.5) {
	        return 31;
	       } else {
	        return 85;
	       }
	      } else {
	       if (lng < -71.25) {
	        if (lat < -45.5) {
	         return 88;
	        } else {
	         return 31;
	        }
	       } else {
	        return 88;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < -48.0) {
	    return 85;
	   } else {
	    if (lng < -69.0) {
	     if (lat < -45.75) {
	      return 85;
	     } else {
	      return 88;
	     }
	    } else {
	     if (lat < -45.75) {
	      return 85;
	     } else {
	      return 88;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup23(double lat, double lng)
	{
	 if (lng < -70.5) {
	  if (lat < -36.75) {
	   if (lng < -71.5) {
	    return 31;
	   } else {
	    if (lat < -38.25) {
	     if (lat < -39.0) {
	      if (lng < -71.25) {
	       if (lat < -39.25) {
	        return 128;
	       } else {
	        return 31;
	       }
	      } else {
	       return 128;
	      }
	     } else {
	      if (lng < -71.0) {
	       if (lat < -38.75) {
	        if (lng < -71.25) {
	         return 31;
	        } else {
	         return 128;
	        }
	       } else {
	        return 31;
	       }
	      } else {
	       if (lat < -38.75) {
	        return 128;
	       } else {
	        if (lng < -70.75) {
	         return 31;
	        } else {
	         return 128;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < -37.5) {
	      if (lng < -71.0) {
	       return 31;
	      } else {
	       return 128;
	      }
	     } else {
	      if (lng < -71.0) {
	       return 31;
	      } else {
	       return 128;
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < -36.25) {
	    if (lng < -72.0) {
	     return 31;
	    } else {
	     if (lng < -71.0) {
	      return 31;
	     } else {
	      return 128;
	     }
	    }
	   } else {
	    return 31;
	   }
	  }
	 } else {
	  if (lat < -36.75) {
	   if (lng < -69.0) {
	    if (lat < -37.0) {
	     return 128;
	    } else {
	     if (lng < -69.75) {
	      return 128;
	     } else {
	      return 14;
	     }
	    }
	   } else {
	    if (lat < -37.5) {
	     return 128;
	    } else {
	     if (lng < -68.25) {
	      if (lng < -68.75) {
	       if (lat < -37.25) {
	        return 128;
	       } else {
	        return 14;
	       }
	      } else {
	       if (lat < -37.25) {
	        return 128;
	       } else {
	        return 14;
	       }
	      }
	     } else {
	      if (lng < -68.0) {
	       return 14;
	      } else {
	       return 128;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < -69.0) {
	    if (lat < -35.25) {
	     if (lng < -69.75) {
	      if (lat < -36.0) {
	       if (lng < -70.25) {
	        return 128;
	       } else {
	        if (lat < -36.5) {
	         return 128;
	        } else {
	         if (lng < -70.0) {
	          if (lat < -36.25) {
	           return 128;
	          } else {
	           return 14;
	          }
	         } else {
	          return 14;
	         }
	        }
	       }
	      } else {
	       if (lng < -70.25) {
	        return 31;
	       } else {
	        return 14;
	       }
	      }
	     } else {
	      return 14;
	     }
	    } else {
	     if (lng < -69.75) {
	      if (lat < -34.5) {
	       if (lng < -70.25) {
	        if (lat < -35.0) {
	         return 14;
	        } else {
	         return 31;
	        }
	       } else {
	        return 14;
	       }
	      } else {
	       if (lng < -70.0) {
	        return 31;
	       } else {
	        if (lat < -34.25) {
	         return 14;
	        } else {
	         return 31;
	        }
	       }
	      }
	     } else {
	      return 14;
	     }
	    }
	   } else {
	    if (lat < -35.75) {
	     if (lng < -68.25) {
	      return 14;
	     } else {
	      if (lat < -36.25) {
	       if (lng < -68.0) {
	        return 14;
	       } else {
	        return 128;
	       }
	      } else {
	       if (lng < -68.0) {
	        if (lat < -36.0) {
	         return 14;
	        } else {
	         return 128;
	        }
	       } else {
	        if (lng < -67.75) {
	         if (lat < -36.0) {
	          return 128;
	         } else {
	          return 14;
	         }
	        } else {
	         if (lat < -36.0) {
	          return 128;
	         } else {
	          return 14;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     return 14;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup24(double lat, double lng)
	{
	 if (lat < -28.25) {
	  if (lng < -70.5) {
	   return 31;
	  } else {
	   if (lat < -31.0) {
	    if (lng < -69.0) {
	     if (lat < -32.5) {
	      if (lng < -69.75) {
	       if (lat < -33.25) {
	        return 31;
	       } else {
	        if (lng < -70.0) {
	         return 31;
	        } else {
	         return 14;
	        }
	       }
	      } else {
	       return 14;
	      }
	     } else {
	      if (lng < -69.75) {
	       if (lat < -31.75) {
	        if (lng < -70.25) {
	         return 31;
	        } else {
	         if (lat < -32.25) {
	          if (lng < -70.0) {
	           return 31;
	          } else {
	           return 14;
	          }
	         } else {
	          if (lng < -70.0) {
	           if (lat < -32.0) {
	            return 347;
	           } else {
	            return 31;
	           }
	          } else {
	           return 347;
	          }
	         }
	        }
	       } else {
	        if (lng < -70.25) {
	         if (lat < -31.5) {
	          return 31;
	         } else {
	          return 347;
	         }
	        } else {
	         return 347;
	        }
	       }
	      } else {
	       if (lat < -31.75) {
	        if (lng < -69.5) {
	         if (lat < -32.25) {
	          return 14;
	         } else {
	          return 347;
	         }
	        } else {
	         if (lat < -32.0) {
	          return 14;
	         } else {
	          if (lng < -69.25) {
	           return 347;
	          } else {
	           return 14;
	          }
	         }
	        }
	       } else {
	        return 347;
	       }
	      }
	     }
	    } else {
	     if (lat < -32.25) {
	      return 14;
	     } else {
	      if (lng < -68.25) {
	       if (lat < -32.0) {
	        return 14;
	       } else {
	        return 347;
	       }
	      } else {
	       if (lat < -32.0) {
	        if (lng < -67.75) {
	         return 14;
	        } else {
	         return 347;
	        }
	       } else {
	        return 347;
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < -69.0) {
	     if (lat < -29.75) {
	      if (lng < -69.75) {
	       if (lat < -30.5) {
	        if (lng < -70.25) {
	         return 31;
	        } else {
	         return 347;
	        }
	       } else {
	        if (lng < -70.0) {
	         return 31;
	        } else {
	         if (lat < -30.25) {
	          return 347;
	         } else {
	          return 31;
	         }
	        }
	       }
	      } else {
	       return 347;
	      }
	     } else {
	      if (lng < -69.75) {
	       return 31;
	      } else {
	       if (lat < -28.75) {
	        return 347;
	       } else {
	        if (lng < -69.5) {
	         return 31;
	        } else {
	         if (lng < -69.25) {
	          return 347;
	         } else {
	          if (lat < -28.5) {
	           return 347;
	          } else {
	           return 250;
	          }
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < -29.75) {
	      if (lng < -67.75) {
	       return 347;
	      } else {
	       if (lat < -30.0) {
	        return 347;
	       } else {
	        return 250;
	       }
	      }
	     } else {
	      if (lng < -68.25) {
	       if (lat < -29.0) {
	        if (lng < -68.75) {
	         if (lat < -29.5) {
	          return 347;
	         } else {
	          if (lat < -29.25) {
	           return 250;
	          } else {
	           return 347;
	          }
	         }
	        } else {
	         if (lat < -29.5) {
	          return 347;
	         } else {
	          return 250;
	         }
	        }
	       } else {
	        if (lng < -68.75) {
	         if (lat < -28.75) {
	          return 347;
	         } else {
	          return 250;
	         }
	        } else {
	         return 250;
	        }
	       }
	      } else {
	       return 250;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < -70.5) {
	   return 31;
	  } else {
	   if (lat < -25.5) {
	    if (lng < -69.0) {
	     if (lat < -28.0) {
	      if (lng < -69.25) {
	       return 31;
	      } else {
	       return 250;
	      }
	     } else {
	      return 31;
	     }
	    } else {
	     if (lat < -27.0) {
	      if (lng < -68.25) {
	       if (lat < -27.75) {
	        return 250;
	       } else {
	        if (lng < -68.75) {
	         if (lat < -27.5) {
	          return 88;
	         } else {
	          return 31;
	         }
	        } else {
	         if (lat < -27.5) {
	          if (lng < -68.5) {
	           return 88;
	          } else {
	           return 250;
	          }
	         } else {
	          return 88;
	         }
	        }
	       }
	      } else {
	       if (lat < -28.0) {
	        if (lng < -67.75) {
	         return 250;
	        } else {
	         return 88;
	        }
	       } else {
	        return 88;
	       }
	      }
	     } else {
	      if (lng < -68.25) {
	       if (lat < -25.75) {
	        if (lat < -26.5) {
	         return 31;
	        } else {
	         if (lng < -68.5) {
	          return 31;
	         } else {
	          if (lat < -26.25) {
	           return 88;
	          } else {
	           return 31;
	          }
	         }
	        }
	       } else {
	        return 31;
	       }
	      } else {
	       return 88;
	      }
	     }
	    }
	   } else {
	    if (lng < -68.5) {
	     return 31;
	    } else {
	     if (lat < -24.0) {
	      if (lat < -24.75) {
	       if (lng < -68.0) {
	        if (lat < -25.0) {
	         return 88;
	        } else {
	         if (lng < -68.25) {
	          return 31;
	         } else {
	          return 128;
	         }
	        }
	       } else {
	        if (lat < -25.25) {
	         return 88;
	        } else {
	         if (lng < -67.75) {
	          if (lat < -25.0) {
	           return 88;
	          } else {
	           return 128;
	          }
	         } else {
	          return 128;
	         }
	        }
	       }
	      } else {
	       if (lng < -68.0) {
	        if (lat < -24.5) {
	         return 128;
	        } else {
	         if (lng < -68.25) {
	          return 31;
	         } else {
	          if (lat < -24.25) {
	           return 128;
	          } else {
	           return 31;
	          }
	         }
	        }
	       } else {
	        if (lat < -24.25) {
	         return 128;
	        } else {
	         if (lng < -67.75) {
	          return 31;
	         } else {
	          return 128;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < -22.75) {
	       return 31;
	      } else {
	       if (lng < -67.75) {
	        return 31;
	       } else {
	        return 191;
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup25(double lat, double lng)
	{
	 if (lat < -17.0) {
	  if (lng < -70.5) {
	   return 165;
	  } else {
	   if (lat < -19.75) {
	    if (lng < -69.0) {
	     return 31;
	    } else {
	     if (lat < -21.25) {
	      if (lng < -68.0) {
	       return 31;
	      } else {
	       if (lat < -22.0) {
	        if (lng < -67.75) {
	         return 31;
	        } else {
	         return 191;
	        }
	       } else {
	        return 191;
	       }
	      }
	     } else {
	      if (lng < -68.25) {
	       if (lat < -20.5) {
	        if (lng < -68.5) {
	         return 31;
	        } else {
	         if (lat < -20.75) {
	          return 31;
	         } else {
	          return 191;
	         }
	        }
	       } else {
	        if (lng < -68.5) {
	         return 31;
	        } else {
	         return 191;
	        }
	       }
	      } else {
	       if (lat < -21.0) {
	        if (lng < -68.0) {
	         return 31;
	        } else {
	         return 191;
	        }
	       } else {
	        return 191;
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < -69.0) {
	     if (lat < -18.25) {
	      return 31;
	     } else {
	      if (lng < -69.75) {
	       return 165;
	      } else {
	       if (lat < -17.75) {
	        return 31;
	       } else {
	        if (lng < -69.5) {
	         if (lat < -17.5) {
	          return 31;
	         } else {
	          return 165;
	         }
	        } else {
	         if (lat < -17.5) {
	          if (lng < -69.25) {
	           return 31;
	          } else {
	           return 191;
	          }
	         } else {
	          if (lng < -69.25) {
	           if (lat < -17.25) {
	            return 165;
	           } else {
	            return 191;
	           }
	          } else {
	           return 191;
	          }
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < -18.5) {
	      if (lng < -68.25) {
	       if (lat < -19.25) {
	        if (lng < -68.5) {
	         return 31;
	        } else {
	         if (lat < -19.5) {
	          return 191;
	         } else {
	          return 31;
	         }
	        }
	       } else {
	        if (lng < -68.75) {
	         return 31;
	        } else {
	         if (lat < -19.0) {
	          if (lng < -68.5) {
	           return 31;
	          } else {
	           return 191;
	          }
	         } else {
	          return 191;
	         }
	        }
	       }
	      } else {
	       return 191;
	      }
	     } else {
	      return 191;
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < -69.25) {
	   return 165;
	  } else {
	   if (lat < -14.25) {
	    if (lat < -15.25) {
	     if (lng < -68.75) {
	      if (lat < -16.25) {
	       if (lat < -16.75) {
	        return 191;
	       } else {
	        if (lng < -69.0) {
	         return 165;
	        } else {
	         return 191;
	        }
	       }
	      } else {
	       if (lat < -16.0) {
	        return 165;
	       } else {
	        return 191;
	       }
	      }
	     } else {
	      return 191;
	     }
	    } else {
	     if (lng < -69.0) {
	      if (lat < -14.5) {
	       if (lat < -15.0) {
	        return 165;
	       } else {
	        if (lat < -14.75) {
	         return 191;
	        } else {
	         return 165;
	        }
	       }
	      } else {
	       return 165;
	      }
	     } else {
	      return 191;
	     }
	    }
	   } else {
	    if (lat < -12.75) {
	     if (lng < -68.75) {
	      if (lat < -13.0) {
	       if (lat < -13.75) {
	        return 165;
	       } else {
	        if (lat < -13.5) {
	         if (lng < -69.0) {
	          return 165;
	         } else {
	          return 191;
	         }
	        } else {
	         return 165;
	        }
	       }
	      } else {
	       return 165;
	      }
	     } else {
	      return 191;
	     }
	    } else {
	     if (lng < -68.5) {
	      if (lat < -12.0) {
	       if (lng < -68.75) {
	        return 165;
	       } else {
	        if (lat < -12.5) {
	         return 191;
	        } else {
	         if (lat < -12.25) {
	          return 165;
	         } else {
	          return 191;
	         }
	        }
	       }
	      } else {
	       if (lng < -69.0) {
	        return 165;
	       } else {
	        if (lat < -11.75) {
	         if (lng < -68.75) {
	          return 165;
	         } else {
	          return 191;
	         }
	        } else {
	         return 191;
	        }
	       }
	      }
	     } else {
	      return 191;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup26(double lat, double lng)
	{
	 if (lat < -4.5) {
	  if (lat < -8.0) {
	   if (lng < -73.5) {
	    return 165;
	   } else {
	    if (lat < -8.25) {
	     return 165;
	    } else {
	     return 181;
	    }
	   }
	  } else {
	   if (lng < -73.75) {
	    return 165;
	   } else {
	    if (lat < -6.5) {
	     if (lat < -7.25) {
	      if (lat < -7.75) {
	       if (lng < -73.5) {
	        return 165;
	       } else {
	        return 181;
	       }
	      } else {
	       if (lng < -73.5) {
	        if (lat < -7.5) {
	         return 165;
	        } else {
	         return 181;
	        }
	       } else {
	        return 181;
	       }
	      }
	     } else {
	      if (lat < -7.0) {
	       if (lng < -73.5) {
	        return 165;
	       } else {
	        return 242;
	       }
	      } else {
	       if (lng < -73.5) {
	        if (lat < -6.75) {
	         return 242;
	        } else {
	         return 165;
	        }
	       } else {
	        return 242;
	       }
	      }
	     }
	    } else {
	     return 165;
	    }
	   }
	  }
	 } else {
	  if (lng < -76.0) {
	   if (lat < -2.25) {
	    if (lng < -77.5) {
	     if (lat < -3.5) {
	      if (lng < -78.25) {
	       if (lat < -4.0) {
	        if (lng < -78.5) {
	         return 382;
	        } else {
	         return 165;
	        }
	       } else {
	        if (lng < -78.5) {
	         return 382;
	        } else {
	         if (lat < -3.75) {
	          return 165;
	         } else {
	          return 382;
	         }
	        }
	       }
	      } else {
	       return 165;
	      }
	     } else {
	      if (lng < -78.25) {
	       return 382;
	      } else {
	       if (lat < -3.0) {
	        if (lng < -78.0) {
	         if (lat < -3.25) {
	          return 165;
	         } else {
	          return 382;
	         }
	        } else {
	         return 165;
	        }
	       } else {
	        if (lng < -77.75) {
	         return 382;
	        } else {
	         if (lat < -2.75) {
	          return 165;
	         } else {
	          return 382;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < -2.75) {
	      return 165;
	     } else {
	      if (lng < -76.75) {
	       if (lng < -77.0) {
	        return 382;
	       } else {
	        if (lat < -2.5) {
	         return 165;
	        } else {
	         return 382;
	        }
	       }
	      } else {
	       if (lng < -76.5) {
	        if (lat < -2.5) {
	         return 165;
	        } else {
	         return 382;
	        }
	       } else {
	        return 165;
	       }
	      }
	     }
	    }
	   } else {
	    return 382;
	   }
	  } else {
	   if (lat < -2.0) {
	    return 165;
	   } else {
	    if (lng < -74.75) {
	     if (lat < -1.0) {
	      if (lng < -75.5) {
	       if (lat < -1.75) {
	        if (lng < -75.75) {
	         return 382;
	        } else {
	         return 165;
	        }
	       } else {
	        return 382;
	       }
	      } else {
	       if (lat < -1.25) {
	        return 165;
	       } else {
	        if (lng < -75.25) {
	         return 382;
	        } else {
	         return 165;
	        }
	       }
	      }
	     } else {
	      if (lng < -75.5) {
	       return 382;
	      } else {
	       if (lat < -0.5) {
	        if (lng < -75.25) {
	         return 382;
	        } else {
	         return 165;
	        }
	       } else {
	        if (lng < -75.25) {
	         if (lat < -0.25) {
	          return 382;
	         } else {
	          return 165;
	         }
	        } else {
	         return 165;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < -1.0) {
	      if (lng < -73.5) {
	       return 165;
	      } else {
	       if (lat < -1.75) {
	        return 165;
	       } else {
	        return 391;
	       }
	      }
	     } else {
	      if (lng < -74.25) {
	       if (lat < -0.25) {
	        return 165;
	       } else {
	        return 391;
	       }
	      } else {
	       return 391;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup27(double lat, double lng)
	{
	 if (lat < -8.5) {
	  if (lng < -69.0) {
	   if (lat < -10.0) {
	    if (lng < -69.75) {
	     if (lat < -10.75) {
	      if (lng < -70.25) {
	       return 165;
	      } else {
	       if (lng < -70.0) {
	        if (lat < -11.0) {
	         return 165;
	        } else {
	         return 181;
	        }
	       } else {
	        return 165;
	       }
	      }
	     } else {
	      return 181;
	     }
	    } else {
	     if (lat < -10.75) {
	      if (lng < -69.5) {
	       return 165;
	      } else {
	       if (lng < -69.25) {
	        if (lat < -11.0) {
	         return 165;
	        } else {
	         return 191;
	        }
	       } else {
	        return 191;
	       }
	      }
	     } else {
	      return 181;
	     }
	    }
	   } else {
	    if (lng < -69.25) {
	     return 181;
	    } else {
	     if (lat < -8.75) {
	      return 181;
	     } else {
	      return 242;
	     }
	    }
	   }
	  } else {
	   if (lat < -10.0) {
	    if (lng < -68.25) {
	     if (lat < -10.75) {
	      if (lng < -68.75) {
	       return 191;
	      } else {
	       if (lng < -68.5) {
	        if (lat < -11.0) {
	         return 191;
	        } else {
	         return 181;
	        }
	       } else {
	        if (lat < -11.0) {
	         return 191;
	        } else {
	         return 181;
	        }
	       }
	      }
	     } else {
	      return 181;
	     }
	    } else {
	     if (lat < -10.75) {
	      return 191;
	     } else {
	      if (lng < -68.0) {
	       return 181;
	      } else {
	       if (lat < -10.5) {
	        return 191;
	       } else {
	        return 181;
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < -68.25) {
	     if (lat < -9.0) {
	      return 181;
	     } else {
	      if (lng < -68.75) {
	       if (lat < -8.75) {
	        return 181;
	       } else {
	        return 242;
	       }
	      } else {
	       return 242;
	      }
	     }
	    } else {
	     if (lat < -9.25) {
	      return 181;
	     } else {
	      if (lng < -68.0) {
	       if (lat < -9.0) {
	        return 181;
	       } else {
	        return 242;
	       }
	      } else {
	       if (lat < -9.0) {
	        return 242;
	       } else {
	        if (lng < -67.75) {
	         return 242;
	        } else {
	         return 7;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < -69.0) {
	   if (lat < -8.0) {
	    if (lng < -69.75) {
	     if (lng < -70.0) {
	      return 181;
	     } else {
	      if (lat < -8.25) {
	       return 181;
	      } else {
	       return 242;
	      }
	     }
	    } else {
	     if (lng < -69.5) {
	      if (lat < -8.25) {
	       return 181;
	      } else {
	       return 242;
	      }
	     } else {
	      return 242;
	     }
	    }
	   } else {
	    return 242;
	   }
	  } else {
	   if (lat < -7.25) {
	    if (lng < -68.25) {
	     return 242;
	    } else {
	     if (lat < -8.0) {
	      if (lng < -68.0) {
	       return 242;
	      } else {
	       return 7;
	      }
	     } else {
	      if (lng < -68.0) {
	       if (lat < -7.75) {
	        return 242;
	       } else {
	        return 7;
	       }
	      } else {
	       return 7;
	      }
	     }
	    }
	   } else {
	    if (lng < -68.5) {
	     if (lat < -6.5) {
	      if (lat < -6.75) {
	       return 242;
	      } else {
	       if (lng < -68.75) {
	        return 242;
	       } else {
	        return 7;
	       }
	      }
	     } else {
	      if (lat < -6.25) {
	       if (lng < -68.75) {
	        return 242;
	       } else {
	        return 7;
	       }
	      } else {
	       return 7;
	      }
	     }
	    } else {
	     return 7;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup28(double lat, double lng)
	{
	 if (lng < -70.5) {
	  if (lat < -3.0) {
	   if (lng < -72.0) {
	    if (lat < -4.75) {
	     if (lng < -72.75) {
	      if (lat < -5.5) {
	       if (lng < -73.0) {
	        return 165;
	       } else {
	        return 242;
	       }
	      } else {
	       return 165;
	      }
	     } else {
	      if (lat < -5.0) {
	       return 242;
	      } else {
	       if (lng < -72.5) {
	        return 165;
	       } else {
	        return 242;
	       }
	      }
	     }
	    } else {
	     return 165;
	    }
	   } else {
	    if (lat < -4.5) {
	     return 242;
	    } else {
	     if (lng < -71.25) {
	      if (lat < -4.25) {
	       if (lng < -71.75) {
	        return 165;
	       } else {
	        return 242;
	       }
	      } else {
	       return 165;
	      }
	     } else {
	      if (lat < -4.0) {
	       if (lng < -71.0) {
	        if (lat < -4.25) {
	         return 242;
	        } else {
	         return 165;
	        }
	       } else {
	        if (lng < -70.75) {
	         if (lat < -4.25) {
	          return 242;
	         } else {
	          return 165;
	         }
	        } else {
	         return 242;
	        }
	       }
	      } else {
	       return 165;
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < -1.75) {
	    if (lng < -72.0) {
	     if (lng < -72.75) {
	      if (lat < -2.25) {
	       return 165;
	      } else {
	       if (lng < -73.0) {
	        return 165;
	       } else {
	        return 391;
	       }
	      }
	     } else {
	      if (lat < -2.25) {
	       return 165;
	      } else {
	       return 391;
	      }
	     }
	    } else {
	     if (lng < -71.25) {
	      if (lat < -2.25) {
	       return 165;
	      } else {
	       if (lng < -71.75) {
	        return 391;
	       } else {
	        if (lng < -71.5) {
	         if (lat < -2.0) {
	          return 165;
	         } else {
	          return 391;
	         }
	        } else {
	         if (lat < -2.0) {
	          return 165;
	         } else {
	          return 391;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < -2.25) {
	       return 165;
	      } else {
	       return 391;
	      }
	     }
	    }
	   } else {
	    return 391;
	   }
	  }
	 } else {
	  if (lat < -3.0) {
	   if (lng < -69.25) {
	    if (lat < -4.5) {
	     if (lng < -69.5) {
	      return 242;
	     } else {
	      if (lat < -5.0) {
	       return 242;
	      } else {
	       return 7;
	      }
	     }
	    } else {
	     if (lat < -3.75) {
	      if (lng < -70.0) {
	       if (lat < -4.25) {
	        return 242;
	       } else {
	        if (lng < -70.25) {
	         if (lat < -4.0) {
	          return 242;
	         } else {
	          return 165;
	         }
	        } else {
	         return 165;
	        }
	       }
	      } else {
	       if (lng < -69.75) {
	        if (lat < -4.25) {
	         return 242;
	        } else {
	         if (lat < -4.0) {
	          return 165;
	         } else {
	          return 391;
	         }
	        }
	       } else {
	        return 7;
	       }
	      }
	     } else {
	      if (lng < -70.0) {
	       if (lat < -3.25) {
	        return 391;
	       } else {
	        if (lng < -70.25) {
	         return 165;
	        } else {
	         return 391;
	        }
	       }
	      } else {
	       if (lng < -69.75) {
	        return 391;
	       } else {
	        return 7;
	       }
	      }
	     }
	    }
	   } else {
	    return 7;
	   }
	  } else {
	   if (lng < -69.25) {
	    if (lat < -1.5) {
	     if (lat < -2.25) {
	      if (lng < -70.0) {
	       if (lat < -2.5) {
	        return 165;
	       } else {
	        if (lng < -70.25) {
	         return 165;
	        } else {
	         return 391;
	        }
	       }
	      } else {
	       if (lng < -69.5) {
	        return 391;
	       } else {
	        return 7;
	       }
	      }
	     } else {
	      if (lng < -69.5) {
	       return 391;
	      } else {
	       if (lat < -1.75) {
	        return 7;
	       } else {
	        return 391;
	       }
	      }
	     }
	    } else {
	     if (lat < -0.75) {
	      return 391;
	     } else {
	      if (lng < -69.75) {
	       return 391;
	      } else {
	       if (lat < -0.5) {
	        if (lng < -69.5) {
	         return 391;
	        } else {
	         return 7;
	        }
	       } else {
	        if (lng < -69.5) {
	         if (lat < -0.25) {
	          return 391;
	         } else {
	          return 7;
	         }
	        } else {
	         return 7;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return 7;
	   }
	  }
	 }
	}

	private  int kdLookup29(double lat, double lng)
	{
	 if (lng < -78.75) {
	  if (lat < -11.25) {
	   return 0;
	  } else {
	   if (lng < -84.5) {
	    return 114;
	   } else {
	    if (lat < -5.75) {
	     return 165;
	    } else {
	     if (lng < -81.75) {
	      return 0;
	     } else {
	      if (lat < -3.25) {
	       if (lng < -80.25) {
	        return 165;
	       } else {
	        if (lat < -4.5) {
	         if (lng < -79.25) {
	          return 165;
	         } else {
	          if (lat < -4.75) {
	           return 165;
	          } else {
	           return 382;
	          }
	         }
	        } else {
	         if (lng < -79.5) {
	          if (lat < -4.0) {
	           if (lng < -80.0) {
	            if (lat < -4.25) {
	             return 165;
	            } else {
	             return 382;
	            }
	           } else {
	            if (lng < -79.75) {
	             if (lat < -4.25) {
	              return 165;
	             } else {
	              return 382;
	             }
	            } else {
	             if (lat < -4.25) {
	              return 165;
	             } else {
	              return 382;
	             }
	            }
	           }
	          } else {
	           if (lng < -80.0) {
	            if (lat < -3.75) {
	             return 382;
	            } else {
	             return 165;
	            }
	           } else {
	            return 382;
	           }
	          }
	         } else {
	          return 382;
	         }
	        }
	       }
	      } else {
	       return 382;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < -11.25) {
	   if (lng < -73.25) {
	    return 165;
	   } else {
	    return kdLookup25(lat,lng);
	   }
	  } else {
	   if (lng < -73.25) {
	    return kdLookup26(lat,lng);
	   } else {
	    if (lat < -5.75) {
	     if (lng < -70.5) {
	      if (lat < -8.5) {
	       if (lng < -72.0) {
	        if (lat < -9.75) {
	         return 165;
	        } else {
	         if (lng < -72.75) {
	          if (lat < -9.25) {
	           return 165;
	          } else {
	           if (lat < -9.0) {
	            if (lng < -73.0) {
	             return 165;
	            } else {
	             return 181;
	            }
	           } else {
	            if (lng < -73.0) {
	             return 165;
	            } else {
	             if (lat < -8.75) {
	              return 165;
	             } else {
	              return 181;
	             }
	            }
	           }
	          }
	         } else {
	          if (lat < -9.25) {
	           if (lng < -72.25) {
	            return 165;
	           } else {
	            return 181;
	           }
	          } else {
	           return 181;
	          }
	         }
	        }
	       } else {
	        if (lat < -10.0) {
	         return 165;
	        } else {
	         if (lng < -71.25) {
	          if (lat < -9.75) {
	           if (lng < -71.5) {
	            return 181;
	           } else {
	            return 165;
	           }
	          } else {
	           return 181;
	          }
	         } else {
	          if (lat < -9.5) {
	           if (lng < -71.0) {
	            if (lat < -9.75) {
	             return 165;
	            } else {
	             return 181;
	            }
	           } else {
	            if (lng < -70.75) {
	             if (lat < -9.75) {
	              return 165;
	             } else {
	              return 181;
	             }
	            } else {
	             return 165;
	            }
	           }
	          } else {
	           return 181;
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < -72.0) {
	        if (lat < -7.25) {
	         if (lng < -72.75) {
	          return 181;
	         } else {
	          if (lat < -7.5) {
	           return 181;
	          } else {
	           return 242;
	          }
	         }
	        } else {
	         if (lat < -6.5) {
	          return 242;
	         } else {
	          if (lng < -73.0) {
	           return 165;
	          } else {
	           return 242;
	          }
	         }
	        }
	       } else {
	        if (lat < -7.75) {
	         if (lng < -70.75) {
	          return 181;
	         } else {
	          if (lat < -8.0) {
	           return 181;
	          } else {
	           return 242;
	          }
	         }
	        } else {
	         return 242;
	        }
	       }
	      }
	     } else {
	      return kdLookup27(lat,lng);
	     }
	    } else {
	     return kdLookup28(lat,lng);
	    }
	   }
	  }
	 }
	}

	private  int kdLookup30(double lat, double lng)
	{
	 if (lat < -39.5) {
	  if (lng < -64.75) {
	   if (lat < -42.25) {
	    return 88;
	   } else {
	    if (lng < -66.25) {
	     if (lat < -41.75) {
	      return 88;
	     } else {
	      return 128;
	     }
	    } else {
	     if (lat < -41.75) {
	      return 88;
	     } else {
	      return 128;
	     }
	    }
	   }
	  } else {
	   if (lat < -42.25) {
	    return 88;
	   } else {
	    if (lng < -63.5) {
	     if (lat < -41.25) {
	      return 88;
	     } else {
	      return 128;
	     }
	    } else {
	     if (lat < -41.0) {
	      if (lng < -62.75) {
	       if (lat < -41.75) {
	        return 88;
	       } else {
	        return 128;
	       }
	      } else {
	       return 227;
	      }
	     } else {
	      if (lng < -62.75) {
	       if (lat < -40.25) {
	        if (lng < -63.25) {
	         return 128;
	        } else {
	         if (lat < -40.75) {
	          return 128;
	         } else {
	          if (lng < -63.0) {
	           if (lat < -40.5) {
	            return 128;
	           } else {
	            return 227;
	           }
	          } else {
	           return 227;
	          }
	         }
	        }
	       } else {
	        if (lng < -63.25) {
	         return 128;
	        } else {
	         return 227;
	        }
	       }
	      } else {
	       return 227;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < -36.75) {
	   if (lng < -63.25) {
	    return 128;
	   } else {
	    return 227;
	   }
	  } else {
	   if (lng < -64.75) {
	    if (lat < -35.25) {
	     if (lng < -66.25) {
	      if (lat < -36.0) {
	       return 128;
	      } else {
	       if (lng < -67.0) {
	        return 14;
	       } else {
	        if (lng < -66.75) {
	         if (lat < -35.75) {
	          return 128;
	         } else {
	          return 14;
	         }
	        } else {
	         if (lat < -35.75) {
	          if (lng < -66.5) {
	           return 128;
	          } else {
	           return 315;
	          }
	         } else {
	          if (lng < -66.5) {
	           return 14;
	          } else {
	           return 315;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < -65.5) {
	       if (lat < -36.0) {
	        return 128;
	       } else {
	        if (lng < -66.0) {
	         return 315;
	        } else {
	         if (lat < -35.75) {
	          if (lng < -65.75) {
	           return 128;
	          } else {
	           return 315;
	          }
	         } else {
	          return 315;
	         }
	        }
	       }
	      } else {
	       if (lat < -36.0) {
	        return 128;
	       } else {
	        if (lng < -65.0) {
	         return 315;
	        } else {
	         return 128;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -66.25) {
	      if (lat < -34.5) {
	       if (lng < -66.5) {
	        return 14;
	       } else {
	        return 315;
	       }
	      } else {
	       if (lng < -66.75) {
	        return 14;
	       } else {
	        return 315;
	       }
	      }
	     } else {
	      if (lng < -65.0) {
	       return 315;
	      } else {
	       if (lat < -34.75) {
	        return 128;
	       } else {
	        return 279;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < -35.25) {
	     if (lng < -63.25) {
	      return 128;
	     } else {
	      return 227;
	     }
	    } else {
	     if (lng < -63.5) {
	      if (lat < -35.0) {
	       return 128;
	      } else {
	       return 279;
	      }
	     } else {
	      if (lng < -62.75) {
	       if (lat < -34.5) {
	        if (lng < -63.25) {
	         if (lat < -35.0) {
	          return 128;
	         } else {
	          return 279;
	         }
	        } else {
	         return 227;
	        }
	       } else {
	        if (lng < -63.25) {
	         return 279;
	        } else {
	         if (lat < -34.25) {
	          return 227;
	         } else {
	          return 279;
	         }
	        }
	       }
	      } else {
	       if (lat < -34.25) {
	        return 227;
	       } else {
	        return 279;
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup31(double lat, double lng)
	{
	 if (lng < -64.75) {
	  if (lat < -24.0) {
	   if (lng < -66.25) {
	    if (lat < -25.25) {
	     return 88;
	    } else {
	     return 128;
	    }
	   } else {
	    if (lng < -65.5) {
	     return 128;
	    } else {
	     if (lat < -24.5) {
	      return 128;
	     } else {
	      if (lng < -65.25) {
	       if (lat < -24.25) {
	        return 128;
	       } else {
	        return 160;
	       }
	      } else {
	       if (lng < -65.0) {
	        if (lat < -24.25) {
	         return 128;
	        } else {
	         return 160;
	        }
	       } else {
	        return 160;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < -66.25) {
	    if (lat < -23.25) {
	     if (lng < -67.0) {
	      if (lat < -23.75) {
	       if (lng < -67.25) {
	        return 31;
	       } else {
	        return 128;
	       }
	      } else {
	       return 31;
	      }
	     } else {
	      if (lng < -66.75) {
	       if (lat < -23.75) {
	        return 128;
	       } else {
	        return 160;
	       }
	      } else {
	       return 160;
	      }
	     }
	    } else {
	     if (lng < -67.0) {
	      if (lat < -22.75) {
	       return 31;
	      } else {
	       return 191;
	      }
	     } else {
	      if (lng < -66.75) {
	       if (lat < -23.0) {
	        return 160;
	       } else {
	        if (lat < -22.75) {
	         return 31;
	        } else {
	         return 160;
	        }
	       }
	      } else {
	       return 160;
	      }
	     }
	    }
	   } else {
	    if (lng < -65.5) {
	     if (lat < -23.25) {
	      if (lng < -66.0) {
	       return 128;
	      } else {
	       if (lat < -23.75) {
	        if (lng < -65.75) {
	         return 128;
	        } else {
	         return 160;
	        }
	       } else {
	        return 160;
	       }
	      }
	     } else {
	      return 160;
	     }
	    } else {
	     if (lat < -23.25) {
	      return 160;
	     } else {
	      if (lng < -65.25) {
	       return 160;
	      } else {
	       if (lat < -23.0) {
	        if (lng < -65.0) {
	         return 160;
	        } else {
	         return 128;
	        }
	       } else {
	        if (lng < -65.0) {
	         if (lat < -22.75) {
	          return 160;
	         } else {
	          return 128;
	         }
	        } else {
	         return 128;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < -24.0) {
	   if (lng < -63.5) {
	    if (lat < -24.5) {
	     return 128;
	    } else {
	     if (lng < -64.25) {
	      if (lng < -64.5) {
	       return 160;
	      } else {
	       if (lat < -24.25) {
	        return 128;
	       } else {
	        return 160;
	       }
	      }
	     } else {
	      if (lng < -64.0) {
	       if (lat < -24.25) {
	        return 128;
	       } else {
	        return 160;
	       }
	      } else {
	       return 128;
	      }
	     }
	    }
	   } else {
	    if (lng < -62.75) {
	     if (lat < -25.0) {
	      if (lng < -63.25) {
	       return 128;
	      } else {
	       if (lng < -63.0) {
	        if (lat < -25.25) {
	         return 279;
	        } else {
	         return 128;
	        }
	       } else {
	        return 279;
	       }
	      }
	     } else {
	      return 128;
	     }
	    } else {
	     if (lat < -24.75) {
	      return 279;
	     } else {
	      if (lng < -62.5) {
	       return 128;
	      } else {
	       if (lat < -24.5) {
	        return 279;
	       } else {
	        if (lng < -62.25) {
	         return 128;
	        } else {
	         return 279;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < -63.5) {
	    if (lat < -23.25) {
	     if (lng < -64.25) {
	      if (lat < -23.5) {
	       return 160;
	      } else {
	       if (lng < -64.5) {
	        return 160;
	       } else {
	        return 128;
	       }
	      }
	     } else {
	      if (lng < -64.0) {
	       if (lat < -23.5) {
	        return 160;
	       } else {
	        return 128;
	       }
	      } else {
	       return 128;
	      }
	     }
	    } else {
	     return 128;
	    }
	   } else {
	    if (lng < -62.25) {
	     return 128;
	    } else {
	     return 279;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup32(double lat, double lng)
	{
	 if (lat < -28.25) {
	  if (lng < -64.75) {
	   if (lat < -31.0) {
	    if (lng < -66.25) {
	     if (lat < -32.5) {
	      if (lng < -67.0) {
	       return 14;
	      } else {
	       if (lat < -33.5) {
	        if (lng < -66.75) {
	         return 14;
	        } else {
	         return 315;
	        }
	       } else {
	        return 315;
	       }
	      }
	     } else {
	      if (lat < -31.75) {
	       if (lng < -67.25) {
	        if (lat < -32.25) {
	         return 14;
	        } else {
	         return 347;
	        }
	       } else {
	        return 315;
	       }
	      } else {
	       if (lng < -67.0) {
	        return 347;
	       } else {
	        if (lng < -66.75) {
	         if (lat < -31.5) {
	          return 347;
	         } else {
	          return 250;
	         }
	        } else {
	         return 250;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < -32.5) {
	      if (lng < -65.0) {
	       return 315;
	      } else {
	       if (lat < -32.75) {
	        return 279;
	       } else {
	        return 315;
	       }
	      }
	     } else {
	      if (lng < -65.5) {
	       if (lat < -31.75) {
	        return 315;
	       } else {
	        if (lng < -65.75) {
	         return 250;
	        } else {
	         return 279;
	        }
	       }
	      } else {
	       if (lat < -31.75) {
	        if (lng < -65.25) {
	         return 315;
	        } else {
	         if (lat < -32.25) {
	          return 315;
	         } else {
	          if (lng < -65.0) {
	           if (lat < -32.0) {
	            return 315;
	           } else {
	            return 279;
	           }
	          } else {
	           return 279;
	          }
	         }
	        }
	       } else {
	        return 279;
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < -66.25) {
	     if (lat < -30.25) {
	      if (lng < -67.0) {
	       if (lat < -30.5) {
	        return 347;
	       } else {
	        if (lng < -67.25) {
	         return 347;
	        } else {
	         return 250;
	        }
	       }
	      } else {
	       return 250;
	      }
	     } else {
	      return 250;
	     }
	    } else {
	     if (lat < -29.75) {
	      if (lng < -65.5) {
	       return 250;
	      } else {
	       if (lat < -30.25) {
	        return 279;
	       } else {
	        if (lng < -65.25) {
	         return 250;
	        } else {
	         if (lng < -65.0) {
	          if (lat < -30.0) {
	           return 279;
	          } else {
	           return 250;
	          }
	         } else {
	          return 279;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < -65.5) {
	       if (lat < -29.0) {
	        if (lng < -65.75) {
	         return 250;
	        } else {
	         if (lat < -29.25) {
	          return 250;
	         } else {
	          return 88;
	         }
	        }
	       } else {
	        if (lng < -66.0) {
	         if (lat < -28.75) {
	          return 250;
	         } else {
	          return 88;
	         }
	        } else {
	         return 88;
	        }
	       }
	      } else {
	       if (lat < -29.0) {
	        if (lng < -65.25) {
	         if (lat < -29.5) {
	          return 250;
	         } else {
	          return 88;
	         }
	        } else {
	         if (lat < -29.25) {
	          return 88;
	         } else {
	          if (lng < -65.0) {
	           return 88;
	          } else {
	           return 279;
	          }
	         }
	        }
	       } else {
	        if (lng < -65.0) {
	         return 88;
	        } else {
	         return 279;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   return 279;
	  }
	 } else {
	  if (lat < -25.5) {
	   if (lng < -64.75) {
	    if (lng < -66.25) {
	     if (lat < -27.0) {
	      if (lng < -67.25) {
	       if (lat < -28.0) {
	        return 250;
	       } else {
	        return 88;
	       }
	      } else {
	       return 88;
	      }
	     } else {
	      if (lat < -26.25) {
	       return 88;
	      } else {
	       if (lng < -66.75) {
	        return 88;
	       } else {
	        if (lat < -26.0) {
	         if (lng < -66.5) {
	          return 88;
	         } else {
	          return 128;
	         }
	        } else {
	         if (lng < -66.5) {
	          if (lat < -25.75) {
	           return 88;
	          } else {
	           return 128;
	          }
	         } else {
	          return 128;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < -27.0) {
	      if (lng < -65.5) {
	       if (lat < -27.75) {
	        return 88;
	       } else {
	        if (lng < -66.0) {
	         return 88;
	        } else {
	         if (lat < -27.5) {
	          if (lng < -65.75) {
	           return 88;
	          } else {
	           return 130;
	          }
	         } else {
	          if (lng < -65.75) {
	           if (lat < -27.25) {
	            return 88;
	           } else {
	            return 130;
	           }
	          } else {
	           return 130;
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < -27.75) {
	        if (lng < -65.0) {
	         return 88;
	        } else {
	         return 279;
	        }
	       } else {
	        if (lng < -65.0) {
	         return 130;
	        } else {
	         if (lat < -27.25) {
	          return 279;
	         } else {
	          return 130;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < -65.5) {
	       if (lat < -26.25) {
	        if (lng < -66.0) {
	         return 88;
	        } else {
	         if (lat < -26.75) {
	          if (lng < -65.75) {
	           return 88;
	          } else {
	           return 130;
	          }
	         } else {
	          if (lng < -65.75) {
	           if (lat < -26.5) {
	            return 88;
	           } else {
	            return 130;
	           }
	          } else {
	           return 130;
	          }
	         }
	        }
	       } else {
	        if (lng < -66.0) {
	         if (lat < -26.0) {
	          return 88;
	         } else {
	          return 128;
	         }
	        } else {
	         return 128;
	        }
	       }
	      } else {
	       if (lat < -26.25) {
	        return 130;
	       } else {
	        if (lng < -65.25) {
	         if (lat < -26.0) {
	          return 130;
	         } else {
	          return 128;
	         }
	        } else {
	         if (lat < -26.0) {
	          if (lng < -65.0) {
	           return 130;
	          } else {
	           return 128;
	          }
	         } else {
	          return 128;
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < -64.25) {
	     if (lat < -27.0) {
	      return 279;
	     } else {
	      if (lat < -26.25) {
	       if (lat < -26.75) {
	        if (lng < -64.5) {
	         return 130;
	        } else {
	         return 279;
	        }
	       } else {
	        if (lng < -64.5) {
	         return 130;
	        } else {
	         return 279;
	        }
	       }
	      } else {
	       if (lat < -26.0) {
	        return 130;
	       } else {
	        return 128;
	       }
	      }
	     }
	    } else {
	     return 279;
	    }
	   }
	  } else {
	   return kdLookup31(lat,lng);
	  }
	 }
	}

	private  int kdLookup33(double lat, double lng)
	{
	 if (lat < -28.25) {
	  if (lng < -58.25) {
	   if (lat < -33.25) {
	    if (lng < -60.25) {
	     if (lng < -61.0) {
	      return 279;
	     } else {
	      if (lng < -60.75) {
	       if (lat < -33.5) {
	        return 227;
	       } else {
	        return 279;
	       }
	      } else {
	       if (lng < -60.5) {
	        if (lat < -33.5) {
	         return 227;
	        } else {
	         return 279;
	        }
	       } else {
	        if (lat < -33.5) {
	         return 227;
	        } else {
	         return 279;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -59.25) {
	      if (lng < -59.75) {
	       return 227;
	      } else {
	       if (lng < -59.5) {
	        if (lat < -33.5) {
	         return 227;
	        } else {
	         return 279;
	        }
	       } else {
	        if (lat < -33.5) {
	         return 227;
	        } else {
	         return 279;
	        }
	       }
	      }
	     } else {
	      return 279;
	     }
	    }
	   } else {
	    return 279;
	   }
	  } else {
	   if (lat < -31.0) {
	    if (lat < -32.5) {
	     if (lng < -58.0) {
	      if (lat < -33.0) {
	       return 246;
	      } else {
	       return 279;
	      }
	     } else {
	      return 246;
	     }
	    } else {
	     if (lng < -57.75) {
	      if (lat < -31.75) {
	       if (lat < -32.25) {
	        if (lng < -58.0) {
	         return 279;
	        } else {
	         return 246;
	        }
	       } else {
	        if (lng < -58.0) {
	         return 279;
	        } else {
	         return 246;
	        }
	       }
	      } else {
	       if (lat < -31.5) {
	        if (lng < -58.0) {
	         return 279;
	        } else {
	         return 246;
	        }
	       } else {
	        if (lng < -58.0) {
	         return 279;
	        } else {
	         if (lat < -31.25) {
	          return 246;
	         } else {
	          return 279;
	         }
	        }
	       }
	      }
	     } else {
	      return 246;
	     }
	    }
	   } else {
	    if (lat < -29.75) {
	     if (lng < -57.25) {
	      if (lat < -30.5) {
	       if (lng < -57.75) {
	        return 279;
	       } else {
	        return 246;
	       }
	      } else {
	       if (lng < -57.75) {
	        return 279;
	       } else {
	        if (lat < -30.25) {
	         return 246;
	        } else {
	         if (lng < -57.5) {
	          return 279;
	         } else {
	          if (lat < -30.0) {
	           return 330;
	          } else {
	           return 279;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < -30.25) {
	       return 246;
	      } else {
	       if (lng < -56.75) {
	        if (lng < -57.0) {
	         return 330;
	        } else {
	         if (lat < -30.0) {
	          return 246;
	         } else {
	          return 330;
	         }
	        }
	       } else {
	        if (lng < -56.5) {
	         if (lat < -30.0) {
	          return 246;
	         } else {
	          return 330;
	         }
	        } else {
	         return 330;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -57.0) {
	      return 279;
	     } else {
	      if (lat < -29.0) {
	       if (lng < -56.75) {
	        if (lat < -29.5) {
	         return 330;
	        } else {
	         return 279;
	        }
	       } else {
	        if (lat < -29.25) {
	         return 330;
	        } else {
	         if (lng < -56.5) {
	          return 279;
	         } else {
	          return 330;
	         }
	        }
	       }
	      } else {
	       return 279;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < -59.25) {
	   if (lat < -24.25) {
	    return 279;
	   } else {
	    if (lng < -60.75) {
	     if (lat < -23.5) {
	      if (lng < -61.0) {
	       return 279;
	      } else {
	       if (lat < -23.75) {
	        return 279;
	       } else {
	        return 220;
	       }
	      }
	     } else {
	      if (lng < -61.5) {
	       if (lat < -23.0) {
	        return 279;
	       } else {
	        return 220;
	       }
	      } else {
	       if (lat < -23.25) {
	        if (lng < -61.25) {
	         return 279;
	        } else {
	         return 220;
	        }
	       } else {
	        return 220;
	       }
	      }
	     }
	    } else {
	     if (lat < -23.75) {
	      if (lng < -60.0) {
	       if (lng < -60.25) {
	        return 279;
	       } else {
	        if (lat < -24.0) {
	         return 279;
	        } else {
	         return 220;
	        }
	       }
	      } else {
	       if (lng < -59.75) {
	        if (lat < -24.0) {
	         return 279;
	        } else {
	         return 220;
	        }
	       } else {
	        if (lng < -59.5) {
	         if (lat < -24.0) {
	          return 279;
	         } else {
	          return 220;
	         }
	        } else {
	         return 220;
	        }
	       }
	      }
	     } else {
	      return 220;
	     }
	    }
	   }
	  } else {
	   if (lat < -25.5) {
	    if (lng < -57.75) {
	     if (lat < -27.0) {
	      if (lng < -58.5) {
	       return 279;
	      } else {
	       if (lat < -27.25) {
	        return 279;
	       } else {
	        return 220;
	       }
	      }
	     } else {
	      if (lng < -58.25) {
	       return 279;
	      } else {
	       if (lat < -26.25) {
	        if (lat < -26.75) {
	         return 220;
	        } else {
	         if (lng < -58.0) {
	          return 279;
	         } else {
	          return 220;
	         }
	        }
	       } else {
	        if (lat < -26.0) {
	         if (lng < -58.0) {
	          return 279;
	         } else {
	          return 220;
	         }
	        } else {
	         return 279;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < -27.25) {
	      if (lng < -57.0) {
	       return 279;
	      } else {
	       if (lat < -27.5) {
	        return 279;
	       } else {
	        return 220;
	       }
	      }
	     } else {
	      return 220;
	     }
	    }
	   } else {
	    if (lng < -57.75) {
	     if (lat < -24.5) {
	      if (lng < -58.5) {
	       if (lat < -24.75) {
	        return 279;
	       } else {
	        if (lng < -58.75) {
	         return 279;
	        } else {
	         return 220;
	        }
	       }
	      } else {
	       if (lat < -25.0) {
	        return 279;
	       } else {
	        if (lng < -58.25) {
	         if (lat < -24.75) {
	          return 279;
	         } else {
	          return 220;
	         }
	        } else {
	         if (lng < -58.0) {
	          if (lat < -24.75) {
	           return 279;
	          } else {
	           return 220;
	          }
	         } else {
	          return 220;
	         }
	        }
	       }
	      }
	     } else {
	      return 220;
	     }
	    } else {
	     if (lat < -25.0) {
	      if (lng < -57.5) {
	       return 279;
	      } else {
	       return 220;
	      }
	     } else {
	      return 220;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup34(double lat, double lng)
	{
	 if (lat < -25.5) {
	  if (lng < -53.5) {
	   if (lng < -55.0) {
	    if (lat < -27.0) {
	     if (lng < -55.75) {
	      if (lat < -27.25) {
	       return 279;
	      } else {
	       return 220;
	      }
	     } else {
	      if (lat < -27.75) {
	       if (lng < -55.5) {
	        if (lat < -28.0) {
	         return 330;
	        } else {
	         return 279;
	        }
	       } else {
	        if (lng < -55.25) {
	         if (lat < -28.0) {
	          return 330;
	         } else {
	          return 279;
	         }
	        } else {
	         return 330;
	        }
	       }
	      } else {
	       if (lng < -55.5) {
	        if (lat < -27.25) {
	         return 279;
	        } else {
	         return 220;
	        }
	       } else {
	        return 279;
	       }
	      }
	     }
	    } else {
	     if (lat < -26.75) {
	      if (lng < -55.25) {
	       return 220;
	      } else {
	       return 279;
	      }
	     } else {
	      return 220;
	     }
	    }
	   } else {
	    if (lat < -27.0) {
	     if (lng < -54.25) {
	      if (lat < -27.75) {
	       return 330;
	      } else {
	       if (lng < -54.75) {
	        return 279;
	       } else {
	        if (lat < -27.5) {
	         return 330;
	        } else {
	         if (lng < -54.5) {
	          return 279;
	         } else {
	          if (lat < -27.25) {
	           return 330;
	          } else {
	           return 279;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < -27.25) {
	       return 330;
	      } else {
	       if (lng < -54.0) {
	        return 279;
	       } else {
	        return 330;
	       }
	      }
	     }
	    } else {
	     if (lng < -54.25) {
	      if (lat < -26.25) {
	       if (lng < -54.75) {
	        if (lat < -26.75) {
	         return 279;
	        } else {
	         return 220;
	        }
	       } else {
	        return 279;
	       }
	      } else {
	       if (lng < -54.5) {
	        return 220;
	       } else {
	        return 279;
	       }
	      }
	     } else {
	      if (lat < -26.0) {
	       return 279;
	      } else {
	       if (lng < -53.75) {
	        return 279;
	       } else {
	        return 330;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   return 330;
	  }
	 } else {
	  if (lng < -53.5) {
	   if (lat < -24.0) {
	    if (lng < -54.5) {
	     return 220;
	    } else {
	     if (lat < -24.75) {
	      if (lng < -54.25) {
	       if (lat < -25.25) {
	        return 330;
	       } else {
	        return 220;
	       }
	      } else {
	       return 330;
	      }
	     } else {
	      if (lng < -54.25) {
	       return 220;
	      } else {
	       return 330;
	      }
	     }
	    }
	   } else {
	    if (lng < -55.0) {
	     if (lat < -23.25) {
	      if (lng < -55.5) {
	       return 220;
	      } else {
	       if (lat < -23.75) {
	        return 220;
	       } else {
	        if (lng < -55.25) {
	         if (lat < -23.5) {
	          return 220;
	         } else {
	          return 318;
	         }
	        } else {
	         return 318;
	        }
	       }
	      }
	     } else {
	      if (lng < -55.5) {
	       return 220;
	      } else {
	       return 318;
	      }
	     }
	    } else {
	     if (lng < -54.25) {
	      if (lat < -23.75) {
	       return 220;
	      } else {
	       return 318;
	      }
	     } else {
	      if (lat < -23.25) {
	       if (lng < -54.0) {
	        return 318;
	       } else {
	        if (lat < -23.5) {
	         return 330;
	        } else {
	         if (lng < -53.75) {
	          return 318;
	         } else {
	          return 330;
	         }
	        }
	       }
	      } else {
	       return 318;
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < -22.75) {
	    return 330;
	   } else {
	    if (lng < -53.25) {
	     return 318;
	    } else {
	     return 330;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup35(double lat, double lng)
	{
	 if (lng < -56.25) {
	  if (lat < -33.75) {
	   if (lng < -62.0) {
	    return kdLookup30(lat,lng);
	   } else {
	    if (lat < -39.5) {
	     return 227;
	    } else {
	     if (lng < -59.25) {
	      if (lat < -36.75) {
	       return 227;
	      } else {
	       if (lat < -34.25) {
	        return 227;
	       } else {
	        if (lng < -61.25) {
	         if (lng < -61.5) {
	          return 279;
	         } else {
	          if (lat < -34.0) {
	           return 227;
	          } else {
	           return 279;
	          }
	         }
	        } else {
	         return 227;
	        }
	       }
	      }
	     } else {
	      if (lat < -36.75) {
	       return 227;
	      } else {
	       if (lng < -57.75) {
	        if (lat < -34.5) {
	         return 227;
	        } else {
	         if (lng < -58.5) {
	          return 227;
	         } else {
	          if (lng < -58.25) {
	           if (lat < -34.0) {
	            return 227;
	           } else {
	            return 279;
	           }
	          } else {
	           if (lat < -34.25) {
	            if (lng < -58.0) {
	             return 227;
	            } else {
	             return 246;
	            }
	           } else {
	            return 246;
	           }
	          }
	         }
	        }
	       } else {
	        if (lat < -35.25) {
	         return 227;
	        } else {
	         if (lng < -57.0) {
	          if (lat < -34.5) {
	           return 227;
	          } else {
	           return 246;
	          }
	         } else {
	          return 246;
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < -62.0) {
	    return kdLookup32(lat,lng);
	   } else {
	    return kdLookup33(lat,lng);
	   }
	  }
	 } else {
	  if (lat < -33.75) {
	   return 246;
	  } else {
	   if (lng < -50.75) {
	    if (lat < -28.25) {
	     if (lng < -53.5) {
	      if (lat < -31.0) {
	       if (lng < -55.0) {
	        if (lat < -31.25) {
	         return 246;
	        } else {
	         if (lng < -55.25) {
	          return 246;
	         } else {
	          return 330;
	         }
	        }
	       } else {
	        if (lat < -32.0) {
	         return 246;
	        } else {
	         if (lng < -54.25) {
	          if (lat < -31.5) {
	           return 246;
	          } else {
	           if (lng < -54.75) {
	            if (lat < -31.25) {
	             return 246;
	            } else {
	             return 330;
	            }
	           } else {
	            if (lng < -54.5) {
	             if (lat < -31.25) {
	              return 246;
	             } else {
	              return 330;
	             }
	            } else {
	             return 330;
	            }
	           }
	          }
	         } else {
	          if (lat < -31.75) {
	           if (lng < -53.75) {
	            return 246;
	           } else {
	            return 330;
	           }
	          } else {
	           return 330;
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < -55.25) {
	        if (lat < -29.75) {
	         if (lat < -30.5) {
	          if (lng < -55.75) {
	           if (lng < -56.0) {
	            return 246;
	           } else {
	            return 330;
	           }
	          } else {
	           if (lng < -55.5) {
	            if (lat < -30.75) {
	             return 246;
	            } else {
	             return 330;
	            }
	           } else {
	            if (lat < -30.75) {
	             return 246;
	            } else {
	             return 330;
	            }
	           }
	          }
	         } else {
	          return 330;
	         }
	        } else {
	         if (lat < -28.75) {
	          return 330;
	         } else {
	          if (lng < -55.75) {
	           if (lng < -56.0) {
	            return 279;
	           } else {
	            if (lat < -28.5) {
	             return 330;
	            } else {
	             return 279;
	            }
	           }
	          } else {
	           return 330;
	          }
	         }
	        }
	       } else {
	        return 330;
	       }
	      }
	     } else {
	      if (lat < -31.5) {
	       if (lng < -52.25) {
	        if (lat < -32.75) {
	         if (lng < -53.25) {
	          if (lat < -33.25) {
	           if (lat < -33.5) {
	            return 246;
	           } else {
	            return 330;
	           }
	          } else {
	           if (lat < -33.0) {
	            return 330;
	           } else {
	            return 246;
	           }
	          }
	         } else {
	          return 330;
	         }
	        } else {
	         if (lng < -53.0) {
	          if (lat < -32.25) {
	           if (lng < -53.25) {
	            return 246;
	           } else {
	            if (lat < -32.5) {
	             return 246;
	            } else {
	             return 330;
	            }
	           }
	          } else {
	           return 330;
	          }
	         } else {
	          return 330;
	         }
	        }
	       } else {
	        return 330;
	       }
	      } else {
	       return 330;
	      }
	     }
	    } else {
	     return kdLookup34(lat,lng);
	    }
	   } else {
	    return 330;
	   }
	  }
	 }
	}

	private  int kdLookup36(double lat, double lng)
	{
	 if (lat < -17.0) {
	  if (lng < -64.75) {
	   if (lat < -21.75) {
	    if (lng < -66.25) {
	     if (lng < -66.75) {
	      return 191;
	     } else {
	      if (lat < -22.25) {
	       return 160;
	      } else {
	       if (lng < -66.5) {
	        return 191;
	       } else {
	        if (lat < -22.0) {
	         return 160;
	        } else {
	         return 191;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -65.5) {
	      if (lng < -65.75) {
	       return 160;
	      } else {
	       if (lat < -22.0) {
	        return 160;
	       } else {
	        return 191;
	       }
	      }
	     } else {
	      if (lng < -65.25) {
	       if (lat < -22.0) {
	        return 160;
	       } else {
	        return 191;
	       }
	      } else {
	       if (lat < -22.25) {
	        return 128;
	       } else {
	        if (lng < -65.0) {
	         if (lat < -22.0) {
	          return 160;
	         } else {
	          return 191;
	         }
	        } else {
	         if (lat < -22.0) {
	          return 128;
	         } else {
	          return 191;
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return 191;
	   }
	  } else {
	   if (lat < -20.5) {
	    if (lng < -63.5) {
	     if (lat < -22.0) {
	      if (lng < -64.25) {
	       if (lng < -64.5) {
	        return 128;
	       } else {
	        if (lat < -22.25) {
	         return 128;
	        } else {
	         return 191;
	        }
	       }
	      } else {
	       if (lng < -64.0) {
	        return 191;
	       } else {
	        return 128;
	       }
	      }
	     } else {
	      return 191;
	     }
	    } else {
	     if (lat < -21.5) {
	      if (lng < -62.75) {
	       if (lat < -22.0) {
	        return 128;
	       } else {
	        return 191;
	       }
	      } else {
	       if (lat < -22.0) {
	        if (lng < -62.5) {
	         return 128;
	        } else {
	         if (lng < -62.25) {
	          if (lat < -22.25) {
	           return 128;
	          } else {
	           return 220;
	          }
	         } else {
	          return 220;
	         }
	        }
	       } else {
	        if (lng < -62.5) {
	         return 191;
	        } else {
	         if (lng < -62.25) {
	          if (lat < -21.75) {
	           return 220;
	          } else {
	           return 191;
	          }
	         } else {
	          return 220;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < -62.25) {
	       return 191;
	      } else {
	       return 220;
	      }
	     }
	    }
	   } else {
	    return 191;
	   }
	  }
	 } else {
	  if (lat < -13.0) {
	   return 191;
	  } else {
	   if (lng < -64.75) {
	    if (lng < -65.25) {
	     return 191;
	    } else {
	     if (lat < -11.75) {
	      return 191;
	     } else {
	      if (lng < -65.0) {
	       if (lat < -11.5) {
	        return 191;
	       } else {
	        return 342;
	       }
	      } else {
	       return 342;
	      }
	     }
	    }
	   } else {
	    if (lng < -63.5) {
	     if (lat < -12.25) {
	      if (lng < -64.0) {
	       return 191;
	      } else {
	       if (lat < -12.5) {
	        return 191;
	       } else {
	        if (lng < -63.75) {
	         return 342;
	        } else {
	         return 191;
	        }
	       }
	      }
	     } else {
	      if (lng < -64.25) {
	       if (lat < -12.0) {
	        return 191;
	       } else {
	        return 342;
	       }
	      } else {
	       return 342;
	      }
	     }
	    } else {
	     if (lat < -12.5) {
	      if (lng < -62.75) {
	       if (lng < -63.0) {
	        return 191;
	       } else {
	        if (lat < -12.75) {
	         return 191;
	        } else {
	         return 342;
	        }
	       }
	      } else {
	       if (lng < -62.5) {
	        if (lat < -12.75) {
	         return 191;
	        } else {
	         return 342;
	        }
	       } else {
	        return 342;
	       }
	      }
	     } else {
	      return 342;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup37(double lat, double lng)
	{
	 if (lat < -17.0) {
	  if (lng < -59.25) {
	   if (lat < -19.75) {
	    if (lng < -61.75) {
	     if (lat < -20.0) {
	      return 220;
	     } else {
	      return 191;
	     }
	    } else {
	     return 220;
	    }
	   } else {
	    if (lng < -60.75) {
	     if (lat < -19.5) {
	      if (lng < -61.75) {
	       return 191;
	      } else {
	       return 220;
	      }
	     } else {
	      return 191;
	     }
	    } else {
	     if (lat < -19.25) {
	      return 220;
	     } else {
	      return 191;
	     }
	    }
	   }
	  } else {
	   if (lat < -19.75) {
	    if (lng < -57.75) {
	     if (lat < -20.5) {
	      return 220;
	     } else {
	      if (lng < -58.0) {
	       return 220;
	      } else {
	       if (lat < -20.0) {
	        return 318;
	       } else {
	        return 191;
	       }
	      }
	     }
	    } else {
	     if (lat < -22.0) {
	      return 220;
	     } else {
	      return 318;
	     }
	    }
	   } else {
	    if (lng < -57.75) {
	     if (lat < -18.5) {
	      if (lng < -58.5) {
	       if (lat < -19.25) {
	        if (lng < -58.75) {
	         return 220;
	        } else {
	         if (lat < -19.5) {
	          return 220;
	         } else {
	          return 191;
	         }
	        }
	       } else {
	        return 191;
	       }
	      } else {
	       if (lat < -19.25) {
	        if (lng < -58.25) {
	         if (lat < -19.5) {
	          return 220;
	         } else {
	          return 191;
	         }
	        } else {
	         if (lng < -58.0) {
	          return 191;
	         } else {
	          return 318;
	         }
	        }
	       } else {
	        return 191;
	       }
	      }
	     } else {
	      if (lng < -58.25) {
	       return 191;
	      } else {
	       if (lat < -17.5) {
	        return 191;
	       } else {
	        if (lng < -58.0) {
	         if (lat < -17.25) {
	          return 191;
	         } else {
	          return 364;
	         }
	        } else {
	         return 364;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < -18.5) {
	      if (lng < -57.5) {
	       if (lat < -19.0) {
	        return 318;
	       } else {
	        return 191;
	       }
	      } else {
	       return 318;
	      }
	     } else {
	      if (lng < -57.0) {
	       if (lat < -17.75) {
	        if (lng < -57.5) {
	         return 191;
	        } else {
	         return 318;
	        }
	       } else {
	        if (lng < -57.5) {
	         if (lat < -17.5) {
	          return 191;
	         } else {
	          return 364;
	         }
	        } else {
	         return 364;
	        }
	       }
	      } else {
	       if (lat < -17.5) {
	        return 318;
	       } else {
	        if (lng < -56.75) {
	         return 364;
	        } else {
	         if (lng < -56.5) {
	          if (lat < -17.25) {
	           return 318;
	          } else {
	           return 364;
	          }
	         } else {
	          if (lat < -17.25) {
	           return 318;
	          } else {
	           return 364;
	          }
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < -59.25) {
	   if (lat < -14.25) {
	    if (lng < -60.25) {
	     return 191;
	    } else {
	     if (lat < -15.75) {
	      if (lat < -16.25) {
	       return 191;
	      } else {
	       if (lng < -60.0) {
	        return 191;
	       } else {
	        return 364;
	       }
	      }
	     } else {
	      if (lat < -15.25) {
	       if (lng < -60.0) {
	        return 191;
	       } else {
	        return 364;
	       }
	      } else {
	       return 364;
	      }
	     }
	    }
	   } else {
	    if (lat < -12.75) {
	     if (lng < -60.75) {
	      if (lat < -13.5) {
	       return 191;
	      } else {
	       if (lng < -61.75) {
	        if (lat < -13.25) {
	         return 191;
	        } else {
	         return 342;
	        }
	       } else {
	        return 342;
	       }
	      }
	     } else {
	      if (lng < -60.0) {
	       if (lat < -13.5) {
	        if (lng < -60.5) {
	         return 191;
	        } else {
	         if (lat < -14.0) {
	          if (lng < -60.25) {
	           return 191;
	          } else {
	           return 364;
	          }
	         } else {
	          if (lng < -60.25) {
	           if (lat < -13.75) {
	            return 191;
	           } else {
	            return 364;
	           }
	          } else {
	           return 364;
	          }
	         }
	        }
	       } else {
	        if (lng < -60.5) {
	         return 342;
	        } else {
	         if (lat < -13.25) {
	          return 364;
	         } else {
	          if (lng < -60.25) {
	           return 342;
	          } else {
	           if (lat < -13.0) {
	            return 364;
	           } else {
	            return 342;
	           }
	          }
	         }
	        }
	       }
	      } else {
	       return 364;
	      }
	     }
	    } else {
	     if (lng < -60.0) {
	      return 342;
	     } else {
	      if (lat < -12.0) {
	       if (lng < -59.75) {
	        if (lat < -12.5) {
	         return 364;
	        } else {
	         return 342;
	        }
	       } else {
	        return 364;
	       }
	      } else {
	       if (lng < -59.75) {
	        if (lat < -11.75) {
	         return 342;
	        } else {
	         if (lat < -11.5) {
	          return 364;
	         } else {
	          return 342;
	         }
	        }
	       } else {
	        return 364;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < -16.25) {
	    if (lng < -58.25) {
	     return 191;
	    } else {
	     return 364;
	    }
	   } else {
	    return 364;
	   }
	  }
	 }
	}

	private  int kdLookup38(double lat, double lng)
	{
	 if (lng < -59.25) {
	  if (lat < -8.5) {
	   if (lng < -60.75) {
	    if (lat < -10.0) {
	     if (lng < -61.5) {
	      return 342;
	     } else {
	      if (lat < -10.75) {
	       if (lng < -61.25) {
	        if (lat < -11.0) {
	         return 342;
	        } else {
	         return 364;
	        }
	       } else {
	        if (lng < -61.0) {
	         if (lat < -11.0) {
	          return 342;
	         } else {
	          return 364;
	         }
	        } else {
	         return 342;
	        }
	       }
	      } else {
	       if (lng < -61.25) {
	        if (lat < -10.25) {
	         return 342;
	        } else {
	         return 364;
	        }
	       } else {
	        return 364;
	       }
	      }
	     }
	    } else {
	     if (lat < -9.25) {
	      if (lng < -61.5) {
	       return 342;
	      } else {
	       return 364;
	      }
	     } else {
	      if (lng < -61.5) {
	       if (lat < -8.75) {
	        return 342;
	       } else {
	        if (lng < -61.75) {
	         return 7;
	        } else {
	         return 342;
	        }
	       }
	      } else {
	       if (lng < -61.25) {
	        if (lat < -8.75) {
	         return 364;
	        } else {
	         return 7;
	        }
	       } else {
	        if (lat < -8.75) {
	         return 364;
	        } else {
	         return 7;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < -10.0) {
	     if (lng < -60.0) {
	      if (lat < -11.0) {
	       return 342;
	      } else {
	       return 364;
	      }
	     } else {
	      if (lat < -11.0) {
	       if (lng < -59.75) {
	        return 342;
	       } else {
	        return 364;
	       }
	      } else {
	       return 364;
	      }
	     }
	    } else {
	     if (lng < -60.0) {
	      if (lat < -8.75) {
	       return 364;
	      } else {
	       return 7;
	      }
	     } else {
	      if (lat < -8.75) {
	       return 364;
	      } else {
	       return 7;
	      }
	     }
	    }
	   }
	  } else {
	   return 7;
	  }
	 } else {
	  if (lat < -8.5) {
	   if (lng < -57.75) {
	    if (lat < -8.75) {
	     return 364;
	    } else {
	     if (lng < -59.0) {
	      return 7;
	     } else {
	      return 364;
	     }
	    }
	   } else {
	    if (lat < -9.25) {
	     return 364;
	    } else {
	     if (lng < -57.0) {
	      if (lng < -57.5) {
	       return 364;
	      } else {
	       if (lat < -8.75) {
	        return 364;
	       } else {
	        return 311;
	       }
	      }
	     } else {
	      if (lng < -56.75) {
	       if (lat < -9.0) {
	        return 364;
	       } else {
	        return 311;
	       }
	      } else {
	       return 311;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < -57.75) {
	    if (lat < -7.25) {
	     if (lng < -58.25) {
	      return 7;
	     } else {
	      if (lat < -7.5) {
	       return 364;
	      } else {
	       if (lng < -58.0) {
	        return 7;
	       } else {
	        return 364;
	       }
	      }
	     }
	    } else {
	     if (lng < -58.25) {
	      return 7;
	     } else {
	      if (lat < -6.5) {
	       if (lat < -7.0) {
	        if (lng < -58.0) {
	         return 7;
	        } else {
	         return 311;
	        }
	       } else {
	        return 311;
	       }
	      } else {
	       if (lat < -6.25) {
	        return 311;
	       } else {
	        if (lng < -58.0) {
	         return 7;
	        } else {
	         return 311;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < -8.0) {
	     if (lng < -57.5) {
	      return 364;
	     } else {
	      return 311;
	     }
	    } else {
	     return 311;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup39(double lat, double lng)
	{
	 if (lng < -62.0) {
	  if (lat < -5.75) {
	   if (lng < -64.75) {
	    if (lat < -9.25) {
	     if (lng < -66.25) {
	      if (lat < -10.25) {
	       return 191;
	      } else {
	       if (lng < -67.0) {
	        if (lat < -9.5) {
	         return 181;
	        } else {
	         return 7;
	        }
	       } else {
	        if (lat < -9.75) {
	         if (lng < -66.75) {
	          if (lat < -10.0) {
	           return 191;
	          } else {
	           return 181;
	          }
	         } else {
	          return 191;
	         }
	        } else {
	         if (lng < -66.5) {
	          return 7;
	         } else {
	          if (lat < -9.5) {
	           return 342;
	          } else {
	           return 7;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < -10.25) {
	       if (lng < -65.25) {
	        return 191;
	       } else {
	        return 342;
	       }
	      } else {
	       if (lng < -65.5) {
	        if (lat < -9.75) {
	         return 191;
	        } else {
	         if (lng < -65.75) {
	          return 342;
	         } else {
	          if (lat < -9.5) {
	           return 342;
	          } else {
	           return 7;
	          }
	         }
	        }
	       } else {
	        if (lat < -9.75) {
	         if (lng < -65.25) {
	          return 191;
	         } else {
	          return 342;
	         }
	        } else {
	         if (lng < -65.25) {
	          if (lat < -9.5) {
	           return 191;
	          } else {
	           return 342;
	          }
	         } else {
	          return 342;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     return 7;
	    }
	   } else {
	    if (lat < -8.5) {
	     if (lng < -64.0) {
	      if (lat < -8.75) {
	       return 342;
	      } else {
	       return 7;
	      }
	     } else {
	      return 342;
	     }
	    } else {
	     if (lng < -63.5) {
	      if (lat < -8.25) {
	       if (lng < -63.75) {
	        return 7;
	       } else {
	        return 342;
	       }
	      } else {
	       return 7;
	      }
	     } else {
	      if (lat < -8.0) {
	       if (lng < -62.5) {
	        return 342;
	       } else {
	        if (lng < -62.25) {
	         if (lat < -8.25) {
	          return 342;
	         } else {
	          return 7;
	         }
	        } else {
	         return 7;
	        }
	       }
	      } else {
	       return 7;
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < -0.75) {
	    return 7;
	   } else {
	    if (lng < -62.5) {
	     return 7;
	    } else {
	     if (lat < -0.5) {
	      return 350;
	     } else {
	      if (lng < -62.25) {
	       return 7;
	      } else {
	       return 350;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < -5.75) {
	   return kdLookup38(lat,lng);
	  } else {
	   if (lng < -59.25) {
	    if (lat < -1.25) {
	     return 7;
	    } else {
	     if (lng < -60.75) {
	      if (lng < -61.5) {
	       if (lat < -1.0) {
	        if (lng < -61.75) {
	         return 7;
	        } else {
	         return 350;
	        }
	       } else {
	        return 350;
	       }
	      } else {
	       if (lat < -0.5) {
	        return 7;
	       } else {
	        return 350;
	       }
	      }
	     } else {
	      if (lng < -60.25) {
	       if (lat < -0.75) {
	        return 7;
	       } else {
	        return 350;
	       }
	      } else {
	       return 7;
	      }
	     }
	    }
	   } else {
	    if (lat < -3.0) {
	     if (lng < -57.75) {
	      return 7;
	     } else {
	      if (lat < -4.5) {
	       if (lng < -57.25) {
	        if (lat < -5.25) {
	         return 311;
	        } else {
	         if (lat < -5.0) {
	          if (lng < -57.5) {
	           return 7;
	          } else {
	           return 311;
	          }
	         } else {
	          if (lng < -57.5) {
	           return 7;
	          } else {
	           if (lat < -4.75) {
	            return 311;
	           } else {
	            return 7;
	           }
	          }
	         }
	        }
	       } else {
	        return 311;
	       }
	      } else {
	       if (lng < -57.0) {
	        if (lat < -4.0) {
	         if (lng < -57.25) {
	          return 7;
	         } else {
	          return 311;
	         }
	        } else {
	         return 7;
	        }
	       } else {
	        if (lat < -3.5) {
	         return 311;
	        } else {
	         if (lng < -56.75) {
	          return 7;
	         } else {
	          return 311;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -57.75) {
	      if (lat < -1.25) {
	       return 7;
	      } else {
	       if (lng < -58.5) {
	        if (lat < -0.25) {
	         return 7;
	        } else {
	         if (lng < -58.75) {
	          return 7;
	         } else {
	          return 311;
	         }
	        }
	       } else {
	        if (lat < -0.75) {
	         if (lng < -58.25) {
	          return 7;
	         } else {
	          if (lng < -58.0) {
	           if (lat < -1.0) {
	            return 7;
	           } else {
	            return 311;
	           }
	          } else {
	           return 311;
	          }
	         }
	        } else {
	         return 311;
	        }
	       }
	      }
	     } else {
	      if (lat < -1.5) {
	       if (lng < -57.0) {
	        if (lat < -1.75) {
	         return 7;
	        } else {
	         if (lng < -57.25) {
	          return 7;
	         } else {
	          return 311;
	         }
	        }
	       } else {
	        if (lat < -2.25) {
	         if (lng < -56.5) {
	          return 7;
	         } else {
	          if (lat < -2.5) {
	           return 311;
	          } else {
	           return 7;
	          }
	         }
	        } else {
	         if (lng < -56.75) {
	          if (lat < -1.75) {
	           return 7;
	          } else {
	           return 311;
	          }
	         } else {
	          if (lat < -2.0) {
	           return 7;
	          } else {
	           return 311;
	          }
	         }
	        }
	       }
	      } else {
	       return 311;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup40(double lat, double lng)
	{
	 if (lng < -53.5) {
	  if (lat < -19.75) {
	   if (lng < -55.5) {
	    if (lat < -22.0) {
	     if (lng < -56.0) {
	      return 220;
	     } else {
	      if (lng < -55.75) {
	       if (lat < -22.25) {
	        return 220;
	       } else {
	        return 318;
	       }
	      } else {
	       if (lat < -22.25) {
	        return 220;
	       } else {
	        return 318;
	       }
	      }
	     }
	    } else {
	     return 318;
	    }
	   } else {
	    return 318;
	   }
	  } else {
	   if (lng < -55.0) {
	    if (lat < -17.5) {
	     return 318;
	    } else {
	     if (lng < -55.75) {
	      if (lng < -56.0) {
	       return 318;
	      } else {
	       if (lat < -17.25) {
	        return 318;
	       } else {
	        return 364;
	       }
	      }
	     } else {
	      if (lng < -55.5) {
	       if (lat < -17.25) {
	        return 318;
	       } else {
	        return 364;
	       }
	      } else {
	       return 364;
	      }
	     }
	    }
	   } else {
	    if (lat < -18.0) {
	     return 318;
	    } else {
	     if (lng < -54.25) {
	      if (lat < -17.5) {
	       return 318;
	      } else {
	       return 364;
	      }
	     } else {
	      if (lat < -17.5) {
	       if (lng < -53.75) {
	        return 318;
	       } else {
	        return 364;
	       }
	      } else {
	       if (lng < -54.0) {
	        return 364;
	       } else {
	        if (lng < -53.75) {
	         if (lat < -17.25) {
	          return 318;
	         } else {
	          return 364;
	         }
	        } else {
	         if (lat < -17.25) {
	          return 318;
	         } else {
	          return 364;
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < -19.75) {
	   if (lng < -52.25) {
	    if (lat < -22.0) {
	     if (lng < -52.75) {
	      return 318;
	     } else {
	      if (lng < -52.5) {
	       if (lat < -22.25) {
	        return 330;
	       } else {
	        return 318;
	       }
	      } else {
	       return 330;
	      }
	     }
	    } else {
	     return 318;
	    }
	   } else {
	    if (lat < -21.25) {
	     if (lng < -51.75) {
	      if (lat < -21.75) {
	       return 330;
	      } else {
	       if (lng < -52.0) {
	        return 318;
	       } else {
	        if (lat < -21.5) {
	         return 330;
	        } else {
	         return 318;
	        }
	       }
	      }
	     } else {
	      return 330;
	     }
	    } else {
	     if (lng < -51.5) {
	      if (lat < -21.0) {
	       if (lng < -51.75) {
	        return 318;
	       } else {
	        return 330;
	       }
	      } else {
	       return 318;
	      }
	     } else {
	      if (lat < -20.5) {
	       return 330;
	      } else {
	       if (lng < -51.25) {
	        return 318;
	       } else {
	        if (lat < -20.25) {
	         return 330;
	        } else {
	         if (lng < -51.0) {
	          return 318;
	         } else {
	          if (lat < -20.0) {
	           return 330;
	          } else {
	           return 318;
	          }
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < -52.25) {
	    if (lat < -18.5) {
	     return 318;
	    } else {
	     if (lat < -17.75) {
	      if (lng < -53.0) {
	       if (lat < -18.0) {
	        return 318;
	       } else {
	        if (lng < -53.25) {
	         return 364;
	        } else {
	         return 318;
	        }
	       }
	      } else {
	       if (lng < -52.75) {
	        if (lat < -18.25) {
	         return 318;
	        } else {
	         return 330;
	        }
	       } else {
	        return 330;
	       }
	      }
	     } else {
	      if (lng < -53.0) {
	       return 364;
	      } else {
	       return 330;
	      }
	     }
	    }
	   } else {
	    if (lat < -18.75) {
	     if (lng < -51.5) {
	      if (lat < -19.0) {
	       return 318;
	      } else {
	       if (lng < -51.75) {
	        return 318;
	       } else {
	        return 330;
	       }
	      }
	     } else {
	      if (lat < -19.25) {
	       if (lng < -51.0) {
	        return 318;
	       } else {
	        if (lat < -19.5) {
	         return 330;
	        } else {
	         return 318;
	        }
	       }
	      } else {
	       if (lng < -51.25) {
	        if (lat < -19.0) {
	         return 318;
	        } else {
	         return 330;
	        }
	       } else {
	        return 330;
	       }
	      }
	     }
	    } else {
	     return 330;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup41(double lat, double lng)
	{
	 if (lng < -50.75) {
	  if (lat < -17.0) {
	   return kdLookup40(lat,lng);
	  } else {
	   if (lat < -14.5) {
	    if (lng < -53.0) {
	     return 364;
	    } else {
	     if (lat < -15.75) {
	      if (lng < -52.25) {
	       if (lat < -16.5) {
	        if (lng < -52.75) {
	         if (lat < -16.75) {
	          return 330;
	         } else {
	          return 364;
	         }
	        } else {
	         return 330;
	        }
	       } else {
	        if (lng < -52.5) {
	         return 364;
	        } else {
	         if (lat < -16.25) {
	          return 330;
	         } else {
	          return 364;
	         }
	        }
	       }
	      } else {
	       return 330;
	      }
	     } else {
	      if (lng < -51.75) {
	       return 364;
	      } else {
	       if (lat < -15.25) {
	        if (lng < -51.5) {
	         if (lat < -15.5) {
	          return 330;
	         } else {
	          return 364;
	         }
	        } else {
	         return 330;
	        }
	       } else {
	        if (lng < -51.25) {
	         if (lat < -15.0) {
	          if (lng < -51.5) {
	           return 364;
	          } else {
	           return 330;
	          }
	         } else {
	          return 364;
	         }
	        } else {
	         if (lat < -15.0) {
	          return 330;
	         } else {
	          if (lng < -51.0) {
	           return 364;
	          } else {
	           return 330;
	          }
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return 364;
	   }
	  }
	 } else {
	  if (lat < -15.0) {
	   return 330;
	  } else {
	   if (lng < -48.0) {
	    if (lat < -13.25) {
	     if (lng < -50.5) {
	      if (lat < -13.5) {
	       return 330;
	      } else {
	       return 364;
	      }
	     } else {
	      return 330;
	     }
	    } else {
	     if (lng < -49.5) {
	      if (lat < -12.25) {
	       if (lng < -50.25) {
	        if (lat < -12.75) {
	         if (lng < -50.5) {
	          return 364;
	         } else {
	          return 330;
	         }
	        } else {
	         if (lng < -50.5) {
	          return 364;
	         } else {
	          return 61;
	         }
	        }
	       } else {
	        if (lat < -12.75) {
	         return 330;
	        } else {
	         return 61;
	        }
	       }
	      } else {
	       if (lng < -50.5) {
	        return 364;
	       } else {
	        return 61;
	       }
	      }
	     } else {
	      if (lat < -12.5) {
	       if (lng < -48.75) {
	        if (lng < -49.25) {
	         if (lat < -13.0) {
	          return 330;
	         } else {
	          return 61;
	         }
	        } else {
	         if (lat < -12.75) {
	          return 330;
	         } else {
	          if (lng < -49.0) {
	           return 61;
	          } else {
	           return 330;
	          }
	         }
	        }
	       } else {
	        if (lng < -48.5) {
	         if (lat < -13.0) {
	          return 330;
	         } else {
	          return 61;
	         }
	        } else {
	         if (lat < -13.0) {
	          return 330;
	         } else {
	          return 61;
	         }
	        }
	       }
	      } else {
	       return 61;
	      }
	     }
	    }
	   } else {
	    if (lat < -13.25) {
	     if (lng < -46.25) {
	      return 330;
	     } else {
	      if (lat < -14.25) {
	       if (lng < -45.75) {
	        return 330;
	       } else {
	        if (lng < -45.5) {
	         return 172;
	        } else {
	         if (lat < -14.75) {
	          return 330;
	         } else {
	          return 172;
	         }
	        }
	       }
	      } else {
	       if (lng < -46.0) {
	        if (lat < -13.75) {
	         return 330;
	        } else {
	         if (lat < -13.5) {
	          return 172;
	         } else {
	          return 330;
	         }
	        }
	       } else {
	        return 172;
	       }
	      }
	     }
	    } else {
	     if (lng < -46.5) {
	      if (lat < -12.75) {
	       if (lng < -47.25) {
	        return 61;
	       } else {
	        if (lng < -47.0) {
	         if (lat < -13.0) {
	          return 330;
	         } else {
	          return 61;
	         }
	        } else {
	         if (lng < -46.75) {
	          if (lat < -13.0) {
	           return 330;
	          } else {
	           return 61;
	          }
	         } else {
	          return 330;
	         }
	        }
	       }
	      } else {
	       return 61;
	      }
	     } else {
	      if (lat < -12.25) {
	       if (lng < -46.0) {
	        if (lat < -12.75) {
	         return 330;
	        } else {
	         return 61;
	        }
	       } else {
	        return 172;
	       }
	      } else {
	       if (lng < -46.0) {
	        if (lat < -11.75) {
	         if (lng < -46.25) {
	          return 61;
	         } else {
	          if (lat < -12.0) {
	           return 172;
	          } else {
	           return 61;
	          }
	         }
	        } else {
	         if (lng < -46.25) {
	          return 61;
	         } else {
	          return 172;
	         }
	        }
	       } else {
	        return 172;
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup42(double lat, double lng)
	{
	 if (lat < -5.75) {
	  if (lng < -53.5) {
	   if (lat < -9.25) {
	    if (lng < -55.0) {
	     if (lat < -9.5) {
	      return 364;
	     } else {
	      if (lng < -56.0) {
	       return 364;
	      } else {
	       return 311;
	      }
	     }
	    } else {
	     if (lat < -9.5) {
	      return 364;
	     } else {
	      return 311;
	     }
	    }
	   } else {
	    return 311;
	   }
	  } else {
	   if (lat < -8.5) {
	    if (lng < -52.25) {
	     if (lat < -9.5) {
	      return 364;
	     } else {
	      return 311;
	     }
	    } else {
	     if (lat < -9.75) {
	      return 364;
	     } else {
	      if (lng < -52.0) {
	       if (lat < -9.25) {
	        if (lat < -9.5) {
	         return 364;
	        } else {
	         return 311;
	        }
	       } else {
	        if (lat < -8.75) {
	         return 311;
	        } else {
	         return 401;
	        }
	       }
	      } else {
	       return 401;
	      }
	     }
	    }
	   } else {
	    if (lng < -52.25) {
	     if (lat < -7.25) {
	      if (lng < -52.75) {
	       return 311;
	      } else {
	       if (lat < -8.0) {
	        if (lng < -52.5) {
	         if (lat < -8.25) {
	          return 311;
	         } else {
	          return 401;
	         }
	        } else {
	         return 401;
	        }
	       } else {
	        if (lat < -7.5) {
	         return 401;
	        } else {
	         if (lng < -52.5) {
	          return 311;
	         } else {
	          return 401;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < -6.5) {
	       if (lng < -52.5) {
	        return 311;
	       } else {
	        if (lat < -7.0) {
	         return 401;
	        } else {
	         return 311;
	        }
	       }
	      } else {
	       if (lng < -52.5) {
	        return 311;
	       } else {
	        if (lat < -6.0) {
	         return 311;
	        } else {
	         return 401;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < -6.75) {
	      return 401;
	     } else {
	      if (lng < -51.75) {
	       if (lat < -6.5) {
	        return 311;
	       } else {
	        return 401;
	       }
	      } else {
	       return 401;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < -3.0) {
	   if (lng < -52.75) {
	    return 311;
	   } else {
	    if (lat < -4.5) {
	     if (lng < -52.5) {
	      if (lat < -5.25) {
	       return 311;
	      } else {
	       return 401;
	      }
	     } else {
	      return 401;
	     }
	    } else {
	     if (lng < -51.75) {
	      if (lat < -3.75) {
	       if (lng < -52.5) {
	        return 311;
	       } else {
	        return 401;
	       }
	      } else {
	       if (lng < -52.25) {
	        return 311;
	       } else {
	        if (lat < -3.25) {
	         return 401;
	        } else {
	         return 311;
	        }
	       }
	      }
	     } else {
	      return 401;
	     }
	    }
	   }
	  } else {
	   if (lng < -52.75) {
	    return 311;
	   } else {
	    if (lat < -1.5) {
	     if (lng < -51.75) {
	      if (lat < -2.25) {
	       if (lng < -52.0) {
	        return 311;
	       } else {
	        if (lat < -2.75) {
	         return 311;
	        } else {
	         return 401;
	        }
	       }
	      } else {
	       if (lng < -52.25) {
	        return 311;
	       } else {
	        if (lat < -2.0) {
	         if (lng < -52.0) {
	          return 311;
	         } else {
	          return 401;
	         }
	        } else {
	         if (lng < -52.0) {
	          if (lat < -1.75) {
	           return 311;
	          } else {
	           return 401;
	          }
	         } else {
	          return 401;
	         }
	        }
	       }
	      }
	     } else {
	      return 401;
	     }
	    } else {
	     if (lng < -51.75) {
	      if (lat < -0.75) {
	       if (lng < -52.25) {
	        return 311;
	       } else {
	        if (lat < -1.0) {
	         return 311;
	        } else {
	         return 401;
	        }
	       }
	      } else {
	       if (lng < -52.5) {
	        if (lat < -0.25) {
	         return 311;
	        } else {
	         return 401;
	        }
	       } else {
	        return 401;
	       }
	      }
	     } else {
	      return 401;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup43(double lat, double lng)
	{
	 if (lng < -48.0) {
	  if (lat < -8.5) {
	   if (lng < -49.5) {
	    if (lat < -10.0) {
	     if (lng < -50.25) {
	      if (lat < -10.75) {
	       if (lng < -50.5) {
	        return 364;
	       } else {
	        return 61;
	       }
	      } else {
	       if (lat < -10.5) {
	        if (lng < -50.5) {
	         return 364;
	        } else {
	         return 61;
	        }
	       } else {
	        if (lng < -50.5) {
	         return 364;
	        } else {
	         if (lat < -10.25) {
	          return 61;
	         } else {
	          return 364;
	         }
	        }
	       }
	      }
	     } else {
	      return 61;
	     }
	    } else {
	     if (lat < -9.25) {
	      if (lng < -50.25) {
	       if (lat < -9.75) {
	        return 364;
	       } else {
	        return 401;
	       }
	      } else {
	       if (lng < -50.0) {
	        if (lat < -9.75) {
	         return 61;
	        } else {
	         return 401;
	        }
	       } else {
	        return 61;
	       }
	      }
	     } else {
	      if (lng < -49.75) {
	       return 401;
	      } else {
	       if (lat < -8.75) {
	        return 61;
	       } else {
	        return 401;
	       }
	      }
	     }
	    }
	   } else {
	    return 61;
	   }
	  } else {
	   if (lng < -49.25) {
	    return 401;
	   } else {
	    if (lat < -7.25) {
	     if (lng < -49.0) {
	      if (lat < -8.0) {
	       return 61;
	      } else {
	       if (lat < -7.5) {
	        return 401;
	       } else {
	        return 61;
	       }
	      }
	     } else {
	      return 61;
	     }
	    } else {
	     if (lat < -6.5) {
	      if (lng < -48.75) {
	       if (lat < -7.0) {
	        if (lng < -49.0) {
	         return 401;
	        } else {
	         return 61;
	        }
	       } else {
	        if (lng < -49.0) {
	         return 401;
	        } else {
	         if (lat < -6.75) {
	          return 61;
	         } else {
	          return 401;
	         }
	        }
	       }
	      } else {
	       return 61;
	      }
	     } else {
	      if (lng < -48.5) {
	       return 401;
	      } else {
	       if (lat < -6.25) {
	        return 61;
	       } else {
	        if (lng < -48.25) {
	         return 401;
	        } else {
	         return 61;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < -8.5) {
	   if (lng < -46.5) {
	    if (lat < -9.25) {
	     return 61;
	    } else {
	     if (lng < -47.0) {
	      return 61;
	     } else {
	      if (lat < -9.0) {
	       if (lng < -46.75) {
	        return 61;
	       } else {
	        return 144;
	       }
	      } else {
	       if (lng < -46.75) {
	        if (lat < -8.75) {
	         return 144;
	        } else {
	         return 61;
	        }
	       } else {
	        return 144;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < -10.0) {
	     if (lng < -45.75) {
	      if (lat < -10.75) {
	       if (lng < -46.25) {
	        if (lat < -11.0) {
	         return 172;
	        } else {
	         return 61;
	        }
	       } else {
	        return 172;
	       }
	      } else {
	       if (lng < -46.25) {
	        return 61;
	       } else {
	        if (lat < -10.5) {
	         return 172;
	        } else {
	         if (lng < -46.0) {
	          return 61;
	         } else {
	          if (lat < -10.25) {
	           return 61;
	          } else {
	           return 144;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < -10.75) {
	       return 172;
	      } else {
	       if (lng < -45.5) {
	        if (lat < -10.25) {
	         return 172;
	        } else {
	         return 144;
	        }
	       } else {
	        if (lat < -10.5) {
	         if (lng < -45.25) {
	          return 172;
	         } else {
	          return 144;
	         }
	        } else {
	         if (lng < -45.25) {
	          if (lat < -10.25) {
	           return 172;
	          } else {
	           return 144;
	          }
	         } else {
	          return 144;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -46.25) {
	      if (lat < -9.75) {
	       return 61;
	      } else {
	       return 144;
	      }
	     } else {
	      return 144;
	     }
	    }
	   }
	  } else {
	   if (lng < -46.5) {
	    if (lat < -7.25) {
	     if (lng < -47.25) {
	      return 61;
	     } else {
	      if (lat < -8.0) {
	       if (lng < -46.75) {
	        return 61;
	       } else {
	        if (lat < -8.25) {
	         return 144;
	        } else {
	         return 61;
	        }
	       }
	      } else {
	       if (lng < -47.0) {
	        if (lat < -7.5) {
	         return 61;
	        } else {
	         return 144;
	        }
	       } else {
	        if (lat < -7.75) {
	         if (lng < -46.75) {
	          return 144;
	         } else {
	          return 61;
	         }
	        } else {
	         return 144;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -47.25) {
	      if (lat < -6.75) {
	       if (lng < -47.5) {
	        return 61;
	       } else {
	        return 144;
	       }
	      } else {
	       return 61;
	      }
	     } else {
	      return 144;
	     }
	    }
	   } else {
	    if (lat < -6.0) {
	     if (lat < -6.25) {
	      if (lat < -6.5) {
	       if (lat < -6.75) {
	        if (lat < -7.0) {
	         if (lng < -46.25) {
	          if (lat < -7.25) {
	           if (lat < -8.0) {
	            return 144;
	           } else {
	            if (lat < -7.75) {
	             return 61;
	            } else {
	             return 144;
	            }
	           }
	          } else {
	           return 144;
	          }
	         } else {
	          return 144;
	         }
	        } else {
	         return 144;
	        }
	       } else {
	        return 144;
	       }
	      } else {
	       return 144;
	      }
	     } else {
	      return 144;
	     }
	    } else {
	     return 144;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup44(double lat, double lng)
	{
	 if (lng < -67.5) {
	  if (lat < -22.5) {
	   if (lng < -78.75) {
	    return 31;
	   } else {
	    if (lat < -33.75) {
	     if (lng < -73.25) {
	      return 31;
	     } else {
	      if (lat < -39.5) {
	       if (lng < -70.5) {
	        if (lat < -42.25) {
	         if (lng < -72.0) {
	          return 31;
	         } else {
	          if (lat < -43.75) {
	           if (lng < -71.25) {
	            if (lat < -44.5) {
	             if (lng < -71.5) {
	              return 31;
	             } else {
	              return 88;
	             }
	            } else {
	             if (lng < -71.75) {
	              return 31;
	             } else {
	              if (lat < -44.25) {
	               return 31;
	              } else {
	               if (lng < -71.5) {
	                if (lat < -44.0) {
	                 return 88;
	                } else {
	                 return 31;
	                }
	               } else {
	                return 88;
	               }
	              }
	             }
	            }
	           } else {
	            if (lat < -44.5) {
	             if (lng < -71.0) {
	              if (lat < -44.75) {
	               return 88;
	              } else {
	               return 31;
	              }
	             } else {
	              return 88;
	             }
	            } else {
	             if (lng < -71.0) {
	              if (lat < -44.25) {
	               return 31;
	              } else {
	               return 88;
	              }
	             } else {
	              return 88;
	             }
	            }
	           }
	          } else {
	           if (lng < -71.5) {
	            if (lat < -43.0) {
	             if (lat < -43.5) {
	              return 31;
	             } else {
	              if (lng < -71.75) {
	               return 31;
	              } else {
	               if (lat < -43.25) {
	                return 88;
	               } else {
	                return 31;
	               }
	              }
	             }
	            } else {
	             return 88;
	            }
	           } else {
	            return 88;
	           }
	          }
	         }
	        } else {
	         if (lng < -72.0) {
	          return 31;
	         } else {
	          if (lat < -41.0) {
	           if (lng < -71.25) {
	            if (lat < -41.75) {
	             if (lng < -71.75) {
	              if (lat < -42.0) {
	               return 88;
	              } else {
	               return 31;
	              }
	             } else {
	              return 88;
	             }
	            } else {
	             if (lng < -71.75) {
	              return 31;
	             } else {
	              return 128;
	             }
	            }
	           } else {
	            if (lat < -41.75) {
	             return 88;
	            } else {
	             return 128;
	            }
	           }
	          } else {
	           if (lng < -71.5) {
	            if (lat < -40.25) {
	             if (lat < -40.75) {
	              if (lng < -71.75) {
	               return 31;
	              } else {
	               return 128;
	              }
	             } else {
	              if (lng < -71.75) {
	               return 31;
	              } else {
	               return 128;
	              }
	             }
	            } else {
	             if (lat < -40.0) {
	              if (lng < -71.75) {
	               return 31;
	              } else {
	               return 128;
	              }
	             } else {
	              return 31;
	             }
	            }
	           } else {
	            return 128;
	           }
	          }
	         }
	        }
	       } else {
	        if (lat < -41.75) {
	         return 88;
	        } else {
	         return 128;
	        }
	       }
	      } else {
	       return kdLookup23(lat,lng);
	      }
	     }
	    } else {
	     if (lng < -73.25) {
	      return 0;
	     } else {
	      return kdLookup24(lat,lng);
	     }
	    }
	   }
	  } else {
	   return kdLookup29(lat,lng);
	  }
	 } else {
	  if (lat < -22.5) {
	   return kdLookup35(lat,lng);
	  } else {
	   if (lng < -56.25) {
	    if (lat < -11.25) {
	     if (lng < -62.0) {
	      return kdLookup36(lat,lng);
	     } else {
	      return kdLookup37(lat,lng);
	     }
	    } else {
	     return kdLookup39(lat,lng);
	    }
	   } else {
	    if (lat < -11.25) {
	     return kdLookup41(lat,lng);
	    } else {
	     if (lng < -50.75) {
	      return kdLookup42(lat,lng);
	     } else {
	      if (lat < -5.75) {
	       return kdLookup43(lat,lng);
	      } else {
	       if (lng < -48.0) {
	        if (lat < -4.75) {
	         if (lng < -48.5) {
	          return 401;
	         } else {
	          if (lat < -5.25) {
	           if (lng < -48.25) {
	            return 401;
	           } else {
	            return 61;
	           }
	          } else {
	           if (lng < -48.25) {
	            if (lat < -5.0) {
	             return 61;
	            } else {
	             return 401;
	            }
	           } else {
	            if (lat < -5.0) {
	             return 61;
	            } else {
	             return 144;
	            }
	           }
	          }
	         }
	        } else {
	         return 401;
	        }
	       } else {
	        if (lat < -3.0) {
	         if (lng < -46.75) {
	          if (lat < -4.5) {
	           if (lng < -47.5) {
	            if (lat < -5.25) {
	             return 61;
	            } else {
	             if (lat < -5.0) {
	              if (lng < -47.75) {
	               return 61;
	              } else {
	               return 144;
	              }
	             } else {
	              return 144;
	             }
	            }
	           } else {
	            if (lat < -5.5) {
	             if (lng < -47.25) {
	              return 61;
	             } else {
	              return 144;
	             }
	            } else {
	             return 144;
	            }
	           }
	          } else {
	           if (lat < -3.75) {
	            if (lng < -47.5) {
	             return 401;
	            } else {
	             if (lng < -47.25) {
	              if (lat < -4.25) {
	               return 144;
	              } else {
	               return 401;
	              }
	             } else {
	              if (lat < -4.0) {
	               return 144;
	              } else {
	               if (lng < -47.0) {
	                return 401;
	               } else {
	                return 144;
	               }
	              }
	             }
	            }
	           } else {
	            if (lng < -47.0) {
	             return 401;
	            } else {
	             if (lat < -3.5) {
	              return 144;
	             } else {
	              return 401;
	             }
	            }
	           }
	          }
	         } else {
	          return 144;
	         }
	        } else {
	         if (lng < -46.5) {
	          return 401;
	         } else {
	          if (lat < -1.5) {
	           if (lng < -46.0) {
	            if (lat < -2.25) {
	             if (lat < -2.5) {
	              return 144;
	             } else {
	              if (lng < -46.25) {
	               return 401;
	              } else {
	               return 144;
	              }
	             }
	            } else {
	             if (lat < -2.0) {
	              if (lng < -46.25) {
	               return 401;
	              } else {
	               return 144;
	              }
	             } else {
	              if (lng < -46.25) {
	               return 401;
	              } else {
	               if (lat < -1.75) {
	                return 401;
	               } else {
	                return 144;
	               }
	              }
	             }
	            }
	           } else {
	            return 144;
	           }
	          } else {
	           if (lng < -45.75) {
	            if (lat < -0.75) {
	             if (lng < -46.0) {
	              return 401;
	             } else {
	              return 144;
	             }
	            } else {
	             return 401;
	            }
	           } else {
	            return 144;
	           }
	          }
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup45(double lat, double lng)
	{
	 if (lng < -39.5) {
	  if (lat < -17.0) {
	   if (lng < -40.75) {
	    return 330;
	   } else {
	    if (lat < -19.75) {
	     return 330;
	    } else {
	     if (lat < -18.25) {
	      return 330;
	     } else {
	      if (lng < -40.25) {
	       if (lat < -17.25) {
	        return 330;
	       } else {
	        if (lng < -40.5) {
	         return 330;
	        } else {
	         return 172;
	        }
	       }
	      } else {
	       if (lat < -17.75) {
	        if (lng < -40.0) {
	         return 330;
	        } else {
	         if (lng < -39.75) {
	          if (lat < -18.0) {
	           return 330;
	          } else {
	           return 172;
	          }
	         } else {
	          return 172;
	         }
	        }
	       } else {
	        if (lng < -40.0) {
	         if (lat < -17.5) {
	          return 330;
	         } else {
	          return 172;
	         }
	        } else {
	         return 172;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < -14.25) {
	    if (lng < -42.25) {
	     if (lng < -43.75) {
	      if (lat < -14.5) {
	       return 330;
	      } else {
	       if (lng < -44.75) {
	        return 172;
	       } else {
	        return 330;
	       }
	      }
	     } else {
	      if (lat < -14.75) {
	       return 330;
	      } else {
	       if (lng < -43.0) {
	        if (lng < -43.5) {
	         if (lat < -14.5) {
	          return 330;
	         } else {
	          return 172;
	         }
	        } else {
	         if (lng < -43.25) {
	          return 172;
	         } else {
	          if (lat < -14.5) {
	           return 330;
	          } else {
	           return 172;
	          }
	         }
	        }
	       } else {
	        if (lng < -42.75) {
	         if (lat < -14.5) {
	          return 330;
	         } else {
	          return 172;
	         }
	        } else {
	         return 172;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -41.0) {
	      if (lat < -15.75) {
	       return 330;
	      } else {
	       if (lat < -15.0) {
	        if (lng < -41.5) {
	         return 330;
	        } else {
	         if (lat < -15.5) {
	          if (lng < -41.25) {
	           return 330;
	          } else {
	           return 172;
	          }
	         } else {
	          if (lng < -41.25) {
	           if (lat < -15.25) {
	            return 330;
	           } else {
	            return 172;
	           }
	          } else {
	           return 172;
	          }
	         }
	        }
	       } else {
	        return 172;
	       }
	      }
	     } else {
	      if (lat < -15.75) {
	       if (lng < -40.25) {
	        if (lat < -16.75) {
	         if (lng < -40.5) {
	          return 330;
	         } else {
	          return 172;
	         }
	        } else {
	         return 330;
	        }
	       } else {
	        if (lat < -16.5) {
	         return 172;
	        } else {
	         if (lng < -40.0) {
	          return 330;
	         } else {
	          if (lat < -16.25) {
	           return 172;
	          } else {
	           if (lng < -39.75) {
	            return 330;
	           } else {
	            return 172;
	           }
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < -40.5) {
	        if (lat < -15.5) {
	         return 330;
	        } else {
	         return 172;
	        }
	       } else {
	        return 172;
	       }
	      }
	     }
	    }
	   } else {
	    return 172;
	   }
	  }
	 } else {
	  if (lat < -17.0) {
	   if (lng < -36.75) {
	    if (lat < -19.75) {
	     return 0;
	    } else {
	     if (lng < -38.25) {
	      if (lat < -18.5) {
	       return 330;
	      } else {
	       if (lat < -17.75) {
	        if (lng < -39.0) {
	         if (lat < -18.25) {
	          return 330;
	         } else {
	          return 172;
	         }
	        } else {
	         return 0;
	        }
	       } else {
	        return 172;
	       }
	      }
	     } else {
	      return 0;
	     }
	    }
	   } else {
	    return 0;
	   }
	  } else {
	   if (lng < -36.75) {
	    if (lat < -14.25) {
	     return 172;
	    } else {
	     if (lat < -12.75) {
	      return 172;
	     } else {
	      if (lng < -37.75) {
	       return 172;
	      } else {
	       if (lat < -12.0) {
	        return 172;
	       } else {
	        if (lng < -37.25) {
	         if (lat < -11.75) {
	          return 172;
	         } else {
	          if (lng < -37.5) {
	           if (lat < -11.5) {
	            return 172;
	           } else {
	            return 326;
	           }
	          } else {
	           return 326;
	          }
	         }
	        } else {
	         return 326;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return 0;
	   }
	  }
	 }
	}

	private  int kdLookup46(double lat, double lng)
	{
	 if (lng < -36.75) {
	  if (lat < -8.5) {
	   if (lng < -38.25) {
	    if (lat < -8.75) {
	     return 172;
	    } else {
	     if (lng < -39.0) {
	      return 172;
	     } else {
	      return 192;
	     }
	    }
	   } else {
	    if (lat < -10.0) {
	     if (lng < -37.75) {
	      if (lat < -10.25) {
	       if (lat < -10.5) {
	        if (lat < -11.0) {
	         return 172;
	        } else {
	         if (lng < -38.0) {
	          return 172;
	         } else {
	          if (lat < -10.75) {
	           return 326;
	          } else {
	           return 172;
	          }
	         }
	        }
	       } else {
	        return 172;
	       }
	      } else {
	       return 172;
	      }
	     } else {
	      return 326;
	     }
	    } else {
	     if (lng < -37.5) {
	      if (lat < -9.25) {
	       if (lng < -38.0) {
	        return 172;
	       } else {
	        if (lat < -9.75) {
	         if (lng < -37.75) {
	          return 172;
	         } else {
	          return 326;
	         }
	        } else {
	         return 326;
	        }
	       }
	      } else {
	       if (lng < -38.0) {
	        return 192;
	       } else {
	        if (lat < -9.0) {
	         return 326;
	        } else {
	         if (lng < -37.75) {
	          return 192;
	         } else {
	          if (lat < -8.75) {
	           return 326;
	          } else {
	           return 192;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < -9.25) {
	       return 326;
	      } else {
	       if (lng < -37.25) {
	        if (lat < -8.75) {
	         return 326;
	        } else {
	         return 192;
	        }
	       } else {
	        if (lat < -9.0) {
	         if (lng < -37.0) {
	          return 326;
	         } else {
	          return 192;
	         }
	        } else {
	         return 192;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < -38.25) {
	    if (lat < -7.25) {
	     if (lng < -39.0) {
	      if (lat < -7.5) {
	       return 192;
	      } else {
	       if (lng < -39.25) {
	        return 192;
	       } else {
	        return 144;
	       }
	      }
	     } else {
	      if (lat < -7.75) {
	       return 192;
	      } else {
	       if (lng < -38.75) {
	        return 144;
	       } else {
	        if (lng < -38.5) {
	         if (lat < -7.5) {
	          return 192;
	         } else {
	          return 144;
	         }
	        } else {
	         return 144;
	        }
	       }
	      }
	     }
	    } else {
	     return 144;
	    }
	   } else {
	    if (lat < -7.25) {
	     if (lng < -37.5) {
	      if (lat < -7.75) {
	       return 192;
	      } else {
	       if (lng < -37.75) {
	        return 144;
	       } else {
	        if (lat < -7.5) {
	         return 192;
	        } else {
	         return 144;
	        }
	       }
	      }
	     } else {
	      if (lat < -8.0) {
	       if (lng < -37.0) {
	        return 192;
	       } else {
	        if (lat < -8.25) {
	         return 192;
	        } else {
	         return 144;
	        }
	       }
	      } else {
	       if (lng < -37.0) {
	        return 192;
	       } else {
	        if (lat < -7.5) {
	         return 144;
	        } else {
	         return 192;
	        }
	       }
	      }
	     }
	    } else {
	     return 144;
	    }
	   }
	  }
	 } else {
	  if (lat < -8.5) {
	   if (lng < -35.25) {
	    if (lat < -10.0) {
	     return 326;
	    } else {
	     if (lng < -36.0) {
	      if (lat < -9.25) {
	       return 326;
	      } else {
	       if (lng < -36.5) {
	        return 192;
	       } else {
	        if (lat < -9.0) {
	         return 326;
	        } else {
	         return 192;
	        }
	       }
	      }
	     } else {
	      if (lat < -9.25) {
	       return 326;
	      } else {
	       if (lng < -35.75) {
	        if (lat < -8.75) {
	         return 326;
	        } else {
	         return 192;
	        }
	       } else {
	        if (lat < -8.75) {
	         return 326;
	        } else {
	         return 192;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < -10.0) {
	     return 0;
	    } else {
	     if (lng < -34.5) {
	      if (lat < -9.25) {
	       return 0;
	      } else {
	       if (lng < -35.0) {
	        if (lat < -8.75) {
	         return 326;
	        } else {
	         return 192;
	        }
	       } else {
	        if (lat < -9.0) {
	         return 0;
	        } else {
	         if (lng < -34.75) {
	          if (lat < -8.75) {
	           return 326;
	          } else {
	           return 192;
	          }
	         } else {
	          return 0;
	         }
	        }
	       }
	      }
	     } else {
	      return 0;
	     }
	    }
	   }
	  } else {
	   if (lng < -35.25) {
	    if (lat < -7.25) {
	     if (lng < -36.0) {
	      if (lat < -8.0) {
	       return 192;
	      } else {
	       if (lng < -36.5) {
	        return 144;
	       } else {
	        if (lat < -7.75) {
	         return 192;
	        } else {
	         return 144;
	        }
	       }
	      }
	     } else {
	      if (lat < -7.75) {
	       return 192;
	      } else {
	       if (lng < -35.75) {
	        return 144;
	       } else {
	        if (lng < -35.5) {
	         if (lat < -7.5) {
	          return 192;
	         } else {
	          return 144;
	         }
	        } else {
	         return 192;
	        }
	       }
	      }
	     }
	    } else {
	     return 144;
	    }
	   } else {
	    if (lat < -7.25) {
	     return 192;
	    } else {
	     return 144;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup47(double lat, double lng)
	{
	 if (lat < -45.0) {
	  if (lng < -22.5) {
	   if (lat < -67.5) {
	    return 122;
	   } else {
	    return 69;
	   }
	  } else {
	   return 122;
	  }
	 } else {
	  if (lng < -22.5) {
	   if (lat < -22.5) {
	    return 330;
	   } else {
	    if (lng < -33.75) {
	     if (lat < -11.25) {
	      return kdLookup45(lat,lng);
	     } else {
	      if (lng < -39.5) {
	       if (lat < -5.75) {
	        if (lng < -42.25) {
	         if (lat < -9.25) {
	          if (lng < -43.75) {
	           if (lat < -10.25) {
	            if (lng < -44.5) {
	             if (lat < -10.75) {
	              return 172;
	             } else {
	              return 144;
	             }
	            } else {
	             if (lat < -10.5) {
	              return 172;
	             } else {
	              if (lng < -44.0) {
	               return 144;
	              } else {
	               return 172;
	              }
	             }
	            }
	           } else {
	            return 144;
	           }
	          } else {
	           if (lat < -10.0) {
	            return 172;
	           } else {
	            if (lng < -43.0) {
	             if (lng < -43.5) {
	              if (lat < -9.5) {
	               return 144;
	              } else {
	               return 172;
	              }
	             } else {
	              return 172;
	             }
	            } else {
	             if (lng < -42.75) {
	              return 172;
	             } else {
	              if (lat < -9.5) {
	               return 172;
	              } else {
	               return 144;
	              }
	             }
	            }
	           }
	          }
	         } else {
	          return 144;
	         }
	        } else {
	         if (lat < -8.5) {
	          if (lng < -41.0) {
	           if (lat < -9.25) {
	            return 172;
	           } else {
	            if (lng < -41.75) {
	             if (lat < -9.0) {
	              if (lng < -42.0) {
	               return 144;
	              } else {
	               return 172;
	              }
	             } else {
	              return 144;
	             }
	            } else {
	             if (lng < -41.5) {
	              if (lat < -9.0) {
	               return 172;
	              } else {
	               return 144;
	              }
	             } else {
	              if (lat < -8.75) {
	               return 172;
	              } else {
	               if (lng < -41.25) {
	                return 144;
	               } else {
	                return 172;
	               }
	              }
	             }
	            }
	           }
	          } else {
	           if (lat < -9.25) {
	            return 172;
	           } else {
	            if (lng < -40.25) {
	             if (lng < -40.75) {
	              if (lat < -8.75) {
	               return 172;
	              } else {
	               return 192;
	              }
	             } else {
	              if (lat < -9.0) {
	               if (lng < -40.5) {
	                return 172;
	               } else {
	                return 192;
	               }
	              } else {
	               return 192;
	              }
	             }
	            } else {
	             if (lng < -40.0) {
	              if (lat < -9.0) {
	               return 172;
	              } else {
	               return 192;
	              }
	             } else {
	              if (lat < -9.0) {
	               return 172;
	              } else {
	               if (lng < -39.75) {
	                return 192;
	               } else {
	                if (lat < -8.75) {
	                 return 172;
	                } else {
	                 return 192;
	                }
	               }
	              }
	             }
	            }
	           }
	          }
	         } else {
	          if (lng < -41.0) {
	           return 144;
	          } else {
	           if (lat < -7.25) {
	            if (lng < -40.5) {
	             if (lat < -8.0) {
	              if (lng < -40.75) {
	               if (lat < -8.25) {
	                return 192;
	               } else {
	                return 144;
	               }
	              } else {
	               return 192;
	              }
	             } else {
	              return 144;
	             }
	            } else {
	             return 192;
	            }
	           } else {
	            return 144;
	           }
	          }
	         }
	        }
	       } else {
	        return 144;
	       }
	      } else {
	       if (lat < -5.75) {
	        return kdLookup46(lat,lng);
	       } else {
	        return 144;
	       }
	      }
	     }
	    } else {
	     return 0;
	    }
	   }
	  } else {
	   return 157;
	  }
	 }
	}

	private  int kdLookup48(double lat, double lng)
	{
	 if (lat < 16.75) {
	  if (lng < -87.25) {
	   if (lat < 14.0) {
	    if (lng < -87.75) {
	     return 259;
	    } else {
	     if (lat < 12.5) {
	      return 0;
	     } else {
	      if (lat < 13.25) {
	       return 260;
	      } else {
	       if (lat < 13.5) {
	        return 259;
	       } else {
	        if (lng < -87.5) {
	         return 259;
	        } else {
	         return 146;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < -88.75) {
	     if (lat < 15.25) {
	      if (lng < -89.5) {
	       if (lat < 14.25) {
	        if (lng < -89.75) {
	         return 393;
	        } else {
	         return 259;
	        }
	       } else {
	        return 393;
	       }
	      } else {
	       if (lat < 14.5) {
	        return 259;
	       } else {
	        if (lng < -89.25) {
	         return 393;
	        } else {
	         if (lat < 14.75) {
	          return 146;
	         } else {
	          if (lng < -89.0) {
	           return 393;
	          } else {
	           return 146;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 16.0) {
	       return 393;
	      } else {
	       if (lng < -89.0) {
	        return 393;
	       } else {
	        return 41;
	       }
	      }
	     }
	    } else {
	     if (lat < 15.25) {
	      if (lng < -88.5) {
	       if (lat < 14.25) {
	        return 259;
	       } else {
	        return 146;
	       }
	      } else {
	       return 146;
	      }
	     } else {
	      if (lng < -88.0) {
	       if (lat < 16.0) {
	        if (lng < -88.5) {
	         if (lat < 15.5) {
	          return 146;
	         } else {
	          return 393;
	         }
	        } else {
	         if (lat < 15.5) {
	          return 146;
	         } else {
	          if (lng < -88.25) {
	           return 393;
	          } else {
	           return 146;
	          }
	         }
	        }
	       } else {
	        if (lng < -88.5) {
	         return 41;
	        } else {
	         if (lat < 16.25) {
	          return 393;
	         } else {
	          return 41;
	         }
	        }
	       }
	      } else {
	       return 146;
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 14.0) {
	    if (lng < -86.5) {
	     if (lat < 12.5) {
	      return 260;
	     } else {
	      if (lat < 13.25) {
	       if (lng < -87.0) {
	        if (lat < 13.0) {
	         return 260;
	        } else {
	         return 146;
	        }
	       } else {
	        return 260;
	       }
	      } else {
	       if (lng < -86.75) {
	        return 146;
	       } else {
	        if (lat < 13.5) {
	         return 260;
	        } else {
	         if (lat < 13.75) {
	          return 146;
	         } else {
	          return 260;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     return 260;
	    }
	   } else {
	    if (lng < -86.0) {
	     return 146;
	    } else {
	     if (lat < 15.25) {
	      if (lng < -85.25) {
	       if (lat < 14.25) {
	        if (lng < -85.5) {
	         return 146;
	        } else {
	         return 260;
	        }
	       } else {
	        return 146;
	       }
	      } else {
	       if (lat < 14.5) {
	        return 260;
	       } else {
	        if (lng < -85.0) {
	         return 146;
	        } else {
	         if (lat < 14.75) {
	          return 260;
	         } else {
	          return 146;
	         }
	        }
	       }
	      }
	     } else {
	      return 146;
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 19.5) {
	   if (lng < -87.25) {
	    if (lng < -88.75) {
	     if (lat < 18.0) {
	      if (lng < -89.0) {
	       return 393;
	      } else {
	       return 41;
	      }
	     } else {
	      if (lat < 18.75) {
	       if (lng < -89.25) {
	        return 32;
	       } else {
	        return 402;
	       }
	      } else {
	       if (lng < -89.25) {
	        return 32;
	       } else {
	        return 402;
	       }
	      }
	     }
	    } else {
	     if (lat < 18.0) {
	      return 41;
	     } else {
	      if (lng < -88.0) {
	       if (lat < 18.75) {
	        if (lng < -88.5) {
	         if (lat < 18.25) {
	          return 41;
	         } else {
	          return 402;
	         }
	        } else {
	         if (lat < 18.5) {
	          return 41;
	         } else {
	          return 402;
	         }
	        }
	       } else {
	        return 402;
	       }
	      } else {
	       if (lat < 18.5) {
	        if (lng < -87.75) {
	         return 41;
	        } else {
	         return 402;
	        }
	       } else {
	        return 402;
	       }
	      }
	     }
	    }
	   } else {
	    return 0;
	   }
	  } else {
	   if (lng < -87.25) {
	    if (lat < 21.0) {
	     if (lng < -88.75) {
	      if (lat < 20.0) {
	       if (lng < -89.25) {
	        return 32;
	       } else {
	        return 402;
	       }
	      } else {
	       return 32;
	      }
	     } else {
	      if (lng < -88.0) {
	       if (lat < 20.25) {
	        return 402;
	       } else {
	        if (lng < -88.25) {
	         return 32;
	        } else {
	         if (lat < 20.5) {
	          return 402;
	         } else {
	          return 32;
	         }
	        }
	       }
	      } else {
	       if (lat < 20.75) {
	        return 402;
	       } else {
	        if (lng < -87.5) {
	         return 32;
	        } else {
	         return 402;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -88.75) {
	      return 32;
	     } else {
	      if (lng < -88.0) {
	       return 32;
	      } else {
	       if (lat < 21.75) {
	        if (lng < -87.5) {
	         return 32;
	        } else {
	         return 402;
	        }
	       } else {
	        return 32;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 21.0) {
	     return 402;
	    } else {
	     if (lng < -86.0) {
	      return 402;
	     } else {
	      return 377;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup49(double lat, double lng)
	{
	 if (lat < 5.5) {
	  if (lng < -70.0) {
	   return 391;
	  } else {
	   if (lat < 2.75) {
	    if (lat < 1.25) {
	     if (lng < -69.0) {
	      if (lat < 0.75) {
	       return 7;
	      } else {
	       return 391;
	      }
	     } else {
	      return 7;
	     }
	    } else {
	     if (lng < -68.75) {
	      if (lat < 2.0) {
	       if (lng < -69.5) {
	        if (lat < 1.5) {
	         if (lng < -69.75) {
	          return 391;
	         } else {
	          return 7;
	         }
	        } else {
	         if (lng < -69.75) {
	          return 391;
	         } else {
	          if (lat < 1.75) {
	           return 7;
	          } else {
	           return 391;
	          }
	         }
	        }
	       } else {
	        if (lng < -69.25) {
	         return 7;
	        } else {
	         if (lat < 1.75) {
	          return 7;
	         } else {
	          return 391;
	         }
	        }
	       }
	      } else {
	       return 391;
	      }
	     } else {
	      if (lat < 2.0) {
	       if (lng < -68.25) {
	        if (lat < 1.75) {
	         return 7;
	        } else {
	         return 391;
	        }
	       } else {
	        if (lng < -68.0) {
	         if (lat < 1.75) {
	          return 7;
	         } else {
	          return 391;
	         }
	        } else {
	         return 7;
	        }
	       }
	      } else {
	       return 391;
	      }
	     }
	    }
	   } else {
	    if (lat < 4.25) {
	     return 391;
	    } else {
	     if (lng < -67.75) {
	      return 391;
	     } else {
	      return 398;
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < -70.5) {
	   if (lat < 8.25) {
	    if (lng < -72.0) {
	     if (lat < 7.5) {
	      return 391;
	     } else {
	      if (lng < -72.25) {
	       return 391;
	      } else {
	       return 398;
	      }
	     }
	    } else {
	     if (lat < 7.0) {
	      return 391;
	     } else {
	      if (lng < -71.25) {
	       if (lat < 7.25) {
	        return 391;
	       } else {
	        return 398;
	       }
	      } else {
	       if (lat < 7.25) {
	        if (lng < -71.0) {
	         return 391;
	        } else {
	         if (lng < -70.75) {
	          return 398;
	         } else {
	          return 391;
	         }
	        }
	       } else {
	        return 398;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 9.75) {
	     if (lng < -72.25) {
	      if (lat < 9.0) {
	       if (lng < -72.5) {
	        return 391;
	       } else {
	        if (lat < 8.5) {
	         return 391;
	        } else {
	         return 398;
	        }
	       }
	      } else {
	       if (lng < -72.75) {
	        if (lat < 9.25) {
	         return 391;
	        } else {
	         if (lng < -73.0) {
	          if (lat < 9.5) {
	           return 398;
	          } else {
	           return 391;
	          }
	         } else {
	          if (lat < 9.5) {
	           return 391;
	          } else {
	           return 398;
	          }
	         }
	        }
	       } else {
	        if (lat < 9.25) {
	         if (lng < -72.5) {
	          return 391;
	         } else {
	          return 398;
	         }
	        } else {
	         return 398;
	        }
	       }
	      }
	     } else {
	      return 398;
	     }
	    } else {
	     if (lng < -72.5) {
	      if (lat < 10.5) {
	       if (lng < -73.0) {
	        return 391;
	       } else {
	        if (lat < 10.0) {
	         return 398;
	        } else {
	         if (lng < -72.75) {
	          return 391;
	         } else {
	          return 398;
	         }
	        }
	       }
	      } else {
	       if (lng < -72.75) {
	        return 391;
	       } else {
	        if (lat < 10.75) {
	         return 398;
	        } else {
	         return 391;
	        }
	       }
	      }
	     } else {
	      return 398;
	     }
	    }
	   }
	  } else {
	   if (lat < 8.25) {
	    if (lng < -69.0) {
	     if (lat < 6.75) {
	      if (lng < -69.5) {
	       return 391;
	      } else {
	       if (lat < 6.25) {
	        return 391;
	       } else {
	        return 398;
	       }
	      }
	     } else {
	      if (lng < -69.75) {
	       if (lat < 7.25) {
	        if (lng < -70.25) {
	         return 391;
	        } else {
	         if (lng < -70.0) {
	          if (lat < 7.0) {
	           return 391;
	          } else {
	           return 398;
	          }
	         } else {
	          if (lat < 7.0) {
	           return 391;
	          } else {
	           return 398;
	          }
	         }
	        }
	       } else {
	        return 398;
	       }
	      } else {
	       return 398;
	      }
	     }
	    } else {
	     if (lat < 6.5) {
	      if (lng < -68.25) {
	       if (lat < 6.25) {
	        return 391;
	       } else {
	        return 398;
	       }
	      } else {
	       if (lat < 6.25) {
	        return 391;
	       } else {
	        if (lng < -67.75) {
	         return 398;
	        } else {
	         return 391;
	        }
	       }
	      }
	     } else {
	      return 398;
	     }
	    }
	   } else {
	    return 398;
	   }
	  }
	 }
	}

	private  int kdLookup50(double lat, double lng)
	{
	 if (lat < 11.25) {
	  if (lng < -73.25) {
	   if (lat < 1.5) {
	    if (lng < -76.0) {
	     if (lng < -77.5) {
	      if (lat < 1.0) {
	       return 382;
	      } else {
	       if (lng < -78.25) {
	        if (lng < -78.5) {
	         return 382;
	        } else {
	         if (lat < 1.25) {
	          return 382;
	         } else {
	          return 391;
	         }
	        }
	       } else {
	        if (lng < -78.0) {
	         if (lat < 1.25) {
	          return 382;
	         } else {
	          return 391;
	         }
	        } else {
	         return 391;
	        }
	       }
	      }
	     } else {
	      if (lng < -76.75) {
	       if (lat < 0.75) {
	        if (lng < -77.25) {
	         return 382;
	        } else {
	         if (lat < 0.5) {
	          return 382;
	         } else {
	          return 391;
	         }
	        }
	       } else {
	        return 391;
	       }
	      } else {
	       if (lat < 0.5) {
	        if (lng < -76.5) {
	         return 382;
	        } else {
	         if (lng < -76.25) {
	          if (lat < 0.25) {
	           return 382;
	          } else {
	           return 391;
	          }
	         } else {
	          return 382;
	         }
	        }
	       } else {
	        return 391;
	       }
	      }
	     }
	    } else {
	     if (lng < -75.5) {
	      if (lat < 0.5) {
	       if (lng < -75.75) {
	        return 382;
	       } else {
	        if (lat < 0.25) {
	         return 382;
	        } else {
	         return 391;
	        }
	       }
	      } else {
	       return 391;
	      }
	     } else {
	      return 391;
	     }
	    }
	   } else {
	    if (lat < 7.5) {
	     return 391;
	    } else {
	     if (lng < -76.0) {
	      if (lat < 9.25) {
	       if (lng < -77.5) {
	        return 22;
	       } else {
	        if (lat < 8.25) {
	         if (lng < -77.0) {
	          if (lat < 7.75) {
	           return 391;
	          } else {
	           if (lng < -77.25) {
	            return 22;
	           } else {
	            if (lat < 8.0) {
	             return 391;
	            } else {
	             return 22;
	            }
	           }
	          }
	         } else {
	          return 391;
	         }
	        } else {
	         if (lng < -76.75) {
	          if (lat < 8.75) {
	           if (lng < -77.25) {
	            return 22;
	           } else {
	            return 391;
	           }
	          } else {
	           return 22;
	          }
	         } else {
	          return 391;
	         }
	        }
	       }
	      } else {
	       if (lng < -77.5) {
	        return 22;
	       } else {
	        return 391;
	       }
	      }
	     } else {
	      return 391;
	     }
	    }
	   }
	  } else {
	   return kdLookup49(lat,lng);
	  }
	 } else {
	  if (lng < -73.25) {
	   if (lat < 16.75) {
	    return 391;
	   } else {
	    if (lat < 19.5) {
	     if (lng < -76.0) {
	      return 72;
	     } else {
	      return 19;
	     }
	    } else {
	     if (lng < -76.0) {
	      return 377;
	     } else {
	      if (lat < 21.0) {
	       if (lng < -74.5) {
	        return 377;
	       } else {
	        if (lat < 20.25) {
	         if (lng < -74.0) {
	          return 377;
	         } else {
	          return 19;
	         }
	        } else {
	         if (lng < -74.0) {
	          return 377;
	         } else {
	          if (lng < -73.75) {
	           return 377;
	          } else {
	           return 282;
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < -74.75) {
	        return 377;
	       } else {
	        return 282;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 16.75) {
	    if (lng < -70.5) {
	     if (lat < 14.0) {
	      if (lng < -72.0) {
	       return 391;
	      } else {
	       if (lat < 12.5) {
	        if (lng < -71.25) {
	         if (lat < 11.75) {
	          return 398;
	         } else {
	          if (lng < -71.5) {
	           return 391;
	          } else {
	           if (lat < 12.0) {
	            return 398;
	           } else {
	            return 391;
	           }
	          }
	         }
	        } else {
	         if (lat < 11.75) {
	          return 398;
	         } else {
	          return 391;
	         }
	        }
	       } else {
	        return 391;
	       }
	      }
	     } else {
	      return 0;
	     }
	    } else {
	     if (lat < 14.0) {
	      if (lng < -69.0) {
	       if (lat < 12.5) {
	        return 398;
	       } else {
	        return 235;
	       }
	      } else {
	       return 398;
	      }
	     } else {
	      return 0;
	     }
	    }
	   } else {
	    if (lng < -70.5) {
	     if (lat < 19.5) {
	      if (lng < -72.0) {
	       return 19;
	      } else {
	       if (lat < 18.0) {
	        return 230;
	       } else {
	        if (lng < -71.5) {
	         if (lat < 18.75) {
	          if (lat < 18.25) {
	           return 19;
	          } else {
	           if (lng < -71.75) {
	            return 19;
	           } else {
	            if (lat < 18.5) {
	             return 19;
	            } else {
	             return 230;
	            }
	           }
	          }
	         } else {
	          if (lat < 19.0) {
	           return 19;
	          } else {
	           if (lng < -71.75) {
	            return 19;
	           } else {
	            if (lat < 19.25) {
	             return 230;
	            } else {
	             return 19;
	            }
	           }
	          }
	         }
	        } else {
	         return 230;
	        }
	       }
	      }
	     } else {
	      if (lat < 21.0) {
	       if (lng < -72.0) {
	        if (lat < 20.25) {
	         return 19;
	        } else {
	         return 282;
	        }
	       } else {
	        if (lng < -71.25) {
	         if (lat < 20.25) {
	          if (lng < -71.75) {
	           return 19;
	          } else {
	           if (lat < 19.75) {
	            if (lng < -71.5) {
	             return 19;
	            } else {
	             return 230;
	            }
	           } else {
	            return 230;
	           }
	          }
	         } else {
	          return 0;
	         }
	        } else {
	         return 230;
	        }
	       }
	      } else {
	       if (lng < -72.0) {
	        return 282;
	       } else {
	        return 103;
	       }
	      }
	     }
	    } else {
	     return 230;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup51(double lat, double lng)
	{
	 if (lat < 36.5) {
	  if (lng < -85.5) {
	   return 161;
	  } else {
	   if (lat < 35.0) {
	    if (lat < 34.25) {
	     if (lng < -85.25) {
	      return 161;
	     } else {
	      return 166;
	     }
	    } else {
	     if (lng < -85.25) {
	      if (lat < 34.5) {
	       return 161;
	      } else {
	       return 166;
	      }
	     } else {
	      return 166;
	     }
	    }
	   } else {
	    if (lat < 35.75) {
	     if (lng < -85.0) {
	      if (lat < 35.25) {
	       if (lng < -85.25) {
	        return 161;
	       } else {
	        return 166;
	       }
	      } else {
	       if (lng < -85.25) {
	        return 161;
	       } else {
	        if (lat < 35.5) {
	         return 166;
	        } else {
	         return 161;
	        }
	       }
	      }
	     } else {
	      return 166;
	     }
	    } else {
	     if (lng < -84.75) {
	      return 161;
	     } else {
	      if (lat < 36.0) {
	       return 166;
	      } else {
	       if (lat < 36.25) {
	        return 161;
	       } else {
	        return 166;
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < -86.0) {
	   if (lat < 38.0) {
	    return 161;
	   } else {
	    if (lng < -86.75) {
	     if (lat < 38.5) {
	      if (lng < -87.0) {
	       if (lat < 38.25) {
	        return 161;
	       } else {
	        return 54;
	       }
	      } else {
	       if (lat < 38.25) {
	        return 161;
	       } else {
	        return 310;
	       }
	      }
	     } else {
	      if (lat < 38.75) {
	       if (lng < -87.0) {
	        return 54;
	       } else {
	        return 310;
	       }
	      } else {
	       if (lng < -87.0) {
	        if (lat < 39.0) {
	         return 310;
	        } else {
	         return 89;
	        }
	       } else {
	        if (lat < 39.0) {
	         return 310;
	        } else {
	         return 89;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 38.5) {
	      if (lng < -86.5) {
	       if (lat < 38.25) {
	        return 90;
	       } else {
	        return 310;
	       }
	      } else {
	       if (lng < -86.25) {
	        if (lat < 38.25) {
	         return 161;
	        } else {
	         return 389;
	        }
	       } else {
	        if (lat < 38.25) {
	         return 166;
	        } else {
	         return 131;
	        }
	       }
	      }
	     } else {
	      if (lng < -86.5) {
	       if (lat < 39.0) {
	        return 310;
	       } else {
	        return 89;
	       }
	      } else {
	       return 89;
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 37.75) {
	    if (lng < -85.25) {
	     if (lat < 37.5) {
	      return 161;
	     } else {
	      return 166;
	     }
	    } else {
	     if (lat < 37.0) {
	      if (lng < -85.0) {
	       return 161;
	      } else {
	       if (lng < -84.75) {
	        if (lat < 36.75) {
	         return 161;
	        } else {
	         return 110;
	        }
	       } else {
	        if (lat < 36.75) {
	         return 161;
	        } else {
	         return 110;
	        }
	       }
	      }
	     } else {
	      if (lng < -85.0) {
	       if (lat < 37.5) {
	        return 161;
	       } else {
	        return 166;
	       }
	      } else {
	       if (lat < 37.25) {
	        if (lng < -84.75) {
	         return 161;
	        } else {
	         return 166;
	        }
	       } else {
	        return 166;
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < -85.25) {
	     if (lat < 38.5) {
	      if (lng < -85.75) {
	       if (lat < 38.0) {
	        return 166;
	       } else {
	        return 131;
	       }
	      } else {
	       return 166;
	      }
	     } else {
	      if (lng < -85.75) {
	       return 89;
	      } else {
	       if (lat < 38.75) {
	        return 131;
	       } else {
	        return 89;
	       }
	      }
	     }
	    } else {
	     if (lat < 38.75) {
	      return 166;
	     } else {
	      if (lng < -85.0) {
	       return 89;
	      } else {
	       return 166;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup52(double lat, double lng)
	{
	 if (lng < -84.5) {
	  if (lat < 39.25) {
	   if (lng < -87.25) {
	    if (lat < 38.5) {
	     return 161;
	    } else {
	     if (lng < -87.5) {
	      return 161;
	     } else {
	      if (lat < 39.0) {
	       return 310;
	      } else {
	       return 89;
	      }
	     }
	    }
	   } else {
	    return kdLookup51(lat,lng);
	   }
	  } else {
	   if (lat < 42.0) {
	    if (lng < -87.25) {
	     if (lng < -87.5) {
	      return 161;
	     } else {
	      if (lat < 40.75) {
	       return 89;
	      } else {
	       return 161;
	      }
	     }
	    } else {
	     if (lng < -86.0) {
	      if (lat < 40.75) {
	       return 89;
	      } else {
	       if (lng < -86.75) {
	        if (lat < 41.0) {
	         if (lng < -87.0) {
	          return 161;
	         } else {
	          return 89;
	         }
	        } else {
	         return 161;
	        }
	       } else {
	        if (lat < 41.25) {
	         if (lng < -86.5) {
	          if (lat < 41.0) {
	           return 89;
	          } else {
	           return 249;
	          }
	         } else {
	          if (lng < -86.25) {
	           if (lat < 41.0) {
	            return 89;
	           } else {
	            return 249;
	           }
	          } else {
	           return 89;
	          }
	         }
	        } else {
	         if (lng < -86.5) {
	          if (lat < 41.5) {
	           return 258;
	          } else {
	           return 161;
	          }
	         } else {
	          if (lat < 41.5) {
	           if (lng < -86.25) {
	            return 258;
	           } else {
	            return 89;
	           }
	          } else {
	           return 89;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 40.5) {
	       if (lng < -85.0) {
	        return 89;
	       } else {
	        if (lat < 39.75) {
	         if (lng < -84.75) {
	          if (lat < 39.5) {
	           return 166;
	          } else {
	           return 89;
	          }
	         } else {
	          return 166;
	         }
	        } else {
	         if (lat < 40.0) {
	          if (lng < -84.75) {
	           return 89;
	          } else {
	           return 166;
	          }
	         } else {
	          if (lng < -84.75) {
	           return 89;
	          } else {
	           return 166;
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < -84.75) {
	        return 89;
	       } else {
	        if (lat < 41.75) {
	         return 166;
	        } else {
	         return 93;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < -87.0) {
	     return 161;
	    } else {
	     return 93;
	    }
	   }
	  }
	 } else {
	  if (lat < 41.75) {
	   return 166;
	  } else {
	   if (lng < -81.75) {
	    if (lat < 43.25) {
	     if (lng < -83.0) {
	      return 93;
	     } else {
	      if (lat < 42.5) {
	       if (lng < -82.5) {
	        if (lat < 42.0) {
	         return 166;
	        } else {
	         return 239;
	        }
	       } else {
	        if (lng < -82.0) {
	         return 239;
	        } else {
	         if (lat < 42.0) {
	          return 166;
	         } else {
	          return 239;
	         }
	        }
	       }
	      } else {
	       if (lng < -82.5) {
	        return 93;
	       } else {
	        if (lng < -82.25) {
	         if (lat < 42.75) {
	          return 239;
	         } else {
	          return 93;
	         }
	        } else {
	         return 239;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -83.25) {
	      return 93;
	     } else {
	      if (lat < 44.0) {
	       if (lng < -82.25) {
	        return 93;
	       } else {
	        return 239;
	       }
	      } else {
	       return 93;
	      }
	     }
	    }
	   } else {
	    if (lat < 43.25) {
	     if (lng < -80.25) {
	      if (lng < -81.0) {
	       if (lat < 42.5) {
	        if (lng < -81.5) {
	         return 239;
	        } else {
	         return 166;
	        }
	       } else {
	        return 239;
	       }
	      } else {
	       if (lat < 42.5) {
	        return 166;
	       } else {
	        return 239;
	       }
	      }
	     } else {
	      if (lng < -79.5) {
	       if (lat < 42.5) {
	        return 166;
	       } else {
	        if (lng < -79.75) {
	         return 239;
	        } else {
	         if (lat < 42.75) {
	          return 166;
	         } else {
	          return 239;
	         }
	        }
	       }
	      } else {
	       if (lat < 42.75) {
	        return 166;
	       } else {
	        if (lng < -79.0) {
	         return 239;
	        } else {
	         return 166;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -80.25) {
	      return 239;
	     } else {
	      if (lat < 43.75) {
	       if (lng < -79.0) {
	        return 239;
	       } else {
	        return 166;
	       }
	      } else {
	       return 239;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup53(double lat, double lng)
	{
	 if (lng < -78.75) {
	  if (lat < 33.75) {
	   if (lng < -84.5) {
	    if (lat < 28.0) {
	     return 0;
	    } else {
	     if (lat < 30.75) {
	      if (lng < -85.25) {
	       return 161;
	      } else {
	       if (lat < 29.25) {
	        return 0;
	       } else {
	        if (lat < 30.0) {
	         return 166;
	        } else {
	         if (lng < -85.0) {
	          return 161;
	         } else {
	          if (lat < 30.5) {
	           return 166;
	          } else {
	           if (lng < -84.75) {
	            return 161;
	           } else {
	            return 166;
	           }
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < -85.25) {
	       return 161;
	      } else {
	       if (lat < 32.25) {
	        if (lat < 31.5) {
	         if (lng < -85.0) {
	          return 161;
	         } else {
	          if (lat < 31.0) {
	           if (lng < -84.75) {
	            return 161;
	           } else {
	            return 166;
	           }
	          } else {
	           return 166;
	          }
	         }
	        } else {
	         if (lng < -85.0) {
	          return 161;
	         } else {
	          return 166;
	         }
	        }
	       } else {
	        if (lat < 33.0) {
	         if (lng < -85.0) {
	          return 161;
	         } else {
	          if (lat < 32.5) {
	           if (lng < -84.75) {
	            return 161;
	           } else {
	            return 166;
	           }
	          } else {
	           if (lng < -84.75) {
	            if (lat < 32.75) {
	             return 161;
	            } else {
	             return 166;
	            }
	           } else {
	            return 166;
	           }
	          }
	         }
	        } else {
	         if (lng < -85.0) {
	          if (lat < 33.25) {
	           return 161;
	          } else {
	           return 166;
	          }
	         } else {
	          return 166;
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 28.0) {
	     if (lng < -81.75) {
	      if (lat < 25.25) {
	       return 377;
	      } else {
	       return 166;
	      }
	     } else {
	      if (lat < 25.25) {
	       if (lng < -80.25) {
	        if (lat < 23.75) {
	         return 377;
	        } else {
	         return 166;
	        }
	       } else {
	        return 377;
	       }
	      } else {
	       if (lng < -80.0) {
	        return 166;
	       } else {
	        if (lat < 26.5) {
	         if (lng < -79.5) {
	          return 166;
	         } else {
	          return 282;
	         }
	        } else {
	         return 166;
	        }
	       }
	      }
	     }
	    } else {
	     return 166;
	    }
	   }
	  } else {
	   return kdLookup52(lat,lng);
	  }
	 } else {
	  if (lat < 33.75) {
	   if (lng < -73.25) {
	    if (lat < 28.0) {
	     if (lng < -76.0) {
	      if (lat < 25.25) {
	       if (lng < -77.75) {
	        if (lat < 23.75) {
	         return 377;
	        } else {
	         return 282;
	        }
	       } else {
	        return 282;
	       }
	      } else {
	       return 282;
	      }
	     } else {
	      return 282;
	     }
	    } else {
	     return 0;
	    }
	   } else {
	    return 0;
	   }
	  } else {
	   if (lng < -75.25) {
	    if (lat < 39.25) {
	     return 166;
	    } else {
	     if (lat < 43.5) {
	      return 166;
	     } else {
	      if (lng < -77.0) {
	       if (lng < -78.0) {
	        if (lat < 43.75) {
	         return 166;
	        } else {
	         return 239;
	        }
	       } else {
	        if (lat < 44.0) {
	         if (lng < -77.5) {
	          if (lng < -77.75) {
	           if (lat < 43.75) {
	            return 166;
	           } else {
	            return 239;
	           }
	          } else {
	           return 166;
	          }
	         } else {
	          if (lng < -77.25) {
	           return 239;
	          } else {
	           if (lat < 43.75) {
	            return 166;
	           } else {
	            return 239;
	           }
	          }
	         }
	        } else {
	         return 239;
	        }
	       }
	      } else {
	       if (lng < -76.25) {
	        if (lat < 44.25) {
	         if (lng < -76.75) {
	          if (lat < 43.75) {
	           return 166;
	          } else {
	           return 239;
	          }
	         } else {
	          if (lat < 43.75) {
	           return 166;
	          } else {
	           return 239;
	          }
	         }
	        } else {
	         return 239;
	        }
	       } else {
	        if (lat < 44.5) {
	         return 166;
	        } else {
	         if (lng < -75.75) {
	          return 239;
	         } else {
	          if (lng < -75.5) {
	           if (lat < 44.75) {
	            return 166;
	           } else {
	            return 239;
	           }
	          } else {
	           if (lat < 44.75) {
	            return 166;
	           } else {
	            return 239;
	           }
	          }
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return 166;
	   }
	  }
	 }
	}

	private  int kdLookup54(double lat, double lng)
	{
	 if (lng < -64.75) {
	  if (lat < 2.75) {
	   if (lng < -66.25) {
	    if (lat < 1.25) {
	     if (lng < -66.5) {
	      return 7;
	     } else {
	      if (lat < 1.0) {
	       return 7;
	      } else {
	       return 398;
	      }
	     }
	    } else {
	     if (lat < 2.0) {
	      if (lng < -67.0) {
	       return 7;
	      } else {
	       if (lng < -66.75) {
	        if (lat < 1.75) {
	         return 391;
	        } else {
	         return 398;
	        }
	       } else {
	        return 398;
	       }
	      }
	     } else {
	      if (lng < -67.0) {
	       if (lat < 2.25) {
	        if (lng < -67.25) {
	         return 7;
	        } else {
	         return 391;
	        }
	       } else {
	        if (lng < -67.25) {
	         return 391;
	        } else {
	         if (lat < 2.5) {
	          return 391;
	         } else {
	          return 398;
	         }
	        }
	       }
	      } else {
	       return 398;
	      }
	     }
	    }
	   } else {
	    if (lat < 1.25) {
	     if (lng < -65.5) {
	      if (lat < 1.0) {
	       return 7;
	      } else {
	       return 398;
	      }
	     } else {
	      if (lat < 0.75) {
	       return 7;
	      } else {
	       if (lng < -65.25) {
	        return 398;
	       } else {
	        if (lng < -65.0) {
	         if (lat < 1.0) {
	          return 7;
	         } else {
	          return 398;
	         }
	        } else {
	         return 7;
	        }
	       }
	      }
	     }
	    } else {
	     return 398;
	    }
	   }
	  } else {
	   if (lng < -67.25) {
	    if (lat < 5.25) {
	     if (lat < 5.0) {
	      if (lat < 4.75) {
	       if (lat < 4.5) {
	        if (lat < 3.5) {
	         if (lat < 3.25) {
	          return 398;
	         } else {
	          return 391;
	         }
	        } else {
	         if (lat < 3.75) {
	          return 391;
	         } else {
	          return 398;
	         }
	        }
	       } else {
	        return 398;
	       }
	      } else {
	       return 398;
	      }
	     } else {
	      return 398;
	     }
	    } else {
	     return 398;
	    }
	   } else {
	    return 398;
	   }
	  }
	 } else {
	  if (lat < 2.75) {
	   if (lng < -63.5) {
	    if (lat < 1.5) {
	     return 7;
	    } else {
	     if (lng < -64.0) {
	      return 398;
	     } else {
	      if (lat < 2.0) {
	       return 7;
	      } else {
	       if (lat < 2.25) {
	        if (lng < -63.75) {
	         return 398;
	        } else {
	         return 7;
	        }
	       } else {
	        if (lng < -63.75) {
	         if (lat < 2.5) {
	          return 398;
	         } else {
	          return 350;
	         }
	        } else {
	         if (lat < 2.5) {
	          return 398;
	         } else {
	          return 350;
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 1.25) {
	     if (lng < -62.5) {
	      return 7;
	     } else {
	      if (lat < 0.5) {
	       return 350;
	      } else {
	       if (lat < 0.75) {
	        if (lng < -62.25) {
	         return 7;
	        } else {
	         return 350;
	        }
	       } else {
	        return 350;
	       }
	      }
	     }
	    } else {
	     if (lng < -62.75) {
	      if (lat < 2.25) {
	       return 7;
	      } else {
	       if (lng < -63.25) {
	        if (lat < 2.5) {
	         return 398;
	        } else {
	         return 350;
	        }
	       } else {
	        return 350;
	       }
	      }
	     } else {
	      if (lat < 2.0) {
	       if (lng < -62.5) {
	        return 7;
	       } else {
	        return 350;
	       }
	      } else {
	       return 350;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < -63.5) {
	    if (lat < 4.0) {
	     if (lng < -64.25) {
	      return 398;
	     } else {
	      if (lat < 3.25) {
	       if (lng < -64.0) {
	        return 398;
	       } else {
	        if (lng < -63.75) {
	         if (lat < 3.0) {
	          return 398;
	         } else {
	          return 350;
	         }
	        } else {
	         return 350;
	        }
	       }
	      } else {
	       if (lng < -64.0) {
	        if (lat < 3.75) {
	         return 398;
	        } else {
	         return 350;
	        }
	       } else {
	        return 350;
	       }
	      }
	     }
	    } else {
	     if (lat < 4.5) {
	      if (lng < -64.25) {
	       if (lng < -64.5) {
	        if (lat < 4.25) {
	         return 398;
	        } else {
	         return 350;
	        }
	       } else {
	        if (lat < 4.25) {
	         return 350;
	        } else {
	         return 398;
	        }
	       }
	      } else {
	       if (lng < -64.0) {
	        if (lat < 4.25) {
	         return 350;
	        } else {
	         return 398;
	        }
	       } else {
	        return 398;
	       }
	      }
	     } else {
	      return 398;
	     }
	    }
	   } else {
	    if (lat < 4.0) {
	     if (lng < -62.75) {
	      if (lat < 3.75) {
	       return 350;
	      } else {
	       if (lng < -63.0) {
	        return 350;
	       } else {
	        return 398;
	       }
	      }
	     } else {
	      if (lat < 3.75) {
	       return 350;
	      } else {
	       if (lng < -62.5) {
	        return 398;
	       } else {
	        return 350;
	       }
	      }
	     }
	    } else {
	     if (lng < -62.75) {
	      return 398;
	     } else {
	      if (lat < 4.25) {
	       return 350;
	      } else {
	       return 398;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup55(double lat, double lng)
	{
	 if (lng < -59.25) {
	  if (lat < 2.75) {
	   if (lng < -60.0) {
	    return 350;
	   } else {
	    if (lat < 1.25) {
	     if (lat < 0.25) {
	      return 7;
	     } else {
	      return 350;
	     }
	    } else {
	     if (lat < 2.0) {
	      if (lng < -59.5) {
	       return 350;
	      } else {
	       if (lat < 1.75) {
	        return 350;
	       } else {
	        return 299;
	       }
	      }
	     } else {
	      if (lng < -59.75) {
	       return 350;
	      } else {
	       if (lat < 2.25) {
	        if (lng < -59.5) {
	         return 350;
	        } else {
	         return 299;
	        }
	       } else {
	        if (lng < -59.5) {
	         if (lat < 2.5) {
	          return 350;
	         } else {
	          return 299;
	         }
	        } else {
	         return 299;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < -60.75) {
	    if (lat < 4.25) {
	     return 350;
	    } else {
	     if (lng < -61.5) {
	      return 398;
	     } else {
	      if (lat < 4.75) {
	       if (lng < -61.25) {
	        if (lat < 4.5) {
	         return 350;
	        } else {
	         return 398;
	        }
	       } else {
	        return 350;
	       }
	      } else {
	       return 398;
	      }
	     }
	    }
	   } else {
	    if (lat < 4.0) {
	     if (lng < -59.75) {
	      return 350;
	     } else {
	      if (lat < 3.75) {
	       return 299;
	      } else {
	       if (lng < -59.5) {
	        return 350;
	       } else {
	        return 299;
	       }
	      }
	     }
	    } else {
	     if (lng < -60.0) {
	      if (lat < 5.0) {
	       return 350;
	      } else {
	       if (lng < -60.5) {
	        if (lat < 5.25) {
	         return 398;
	        } else {
	         return 299;
	        }
	       } else {
	        if (lng < -60.25) {
	         if (lat < 5.25) {
	          return 350;
	         } else {
	          return 299;
	         }
	        } else {
	         if (lat < 5.25) {
	          return 350;
	         } else {
	          return 299;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 4.5) {
	       if (lng < -59.5) {
	        return 350;
	       } else {
	        return 299;
	       }
	      } else {
	       if (lat < 5.0) {
	        return 299;
	       } else {
	        if (lng < -59.75) {
	         if (lat < 5.25) {
	          return 350;
	         } else {
	          return 299;
	         }
	        } else {
	         return 299;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 2.75) {
	   if (lng < -57.75) {
	    if (lat < 1.25) {
	     if (lng < -58.75) {
	      if (lat < 0.25) {
	       return 7;
	      } else {
	       return 350;
	      }
	     } else {
	      return 311;
	     }
	    } else {
	     if (lng < -58.5) {
	      if (lat < 1.5) {
	       if (lng < -58.75) {
	        return 350;
	       } else {
	        return 299;
	       }
	      } else {
	       return 299;
	      }
	     } else {
	      if (lat < 1.75) {
	       if (lng < -58.25) {
	        if (lat < 1.5) {
	         return 311;
	        } else {
	         return 299;
	        }
	       } else {
	        return 311;
	       }
	      } else {
	       return 299;
	      }
	     }
	    }
	   } else {
	    if (lat < 1.75) {
	     return 311;
	    } else {
	     if (lng < -57.0) {
	      if (lat < 2.0) {
	       if (lng < -57.5) {
	        return 299;
	       } else {
	        return 311;
	       }
	      } else {
	       return 299;
	      }
	     } else {
	      if (lat < 2.25) {
	       if (lng < -56.75) {
	        if (lat < 2.0) {
	         return 311;
	        } else {
	         return 299;
	        }
	       } else {
	        if (lng < -56.5) {
	         if (lat < 2.0) {
	          return 311;
	         } else {
	          return 299;
	         }
	        } else {
	         if (lat < 2.0) {
	          return 311;
	         } else {
	          return 201;
	         }
	        }
	       }
	      } else {
	       if (lng < -56.75) {
	        return 299;
	       } else {
	        return 201;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < -57.75) {
	    if (lat < 4.0) {
	     return 299;
	    } else {
	     if (lng < -58.0) {
	      return 299;
	     } else {
	      if (lat < 4.5) {
	       return 201;
	      } else {
	       return 299;
	      }
	     }
	    }
	   } else {
	    if (lat < 4.0) {
	     if (lng < -57.0) {
	      if (lat < 3.25) {
	       return 299;
	      } else {
	       if (lng < -57.5) {
	        if (lat < 3.75) {
	         return 299;
	        } else {
	         return 201;
	        }
	       } else {
	        if (lat < 3.5) {
	         if (lng < -57.25) {
	          return 299;
	         } else {
	          return 201;
	         }
	        } else {
	         return 201;
	        }
	       }
	      }
	     } else {
	      return 201;
	     }
	    } else {
	     if (lng < -57.25) {
	      if (lat < 5.0) {
	       return 201;
	      } else {
	       if (lng < -57.5) {
	        return 299;
	       } else {
	        if (lat < 5.25) {
	         return 201;
	        } else {
	         return 299;
	        }
	       }
	      }
	     } else {
	      return 201;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup56(double lat, double lng)
	{
	 if (lng < -62.0) {
	  if (lat < 5.5) {
	   return kdLookup54(lat,lng);
	  } else {
	   if (lat < 10.5) {
	    if (lng < -67.25) {
	     if (lat < 10.25) {
	      if (lat < 10.0) {
	       if (lat < 9.75) {
	        if (lat < 9.5) {
	         if (lat < 9.25) {
	          if (lat < 9.0) {
	           if (lat < 8.75) {
	            if (lat < 8.5) {
	             if (lat < 8.25) {
	              if (lat < 8.0) {
	               if (lat < 7.75) {
	                if (lat < 7.5) {
	                 if (lat < 7.25) {
	                  if (lat < 7.0) {
	                   if (lat < 6.75) {
	                    if (lat < 6.0) {
	                     return 398;
	                    } else {
	                     if (lat < 6.25) {
	                      return 391;
	                     } else {
	                      return 398;
	                     }
	                    }
	                   } else {
	                    return 398;
	                   }
	                  } else {
	                   return 398;
	                  }
	                 } else {
	                  return 398;
	                 }
	                } else {
	                 return 398;
	                }
	               } else {
	                return 398;
	               }
	              } else {
	               return 398;
	              }
	             } else {
	              return 398;
	             }
	            } else {
	             return 398;
	            }
	           } else {
	            return 398;
	           }
	          } else {
	           return 398;
	          }
	         } else {
	          return 398;
	         }
	        } else {
	         return 398;
	        }
	       } else {
	        return 398;
	       }
	      } else {
	       return 398;
	      }
	     } else {
	      return 398;
	     }
	    } else {
	     return 398;
	    }
	   } else {
	    return 398;
	   }
	  }
	 } else {
	  if (lat < 5.5) {
	   return kdLookup55(lat,lng);
	  } else {
	   if (lng < -59.25) {
	    if (lat < 8.25) {
	     if (lng < -60.75) {
	      if (lat < 6.75) {
	       if (lng < -61.25) {
	        return 398;
	       } else {
	        if (lat < 6.0) {
	         if (lng < -61.0) {
	          return 398;
	         } else {
	          return 299;
	         }
	        } else {
	         if (lat < 6.25) {
	          return 299;
	         } else {
	          if (lng < -61.0) {
	           return 398;
	          } else {
	           return 299;
	          }
	         }
	        }
	       }
	      } else {
	       return 398;
	      }
	     } else {
	      if (lat < 7.0) {
	       return 299;
	      } else {
	       if (lng < -60.0) {
	        if (lat < 7.5) {
	         if (lng < -60.5) {
	          return 398;
	         } else {
	          if (lng < -60.25) {
	           if (lat < 7.25) {
	            return 398;
	           } else {
	            return 299;
	           }
	          } else {
	           return 299;
	          }
	         }
	        } else {
	         if (lng < -60.5) {
	          return 398;
	         } else {
	          if (lat < 8.0) {
	           return 299;
	          } else {
	           return 398;
	          }
	         }
	        }
	       } else {
	        return 299;
	       }
	      }
	     }
	    } else {
	     if (lat < 9.75) {
	      if (lng < -60.5) {
	       return 398;
	      } else {
	       if (lat < 9.0) {
	        if (lng < -59.75) {
	         return 398;
	        } else {
	         return 299;
	        }
	       } else {
	        return 0;
	       }
	      }
	     } else {
	      if (lng < -60.75) {
	       if (lat < 10.25) {
	        if (lng < -61.25) {
	         return 398;
	        } else {
	         if (lng < -61.0) {
	          if (lat < 10.0) {
	           return 398;
	          } else {
	           return 169;
	          }
	         } else {
	          if (lat < 10.0) {
	           return 398;
	          } else {
	           return 169;
	          }
	         }
	        }
	       } else {
	        return 169;
	       }
	      } else {
	       return 169;
	      }
	     }
	    }
	   } else {
	    if (lat < 8.25) {
	     if (lng < -57.75) {
	      return 299;
	     } else {
	      if (lat < 6.75) {
	       if (lng < -57.0) {
	        if (lat < 6.0) {
	         if (lng < -57.25) {
	          return 299;
	         } else {
	          if (lat < 5.75) {
	           return 201;
	          } else {
	           return 299;
	          }
	         }
	        } else {
	         return 299;
	        }
	       } else {
	        return 201;
	       }
	      } else {
	       return 299;
	      }
	     }
	    } else {
	     return 299;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup57(double lat, double lng)
	{
	 if (lng < -53.5) {
	  if (lat < 2.75) {
	   if (lng < -55.0) {
	    if (lat < 2.0) {
	     return 311;
	    } else {
	     if (lng < -55.75) {
	      if (lat < 2.25) {
	       return 201;
	      } else {
	       if (lng < -56.0) {
	        return 201;
	       } else {
	        if (lat < 2.5) {
	         return 311;
	        } else {
	         return 201;
	        }
	       }
	      }
	     } else {
	      if (lng < -55.5) {
	       if (lat < 2.5) {
	        return 311;
	       } else {
	        return 201;
	       }
	      } else {
	       if (lat < 2.5) {
	        return 311;
	       } else {
	        return 201;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 1.5) {
	     return 311;
	    } else {
	     if (lng < -54.25) {
	      if (lat < 2.0) {
	       if (lng < -54.5) {
	        return 311;
	       } else {
	        if (lat < 1.75) {
	         return 311;
	        } else {
	         return 401;
	        }
	       }
	      } else {
	       if (lng < -54.75) {
	        return 311;
	       } else {
	        if (lat < 2.25) {
	         return 401;
	        } else {
	         if (lng < -54.5) {
	          if (lat < 2.5) {
	           return 311;
	          } else {
	           return 201;
	          }
	         } else {
	          if (lat < 2.5) {
	           return 401;
	          } else {
	           return 201;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 2.0) {
	       if (lng < -54.0) {
	        if (lat < 1.75) {
	         return 311;
	        } else {
	         return 401;
	        }
	       } else {
	        if (lng < -53.75) {
	         if (lat < 1.75) {
	          return 311;
	         } else {
	          return 401;
	         }
	        } else {
	         return 401;
	        }
	       }
	      } else {
	       if (lng < -54.0) {
	        if (lat < 2.25) {
	         return 401;
	        } else {
	         return 363;
	        }
	       } else {
	        if (lat < 2.25) {
	         return 401;
	        } else {
	         if (lng < -53.75) {
	          return 363;
	         } else {
	          if (lat < 2.5) {
	           return 401;
	          } else {
	           return 363;
	          }
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < -54.25) {
	    return 201;
	   } else {
	    if (lat < 4.0) {
	     if (lat < 3.25) {
	      if (lng < -54.0) {
	       return 201;
	      } else {
	       return 363;
	      }
	     } else {
	      if (lng < -54.0) {
	       return 201;
	      } else {
	       return 363;
	      }
	     }
	    } else {
	     return 363;
	    }
	   }
	  }
	 } else {
	  if (lat < 2.75) {
	   if (lng < -52.5) {
	    if (lat < 1.25) {
	     if (lat < 0.5) {
	      if (lng < -53.0) {
	       return 311;
	      } else {
	       if (lng < -52.75) {
	        if (lat < 0.25) {
	         return 311;
	        } else {
	         return 401;
	        }
	       } else {
	        return 401;
	       }
	      }
	     } else {
	      if (lng < -53.0) {
	       if (lat < 1.0) {
	        return 311;
	       } else {
	        if (lng < -53.25) {
	         return 311;
	        } else {
	         return 401;
	        }
	       }
	      } else {
	       return 401;
	      }
	     }
	    } else {
	     if (lat < 2.0) {
	      if (lng < -53.25) {
	       if (lat < 1.5) {
	        return 311;
	       } else {
	        return 401;
	       }
	      } else {
	       return 401;
	      }
	     } else {
	      if (lng < -53.0) {
	       if (lat < 2.5) {
	        return 401;
	       } else {
	        return 363;
	       }
	      } else {
	       if (lat < 2.25) {
	        return 401;
	       } else {
	        if (lng < -52.75) {
	         return 363;
	        } else {
	         if (lat < 2.5) {
	          return 401;
	         } else {
	          return 363;
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return 401;
	   }
	  } else {
	   if (lng < -52.25) {
	    return 363;
	   } else {
	    if (lat < 4.0) {
	     if (lng < -51.75) {
	      if (lat < 3.5) {
	       return 401;
	      } else {
	       if (lng < -52.0) {
	        return 363;
	       } else {
	        if (lat < 3.75) {
	         return 401;
	        } else {
	         return 363;
	        }
	       }
	      }
	     } else {
	      return 401;
	     }
	    } else {
	     if (lng < -51.5) {
	      return 363;
	     } else {
	      return 401;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup58(double lat, double lng)
	{
	 if (lng < -67.5) {
	  if (lat < 22.5) {
	   if (lng < -78.75) {
	    if (lat < 11.25) {
	     if (lng < -84.5) {
	      if (lat < 5.5) {
	       return 0;
	      } else {
	       if (lat < 8.25) {
	        return 0;
	       } else {
	        if (lng < -87.25) {
	         return 0;
	        } else {
	         if (lat < 9.75) {
	          return 397;
	         } else {
	          if (lng < -86.0) {
	           return 0;
	          } else {
	           if (lng < -85.5) {
	            return 397;
	           } else {
	            if (lat < 11.0) {
	             return 397;
	            } else {
	             if (lng < -85.0) {
	              return 397;
	             } else {
	              if (lng < -84.75) {
	               return 260;
	              } else {
	               return 397;
	              }
	             }
	            }
	           }
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 5.5) {
	       if (lng < -81.75) {
	        return 0;
	       } else {
	        if (lat < 2.75) {
	         if (lng < -80.25) {
	          return 0;
	         } else {
	          if (lat < 1.5) {
	           return 382;
	          } else {
	           return 391;
	          }
	         }
	        } else {
	         return 0;
	        }
	       }
	      } else {
	       if (lng < -81.75) {
	        if (lat < 8.25) {
	         return 22;
	        } else {
	         if (lat < 9.75) {
	          if (lng < -82.75) {
	           return 397;
	          } else {
	           return 22;
	          }
	         } else {
	          if (lng < -83.25) {
	           if (lat < 11.0) {
	            return 397;
	           } else {
	            if (lng < -84.0) {
	             if (lng < -84.25) {
	              return 397;
	             } else {
	              return 260;
	             }
	            } else {
	             return 260;
	            }
	           }
	          } else {
	           if (lng < -82.5) {
	            return 397;
	           } else {
	            return 22;
	           }
	          }
	         }
	        }
	       } else {
	        return 22;
	       }
	      }
	     }
	    } else {
	     if (lng < -84.5) {
	      return kdLookup48(lat,lng);
	     } else {
	      if (lat < 16.75) {
	       if (lng < -81.75) {
	        if (lat < 14.0) {
	         return 260;
	        } else {
	         if (lng < -83.25) {
	          if (lat < 15.25) {
	           if (lng < -84.0) {
	            if (lat < 14.75) {
	             return 260;
	            } else {
	             return 146;
	            }
	           } else {
	            if (lat < 14.75) {
	             return 260;
	            } else {
	             if (lng < -83.75) {
	              return 146;
	             } else {
	              if (lng < -83.5) {
	               if (lat < 15.0) {
	                return 260;
	               } else {
	                return 146;
	               }
	              } else {
	               return 260;
	              }
	             }
	            }
	           }
	          } else {
	           return 146;
	          }
	         } else {
	          if (lat < 15.25) {
	           if (lng < -82.5) {
	            if (lat < 14.5) {
	             return 260;
	            } else {
	             if (lng < -83.0) {
	              if (lat < 14.75) {
	               return 260;
	              } else {
	               return 146;
	              }
	             } else {
	              if (lat < 14.75) {
	               return 260;
	              } else {
	               return 146;
	              }
	             }
	            }
	           } else {
	            return 0;
	           }
	          } else {
	           return 146;
	          }
	         }
	        }
	       } else {
	        return 0;
	       }
	      } else {
	       if (lng < -80.0) {
	        return 377;
	       } else {
	        if (lat < 19.5) {
	         return 0;
	        } else {
	         if (lat < 21.0) {
	          return 222;
	         } else {
	          return 377;
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return kdLookup50(lat,lng);
	   }
	  } else {
	   return kdLookup53(lat,lng);
	  }
	 } else {
	  if (lat < 22.5) {
	   if (lng < -56.25) {
	    if (lat < 11.25) {
	     return kdLookup56(lat,lng);
	    } else {
	     if (lng < -62.0) {
	      if (lat < 16.75) {
	       return 398;
	      } else {
	       if (lat < 19.5) {
	        if (lng < -64.75) {
	         if (lng < -66.25) {
	          return 394;
	         } else {
	          if (lat < 18.0) {
	           if (lng < -65.5) {
	            return 394;
	           } else {
	            return 339;
	           }
	          } else {
	           return 394;
	          }
	         }
	        } else {
	         if (lng < -63.5) {
	          return 339;
	         } else {
	          return 177;
	         }
	        }
	       } else {
	        return 0;
	       }
	      }
	     } else {
	      if (lat < 16.75) {
	       if (lng < -59.25) {
	        if (lat < 14.0) {
	         if (lng < -60.75) {
	          if (lat < 12.5) {
	           return 86;
	          } else {
	           if (lat < 13.25) {
	            return 2;
	           } else {
	            if (lng < -61.5) {
	             return 0;
	            } else {
	             if (lng < -61.25) {
	              return 2;
	             } else {
	              if (lat < 13.5) {
	               return 2;
	              } else {
	               if (lng < -61.0) {
	                if (lat < 13.75) {
	                 return 2;
	                } else {
	                 return 387;
	                }
	               } else {
	                return 387;
	               }
	              }
	             }
	            }
	           }
	          }
	         } else {
	          return 387;
	         }
	        } else {
	         if (lng < -60.75) {
	          if (lat < 15.25) {
	           if (lng < -61.5) {
	            return 0;
	           } else {
	            if (lat < 14.5) {
	             return 387;
	            } else {
	             return 329;
	            }
	           }
	          } else {
	           return 278;
	          }
	         } else {
	          if (lat < 15.25) {
	           if (lng < -60.0) {
	            if (lat < 14.5) {
	             return 387;
	            } else {
	             return 329;
	            }
	           } else {
	            return 0;
	           }
	          } else {
	           return 0;
	          }
	         }
	        }
	       } else {
	        return 0;
	       }
	      } else {
	       return 205;
	      }
	     }
	    }
	   } else {
	    if (lat < 11.25) {
	     if (lng < -50.75) {
	      if (lat < 5.5) {
	       return kdLookup57(lat,lng);
	      } else {
	       if (lat < 8.25) {
	        if (lng < -53.5) {
	         if (lng < -55.0) {
	          return 201;
	         } else {
	          if (lat < 6.75) {
	           if (lng < -54.25) {
	            return 201;
	           } else {
	            if (lat < 6.0) {
	             if (lng < -54.0) {
	              return 201;
	             } else {
	              if (lng < -53.75) {
	               if (lat < 5.75) {
	                return 363;
	               } else {
	                return 201;
	               }
	              } else {
	               return 363;
	              }
	             }
	            } else {
	             return 201;
	            }
	           }
	          } else {
	           return 0;
	          }
	         }
	        } else {
	         return 363;
	        }
	       } else {
	        return 0;
	       }
	      }
	     } else {
	      return 401;
	     }
	    } else {
	     return 0;
	    }
	   }
	  } else {
	   if (lng < -56.25) {
	    if (lat < 33.75) {
	     return 0;
	    } else {
	     if (lng < -62.0) {
	      if (lat < 39.25) {
	       return 0;
	      } else {
	       if (lat < 42.0) {
	        return 0;
	       } else {
	        if (lng < -65.75) {
	         if (lat < 43.5) {
	          return 0;
	         } else {
	          if (lng < -66.75) {
	           return 166;
	          } else {
	           return 118;
	          }
	         }
	        } else {
	         return 118;
	        }
	       }
	      }
	     } else {
	      return 0;
	     }
	    }
	   } else {
	    return 0;
	   }
	  }
	 }
	}

	private  int kdLookup59(double lat, double lng)
	{
	 if (lng < -87.25) {
	  if (lat < 47.75) {
	   if (lng < -88.75) {
	    if (lat < 46.25) {
	     return 161;
	    } else {
	     if (lat < 47.0) {
	      if (lng < -89.5) {
	       if (lat < 46.5) {
	        return 161;
	       } else {
	        if (lng < -89.75) {
	         return 94;
	        } else {
	         if (lat < 46.75) {
	          return 94;
	         } else {
	          return 93;
	         }
	        }
	       }
	      } else {
	       if (lng < -89.25) {
	        if (lat < 46.75) {
	         return 94;
	        } else {
	         return 93;
	        }
	       } else {
	        if (lat < 46.5) {
	         return 94;
	        } else {
	         return 93;
	        }
	       }
	      }
	     } else {
	      return 93;
	     }
	    }
	   } else {
	    if (lat < 46.25) {
	     if (lng < -88.0) {
	      if (lat < 46.0) {
	       return 161;
	      } else {
	       if (lng < -88.5) {
	        return 161;
	       } else {
	        return 94;
	       }
	      }
	     } else {
	      if (lat < 45.5) {
	       if (lng < -87.5) {
	        return 161;
	       } else {
	        return 94;
	       }
	      } else {
	       if (lng < -87.75) {
	        if (lat < 46.0) {
	         return 161;
	        } else {
	         return 94;
	        }
	       } else {
	        if (lat < 46.0) {
	         return 94;
	        } else {
	         if (lng < -87.5) {
	          return 94;
	         } else {
	          return 93;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -88.0) {
	      if (lat < 46.5) {
	       return 94;
	      } else {
	       return 93;
	      }
	     } else {
	      return 93;
	     }
	    }
	   }
	  } else {
	   if (lng < -88.75) {
	    if (lat < 48.75) {
	     if (lng < -89.5) {
	      if (lat < 48.25) {
	       return 161;
	      } else {
	       return 239;
	      }
	     } else {
	      if (lat < 48.25) {
	       return 93;
	      } else {
	       if (lng < -89.25) {
	        return 239;
	       } else {
	        return 359;
	       }
	      }
	     }
	    } else {
	     return 239;
	    }
	   } else {
	    if (lat < 48.75) {
	     if (lng < -88.0) {
	      if (lat < 48.25) {
	       return 93;
	      } else {
	       return 239;
	      }
	     } else {
	      return 239;
	     }
	    } else {
	     if (lat < 50.25) {
	      if (lng < -87.5) {
	       if (lat < 50.0) {
	        if (lng < -88.25) {
	         return 239;
	        } else {
	         if (lat < 49.75) {
	          if (lat < 49.5) {
	           if (lng < -88.0) {
	            if (lat < 49.0) {
	             return 239;
	            } else {
	             if (lat < 49.25) {
	              return 336;
	             } else {
	              return 239;
	             }
	            }
	           } else {
	            return 239;
	           }
	          } else {
	           return 239;
	          }
	         } else {
	          return 239;
	         }
	        }
	       } else {
	        return 239;
	       }
	      } else {
	       return 239;
	      }
	     } else {
	      return 239;
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 47.75) {
	   if (lng < -86.0) {
	    if (lat < 46.25) {
	     if (lng < -86.75) {
	      if (lat < 45.75) {
	       return 161;
	      } else {
	       return 93;
	      }
	     } else {
	      if (lat < 45.5) {
	       return 161;
	      } else {
	       return 93;
	      }
	     }
	    } else {
	     return 93;
	    }
	   } else {
	    if (lat < 46.5) {
	     return 93;
	    } else {
	     if (lng < -85.25) {
	      if (lat < 47.0) {
	       return 93;
	      } else {
	       return 239;
	      }
	     } else {
	      if (lat < 47.0) {
	       return 93;
	      } else {
	       return 239;
	      }
	     }
	    }
	   }
	  } else {
	   return 239;
	  }
	 }
	}

	private  int kdLookup60(double lat, double lng)
	{
	 if (lng < -84.5) {
	  if (lat < 50.5) {
	   return kdLookup59(lat,lng);
	  } else {
	   if (lat < 53.25) {
	    if (lng < -89.0) {
	     if (lat < 52.75) {
	      return 239;
	     } else {
	      return 285;
	     }
	    } else {
	     return 239;
	    }
	   } else {
	    if (lng < -87.25) {
	     if (lat < 54.0) {
	      if (lng < -88.5) {
	       return 285;
	      } else {
	       return 239;
	      }
	     } else {
	      return 239;
	     }
	    } else {
	     return 239;
	    }
	   }
	  }
	 } else {
	  if (lat < 50.5) {
	   if (lng < -81.75) {
	    if (lat < 46.5) {
	     if (lng < -83.25) {
	      if (lat < 46.25) {
	       return 93;
	      } else {
	       if (lng < -84.0) {
	        return 93;
	       } else {
	        return 239;
	       }
	      }
	     } else {
	      if (lng < -83.0) {
	       if (lat < 45.75) {
	        return 93;
	       } else {
	        return 239;
	       }
	      } else {
	       return 239;
	      }
	     }
	    } else {
	     return 239;
	    }
	   } else {
	    if (lat < 47.75) {
	     if (lng < -79.5) {
	      return 239;
	     } else {
	      if (lat < 46.75) {
	       return 239;
	      } else {
	       if (lat < 47.25) {
	        if (lng < -79.25) {
	         return 239;
	        } else {
	         if (lng < -79.0) {
	          if (lat < 47.0) {
	           return 239;
	          } else {
	           return 20;
	          }
	         } else {
	          return 20;
	         }
	        }
	       } else {
	        if (lng < -79.25) {
	         if (lat < 47.5) {
	          return 239;
	         } else {
	          return 20;
	         }
	        } else {
	         return 20;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -79.5) {
	      return 239;
	     } else {
	      return 20;
	     }
	    }
	   }
	  } else {
	   if (lng < -81.75) {
	    if (lat < 53.25) {
	     if (lng < -82.25) {
	      return 239;
	     } else {
	      if (lat < 53.0) {
	       return 239;
	      } else {
	       return 138;
	      }
	     }
	    } else {
	     return 239;
	    }
	   } else {
	    if (lat < 53.25) {
	     if (lng < -80.25) {
	      if (lat < 52.0) {
	       return 239;
	      } else {
	       if (lng < -81.0) {
	        if (lat < 52.5) {
	         return 239;
	        } else {
	         if (lng < -81.5) {
	          if (lat < 52.75) {
	           return 239;
	          } else {
	           return 138;
	          }
	         } else {
	          if (lat < 52.75) {
	           return 239;
	          } else {
	           return 138;
	          }
	         }
	        }
	       } else {
	        if (lat < 52.5) {
	         return 239;
	        } else {
	         return 138;
	        }
	       }
	      }
	     } else {
	      if (lat < 51.75) {
	       if (lng < -79.5) {
	        if (lat < 51.25) {
	         return 239;
	        } else {
	         if (lng < -80.0) {
	          return 239;
	         } else {
	          if (lng < -79.75) {
	           return 239;
	          } else {
	           return 20;
	          }
	         }
	        }
	       } else {
	        return 20;
	       }
	      } else {
	       if (lng < -79.5) {
	        return 138;
	       } else {
	        if (lat < 52.5) {
	         if (lng < -79.25) {
	          return 138;
	         } else {
	          if (lat < 52.0) {
	           return 20;
	          } else {
	           if (lng < -79.0) {
	            return 138;
	           } else {
	            return 20;
	           }
	          }
	         }
	        } else {
	         return 20;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -80.25) {
	      return 138;
	     } else {
	      if (lat < 54.75) {
	       if (lng < -79.5) {
	        if (lat < 54.0) {
	         return 138;
	        } else {
	         return 20;
	        }
	       } else {
	        return 20;
	       }
	      } else {
	       if (lng < -79.5) {
	        return 138;
	       } else {
	        if (lat < 55.5) {
	         return 20;
	        } else {
	         return 138;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup61(double lat, double lng)
	{
	 if (lng < -84.5) {
	  if (lat < 61.75) {
	   if (lng < -87.25) {
	    if (lat < 59.0) {
	     if (lng < -88.75) {
	      if (lat < 57.5) {
	       if (lng < -89.5) {
	        if (lat < 56.75) {
	         if (lng < -89.75) {
	          return 285;
	         } else {
	          if (lat < 56.5) {
	           return 239;
	          } else {
	           return 285;
	          }
	         }
	        } else {
	         return 285;
	        }
	       } else {
	        if (lat < 56.75) {
	         return 239;
	        } else {
	         if (lng < -89.25) {
	          return 285;
	         } else {
	          if (lat < 57.0) {
	           if (lng < -89.0) {
	            return 285;
	           } else {
	            return 239;
	           }
	          } else {
	           if (lng < -89.0) {
	            return 285;
	           } else {
	            return 239;
	           }
	          }
	         }
	        }
	       }
	      } else {
	       return 0;
	      }
	     } else {
	      return 239;
	     }
	    } else {
	     return 0;
	    }
	   } else {
	    return 0;
	   }
	  } else {
	   if (lat < 64.5) {
	    if (lng < -87.25) {
	     return 308;
	    } else {
	     return 64;
	    }
	   } else {
	    if (lng < -87.25) {
	     if (lat < 67.0) {
	      return 308;
	     } else {
	      if (lng < -89.0) {
	       return 108;
	      } else {
	       return 308;
	      }
	     }
	    } else {
	     if (lat < 66.0) {
	      if (lng < -86.0) {
	       if (lat < 65.25) {
	        if (lng < -86.75) {
	         return 308;
	        } else {
	         return 64;
	        }
	       } else {
	        if (lng < -86.25) {
	         return 308;
	        } else {
	         if (lat < 65.75) {
	          return 64;
	         } else {
	          return 308;
	         }
	        }
	       }
	      } else {
	       return 64;
	      }
	     } else {
	      if (lng < -85.75) {
	       return 308;
	      } else {
	       if (lat < 66.75) {
	        if (lng < -85.25) {
	         if (lat < 66.25) {
	          return 64;
	         } else {
	          return 308;
	         }
	        } else {
	         if (lng < -85.0) {
	          if (lat < 66.25) {
	           return 64;
	          } else {
	           return 308;
	          }
	         } else {
	          if (lat < 66.25) {
	           return 64;
	          } else {
	           return 138;
	          }
	         }
	        }
	       } else {
	        if (lng < -85.0) {
	         return 308;
	        } else {
	         return 138;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 61.75) {
	   return 138;
	  } else {
	   if (lng < -81.75) {
	    if (lat < 64.5) {
	     return 64;
	    } else {
	     if (lat < 66.0) {
	      if (lng < -83.25) {
	       if (lat < 65.5) {
	        return 64;
	       } else {
	        if (lng < -84.0) {
	         if (lng < -84.25) {
	          return 64;
	         } else {
	          if (lat < 65.75) {
	           return 64;
	          } else {
	           return 138;
	          }
	         }
	        } else {
	         return 138;
	        }
	       }
	      } else {
	       return 64;
	      }
	     } else {
	      return 138;
	     }
	    }
	   } else {
	    if (lat < 64.5) {
	     if (lng < -80.25) {
	      if (lat < 63.0) {
	       if (lng < -81.0) {
	        return 64;
	       } else {
	        return 138;
	       }
	      } else {
	       return 64;
	      }
	     } else {
	      if (lat < 63.0) {
	       return 138;
	      } else {
	       return 64;
	      }
	     }
	    } else {
	     if (lng < -80.25) {
	      if (lat < 66.0) {
	       return 64;
	      } else {
	       return 138;
	      }
	     } else {
	      return 0;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup62(double lat, double lng)
	{
	 if (lng < -73.25) {
	  if (lat < 50.5) {
	   if (lng < -76.0) {
	    if (lat < 46.5) {
	     if (lng < -77.5) {
	      if (lat < 46.25) {
	       return 239;
	      } else {
	       if (lng < -78.0) {
	        return 239;
	       } else {
	        return 20;
	       }
	      }
	     } else {
	      if (lng < -76.75) {
	       if (lat < 46.0) {
	        return 239;
	       } else {
	        if (lng < -77.25) {
	         if (lat < 46.25) {
	          return 239;
	         } else {
	          return 20;
	         }
	        } else {
	         return 20;
	        }
	       }
	      } else {
	       if (lat < 45.75) {
	        if (lng < -76.25) {
	         return 239;
	        } else {
	         if (lat < 45.5) {
	          return 239;
	         } else {
	          return 20;
	         }
	        }
	       } else {
	        if (lng < -76.5) {
	         if (lat < 46.0) {
	          return 239;
	         } else {
	          return 20;
	         }
	        } else {
	         return 20;
	        }
	       }
	      }
	     }
	    } else {
	     return 20;
	    }
	   } else {
	    if (lat < 45.75) {
	     if (lng < -74.75) {
	      if (lng < -75.5) {
	       if (lat < 45.5) {
	        return 239;
	       } else {
	        return 20;
	       }
	      } else {
	       return 239;
	      }
	     } else {
	      if (lng < -74.25) {
	       if (lat < 45.25) {
	        return 20;
	       } else {
	        return 239;
	       }
	      } else {
	       if (lng < -73.75) {
	        return 20;
	       } else {
	        if (lat < 45.25) {
	         if (lng < -73.5) {
	          return 166;
	         } else {
	          return 20;
	         }
	        } else {
	         return 20;
	        }
	       }
	      }
	     }
	    } else {
	     return 20;
	    }
	   }
	  } else {
	   if (lat < 56.0) {
	    return 20;
	   } else {
	    if (lng < -77.0) {
	     return 138;
	    } else {
	     return 20;
	    }
	   }
	  }
	 } else {
	  if (lat < 49.25) {
	   if (lng < -70.5) {
	    if (lat < 45.5) {
	     if (lng < -72.0) {
	      if (lng < -72.75) {
	       if (lng < -73.0) {
	        if (lat < 45.25) {
	         return 166;
	        } else {
	         return 20;
	        }
	       } else {
	        return 20;
	       }
	      } else {
	       if (lng < -72.25) {
	        return 20;
	       } else {
	        if (lat < 45.25) {
	         return 166;
	        } else {
	         return 20;
	        }
	       }
	      }
	     } else {
	      if (lng < -71.25) {
	       if (lng < -71.5) {
	        return 20;
	       } else {
	        if (lat < 45.25) {
	         return 166;
	        } else {
	         return 20;
	        }
	       }
	      } else {
	       return 166;
	      }
	     }
	    } else {
	     return 20;
	    }
	   } else {
	    if (lat < 47.0) {
	     if (lng < -69.0) {
	      if (lat < 46.0) {
	       if (lng < -70.25) {
	        if (lat < 45.75) {
	         return 166;
	        } else {
	         return 20;
	        }
	       } else {
	        return 166;
	       }
	      } else {
	       if (lng < -69.75) {
	        if (lat < 46.5) {
	         if (lng < -70.25) {
	          return 20;
	         } else {
	          return 166;
	         }
	        } else {
	         if (lng < -70.0) {
	          return 20;
	         } else {
	          if (lat < 46.75) {
	           return 166;
	          } else {
	           return 20;
	          }
	         }
	        }
	       } else {
	        return 166;
	       }
	      }
	     } else {
	      if (lat < 46.0) {
	       if (lng < -67.75) {
	        return 166;
	       } else {
	        if (lat < 45.75) {
	         return 166;
	        } else {
	         return 77;
	        }
	       }
	      } else {
	       if (lng < -67.75) {
	        return 166;
	       } else {
	        return 77;
	       }
	      }
	     }
	    } else {
	     if (lng < -69.0) {
	      if (lat < 47.5) {
	       if (lng < -69.5) {
	        return 20;
	       } else {
	        if (lng < -69.25) {
	         if (lat < 47.25) {
	          return 166;
	         } else {
	          return 20;
	         }
	        } else {
	         return 166;
	        }
	       }
	      } else {
	       return 20;
	      }
	     } else {
	      if (lat < 48.0) {
	       if (lng < -68.25) {
	        if (lat < 47.5) {
	         if (lng < -68.75) {
	          if (lat < 47.25) {
	           return 166;
	          } else {
	           return 77;
	          }
	         } else {
	          if (lng < -68.5) {
	           if (lat < 47.25) {
	            return 166;
	           } else {
	            return 77;
	           }
	          } else {
	           return 166;
	          }
	         }
	        } else {
	         return 20;
	        }
	       } else {
	        if (lat < 47.5) {
	         if (lng < -68.0) {
	          return 166;
	         } else {
	          if (lng < -67.75) {
	           if (lat < 47.25) {
	            return 166;
	           } else {
	            return 77;
	           }
	          } else {
	           return 77;
	          }
	         }
	        } else {
	         return 77;
	        }
	       }
	      } else {
	       if (lng < -68.0) {
	        return 20;
	       } else {
	        if (lat < 48.25) {
	         if (lng < -67.75) {
	          return 77;
	         } else {
	          return 20;
	         }
	        } else {
	         return 20;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 54.0) {
	    return 20;
	   } else {
	    if (lng < -67.75) {
	     return 20;
	    } else {
	     if (lat < 54.25) {
	      return 183;
	     } else {
	      return 20;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup63(double lat, double lng)
	{
	 if (lng < -78.75) {
	  if (lat < 56.25) {
	   return kdLookup60(lat,lng);
	  } else {
	   return kdLookup61(lat,lng);
	  }
	 } else {
	  if (lat < 56.25) {
	   return kdLookup62(lat,lng);
	  } else {
	   if (lng < -73.25) {
	    if (lat < 61.75) {
	     if (lng < -76.5) {
	      if (lat < 58.5) {
	       if (lng < -77.75) {
	        if (lat < 57.25) {
	         return 138;
	        } else {
	         return 20;
	        }
	       } else {
	        if (lat < 57.25) {
	         return 20;
	        } else {
	         if (lng < -77.25) {
	          if (lat < 57.75) {
	           return 0;
	          } else {
	           if (lat < 58.0) {
	            return 0;
	           } else {
	            if (lng < -77.5) {
	             return 138;
	            } else {
	             return 20;
	            }
	           }
	          }
	         } else {
	          if (lat < 57.5) {
	           return 138;
	          } else {
	           return 20;
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < 60.5) {
	        return 20;
	       } else {
	        if (lng < -78.0) {
	         return 138;
	        } else {
	         return 20;
	        }
	       }
	      }
	     } else {
	      return 20;
	     }
	    } else {
	     if (lat < 64.5) {
	      if (lng < -76.0) {
	       if (lng < -77.5) {
	        if (lat < 63.0) {
	         return 20;
	        } else {
	         return 138;
	        }
	       } else {
	        if (lat < 63.0) {
	         return 20;
	        } else {
	         return 138;
	        }
	       }
	      } else {
	       if (lng < -74.75) {
	        if (lat < 63.0) {
	         return 20;
	        } else {
	         return 138;
	        }
	       } else {
	        if (lat < 63.0) {
	         return 20;
	        } else {
	         return 138;
	        }
	       }
	      }
	     } else {
	      return 138;
	     }
	    }
	   } else {
	    if (lat < 61.75) {
	     if (lng < -70.5) {
	      return 20;
	     } else {
	      if (lat < 59.0) {
	       return 20;
	      } else {
	       if (lng < -69.0) {
	        return 20;
	       } else {
	        if (lat < 60.25) {
	         if (lng < -68.25) {
	          if (lat < 59.5) {
	           if (lng < -68.75) {
	            return 138;
	           } else {
	            return 20;
	           }
	          } else {
	           return 0;
	          }
	         } else {
	          return 138;
	         }
	        } else {
	         if (lng < -68.25) {
	          return 138;
	         } else {
	          if (lat < 61.0) {
	           if (lng < -68.0) {
	            return 138;
	           } else {
	            return 167;
	           }
	          } else {
	           return 0;
	          }
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -70.5) {
	      if (lat < 63.75) {
	       if (lng < -72.0) {
	        if (lat < 62.75) {
	         return 20;
	        } else {
	         return 138;
	        }
	       } else {
	        if (lat < 62.75) {
	         return 20;
	        } else {
	         return 138;
	        }
	       }
	      } else {
	       return 138;
	      }
	     } else {
	      if (lat < 64.5) {
	       if (lng < -68.25) {
	        return 138;
	       } else {
	        if (lat < 63.0) {
	         if (lat < 62.25) {
	          if (lng < -68.0) {
	           return 138;
	          } else {
	           return 167;
	          }
	         } else {
	          if (lng < -68.0) {
	           return 138;
	          } else {
	           return 167;
	          }
	         }
	        } else {
	         if (lat < 63.75) {
	          if (lng < -68.0) {
	           return 138;
	          } else {
	           return 167;
	          }
	         } else {
	          if (lng < -68.0) {
	           return 138;
	          } else {
	           return 167;
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < -68.0) {
	        return 138;
	       } else {
	        return 167;
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup64(double lat, double lng)
	{
	 if (lat < 78.75) {
	  if (lng < -84.5) {
	   if (lat < 73.0) {
	    if (lng < -87.25) {
	     if (lat < 70.25) {
	      if (lng < -88.75) {
	       if (lat < 68.75) {
	        if (lng < -89.0) {
	         return 108;
	        } else {
	         return 308;
	        }
	       } else {
	        if (lat < 69.5) {
	         if (lng < -89.5) {
	          return 108;
	         } else {
	          if (lng < -89.0) {
	           return 108;
	          } else {
	           return 308;
	          }
	         }
	        } else {
	         if (lng < -89.5) {
	          return 0;
	         } else {
	          if (lng < -89.25) {
	           return 0;
	          } else {
	           if (lat < 69.75) {
	            if (lng < -89.0) {
	             return 108;
	            } else {
	             return 308;
	            }
	           } else {
	            return 0;
	           }
	          }
	         }
	        }
	       }
	      } else {
	       return 308;
	      }
	     } else {
	      return 308;
	     }
	    } else {
	     if (lat < 70.25) {
	      if (lng < -86.0) {
	       return 308;
	      } else {
	       if (lat < 68.75) {
	        if (lng < -85.25) {
	         return 308;
	        } else {
	         if (lat < 68.0) {
	          if (lng < -85.0) {
	           return 308;
	          } else {
	           return 138;
	          }
	         } else {
	          if (lng < -85.0) {
	           return 308;
	          } else {
	           return 138;
	          }
	         }
	        }
	       } else {
	        if (lng < -85.0) {
	         return 308;
	        } else {
	         return 138;
	        }
	       }
	      }
	     } else {
	      if (lng < -86.0) {
	       return 308;
	      } else {
	       if (lat < 71.5) {
	        if (lng < -85.0) {
	         return 308;
	        } else {
	         return 138;
	        }
	       } else {
	        if (lng < -85.25) {
	         return 308;
	        } else {
	         if (lat < 72.25) {
	          if (lng < -85.0) {
	           return 308;
	          } else {
	           return 138;
	          }
	         } else {
	          if (lng < -85.0) {
	           return 308;
	          } else {
	           return 138;
	          }
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 75.75) {
	     if (lng < -86.25) {
	      return 308;
	     } else {
	      if (lat < 74.25) {
	       if (lng < -85.5) {
	        return 308;
	       } else {
	        if (lat < 73.5) {
	         if (lng < -85.25) {
	          return 308;
	         } else {
	          return 138;
	         }
	        } else {
	         if (lng < -85.0) {
	          return 308;
	         } else {
	          return 138;
	         }
	        }
	       }
	      } else {
	       if (lng < -85.0) {
	        return 308;
	       } else {
	        return 138;
	       }
	      }
	     }
	    } else {
	     if (lng < -87.25) {
	      return 308;
	     } else {
	      if (lat < 77.25) {
	       if (lng < -86.0) {
	        return 308;
	       } else {
	        if (lng < -85.25) {
	         return 308;
	        } else {
	         if (lat < 76.5) {
	          if (lng < -85.0) {
	           return 308;
	          } else {
	           return 138;
	          }
	         } else {
	          if (lng < -85.0) {
	           return 308;
	          } else {
	           return 138;
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < -86.0) {
	        return 308;
	       } else {
	        if (lng < -85.25) {
	         return 308;
	        } else {
	         if (lat < 78.0) {
	          if (lng < -85.0) {
	           return 308;
	          } else {
	           return 138;
	          }
	         } else {
	          if (lng < -85.0) {
	           return 308;
	          } else {
	           return 138;
	          }
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   return 138;
	  }
	 } else {
	  if (lng < -84.5) {
	   if (lat < 84.25) {
	    if (lng < -87.25) {
	     return 308;
	    } else {
	     if (lat < 81.5) {
	      if (lng < -85.25) {
	       return 308;
	      } else {
	       if (lat < 80.0) {
	        if (lat < 79.25) {
	         if (lng < -85.0) {
	          return 308;
	         } else {
	          return 138;
	         }
	        } else {
	         if (lng < -85.0) {
	          return 308;
	         } else {
	          if (lat < 79.5) {
	           if (lng < -84.75) {
	            return 308;
	           } else {
	            return 138;
	           }
	          } else {
	           return 138;
	          }
	         }
	        }
	       } else {
	        if (lat < 80.75) {
	         if (lng < -85.0) {
	          return 308;
	         } else {
	          return 138;
	         }
	        } else {
	         if (lng < -85.0) {
	          return 308;
	         } else {
	          return 138;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < -86.0) {
	       return 308;
	      } else {
	       if (lat < 82.75) {
	        if (lng < -85.25) {
	         return 308;
	        } else {
	         if (lat < 82.0) {
	          if (lng < -85.0) {
	           return 308;
	          } else {
	           return 138;
	          }
	         } else {
	          if (lng < -85.0) {
	           return 308;
	          } else {
	           return 138;
	          }
	         }
	        }
	       } else {
	        return 0;
	       }
	      }
	     }
	    }
	   } else {
	    return 0;
	   }
	  } else {
	   return 138;
	  }
	 }
	}

	private  int kdLookup65(double lat, double lng)
	{
	 if (lat < 53.25) {
	  if (lng < -64.75) {
	   if (lng < -66.25) {
	    if (lat < 53.0) {
	     return 20;
	    } else {
	     if (lng < -67.25) {
	      return 20;
	     } else {
	      return 183;
	     }
	    }
	   } else {
	    if (lat < 51.75) {
	     return 20;
	    } else {
	     if (lng < -65.5) {
	      if (lat < 52.5) {
	       if (lng < -66.0) {
	        return 20;
	       } else {
	        if (lat < 52.25) {
	         return 20;
	        } else {
	         return 183;
	        }
	       }
	      } else {
	       return 183;
	      }
	     } else {
	      if (lat < 52.25) {
	       if (lng < -65.25) {
	        return 20;
	       } else {
	        if (lng < -65.0) {
	         if (lat < 52.0) {
	          return 20;
	         } else {
	          return 183;
	         }
	        } else {
	         return 183;
	        }
	       }
	      } else {
	       return 183;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < -63.5) {
	    if (lat < 51.75) {
	     return 20;
	    } else {
	     if (lat < 52.5) {
	      if (lng < -64.25) {
	       if (lat < 52.0) {
	        if (lng < -64.5) {
	         return 20;
	        } else {
	         return 183;
	        }
	       } else {
	        return 183;
	       }
	      } else {
	       if (lng < -64.0) {
	        if (lat < 52.25) {
	         return 20;
	        } else {
	         return 183;
	        }
	       } else {
	        if (lat < 52.0) {
	         return 20;
	        } else {
	         if (lng < -63.75) {
	          return 20;
	         } else {
	          return 183;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < -63.75) {
	       return 183;
	      } else {
	       if (lat < 52.75) {
	        return 183;
	       } else {
	        if (lat < 53.0) {
	         return 20;
	        } else {
	         return 183;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 52.0) {
	     return 20;
	    } else {
	     return 183;
	    }
	   }
	  }
	 } else {
	  if (lng < -64.75) {
	   if (lat < 54.75) {
	    if (lng < -66.75) {
	     if (lat < 54.0) {
	      if (lng < -67.25) {
	       if (lat < 53.75) {
	        return 20;
	       } else {
	        return 183;
	       }
	      } else {
	       if (lat < 53.5) {
	        return 20;
	       } else {
	        if (lng < -67.0) {
	         if (lat < 53.75) {
	          return 20;
	         } else {
	          return 183;
	         }
	        } else {
	         return 183;
	        }
	       }
	      }
	     } else {
	      if (lng < -67.25) {
	       if (lat < 54.5) {
	        return 183;
	       } else {
	        return 20;
	       }
	      } else {
	       return 183;
	      }
	     }
	    } else {
	     return 183;
	    }
	   } else {
	    if (lng < -66.25) {
	     if (lat < 55.25) {
	      if (lng < -67.0) {
	       if (lng < -67.25) {
	        return 20;
	       } else {
	        if (lat < 55.0) {
	         return 20;
	        } else {
	         return 183;
	        }
	       }
	      } else {
	       if (lng < -66.75) {
	        if (lat < 55.0) {
	         return 183;
	        } else {
	         return 20;
	        }
	       } else {
	        return 183;
	       }
	      }
	     } else {
	      return 20;
	     }
	    } else {
	     if (lng < -65.5) {
	      if (lat < 55.0) {
	       return 183;
	      } else {
	       return 20;
	      }
	     } else {
	      if (lat < 55.0) {
	       if (lng < -65.25) {
	        return 20;
	       } else {
	        return 183;
	       }
	      } else {
	       return 20;
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 54.75) {
	    return 183;
	   } else {
	    if (lng < -63.5) {
	     if (lat < 55.0) {
	      if (lng < -64.5) {
	       return 20;
	      } else {
	       if (lng < -64.0) {
	        if (lng < -64.25) {
	         return 183;
	        } else {
	         return 20;
	        }
	       } else {
	        if (lng < -63.75) {
	         return 20;
	        } else {
	         return 183;
	        }
	       }
	      }
	     } else {
	      return 20;
	     }
	    } else {
	     if (lng < -63.25) {
	      if (lat < 56.0) {
	       return 183;
	      } else {
	       return 20;
	      }
	     } else {
	      return 183;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup66(double lat, double lng)
	{
	 if (lat < 50.5) {
	  if (lng < -59.25) {
	   if (lat < 47.75) {
	    if (lng < -60.75) {
	     return 118;
	    } else {
	     if (lat < 46.25) {
	      if (lng < -60.0) {
	       if (lat < 45.5) {
	        return 0;
	       } else {
	        if (lng < -60.5) {
	         return 118;
	        } else {
	         if (lat < 45.75) {
	          return 118;
	         } else {
	          if (lng < -60.25) {
	           if (lat < 46.0) {
	            return 118;
	           } else {
	            return 81;
	           }
	          } else {
	           return 81;
	          }
	         }
	        }
	       }
	      } else {
	       return 81;
	      }
	     } else {
	      if (lng < -60.0) {
	       if (lat < 47.0) {
	        if (lng < -60.25) {
	         return 118;
	        } else {
	         if (lat < 46.5) {
	          return 81;
	         } else {
	          return 118;
	         }
	        }
	       } else {
	        return 118;
	       }
	      } else {
	       return 81;
	      }
	     }
	    }
	   } else {
	    if (lng < -60.75) {
	     if (lat < 49.0) {
	      return 0;
	     } else {
	      if (lat < 49.75) {
	       return 20;
	      } else {
	       if (lng < -61.25) {
	        return 20;
	       } else {
	        return 244;
	       }
	      }
	     }
	    } else {
	     if (lat < 49.0) {
	      return 291;
	     } else {
	      return 244;
	     }
	    }
	   }
	  } else {
	   return 291;
	  }
	 } else {
	  if (lng < -59.25) {
	   if (lat < 53.25) {
	    if (lng < -60.75) {
	     if (lat < 52.0) {
	      return 20;
	     } else {
	      return 183;
	     }
	    } else {
	     if (lat < 51.75) {
	      if (lng < -59.75) {
	       return 20;
	      } else {
	       if (lat < 50.75) {
	        return 244;
	       } else {
	        return 20;
	       }
	      }
	     } else {
	      if (lng < -60.0) {
	       if (lat < 52.0) {
	        return 20;
	       } else {
	        return 183;
	       }
	      } else {
	       if (lat < 52.0) {
	        return 20;
	       } else {
	        return 183;
	       }
	      }
	     }
	    }
	   } else {
	    return 183;
	   }
	  } else {
	   if (lat < 53.25) {
	    if (lng < -57.75) {
	     if (lat < 51.75) {
	      if (lng < -58.5) {
	       if (lat < 51.0) {
	        if (lng < -59.0) {
	         return 20;
	        } else {
	         return 244;
	        }
	       } else {
	        if (lng < -59.0) {
	         return 20;
	        } else {
	         if (lat < 51.25) {
	          return 244;
	         } else {
	          if (lng < -58.75) {
	           return 20;
	          } else {
	           if (lat < 51.5) {
	            return 244;
	           } else {
	            return 20;
	           }
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < 51.0) {
	        return 0;
	       } else {
	        if (lng < -58.25) {
	         if (lat < 51.5) {
	          return 244;
	         } else {
	          return 20;
	         }
	        } else {
	         return 20;
	        }
	       }
	      }
	     } else {
	      if (lng < -58.5) {
	       if (lat < 52.0) {
	        return 20;
	       } else {
	        return 183;
	       }
	      } else {
	       if (lat < 52.0) {
	        return 20;
	       } else {
	        return 183;
	       }
	      }
	     }
	    } else {
	     if (lat < 51.75) {
	      if (lng < -57.0) {
	       if (lat < 51.0) {
	        return 291;
	       } else {
	        return 244;
	       }
	      } else {
	       return 291;
	      }
	     } else {
	      if (lng < -57.0) {
	       if (lat < 52.5) {
	        if (lng < -57.5) {
	         if (lat < 52.0) {
	          return 20;
	         } else {
	          return 183;
	         }
	        } else {
	         if (lat < 52.0) {
	          return 20;
	         } else {
	          return 183;
	         }
	        }
	       } else {
	        if (lng < -57.25) {
	         return 183;
	        } else {
	         if (lat < 53.0) {
	          return 183;
	         } else {
	          return 291;
	         }
	        }
	       }
	      } else {
	       return 291;
	      }
	     }
	    }
	   } else {
	    if (lng < -57.75) {
	     return 183;
	    } else {
	     if (lat < 54.75) {
	      if (lng < -57.0) {
	       if (lat < 53.75) {
	        if (lng < -57.5) {
	         return 183;
	        } else {
	         if (lng < -57.25) {
	          if (lat < 53.5) {
	           return 291;
	          } else {
	           return 183;
	          }
	         } else {
	          return 291;
	         }
	        }
	       } else {
	        return 183;
	       }
	      } else {
	       if (lat < 54.0) {
	        if (lng < -56.75) {
	         return 291;
	        } else {
	         if (lat < 53.75) {
	          return 291;
	         } else {
	          return 183;
	         }
	        }
	       } else {
	        return 183;
	       }
	      }
	     } else {
	      return 183;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup67(double lat, double lng)
	{
	 if (lat < 56.25) {
	  if (lng < -62.0) {
	   if (lat < 50.5) {
	    if (lng < -64.75) {
	     if (lat < 47.75) {
	      if (lng < -66.25) {
	       if (lat < 45.75) {
	        if (lng < -67.0) {
	         if (lat < 45.25) {
	          return 166;
	         } else {
	          if (lng < -67.25) {
	           return 166;
	          } else {
	           return 77;
	          }
	         }
	        } else {
	         return 77;
	        }
	       } else {
	        return 77;
	       }
	      } else {
	       if (lat < 45.75) {
	        if (lng < -65.5) {
	         return 77;
	        } else {
	         if (lng < -65.25) {
	          if (lat < 45.25) {
	           return 118;
	          } else {
	           return 77;
	          }
	         } else {
	          if (lat < 45.5) {
	           return 118;
	          } else {
	           return 77;
	          }
	         }
	        }
	       } else {
	        return 77;
	       }
	      }
	     } else {
	      if (lng < -66.25) {
	       if (lat < 48.25) {
	        if (lng < -67.0) {
	         if (lng < -67.25) {
	          if (lat < 48.0) {
	           return 77;
	          } else {
	           return 20;
	          }
	         } else {
	          if (lat < 48.0) {
	           return 77;
	          } else {
	           return 20;
	          }
	         }
	        } else {
	         if (lng < -66.75) {
	          if (lat < 48.0) {
	           return 77;
	          } else {
	           return 20;
	          }
	         } else {
	          return 77;
	         }
	        }
	       } else {
	        return 20;
	       }
	      } else {
	       if (lat < 48.25) {
	        if (lng < -65.5) {
	         return 77;
	        } else {
	         if (lng < -65.25) {
	          if (lat < 48.0) {
	           return 77;
	          } else {
	           return 20;
	          }
	         } else {
	          return 77;
	         }
	        }
	       } else {
	        return 20;
	       }
	      }
	     }
	    } else {
	     if (lat < 47.75) {
	      if (lng < -63.5) {
	       if (lat < 46.25) {
	        if (lng < -64.25) {
	         if (lat < 45.75) {
	          return 118;
	         } else {
	          return 77;
	         }
	        } else {
	         if (lat < 46.0) {
	          return 118;
	         } else {
	          if (lng < -64.0) {
	           return 77;
	          } else {
	           return 118;
	          }
	         }
	        }
	       } else {
	        if (lat < 47.0) {
	         if (lng < -64.25) {
	          if (lat < 46.75) {
	           return 77;
	          } else {
	           if (lng < -64.5) {
	            return 77;
	           } else {
	            return 118;
	           }
	          }
	         } else {
	          if (lng < -64.0) {
	           if (lat < 46.5) {
	            return 77;
	           } else {
	            return 118;
	           }
	          } else {
	           return 118;
	          }
	         }
	        } else {
	         if (lng < -64.25) {
	          return 77;
	         } else {
	          return 118;
	         }
	        }
	       }
	      } else {
	       return 118;
	      }
	     } else {
	      if (lng < -63.5) {
	       if (lat < 49.0) {
	        if (lng < -64.25) {
	         if (lat < 48.25) {
	          return 77;
	         } else {
	          return 20;
	         }
	        } else {
	         if (lat < 48.25) {
	          return 77;
	         } else {
	          return 20;
	         }
	        }
	       } else {
	        return 20;
	       }
	      } else {
	       return 20;
	      }
	     }
	    }
	   } else {
	    return kdLookup65(lat,lng);
	   }
	  } else {
	   return kdLookup66(lat,lng);
	  }
	 } else {
	  if (lng < -62.0) {
	   if (lat < 61.75) {
	    if (lng < -64.75) {
	     if (lat < 60.25) {
	      return 20;
	     } else {
	      if (lng < -66.25) {
	       return 0;
	      } else {
	       if (lng < -65.5) {
	        return 0;
	       } else {
	        if (lat < 61.0) {
	         return 20;
	        } else {
	         return 167;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 59.0) {
	      if (lng < -63.5) {
	       if (lat < 57.5) {
	        if (lng < -64.0) {
	         return 20;
	        } else {
	         if (lat < 56.75) {
	          if (lng < -63.75) {
	           return 20;
	          } else {
	           return 183;
	          }
	         } else {
	          if (lat < 57.0) {
	           return 183;
	          } else {
	           if (lng < -63.75) {
	            return 20;
	           } else {
	            return 183;
	           }
	          }
	         }
	        }
	       } else {
	        if (lat < 58.25) {
	         if (lng < -64.0) {
	          return 20;
	         } else {
	          if (lat < 57.75) {
	           if (lng < -63.75) {
	            return 20;
	           } else {
	            return 183;
	           }
	          } else {
	           if (lng < -63.75) {
	            if (lat < 58.0) {
	             return 20;
	            } else {
	             return 183;
	            }
	           } else {
	            return 183;
	           }
	          }
	         }
	        } else {
	         if (lng < -64.0) {
	          return 20;
	         } else {
	          if (lat < 58.5) {
	           return 183;
	          } else {
	           if (lng < -63.75) {
	            return 20;
	           } else {
	            if (lat < 58.75) {
	             return 183;
	            } else {
	             return 20;
	            }
	           }
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < 58.25) {
	        return 183;
	       } else {
	        if (lng < -62.75) {
	         if (lng < -63.25) {
	          if (lat < 58.75) {
	           return 183;
	          } else {
	           return 20;
	          }
	         } else {
	          return 183;
	         }
	        } else {
	         return 183;
	        }
	       }
	      }
	     } else {
	      if (lng < -63.5) {
	       if (lat < 60.25) {
	        if (lng < -64.25) {
	         if (lat < 59.5) {
	          if (lng < -64.5) {
	           if (lat < 59.25) {
	            return 183;
	           } else {
	            return 20;
	           }
	          } else {
	           return 183;
	          }
	         } else {
	          if (lat < 59.75) {
	           if (lng < -64.5) {
	            return 183;
	           } else {
	            return 20;
	           }
	          } else {
	           return 183;
	          }
	         }
	        } else {
	         return 183;
	        }
	       } else {
	        if (lat < 61.0) {
	         if (lng < -64.25) {
	          if (lat < 60.5) {
	           return 183;
	          } else {
	           return 20;
	          }
	         } else {
	          return 183;
	         }
	        } else {
	         return 167;
	        }
	       }
	      } else {
	       return 183;
	      }
	     }
	    }
	   } else {
	    return 167;
	   }
	  } else {
	   if (lat < 61.75) {
	    return 183;
	   } else {
	    return 167;
	   }
	  }
	 }
	}

	private  int kdLookup68(double lat, double lng)
	{
	 if (lat < 78.75) {
	  if (lng < -62.75) {
	   if (lat < 73.0) {
	    return 167;
	   } else {
	    if (lat < 75.75) {
	     return 0;
	    } else {
	     if (lng < -65.25) {
	      if (lat < 77.25) {
	       return 307;
	      } else {
	       if (lng < -65.75) {
	        return 307;
	       } else {
	        if (lat < 78.25) {
	         return 307;
	        } else {
	         if (lng < -65.5) {
	          if (lat < 78.5) {
	           return 307;
	          } else {
	           return 16;
	          }
	         } else {
	          return 16;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 77.25) {
	       if (lng < -64.0) {
	        if (lat < 76.5) {
	         return 307;
	        } else {
	         if (lng < -64.25) {
	          return 307;
	         } else {
	          if (lat < 76.75) {
	           return 307;
	          } else {
	           return 16;
	          }
	         }
	        }
	       } else {
	        if (lat < 76.5) {
	         if (lng < -63.75) {
	          return 307;
	         } else {
	          return 16;
	         }
	        } else {
	         return 16;
	        }
	       }
	      } else {
	       if (lng < -64.5) {
	        if (lat < 78.0) {
	         if (lng < -65.0) {
	          return 307;
	         } else {
	          if (lat < 77.5) {
	           return 307;
	          } else {
	           if (lng < -64.75) {
	            if (lat < 77.75) {
	             return 307;
	            } else {
	             return 16;
	            }
	           } else {
	            return 16;
	           }
	          }
	         }
	        } else {
	         return 16;
	        }
	       } else {
	        return 16;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   return 16;
	  }
	 } else {
	  if (lng < -62.0) {
	   if (lat < 84.25) {
	    if (lng < -64.75) {
	     if (lat < 81.5) {
	      if (lng < -66.25) {
	       if (lat < 80.0) {
	        return 307;
	       } else {
	        if (lat < 80.75) {
	         return 16;
	        } else {
	         if (lng < -66.5) {
	          return 167;
	         } else {
	          if (lat < 81.0) {
	           return 16;
	          } else {
	           return 167;
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < 80.0) {
	        if (lng < -65.5) {
	         if (lat < 79.0) {
	          if (lng < -66.0) {
	           return 307;
	          } else {
	           return 16;
	          }
	         } else {
	          return 16;
	         }
	        } else {
	         return 16;
	        }
	       } else {
	        if (lng < -65.5) {
	         if (lat < 81.0) {
	          return 16;
	         } else {
	          return 167;
	         }
	        } else {
	         if (lat < 81.25) {
	          return 16;
	         } else {
	          return 167;
	         }
	        }
	       }
	      }
	     } else {
	      return 167;
	     }
	    } else {
	     if (lat < 81.5) {
	      if (lng < -64.25) {
	       if (lat < 81.25) {
	        return 16;
	       } else {
	        return 167;
	       }
	      } else {
	       return 16;
	      }
	     } else {
	      if (lng < -63.5) {
	       return 167;
	      } else {
	       if (lat < 82.75) {
	        if (lng < -62.25) {
	         if (lng < -63.0) {
	          return 167;
	         } else {
	          if (lat < 82.0) {
	           if (lng < -62.75) {
	            if (lat < 81.75) {
	             return 16;
	            } else {
	             return 167;
	            }
	           } else {
	            return 167;
	           }
	          } else {
	           return 167;
	          }
	         }
	        } else {
	         return 167;
	        }
	       } else {
	        return 167;
	       }
	      }
	     }
	    }
	   } else {
	    return 0;
	   }
	  } else {
	   if (lat < 84.25) {
	    if (lng < -59.25) {
	     if (lat < 82.0) {
	      return 16;
	     } else {
	      if (lng < -60.75) {
	       if (lat < 83.0) {
	        if (lng < -61.5) {
	         return 167;
	        } else {
	         if (lat < 82.5) {
	          if (lng < -61.0) {
	           return 167;
	          } else {
	           if (lat < 82.25) {
	            return 16;
	           } else {
	            return 167;
	           }
	          }
	         } else {
	          return 167;
	         }
	        }
	       } else {
	        return 0;
	       }
	      } else {
	       return 16;
	      }
	     }
	    } else {
	     return 16;
	    }
	   } else {
	    return 0;
	   }
	  }
	 }
	}

	private  int kdLookup69(double lat, double lng)
	{
	 if (lat < 45.0) {
	  return kdLookup58(lat,lng);
	 } else {
	  if (lng < -67.5) {
	   if (lat < 67.5) {
	    return kdLookup63(lat,lng);
	   } else {
	    if (lng < -78.75) {
	     return kdLookup64(lat,lng);
	    } else {
	     if (lat < 78.75) {
	      if (lng < -73.25) {
	       return 138;
	      } else {
	       if (lat < 73.0) {
	        if (lng < -70.5) {
	         return 138;
	        } else {
	         if (lat < 70.25) {
	          if (lng < -68.25) {
	           return 138;
	          } else {
	           if (lat < 68.75) {
	            if (lat < 68.0) {
	             if (lng < -68.0) {
	              return 138;
	             } else {
	              return 167;
	             }
	            } else {
	             if (lng < -68.0) {
	              return 138;
	             } else {
	              return 167;
	             }
	            }
	           } else {
	            if (lat < 69.5) {
	             if (lng < -68.0) {
	              return 138;
	             } else {
	              return 167;
	             }
	            } else {
	             if (lng < -68.0) {
	              return 138;
	             } else {
	              if (lat < 69.75) {
	               if (lng < -67.75) {
	                return 138;
	               } else {
	                return 167;
	               }
	              } else {
	               return 167;
	              }
	             }
	            }
	           }
	          }
	         } else {
	          if (lng < -69.0) {
	           return 138;
	          } else {
	           if (lat < 71.5) {
	            if (lng < -68.25) {
	             return 138;
	            } else {
	             if (lat < 70.75) {
	              if (lng < -68.0) {
	               return 138;
	              } else {
	               return 167;
	              }
	             } else {
	              return 0;
	             }
	            }
	           } else {
	            return 0;
	           }
	          }
	         }
	        }
	       } else {
	        return 307;
	       }
	      }
	     } else {
	      if (lng < -73.25) {
	       return 138;
	      } else {
	       if (lat < 84.25) {
	        if (lng < -70.5) {
	         if (lat < 81.5) {
	          if (lng < -72.0) {
	           if (lat < 79.75) {
	            if (lng < -72.5) {
	             return 138;
	            } else {
	             if (lat < 79.25) {
	              return 307;
	             } else {
	              return 138;
	             }
	            }
	           } else {
	            return 138;
	           }
	          } else {
	           if (lat < 79.75) {
	            if (lng < -71.25) {
	             if (lat < 79.25) {
	              return 307;
	             } else {
	              return 138;
	             }
	            } else {
	             return 307;
	            }
	           } else {
	            return 138;
	           }
	          }
	         } else {
	          return 138;
	         }
	        } else {
	         if (lat < 81.5) {
	          if (lng < -69.0) {
	           if (lat < 80.0) {
	            return 307;
	           } else {
	            return 138;
	           }
	          } else {
	           if (lat < 80.0) {
	            return 307;
	           } else {
	            if (lng < -68.0) {
	             return 138;
	            } else {
	             return 167;
	            }
	           }
	          }
	         } else {
	          if (lng < -69.0) {
	           return 138;
	          } else {
	           if (lat < 82.75) {
	            if (lng < -68.0) {
	             return 138;
	            } else {
	             return 167;
	            }
	           } else {
	            if (lng < -68.25) {
	             return 138;
	            } else {
	             if (lat < 83.5) {
	              if (lng < -68.0) {
	               return 138;
	              } else {
	               return 167;
	              }
	             } else {
	              return 0;
	             }
	            }
	           }
	          }
	         }
	        }
	       } else {
	        return 0;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 67.5) {
	    if (lng < -56.25) {
	     return kdLookup67(lat,lng);
	    } else {
	     if (lat < 56.25) {
	      return 291;
	     } else {
	      return 16;
	     }
	    }
	   } else {
	    if (lng < -56.25) {
	     return kdLookup68(lat,lng);
	    } else {
	     return 16;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup70(double lat, double lng)
	{
	 if (lng < -14.25) {
	  if (lat < 14.0) {
	   if (lng < -15.75) {
	    if (lat < 12.5) {
	     return 38;
	    } else {
	     if (lat < 13.25) {
	      return 226;
	     } else {
	      if (lng < -16.5) {
	       if (lat < 13.75) {
	        return 253;
	       } else {
	        return 226;
	       }
	      } else {
	       if (lng < -16.25) {
	        if (lat < 13.75) {
	         return 253;
	        } else {
	         return 226;
	        }
	       } else {
	        if (lat < 13.75) {
	         return 253;
	        } else {
	         return 226;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 12.5) {
	     if (lng < -14.75) {
	      return 38;
	     } else {
	      if (lat < 11.75) {
	       if (lng < -14.5) {
	        if (lat < 11.5) {
	         return 185;
	        } else {
	         return 38;
	        }
	       } else {
	        return 185;
	       }
	      } else {
	       return 38;
	      }
	     }
	    } else {
	     if (lng < -15.0) {
	      if (lat < 13.25) {
	       if (lng < -15.5) {
	        return 226;
	       } else {
	        if (lat < 12.75) {
	         return 38;
	        } else {
	         return 226;
	        }
	       }
	      } else {
	       if (lng < -15.5) {
	        if (lat < 13.5) {
	         return 226;
	        } else {
	         if (lat < 13.75) {
	          return 253;
	         } else {
	          return 226;
	         }
	        }
	       } else {
	        if (lat < 13.5) {
	         return 226;
	        } else {
	         if (lng < -15.25) {
	          if (lat < 13.75) {
	           return 253;
	          } else {
	           return 226;
	          }
	         } else {
	          return 253;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 13.25) {
	       if (lng < -14.75) {
	        if (lat < 12.75) {
	         return 38;
	        } else {
	         return 226;
	        }
	       } else {
	        if (lat < 12.75) {
	         return 38;
	        } else {
	         return 226;
	        }
	       }
	      } else {
	       if (lng < -14.75) {
	        if (lat < 13.5) {
	         return 226;
	        } else {
	         return 253;
	        }
	       } else {
	        if (lat < 13.5) {
	         return 226;
	        } else {
	         if (lng < -14.5) {
	          if (lat < 13.75) {
	           return 253;
	          } else {
	           return 226;
	          }
	         } else {
	          if (lat < 13.75) {
	           return 253;
	          } else {
	           return 226;
	          }
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < -16.25) {
	    if (lat < 16.25) {
	     return 226;
	    } else {
	     return 33;
	    }
	   } else {
	    if (lat < 16.5) {
	     return 226;
	    } else {
	     if (lng < -14.5) {
	      if (lng < -14.75) {
	       if (lng < -15.0) {
	        if (lng < -15.75) {
	         return 226;
	        } else {
	         if (lng < -15.5) {
	          return 33;
	         } else {
	          return 226;
	         }
	        }
	       } else {
	        return 226;
	       }
	      } else {
	       return 226;
	      }
	     } else {
	      return 226;
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 14.0) {
	   if (lng < -12.75) {
	    if (lat < 12.5) {
	     if (lng < -13.5) {
	      if (lat < 11.75) {
	       return 185;
	      } else {
	       if (lng < -13.75) {
	        return 38;
	       } else {
	        if (lat < 12.25) {
	         return 38;
	        } else {
	         return 185;
	        }
	       }
	      }
	     } else {
	      return 185;
	     }
	    } else {
	     if (lng < -13.5) {
	      if (lat < 12.75) {
	       return 38;
	      } else {
	       if (lat < 13.25) {
	        return 226;
	       } else {
	        if (lng < -14.0) {
	         if (lat < 13.5) {
	          return 253;
	         } else {
	          return 226;
	         }
	        } else {
	         if (lat < 13.5) {
	          return 226;
	         } else {
	          if (lng < -13.75) {
	           if (lat < 13.75) {
	            return 253;
	           } else {
	            return 226;
	           }
	          } else {
	           return 226;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 12.75) {
	       if (lng < -13.0) {
	        return 185;
	       } else {
	        return 226;
	       }
	      } else {
	       return 226;
	      }
	     }
	    }
	   } else {
	    if (lat < 12.5) {
	     return 185;
	    } else {
	     if (lng < -12.0) {
	      return 226;
	     } else {
	      if (lat < 13.25) {
	       return 226;
	      } else {
	       if (lng < -11.75) {
	        if (lat < 13.75) {
	         return 226;
	        } else {
	         return 182;
	        }
	       } else {
	        if (lat < 13.5) {
	         if (lng < -11.5) {
	          return 226;
	         } else {
	          return 182;
	         }
	        } else {
	         return 182;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < -12.75) {
	    if (lat < 15.5) {
	     return 226;
	    } else {
	     if (lng < -13.5) {
	      if (lat < 16.25) {
	       return 226;
	      } else {
	       if (lng < -14.0) {
	        return 226;
	       } else {
	        if (lng < -13.75) {
	         if (lat < 16.5) {
	          return 226;
	         } else {
	          return 33;
	         }
	        } else {
	         return 33;
	        }
	       }
	      }
	     } else {
	      if (lat < 16.0) {
	       if (lng < -13.25) {
	        return 226;
	       } else {
	        if (lng < -13.0) {
	         if (lat < 15.75) {
	          return 226;
	         } else {
	          return 33;
	         }
	        } else {
	         return 33;
	        }
	       }
	      } else {
	       if (lng < -13.25) {
	        if (lat < 16.25) {
	         return 226;
	        } else {
	         return 33;
	        }
	       } else {
	        return 33;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 15.25) {
	     if (lng < -12.0) {
	      if (lat < 15.0) {
	       return 226;
	      } else {
	       if (lng < -12.25) {
	        return 226;
	       } else {
	        return 33;
	       }
	      }
	     } else {
	      if (lat < 15.0) {
	       return 182;
	      } else {
	       if (lng < -11.75) {
	        return 33;
	       } else {
	        return 182;
	       }
	      }
	     }
	    } else {
	     if (lng < -11.75) {
	      return 33;
	     } else {
	      if (lat < 15.75) {
	       if (lng < -11.5) {
	        if (lat < 15.5) {
	         return 182;
	        } else {
	         return 33;
	        }
	       } else {
	        return 182;
	       }
	      } else {
	       return 33;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup71(double lat, double lng)
	{
	 if (lat < 8.25) {
	  if (lng < -8.5) {
	   if (lng < -10.0) {
	    if (lat < 6.75) {
	     return 357;
	    } else {
	     if (lat < 7.5) {
	      if (lng < -11.0) {
	       if (lat < 7.25) {
	        return 357;
	       } else {
	        return 68;
	       }
	      } else {
	       return 357;
	      }
	     } else {
	      if (lng < -10.75) {
	       return 68;
	      } else {
	       if (lng < -10.5) {
	        if (lat < 7.75) {
	         return 357;
	        } else {
	         return 68;
	        }
	       } else {
	        return 357;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 7.25) {
	     return 357;
	    } else {
	     if (lng < -9.25) {
	      return 357;
	     } else {
	      if (lat < 7.5) {
	       if (lng < -9.0) {
	        return 357;
	       } else {
	        if (lng < -8.75) {
	         return 185;
	        } else {
	         return 357;
	        }
	       }
	      } else {
	       return 185;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < -7.25) {
	    if (lat < 6.75) {
	     if (lng < -8.0) {
	      if (lat < 6.5) {
	       return 357;
	      } else {
	       return 301;
	      }
	     } else {
	      if (lat < 6.0) {
	       return 357;
	      } else {
	       if (lng < -7.75) {
	        if (lat < 6.5) {
	         return 357;
	        } else {
	         return 301;
	        }
	       } else {
	        return 301;
	       }
	      }
	     }
	    } else {
	     if (lat < 7.5) {
	      if (lng < -8.25) {
	       return 357;
	      } else {
	       return 301;
	      }
	     } else {
	      if (lng < -8.0) {
	       if (lat < 7.75) {
	        if (lng < -8.25) {
	         return 357;
	        } else {
	         return 301;
	        }
	       } else {
	        return 185;
	       }
	      } else {
	       return 301;
	      }
	     }
	    }
	   } else {
	    return 301;
	   }
	  }
	 } else {
	  if (lng < -8.5) {
	   if (lat < 9.75) {
	    if (lng < -10.0) {
	     if (lat < 9.0) {
	      if (lng < -10.5) {
	       return 68;
	      } else {
	       if (lat < 8.5) {
	        if (lng < -10.25) {
	         return 68;
	        } else {
	         return 357;
	        }
	       } else {
	        return 185;
	       }
	      }
	     } else {
	      if (lng < -10.75) {
	       return 68;
	      } else {
	       if (lng < -10.5) {
	        if (lat < 9.5) {
	         return 68;
	        } else {
	         return 185;
	        }
	       } else {
	        return 185;
	       }
	      }
	     }
	    } else {
	     if (lng < -9.5) {
	      if (lat < 8.75) {
	       if (lng < -9.75) {
	        if (lat < 8.5) {
	         return 357;
	        } else {
	         return 185;
	        }
	       } else {
	        return 357;
	       }
	      } else {
	       return 185;
	      }
	     } else {
	      return 185;
	     }
	    }
	   } else {
	    if (lng < -11.0) {
	     if (lat < 10.0) {
	      return 68;
	     } else {
	      return 185;
	     }
	    } else {
	     return 185;
	    }
	   }
	  } else {
	   if (lat < 9.75) {
	    if (lng < -7.5) {
	     if (lat < 9.0) {
	      if (lng < -8.0) {
	       return 185;
	      } else {
	       if (lat < 8.5) {
	        return 301;
	       } else {
	        if (lng < -7.75) {
	         return 185;
	        } else {
	         if (lat < 8.75) {
	          return 185;
	         } else {
	          return 301;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < -8.0) {
	       return 185;
	      } else {
	       if (lat < 9.25) {
	        if (lng < -7.75) {
	         return 185;
	        } else {
	         return 301;
	        }
	       } else {
	        if (lng < -7.75) {
	         if (lat < 9.5) {
	          return 185;
	         } else {
	          return 301;
	         }
	        } else {
	         return 301;
	        }
	       }
	      }
	     }
	    } else {
	     return 301;
	    }
	   } else {
	    if (lng < -7.25) {
	     if (lat < 10.5) {
	      if (lng < -8.0) {
	       return 185;
	      } else {
	       if (lng < -7.75) {
	        if (lat < 10.25) {
	         return 301;
	        } else {
	         return 185;
	        }
	       } else {
	        return 301;
	       }
	      }
	     } else {
	      if (lng < -8.25) {
	       return 185;
	      } else {
	       return 182;
	      }
	     }
	    } else {
	     if (lng < -6.5) {
	      if (lat < 10.5) {
	       if (lng < -7.0) {
	        return 301;
	       } else {
	        if (lat < 10.25) {
	         return 301;
	        } else {
	         if (lng < -6.75) {
	          return 182;
	         } else {
	          return 301;
	         }
	        }
	       }
	      } else {
	       return 182;
	      }
	     } else {
	      if (lat < 10.5) {
	       if (lng < -6.0) {
	        return 301;
	       } else {
	        if (lat < 10.25) {
	         return 301;
	        } else {
	         return 182;
	        }
	       }
	      } else {
	       if (lng < -6.25) {
	        if (lat < 10.75) {
	         return 301;
	        } else {
	         return 182;
	        }
	       } else {
	        if (lat < 10.75) {
	         if (lng < -6.0) {
	          return 301;
	         } else {
	          return 182;
	         }
	        } else {
	         return 182;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup72(double lat, double lng)
	{
	 if (lng < -5.75) {
	  if (lat < 5.5) {
	   if (lng < -8.5) {
	    return 357;
	   } else {
	    if (lat < 2.75) {
	     return 0;
	    } else {
	     if (lng < -7.25) {
	      if (lat < 4.0) {
	       return 0;
	      } else {
	       if (lat < 4.75) {
	        if (lng < -7.5) {
	         return 357;
	        } else {
	         return 301;
	        }
	       } else {
	        if (lng < -7.5) {
	         return 357;
	        } else {
	         if (lat < 5.25) {
	          return 301;
	         } else {
	          return 357;
	         }
	        }
	       }
	      }
	     } else {
	      return 301;
	     }
	    }
	   }
	  } else {
	   return kdLookup71(lat,lng);
	  }
	 } else {
	  if (lat < 5.5) {
	   if (lng < -2.75) {
	    return 301;
	   } else {
	    return 70;
	   }
	  } else {
	   if (lng < -3.0) {
	    if (lat < 9.75) {
	     return 301;
	    } else {
	     if (lng < -4.5) {
	      if (lat < 10.5) {
	       if (lng < -5.0) {
	        return 301;
	       } else {
	        if (lat < 10.0) {
	         return 301;
	        } else {
	         if (lng < -4.75) {
	          if (lat < 10.25) {
	           return 301;
	          } else {
	           return 263;
	          }
	         } else {
	          return 263;
	         }
	        }
	       }
	      } else {
	       if (lng < -5.25) {
	        return 182;
	       } else {
	        return 263;
	       }
	      }
	     } else {
	      if (lng < -3.75) {
	       if (lat < 10.0) {
	        if (lng < -4.25) {
	         return 263;
	        } else {
	         return 301;
	        }
	       } else {
	        return 263;
	       }
	      } else {
	       if (lat < 10.0) {
	        return 301;
	       } else {
	        return 263;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 8.25) {
	     if (lng < -2.5) {
	      if (lat < 6.75) {
	       if (lat < 5.75) {
	        if (lng < -2.75) {
	         return 301;
	        } else {
	         return 70;
	        }
	       } else {
	        return 70;
	       }
	      } else {
	       if (lat < 7.5) {
	        if (lat < 7.25) {
	         return 70;
	        } else {
	         if (lng < -2.75) {
	          return 301;
	         } else {
	          return 70;
	         }
	        }
	       } else {
	        if (lat < 7.75) {
	         if (lng < -2.75) {
	          return 301;
	         } else {
	          return 70;
	         }
	        } else {
	         if (lng < -2.75) {
	          return 301;
	         } else {
	          if (lat < 8.0) {
	           return 70;
	          } else {
	           return 301;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      return 70;
	     }
	    } else {
	     if (lng < -1.5) {
	      if (lat < 9.75) {
	       if (lng < -2.25) {
	        if (lat < 9.0) {
	         if (lng < -2.5) {
	          return 301;
	         } else {
	          if (lat < 8.5) {
	           return 301;
	          } else {
	           return 70;
	          }
	         }
	        } else {
	         if (lng < -2.75) {
	          return 301;
	         } else {
	          if (lat < 9.25) {
	           if (lng < -2.5) {
	            return 301;
	           } else {
	            return 70;
	           }
	          } else {
	           if (lng < -2.5) {
	            if (lat < 9.5) {
	             return 301;
	            } else {
	             return 263;
	            }
	           } else {
	            return 70;
	           }
	          }
	         }
	        }
	       } else {
	        return 70;
	       }
	      } else {
	       if (lng < -2.25) {
	        if (lat < 10.5) {
	         if (lng < -2.75) {
	          return 263;
	         } else {
	          return 70;
	         }
	        } else {
	         if (lng < -2.75) {
	          return 263;
	         } else {
	          if (lat < 11.0) {
	           return 70;
	          } else {
	           return 263;
	          }
	         }
	        }
	       } else {
	        if (lat < 11.0) {
	         return 70;
	        } else {
	         return 263;
	        }
	       }
	      }
	     } else {
	      if (lat < 11.0) {
	       return 70;
	      } else {
	       if (lng < -0.75) {
	        if (lng < -1.25) {
	         return 70;
	        } else {
	         return 263;
	        }
	       } else {
	        if (lng < -0.5) {
	         return 263;
	        } else {
	         return 70;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup73(double lat, double lng)
	{
	 if (lng < -3.0) {
	  if (lat < 14.0) {
	   if (lng < -4.5) {
	    if (lat < 12.25) {
	     if (lng < -5.25) {
	      return 182;
	     } else {
	      if (lat < 12.0) {
	       if (lng < -5.0) {
	        if (lat < 11.5) {
	         return 263;
	        } else {
	         if (lat < 11.75) {
	          return 182;
	         } else {
	          return 263;
	         }
	        }
	       } else {
	        return 263;
	       }
	      } else {
	       if (lng < -4.75) {
	        return 182;
	       } else {
	        return 263;
	       }
	      }
	     }
	    } else {
	     return 182;
	    }
	   } else {
	    if (lat < 12.5) {
	     return 263;
	    } else {
	     if (lng < -3.75) {
	      if (lat < 13.25) {
	       if (lng < -4.25) {
	        return 182;
	       } else {
	        if (lat < 12.75) {
	         return 263;
	        } else {
	         if (lng < -4.0) {
	          if (lat < 13.0) {
	           return 182;
	          } else {
	           return 263;
	          }
	         } else {
	          return 263;
	         }
	        }
	       }
	      } else {
	       if (lng < -4.0) {
	        return 182;
	       } else {
	        if (lat < 13.5) {
	         return 263;
	        } else {
	         return 182;
	        }
	       }
	      }
	     } else {
	      if (lat < 13.25) {
	       return 263;
	      } else {
	       if (lng < -3.5) {
	        if (lat < 13.5) {
	         return 263;
	        } else {
	         return 182;
	        }
	       } else {
	        if (lat < 13.5) {
	         if (lng < -3.25) {
	          return 182;
	         } else {
	          return 263;
	         }
	        } else {
	         if (lng < -3.25) {
	          return 182;
	         } else {
	          if (lat < 13.75) {
	           return 263;
	          } else {
	           return 182;
	          }
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < -5.25) {
	    if (lat < 15.5) {
	     return 182;
	    } else {
	     if (lat < 16.0) {
	      if (lng < -5.5) {
	       return 33;
	      } else {
	       if (lat < 15.75) {
	        return 182;
	       } else {
	        return 33;
	       }
	      }
	     } else {
	      if (lat < 16.5) {
	       return 33;
	      } else {
	       if (lng < -5.5) {
	        return 33;
	       } else {
	        return 182;
	       }
	      }
	     }
	    }
	   } else {
	    return 182;
	   }
	  }
	 } else {
	  if (lat < 14.0) {
	   if (lng < -2.75) {
	    if (lat < 13.75) {
	     return 263;
	    } else {
	     return 182;
	    }
	   } else {
	    return 263;
	   }
	  } else {
	   if (lng < -1.5) {
	    if (lat < 14.5) {
	     if (lng < -2.5) {
	      if (lng < -2.75) {
	       return 182;
	      } else {
	       if (lat < 14.25) {
	        return 263;
	       } else {
	        return 182;
	       }
	      }
	     } else {
	      if (lng < -2.0) {
	       if (lng < -2.25) {
	        return 263;
	       } else {
	        if (lat < 14.25) {
	         return 263;
	        } else {
	         return 182;
	        }
	       }
	      } else {
	       if (lng < -1.75) {
	        if (lat < 14.25) {
	         return 263;
	        } else {
	         return 182;
	        }
	       } else {
	        return 263;
	       }
	      }
	     }
	    } else {
	     return 182;
	    }
	   } else {
	    if (lat < 15.25) {
	     if (lng < -0.75) {
	      if (lat < 14.75) {
	       return 263;
	      } else {
	       if (lng < -1.0) {
	        return 182;
	       } else {
	        if (lat < 15.0) {
	         return 263;
	        } else {
	         return 182;
	        }
	       }
	      }
	     } else {
	      return 263;
	     }
	    } else {
	     return 182;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup74(double lat, double lng)
	{
	 if (lng < -5.75) {
	  if (lat < 16.75) {
	   if (lng < -8.5) {
	    if (lat < 14.0) {
	     if (lng < -10.0) {
	      if (lat < 12.25) {
	       if (lng < -11.0) {
	        if (lat < 12.0) {
	         return 185;
	        } else {
	         return 182;
	        }
	       } else {
	        if (lng < -10.25) {
	         if (lat < 12.0) {
	          return 185;
	         } else {
	          if (lng < -10.75) {
	           return 185;
	          } else {
	           if (lng < -10.5) {
	            return 182;
	           } else {
	            return 185;
	           }
	          }
	         }
	        } else {
	         return 185;
	        }
	       }
	      } else {
	       return 182;
	      }
	     } else {
	      if (lat < 12.5) {
	       if (lng < -9.25) {
	        if (lat < 12.25) {
	         return 185;
	        } else {
	         return 182;
	        }
	       } else {
	        if (lat < 11.75) {
	         return 185;
	        } else {
	         if (lng < -8.75) {
	          return 185;
	         } else {
	          return 182;
	         }
	        }
	       }
	      } else {
	       if (lng < -9.25) {
	        return 182;
	       } else {
	        if (lat < 12.75) {
	         if (lng < -9.0) {
	          return 185;
	         } else {
	          return 182;
	         }
	        } else {
	         return 182;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -10.0) {
	      if (lat < 15.25) {
	       return 182;
	      } else {
	       if (lat < 15.5) {
	        if (lng < -10.25) {
	         if (lng < -10.5) {
	          if (lng < -11.0) {
	           return 182;
	          } else {
	           if (lng < -10.75) {
	            return 33;
	           } else {
	            return 182;
	           }
	          }
	         } else {
	          return 182;
	         }
	        } else {
	         return 182;
	        }
	       } else {
	        return 33;
	       }
	      }
	     } else {
	      if (lat < 15.5) {
	       return 182;
	      } else {
	       return 33;
	      }
	     }
	    }
	   } else {
	    if (lat < 14.0) {
	     if (lng < -8.25) {
	      if (lat < 11.5) {
	       return 185;
	      } else {
	       return 182;
	      }
	     } else {
	      return 182;
	     }
	    } else {
	     if (lng < -7.25) {
	      if (lat < 15.5) {
	       return 182;
	      } else {
	       return 33;
	      }
	     } else {
	      if (lat < 15.5) {
	       return 182;
	      } else {
	       return 33;
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 20.25) {
	    return 33;
	   } else {
	    if (lng < -6.25) {
	     return 33;
	    } else {
	     if (lat < 21.25) {
	      if (lat < 20.75) {
	       if (lng < -6.0) {
	        return 33;
	       } else {
	        return 182;
	       }
	      } else {
	       if (lng < -6.0) {
	        return 33;
	       } else {
	        return 182;
	       }
	      }
	     } else {
	      if (lat < 21.75) {
	       if (lng < -6.0) {
	        return 33;
	       } else {
	        return 182;
	       }
	      } else {
	       if (lat < 22.0) {
	        if (lng < -6.0) {
	         return 33;
	        } else {
	         return 182;
	        }
	       } else {
	        if (lng < -6.0) {
	         if (lat < 22.25) {
	          return 33;
	         } else {
	          return 182;
	         }
	        } else {
	         return 182;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 16.75) {
	   return kdLookup73(lat,lng);
	  } else {
	   if (lng < -3.0) {
	    if (lat < 18.0) {
	     if (lng < -5.5) {
	      return 33;
	     } else {
	      return 182;
	     }
	    } else {
	     return 182;
	    }
	   } else {
	    if (lat < 22.25) {
	     return 182;
	    } else {
	     if (lng < -0.5) {
	      return 182;
	     } else {
	      return 381;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup75(double lat, double lng)
	{
	 if (lat < 28.0) {
	  if (lng < -3.0) {
	   if (lat < 25.25) {
	    if (lng < -4.5) {
	     if (lat < 25.0) {
	      return 182;
	     } else {
	      if (lng < -5.0) {
	       return 33;
	      } else {
	       if (lng < -4.75) {
	        return 182;
	       } else {
	        return 381;
	       }
	      }
	     }
	    } else {
	     if (lat < 24.25) {
	      return 182;
	     } else {
	      if (lng < -3.75) {
	       if (lat < 24.75) {
	        if (lng < -4.0) {
	         return 182;
	        } else {
	         if (lat < 24.5) {
	          return 182;
	         } else {
	          return 381;
	         }
	        }
	       } else {
	        if (lng < -4.25) {
	         if (lat < 25.0) {
	          return 182;
	         } else {
	          return 381;
	         }
	        } else {
	         return 381;
	        }
	       }
	      } else {
	       if (lat < 24.5) {
	        if (lng < -3.5) {
	         return 182;
	        } else {
	         return 381;
	        }
	       } else {
	        return 381;
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < -5.0) {
	     if (lat < 25.75) {
	      if (lng < -5.5) {
	       return 33;
	      } else {
	       if (lng < -5.25) {
	        if (lat < 25.5) {
	         return 33;
	        } else {
	         return 381;
	        }
	       } else {
	        if (lat < 25.5) {
	         return 33;
	        } else {
	         return 381;
	        }
	       }
	      }
	     } else {
	      return 381;
	     }
	    } else {
	     return 381;
	    }
	   }
	  } else {
	   if (lat < 24.0) {
	    if (lng < -1.5) {
	     if (lng < -2.25) {
	      if (lat < 23.75) {
	       return 182;
	      } else {
	       if (lng < -2.75) {
	        return 182;
	       } else {
	        return 381;
	       }
	      }
	     } else {
	      if (lat < 23.25) {
	       return 182;
	      } else {
	       if (lng < -2.0) {
	        if (lat < 23.5) {
	         return 182;
	        } else {
	         return 381;
	        }
	       } else {
	        return 381;
	       }
	      }
	     }
	    } else {
	     if (lng < -0.75) {
	      if (lat < 23.0) {
	       if (lng < -1.25) {
	        return 182;
	       } else {
	        if (lng < -1.0) {
	         if (lat < 22.75) {
	          return 182;
	         } else {
	          return 381;
	         }
	        } else {
	         if (lat < 22.75) {
	          return 182;
	         } else {
	          return 381;
	         }
	        }
	       }
	      } else {
	       return 381;
	      }
	     } else {
	      return 381;
	     }
	    }
	   } else {
	    return 381;
	   }
	  }
	 } else {
	  if (lng < -3.0) {
	   if (lat < 30.75) {
	    if (lng < -4.75) {
	     if (lat < 30.0) {
	      return 381;
	     } else {
	      if (lng < -5.0) {
	       return 344;
	      } else {
	       if (lat < 30.25) {
	        return 381;
	       } else {
	        return 344;
	       }
	      }
	     }
	    } else {
	     return 381;
	    }
	   } else {
	    if (lat < 31.75) {
	     if (lng < -4.0) {
	      return 344;
	     } else {
	      if (lng < -3.5) {
	       if (lat < 31.0) {
	        return 381;
	       } else {
	        if (lat < 31.25) {
	         return 344;
	        } else {
	         if (lng < -3.75) {
	          return 344;
	         } else {
	          if (lat < 31.5) {
	           return 381;
	          } else {
	           return 344;
	          }
	         }
	        }
	       }
	      } else {
	       return 381;
	      }
	     }
	    } else {
	     return 344;
	    }
	   }
	  } else {
	   if (lat < 31.75) {
	    return 381;
	   } else {
	    if (lng < -1.5) {
	     if (lat < 32.25) {
	      if (lng < -2.75) {
	       return 344;
	      } else {
	       return 381;
	      }
	     } else {
	      return 344;
	     }
	    } else {
	     if (lat < 32.75) {
	      if (lng < -1.0) {
	       if (lat < 32.25) {
	        return 381;
	       } else {
	        if (lng < -1.25) {
	         return 344;
	        } else {
	         if (lat < 32.5) {
	          return 381;
	         } else {
	          return 344;
	         }
	        }
	       }
	      } else {
	       return 381;
	      }
	     } else {
	      if (lng < -1.25) {
	       if (lat < 33.25) {
	        return 344;
	       } else {
	        return 381;
	       }
	      } else {
	       return 381;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup76(double lat, double lng)
	{
	 if (lng < -5.75) {
	  if (lat < 39.25) {
	   if (lng < -8.5) {
	    return 57;
	   } else {
	    if (lat < 36.5) {
	     if (lng < -7.25) {
	      return 344;
	     } else {
	      if (lat < 36.0) {
	       return 344;
	      } else {
	       return 335;
	      }
	     }
	    } else {
	     if (lng < -7.25) {
	      return 57;
	     } else {
	      if (lat < 38.0) {
	       return 335;
	      } else {
	       if (lng < -6.75) {
	        if (lat < 38.5) {
	         if (lng < -7.0) {
	          return 57;
	         } else {
	          return 335;
	         }
	        } else {
	         if (lat < 38.75) {
	          return 335;
	         } else {
	          if (lng < -7.0) {
	           return 57;
	          } else {
	           if (lat < 39.0) {
	            return 335;
	           } else {
	            return 57;
	           }
	          }
	         }
	        }
	       } else {
	        return 335;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 42.0) {
	    if (lng < -8.5) {
	     return 57;
	    } else {
	     if (lng < -7.25) {
	      return 57;
	     } else {
	      if (lat < 40.5) {
	       if (lng < -6.75) {
	        if (lat < 39.75) {
	         if (lng < -7.0) {
	          if (lat < 39.5) {
	           return 57;
	          } else {
	           return 335;
	          }
	         } else {
	          return 335;
	         }
	        } else {
	         return 57;
	        }
	       } else {
	        return 335;
	       }
	      } else {
	       if (lng < -6.5) {
	        if (lat < 41.25) {
	         if (lng < -6.75) {
	          return 57;
	         } else {
	          return 335;
	         }
	        } else {
	         return 57;
	        }
	       } else {
	        if (lat < 41.5) {
	         return 335;
	        } else {
	         if (lng < -6.25) {
	          if (lat < 41.75) {
	           return 57;
	          } else {
	           return 335;
	          }
	         } else {
	          return 335;
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < -8.5) {
	     return 335;
	    } else {
	     if (lat < 43.5) {
	      if (lng < -8.0) {
	       if (lat < 42.25) {
	        return 57;
	       } else {
	        return 335;
	       }
	      } else {
	       return 335;
	      }
	     } else {
	      return 335;
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 39.25) {
	   if (lng < -3.0) {
	    if (lat < 36.5) {
	     if (lng < -4.5) {
	      if (lat < 35.5) {
	       return 344;
	      } else {
	       if (lng < -5.25) {
	        if (lat < 36.0) {
	         return 344;
	        } else {
	         return 335;
	        }
	       } else {
	        if (lat < 36.0) {
	         return 344;
	        } else {
	         return 335;
	        }
	       }
	      }
	     } else {
	      return 344;
	     }
	    } else {
	     return 335;
	    }
	   } else {
	    if (lat < 36.5) {
	     if (lng < -1.5) {
	      if (lat < 35.0) {
	       return 344;
	      } else {
	       if (lng < -2.25) {
	        return 344;
	       } else {
	        if (lat < 35.75) {
	         if (lng < -2.0) {
	          return 344;
	         } else {
	          return 381;
	         }
	        } else {
	         return 0;
	        }
	       }
	      }
	     } else {
	      return 381;
	     }
	    } else {
	     return 335;
	    }
	   }
	  } else {
	   if (lng < -3.0) {
	    return 335;
	   } else {
	    if (lat < 43.0) {
	     return 335;
	    } else {
	     if (lng < -1.5) {
	      return 335;
	     } else {
	      if (lat < 43.5) {
	       if (lng < -1.0) {
	        if (lng < -1.25) {
	         return 335;
	        } else {
	         if (lat < 43.25) {
	          return 335;
	         } else {
	          return 298;
	         }
	        }
	       } else {
	        return 298;
	       }
	      } else {
	       return 298;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup77(double lat, double lng)
	{
	 if (lng < -11.25) {
	  if (lat < 33.75) {
	   if (lng < -17.0) {
	    if (lat < 28.0) {
	     return 265;
	    } else {
	     if (lat < 30.75) {
	      return 265;
	     } else {
	      return 63;
	     }
	    }
	   } else {
	    if (lat < 28.0) {
	     if (lng < -14.25) {
	      if (lat < 26.5) {
	       return 29;
	      } else {
	       return 265;
	      }
	     } else {
	      if (lat < 25.25) {
	       if (lng < -12.75) {
	        if (lat < 23.25) {
	         if (lng < -13.0) {
	          return 29;
	         } else {
	          return 33;
	         }
	        } else {
	         return 29;
	        }
	       } else {
	        if (lat < 23.75) {
	         if (lng < -12.0) {
	          if (lat < 23.5) {
	           return 33;
	          } else {
	           return 29;
	          }
	         } else {
	          return 33;
	         }
	        } else {
	         if (lng < -12.0) {
	          return 29;
	         } else {
	          return 33;
	         }
	        }
	       }
	      } else {
	       if (lng < -12.75) {
	        if (lat < 27.75) {
	         return 29;
	        } else {
	         return 344;
	        }
	       } else {
	        if (lat < 26.5) {
	         if (lng < -12.0) {
	          return 29;
	         } else {
	          if (lat < 26.0) {
	           return 33;
	          } else {
	           return 29;
	          }
	         }
	        } else {
	         if (lng < -12.0) {
	          if (lat < 27.75) {
	           return 29;
	          } else {
	           return 344;
	          }
	         } else {
	          if (lat < 27.75) {
	           return 29;
	          } else {
	           return 344;
	          }
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < -14.25) {
	      if (lat < 30.75) {
	       return 265;
	      } else {
	       return 63;
	      }
	     } else {
	      if (lat < 30.75) {
	       if (lng < -12.75) {
	        if (lat < 29.25) {
	         if (lng < -13.5) {
	          return 265;
	         } else {
	          if (lat < 28.5) {
	           return 344;
	          } else {
	           return 265;
	          }
	         }
	        } else {
	         return 265;
	        }
	       } else {
	        return 344;
	       }
	      } else {
	       return 0;
	      }
	     }
	    }
	   }
	  } else {
	   return 0;
	  }
	 } else {
	  if (lat < 33.75) {
	   if (lng < -5.75) {
	    if (lat < 28.0) {
	     if (lng < -8.5) {
	      if (lat < 26.0) {
	       return 33;
	      } else {
	       if (lng < -10.0) {
	        if (lat < 27.75) {
	         return 29;
	        } else {
	         return 344;
	        }
	       } else {
	        if (lat < 27.75) {
	         return 29;
	        } else {
	         return 344;
	        }
	       }
	      }
	     } else {
	      if (lat < 25.25) {
	       if (lng < -6.5) {
	        return 33;
	       } else {
	        if (lat < 23.75) {
	         if (lat < 23.0) {
	          if (lng < -6.25) {
	           return 33;
	          } else {
	           return 182;
	          }
	         } else {
	          if (lng < -6.25) {
	           return 33;
	          } else {
	           return 182;
	          }
	         }
	        } else {
	         if (lat < 24.5) {
	          if (lng < -6.25) {
	           return 33;
	          } else {
	           return 182;
	          }
	         } else {
	          if (lng < -6.25) {
	           if (lat < 25.0) {
	            return 182;
	           } else {
	            return 33;
	           }
	          } else {
	           if (lat < 25.0) {
	            return 182;
	           } else {
	            return 33;
	           }
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < -7.25) {
	        if (lat < 26.75) {
	         return 33;
	        } else {
	         if (lng < -8.0) {
	          if (lat < 27.25) {
	           return 33;
	          } else {
	           return 381;
	          }
	         } else {
	          if (lat < 27.0) {
	           if (lng < -7.5) {
	            return 33;
	           } else {
	            return 381;
	           }
	          } else {
	           return 381;
	          }
	         }
	        }
	       } else {
	        if (lat < 26.5) {
	         if (lng < -6.5) {
	          if (lat < 26.25) {
	           return 33;
	          } else {
	           if (lng < -6.75) {
	            return 33;
	           } else {
	            return 381;
	           }
	          }
	         } else {
	          if (lat < 25.75) {
	           return 33;
	          } else {
	           if (lng < -6.25) {
	            if (lat < 26.25) {
	             return 33;
	            } else {
	             return 381;
	            }
	           } else {
	            if (lat < 26.0) {
	             if (lng < -6.0) {
	              return 33;
	             } else {
	              return 381;
	             }
	            } else {
	             return 381;
	            }
	           }
	          }
	         }
	        } else {
	         return 381;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 30.0) {
	      if (lng < -8.5) {
	       return 344;
	      } else {
	       if (lng < -7.25) {
	        if (lat < 29.0) {
	         return 381;
	        } else {
	         if (lng < -8.0) {
	          return 344;
	         } else {
	          if (lat < 29.5) {
	           if (lng < -7.75) {
	            if (lat < 29.25) {
	             return 381;
	            } else {
	             return 344;
	            }
	           } else {
	            return 381;
	           }
	          } else {
	           return 344;
	          }
	         }
	        }
	       } else {
	        if (lat < 29.75) {
	         return 381;
	        } else {
	         if (lng < -6.0) {
	          return 344;
	         } else {
	          return 381;
	         }
	        }
	       }
	      }
	     } else {
	      return 344;
	     }
	    }
	   } else {
	    return kdLookup75(lat,lng);
	   }
	  } else {
	   return kdLookup76(lat,lng);
	  }
	 }
	}

	private  int kdLookup78(double lat, double lng)
	{
	 if (lng < -5.75) {
	  if (lat < 50.5) {
	   return 0;
	  } else {
	   if (lat < 53.25) {
	    return 286;
	   } else {
	    if (lng < -8.5) {
	     return 286;
	    } else {
	     if (lat < 54.75) {
	      if (lng < -7.25) {
	       if (lat < 54.25) {
	        return 286;
	       } else {
	        if (lng < -8.0) {
	         return 286;
	        } else {
	         if (lng < -7.75) {
	          if (lat < 54.5) {
	           return 286;
	          } else {
	           return 304;
	          }
	         } else {
	          return 304;
	         }
	        }
	       }
	      } else {
	       if (lng < -6.5) {
	        if (lat < 54.25) {
	         return 286;
	        } else {
	         if (lng < -7.0) {
	          return 304;
	         } else {
	          if (lng < -6.75) {
	           if (lat < 54.5) {
	            return 286;
	           } else {
	            return 304;
	           }
	          } else {
	           return 304;
	          }
	         }
	        }
	       } else {
	        if (lat < 54.0) {
	         return 286;
	        } else {
	         if (lng < -6.25) {
	          if (lat < 54.25) {
	           return 286;
	          } else {
	           return 304;
	          }
	         } else {
	          if (lat < 54.25) {
	           return 286;
	          } else {
	           return 304;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < -7.25) {
	       if (lat < 55.5) {
	        if (lng < -8.0) {
	         return 286;
	        } else {
	         if (lng < -7.5) {
	          return 286;
	         } else {
	          if (lat < 55.0) {
	           return 304;
	          } else {
	           return 286;
	          }
	         }
	        }
	       } else {
	        return 286;
	       }
	      } else {
	       if (lng < -6.5) {
	        if (lat < 55.5) {
	         if (lng < -7.0) {
	          if (lat < 55.25) {
	           return 304;
	          } else {
	           return 286;
	          }
	         } else {
	          if (lat < 55.25) {
	           return 304;
	          } else {
	           return 286;
	          }
	         }
	        } else {
	         return 286;
	        }
	       } else {
	        return 304;
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 50.5) {
	   if (lng < -3.0) {
	    if (lat < 47.75) {
	     return 298;
	    } else {
	     if (lng < -4.5) {
	      if (lat < 49.0) {
	       return 298;
	      } else {
	       return 304;
	      }
	     } else {
	      if (lat < 49.25) {
	       return 298;
	      } else {
	       return 304;
	      }
	     }
	    }
	   } else {
	    if (lat < 49.0) {
	     return 298;
	    } else {
	     if (lng < -1.5) {
	      if (lng < -2.25) {
	       if (lat < 49.75) {
	        if (lng < -2.75) {
	         return 298;
	        } else {
	         return 140;
	        }
	       } else {
	        return 0;
	       }
	      } else {
	       if (lat < 49.75) {
	        if (lng < -2.0) {
	         return 140;
	        } else {
	         if (lat < 49.25) {
	          return 298;
	         } else {
	          if (lng < -1.75) {
	           if (lat < 49.5) {
	            return 140;
	           } else {
	            return 298;
	           }
	          } else {
	           return 298;
	          }
	         }
	        }
	       } else {
	        return 298;
	       }
	      }
	     } else {
	      return 298;
	     }
	    }
	   }
	  } else {
	   if (lng < -3.0) {
	    if (lat < 54.0) {
	     return 304;
	    } else {
	     if (lng < -4.5) {
	      if (lat < 55.0) {
	       if (lng < -5.25) {
	        return 304;
	       } else {
	        if (lat < 54.5) {
	         return 243;
	        } else {
	         return 304;
	        }
	       }
	      } else {
	       return 304;
	      }
	     } else {
	      if (lat < 54.5) {
	       if (lng < -3.75) {
	        return 243;
	       } else {
	        return 304;
	       }
	      } else {
	       return 304;
	      }
	     }
	    }
	   } else {
	    return 304;
	   }
	  }
	 }
	}

	private  int kdLookup79(double lat, double lng)
	{
	 if (lat < 78.75) {
	  if (lng < -17.0) {
	   if (lat < 73.0) {
	    if (lng < -19.75) {
	     if (lat < 70.25) {
	      return 16;
	     } else {
	      if (lng < -21.25) {
	       if (lat < 71.5) {
	        if (lng < -22.0) {
	         if (lat < 70.5) {
	          if (lng < -22.25) {
	           return 16;
	          } else {
	           return 73;
	          }
	         } else {
	          return 73;
	         }
	        } else {
	         return 73;
	        }
	       } else {
	        if (lat < 72.25) {
	         if (lng < -22.0) {
	          if (lat < 72.0) {
	           return 73;
	          } else {
	           return 16;
	          }
	         } else {
	          return 73;
	         }
	        } else {
	         return 16;
	        }
	       }
	      } else {
	       return 0;
	      }
	     }
	    } else {
	     return 0;
	    }
	   } else {
	    if (lat < 75.75) {
	     return 16;
	    } else {
	     if (lng < -19.75) {
	      if (lat < 77.25) {
	       if (lng < -21.25) {
	        if (lat < 76.0) {
	         return 16;
	        } else {
	         return 25;
	        }
	       } else {
	        if (lng < -20.5) {
	         if (lat < 76.0) {
	          return 16;
	         } else {
	          return 25;
	         }
	        } else {
	         if (lat < 76.25) {
	          if (lng < -20.25) {
	           if (lat < 76.0) {
	            return 16;
	           } else {
	            return 25;
	           }
	          } else {
	           return 16;
	          }
	         } else {
	          return 25;
	         }
	        }
	       }
	      } else {
	       return 25;
	      }
	     } else {
	      if (lat < 77.25) {
	       if (lng < -18.5) {
	        if (lat < 76.5) {
	         if (lng < -19.25) {
	          if (lat < 76.0) {
	           return 16;
	          } else {
	           if (lng < -19.5) {
	            if (lat < 76.25) {
	             return 16;
	            } else {
	             return 25;
	            }
	           } else {
	            return 0;
	           }
	          }
	         } else {
	          return 25;
	         }
	        } else {
	         return 25;
	        }
	       } else {
	        return 25;
	       }
	      } else {
	       return 25;
	      }
	     }
	    }
	   }
	  } else {
	   return 0;
	  }
	 } else {
	  if (lng < -17.0) {
	   if (lat < 84.25) {
	    if (lng < -19.75) {
	     if (lat < 81.5) {
	      if (lng < -21.25) {
	       if (lat < 79.75) {
	        return 25;
	       } else {
	        return 16;
	       }
	      } else {
	       if (lat < 80.0) {
	        if (lng < -20.5) {
	         if (lat < 79.75) {
	          return 25;
	         } else {
	          return 16;
	         }
	        } else {
	         if (lat < 79.75) {
	          return 25;
	         } else {
	          return 16;
	         }
	        }
	       } else {
	        return 16;
	       }
	      }
	     } else {
	      return 16;
	     }
	    } else {
	     if (lat < 81.5) {
	      if (lng < -18.5) {
	       if (lat < 79.75) {
	        if (lng < -19.25) {
	         return 25;
	        } else {
	         if (lat < 79.5) {
	          return 25;
	         } else {
	          return 16;
	         }
	        }
	       } else {
	        return 16;
	       }
	      } else {
	       if (lat < 79.75) {
	        if (lng < -17.75) {
	         if (lat < 79.25) {
	          return 25;
	         } else {
	          return 16;
	         }
	        } else {
	         if (lat < 79.25) {
	          return 25;
	         } else {
	          if (lng < -17.5) {
	           if (lat < 79.5) {
	            return 25;
	           } else {
	            return 16;
	           }
	          } else {
	           return 0;
	          }
	         }
	        }
	       } else {
	        return 16;
	       }
	      }
	     } else {
	      return 16;
	     }
	    }
	   } else {
	    return 0;
	   }
	  } else {
	   return 16;
	  }
	 }
	}

	private  int kdLookup80(double lat, double lng)
	{
	 if (lat < 45.0) {
	  if (lng < -22.5) {
	   if (lat < 22.5) {
	    return 354;
	   } else {
	    return 284;
	   }
	  } else {
	   if (lat < 22.5) {
	    if (lng < -11.25) {
	     if (lat < 11.25) {
	      if (lng < -17.0) {
	       return 0;
	      } else {
	       if (lat < 5.5) {
	        return 0;
	       } else {
	        if (lng < -14.25) {
	         if (lat < 8.25) {
	          return 0;
	         } else {
	          if (lat < 9.75) {
	           return 0;
	          } else {
	           if (lng < -15.75) {
	            return 38;
	           } else {
	            if (lng < -14.75) {
	             return 38;
	            } else {
	             return 185;
	            }
	           }
	          }
	         }
	        } else {
	         if (lat < 8.25) {
	          return 68;
	         } else {
	          if (lng < -12.75) {
	           if (lat < 9.25) {
	            return 68;
	           } else {
	            return 185;
	           }
	          } else {
	           if (lat < 9.75) {
	            if (lng < -12.5) {
	             if (lat < 9.5) {
	              return 68;
	             } else {
	              return 185;
	             }
	            } else {
	             return 68;
	            }
	           } else {
	            if (lng < -12.0) {
	             if (lat < 10.0) {
	              if (lng < -12.5) {
	               return 185;
	              } else {
	               return 68;
	              }
	             } else {
	              return 185;
	             }
	            } else {
	             if (lat < 10.0) {
	              return 68;
	             } else {
	              return 185;
	             }
	            }
	           }
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < -17.0) {
	       if (lat < 16.75) {
	        return 226;
	       } else {
	        return 29;
	       }
	      } else {
	       if (lat < 16.75) {
	        return kdLookup70(lat,lng);
	       } else {
	        if (lng < -14.25) {
	         if (lat < 21.25) {
	          return 33;
	         } else {
	          if (lng < -15.75) {
	           if (lng < -16.5) {
	            if (lat < 21.5) {
	             if (lng < -16.75) {
	              return 29;
	             } else {
	              return 33;
	             }
	            } else {
	             return 29;
	            }
	           } else {
	            if (lat < 21.5) {
	             return 33;
	            } else {
	             return 29;
	            }
	           }
	          } else {
	           if (lng < -15.0) {
	            if (lat < 21.5) {
	             return 33;
	            } else {
	             return 29;
	            }
	           } else {
	            if (lat < 21.5) {
	             return 33;
	            } else {
	             return 29;
	            }
	           }
	          }
	         }
	        } else {
	         if (lat < 21.5) {
	          return 33;
	         } else {
	          if (lng < -13.0) {
	           return 29;
	          } else {
	           return 33;
	          }
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 11.25) {
	      return kdLookup72(lat,lng);
	     } else {
	      return kdLookup74(lat,lng);
	     }
	    }
	   } else {
	    return kdLookup77(lat,lng);
	   }
	  }
	 } else {
	  if (lng < -22.5) {
	   if (lat < 67.5) {
	    if (lng < -33.0) {
	     return 16;
	    } else {
	     return 21;
	    }
	   } else {
	    if (lng < -33.75) {
	     return 16;
	    } else {
	     if (lat < 78.75) {
	      if (lng < -25.0) {
	       return 16;
	      } else {
	       if (lat < 72.75) {
	        if (lat < 70.0) {
	         return 16;
	        } else {
	         if (lat < 71.25) {
	          if (lng < -23.75) {
	           if (lng < -24.5) {
	            return 16;
	           } else {
	            if (lat < 70.5) {
	             return 16;
	            } else {
	             return 73;
	            }
	           }
	          } else {
	           if (lng < -23.25) {
	            if (lat < 70.5) {
	             return 16;
	            } else {
	             return 73;
	            }
	           } else {
	            if (lat < 70.5) {
	             return 16;
	            } else {
	             return 73;
	            }
	           }
	          }
	         } else {
	          if (lng < -23.75) {
	           if (lat < 72.0) {
	            if (lng < -24.5) {
	             return 16;
	            } else {
	             if (lng < -24.25) {
	              if (lat < 71.5) {
	               return 73;
	              } else {
	               return 16;
	              }
	             } else {
	              return 73;
	             }
	            }
	           } else {
	            if (lng < -24.25) {
	             return 16;
	            } else {
	             if (lat < 72.5) {
	              return 73;
	             } else {
	              return 16;
	             }
	            }
	           }
	          } else {
	           if (lat < 72.0) {
	            return 73;
	           } else {
	            if (lng < -23.25) {
	             if (lat < 72.25) {
	              return 73;
	             } else {
	              return 16;
	             }
	            } else {
	             if (lng < -23.0) {
	              if (lat < 72.25) {
	               return 73;
	              } else {
	               return 16;
	              }
	             } else {
	              if (lat < 72.25) {
	               return 73;
	              } else {
	               return 16;
	              }
	             }
	            }
	           }
	          }
	         }
	        }
	       } else {
	        if (lat < 76.0) {
	         return 16;
	        } else {
	         if (lat < 77.25) {
	          if (lng < -22.75) {
	           return 16;
	          } else {
	           return 25;
	          }
	         } else {
	          if (lng < -22.75) {
	           return 16;
	          } else {
	           if (lat < 78.5) {
	            return 25;
	           } else {
	            return 16;
	           }
	          }
	         }
	        }
	       }
	      }
	     } else {
	      return 16;
	     }
	    }
	   }
	  } else {
	   if (lat < 67.5) {
	    if (lng < -11.25) {
	     return 21;
	    } else {
	     if (lat < 56.25) {
	      return kdLookup78(lat,lng);
	     } else {
	      if (lng < -5.75) {
	       if (lat < 61.75) {
	        if (lng < -8.5) {
	         return 0;
	        } else {
	         if (lat < 59.0) {
	          return 304;
	         } else {
	          return 362;
	         }
	        }
	       } else {
	        return 362;
	       }
	      } else {
	       return 304;
	      }
	     }
	    }
	   } else {
	    if (lng < -11.25) {
	     return kdLookup79(lat,lng);
	    } else {
	     return 111;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup81(double lat, double lng)
	{
	 if (lat < -17.25) {
	  if (lng < 19.5) {
	   if (lat < -17.75) {
	    return 190;
	   } else {
	    if (lng < 18.75) {
	     return 190;
	    } else {
	     if (lng < 19.0) {
	      if (lat < -17.5) {
	       return 190;
	      } else {
	       return 300;
	      }
	     } else {
	      return 300;
	     }
	    }
	   }
	  } else {
	   if (lat < -20.0) {
	    if (lng < 21.0) {
	     if (lat < -22.0) {
	      if (lng < 20.0) {
	       return 190;
	      } else {
	       return 12;
	      }
	     } else {
	      return 190;
	     }
	    } else {
	     if (lat < -20.25) {
	      if (lat < -20.5) {
	       if (lat < -20.75) {
	        if (lat < -21.75) {
	         if (lng < 21.25) {
	          if (lat < -22.0) {
	           return 12;
	          } else {
	           return 190;
	          }
	         } else {
	          return 12;
	         }
	        } else {
	         if (lng < 21.25) {
	          if (lat < -21.5) {
	           return 190;
	          } else {
	           return 12;
	          }
	         } else {
	          return 12;
	         }
	        }
	       } else {
	        return 12;
	       }
	      } else {
	       return 12;
	      }
	     } else {
	      return 12;
	     }
	    }
	   } else {
	    if (lng < 21.0) {
	     if (lat < -18.0) {
	      return 190;
	     } else {
	      if (lng < 20.25) {
	       if (lng < 19.75) {
	        if (lat < -17.75) {
	         return 190;
	        } else {
	         return 300;
	        }
	       } else {
	        if (lat < -17.75) {
	         return 190;
	        } else {
	         return 300;
	        }
	       }
	      } else {
	       if (lng < 20.5) {
	        if (lat < -17.75) {
	         return 190;
	        } else {
	         return 300;
	        }
	       } else {
	        if (lat < -17.75) {
	         if (lng < 20.75) {
	          return 190;
	         } else {
	          return 300;
	         }
	        } else {
	         return 300;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < -18.25) {
	      return 12;
	     } else {
	      if (lng < 21.75) {
	       if (lat < -17.75) {
	        if (lng < 21.5) {
	         return 190;
	        } else {
	         if (lat < -18.0) {
	          return 190;
	         } else {
	          return 300;
	         }
	        }
	       } else {
	        return 300;
	       }
	      } else {
	       if (lat < -17.75) {
	        if (lng < 22.0) {
	         return 190;
	        } else {
	         if (lng < 22.25) {
	          if (lat < -18.0) {
	           return 12;
	          } else {
	           return 190;
	          }
	         } else {
	          if (lat < -18.0) {
	           return 12;
	          } else {
	           return 190;
	          }
	         }
	        }
	       } else {
	        return 300;
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < -14.25) {
	   if (lng < 22.0) {
	    return 300;
	   } else {
	    if (lat < -15.75) {
	     if (lat < -16.5) {
	      return 300;
	     } else {
	      if (lat < -16.25) {
	       if (lng < 22.25) {
	        return 300;
	       } else {
	        return 268;
	       }
	      } else {
	       if (lng < 22.25) {
	        return 300;
	       } else {
	        return 268;
	       }
	      }
	     }
	    } else {
	     if (lat < -15.0) {
	      if (lat < -15.5) {
	       if (lng < 22.25) {
	        return 300;
	       } else {
	        return 268;
	       }
	      } else {
	       if (lng < 22.25) {
	        if (lat < -15.25) {
	         return 300;
	        } else {
	         return 268;
	        }
	       } else {
	        return 268;
	       }
	      }
	     } else {
	      if (lat < -14.75) {
	       return 268;
	      } else {
	       if (lng < 22.25) {
	        return 300;
	       } else {
	        return 268;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 22.25) {
	    return 300;
	   } else {
	    if (lat < -13.0) {
	     return 268;
	    } else {
	     return 300;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup82(double lat, double lng)
	{
	 if (lat < -3.0) {
	  if (lng < 14.0) {
	   if (lng < 12.5) {
	    if (lat < -4.5) {
	     if (lng < 11.75) {
	      return 0;
	     } else {
	      if (lat < -5.0) {
	       return 300;
	      } else {
	       if (lng < 12.25) {
	        return 155;
	       } else {
	        if (lat < -4.75) {
	         return 300;
	        } else {
	         return 155;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < -3.5) {
	      return 155;
	     } else {
	      if (lng < 12.0) {
	       return 156;
	      } else {
	       if (lng < 12.25) {
	        if (lat < -3.25) {
	         return 155;
	        } else {
	         return 156;
	        }
	       } else {
	        return 155;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < -4.5) {
	     if (lng < 13.0) {
	      if (lat < -5.25) {
	       if (lng < 12.75) {
	        if (lat < -5.5) {
	         return 332;
	        } else {
	         return 300;
	        }
	       } else {
	        return 332;
	       }
	      } else {
	       if (lat < -5.0) {
	        if (lng < 12.75) {
	         return 300;
	        } else {
	         return 332;
	        }
	       } else {
	        if (lng < 12.75) {
	         return 300;
	        } else {
	         if (lat < -4.75) {
	          return 332;
	         } else {
	          return 300;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < -4.75) {
	       return 332;
	      } else {
	       if (lng < 13.5) {
	        return 332;
	       } else {
	        if (lng < 13.75) {
	         return 155;
	        } else {
	         return 332;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 13.75) {
	      if (lat < -4.25) {
	       if (lng < 13.5) {
	        if (lng < 13.25) {
	         if (lng < 12.75) {
	          return 155;
	         } else {
	          if (lng < 13.0) {
	           return 300;
	          } else {
	           return 155;
	          }
	         }
	        } else {
	         return 155;
	        }
	       } else {
	        return 155;
	       }
	      } else {
	       return 155;
	      }
	     } else {
	      if (lat < -4.25) {
	       return 332;
	      } else {
	       return 155;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 15.25) {
	    if (lat < -4.5) {
	     if (lng < 14.5) {
	      return 332;
	     } else {
	      if (lat < -4.75) {
	       return 332;
	      } else {
	       if (lng < 15.0) {
	        return 155;
	       } else {
	        return 332;
	       }
	      }
	     }
	    } else {
	     if (lat < -4.25) {
	      if (lng < 14.5) {
	       return 332;
	      } else {
	       return 155;
	      }
	     } else {
	      return 155;
	     }
	    }
	   } else {
	    if (lat < -4.25) {
	     return 332;
	    } else {
	     if (lng < 16.0) {
	      if (lat < -3.75) {
	       if (lng < 15.5) {
	        return 155;
	       } else {
	        if (lng < 15.75) {
	         if (lat < -4.0) {
	          return 332;
	         } else {
	          return 155;
	         }
	        } else {
	         return 332;
	        }
	       }
	      } else {
	       return 155;
	      }
	     } else {
	      if (lat < -3.5) {
	       return 332;
	      } else {
	       if (lng < 16.25) {
	        return 155;
	       } else {
	        return 332;
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 14.0) {
	   if (lat < -1.75) {
	    if (lng < 12.5) {
	     if (lng < 11.75) {
	      return 156;
	     } else {
	      if (lat < -2.5) {
	       if (lng < 12.0) {
	        if (lat < -2.75) {
	         return 156;
	        } else {
	         return 155;
	        }
	       } else {
	        if (lng < 12.25) {
	         if (lat < -2.75) {
	          return 156;
	         } else {
	          return 155;
	         }
	        } else {
	         return 155;
	        }
	       }
	      } else {
	       if (lng < 12.0) {
	        if (lat < -2.25) {
	         return 155;
	        } else {
	         return 156;
	        }
	       } else {
	        if (lat < -2.25) {
	         return 155;
	        } else {
	         return 156;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 13.25) {
	      if (lat < -2.25) {
	       return 155;
	      } else {
	       if (lng < 12.75) {
	        if (lat < -2.0) {
	         return 156;
	        } else {
	         return 155;
	        }
	       } else {
	        if (lng < 13.0) {
	         return 155;
	        } else {
	         if (lat < -2.0) {
	          return 155;
	         } else {
	          return 156;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < -2.25) {
	       return 155;
	      } else {
	       if (lng < 13.75) {
	        return 156;
	       } else {
	        if (lat < -2.0) {
	         return 155;
	        } else {
	         return 156;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return 156;
	   }
	  } else {
	   if (lat < -1.5) {
	    if (lng < 15.25) {
	     if (lat < -2.25) {
	      return 155;
	     } else {
	      if (lng < 14.5) {
	       if (lat < -2.0) {
	        if (lng < 14.25) {
	         return 156;
	        } else {
	         return 155;
	        }
	       } else {
	        return 156;
	       }
	      } else {
	       return 155;
	      }
	     }
	    } else {
	     if (lng < 16.25) {
	      return 155;
	     } else {
	      if (lat < -2.0) {
	       return 332;
	      } else {
	       if (lng < 16.5) {
	        return 155;
	       } else {
	        if (lat < -1.75) {
	         return 332;
	        } else {
	         return 155;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 14.5) {
	     if (lat < -0.25) {
	      return 156;
	     } else {
	      if (lng < 14.25) {
	       return 156;
	      } else {
	       return 155;
	      }
	     }
	    } else {
	     return 155;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup83(double lat, double lng)
	{
	 if (lat < -5.75) {
	  if (lng < 19.5) {
	   if (lat < -8.0) {
	    return 300;
	   } else {
	    if (lng < 18.0) {
	     if (lat < -7.0) {
	      if (lng < 17.25) {
	       if (lat < -7.25) {
	        return 300;
	       } else {
	        if (lng < 17.0) {
	         return 300;
	        } else {
	         return 332;
	        }
	       }
	      } else {
	       if (lat < -7.5) {
	        if (lng < 17.5) {
	         return 300;
	        } else {
	         return 332;
	        }
	       } else {
	        return 332;
	       }
	      }
	     } else {
	      if (lng < 17.0) {
	       if (lat < -6.5) {
	        return 300;
	       } else {
	        return 332;
	       }
	      } else {
	       return 332;
	      }
	     }
	    } else {
	     if (lat < -7.75) {
	      if (lng < 18.75) {
	       if (lng < 18.25) {
	        return 332;
	       } else {
	        if (lng < 18.5) {
	         return 300;
	        } else {
	         return 332;
	        }
	       }
	      } else {
	       if (lng < 19.0) {
	        return 300;
	       } else {
	        if (lng < 19.25) {
	         return 332;
	        } else {
	         return 300;
	        }
	       }
	      }
	     } else {
	      return 332;
	     }
	    }
	   }
	  } else {
	   if (lat < -8.5) {
	    if (lng < 22.0) {
	     return 300;
	    } else {
	     if (lat < -10.0) {
	      if (lat < -10.25) {
	       if (lat < -10.5) {
	        if (lat < -11.0) {
	         return 300;
	        } else {
	         if (lng < 22.25) {
	          return 300;
	         } else {
	          if (lat < -10.75) {
	           return 353;
	          } else {
	           return 300;
	          }
	         }
	        }
	       } else {
	        return 300;
	       }
	      } else {
	       return 300;
	      }
	     } else {
	      if (lat < -9.75) {
	       if (lng < 22.25) {
	        return 300;
	       } else {
	        return 353;
	       }
	      } else {
	       return 353;
	      }
	     }
	    }
	   } else {
	    if (lng < 21.0) {
	     if (lat < -7.25) {
	      return 300;
	     } else {
	      if (lng < 20.25) {
	       if (lat < -6.5) {
	        if (lng < 19.75) {
	         return 332;
	        } else {
	         if (lat < -6.75) {
	          return 300;
	         } else {
	          if (lng < 20.0) {
	           return 332;
	          } else {
	           return 353;
	          }
	         }
	        }
	       } else {
	        if (lng < 19.75) {
	         return 332;
	        } else {
	         if (lat < -6.0) {
	          return 353;
	         } else {
	          if (lng < 20.0) {
	           return 332;
	          } else {
	           return 353;
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < -6.75) {
	        if (lng < 20.75) {
	         return 300;
	        } else {
	         return 353;
	        }
	       } else {
	        return 353;
	       }
	      }
	     }
	    } else {
	     if (lat < -7.25) {
	      if (lng < 22.0) {
	       return 300;
	      } else {
	       return 353;
	      }
	     } else {
	      return 353;
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 19.5) {
	   if (lat < -1.25) {
	    return 332;
	   } else {
	    if (lng < 17.75) {
	     if (lat < -0.75) {
	      if (lng < 17.25) {
	       if (lng < 17.0) {
	        return 155;
	       } else {
	        if (lat < -1.0) {
	         return 332;
	        } else {
	         return 155;
	        }
	       }
	      } else {
	       if (lng < 17.5) {
	        if (lat < -1.0) {
	         return 332;
	        } else {
	         return 155;
	        }
	       } else {
	        return 332;
	       }
	      }
	     } else {
	      return 155;
	     }
	    } else {
	     return 332;
	    }
	   }
	  } else {
	   if (lat < -3.0) {
	    if (lng < 20.75) {
	     if (lat < -4.5) {
	      if (lng < 20.25) {
	       return 332;
	      } else {
	       return 353;
	      }
	     } else {
	      if (lat < -4.25) {
	       if (lng < 20.25) {
	        return 332;
	       } else {
	        return 353;
	       }
	      } else {
	       return 332;
	      }
	     }
	    } else {
	     return 353;
	    }
	   } else {
	    if (lng < 21.0) {
	     return 332;
	    } else {
	     if (lat < -1.75) {
	      if (lng < 21.75) {
	       if (lat < -2.5) {
	        return 353;
	       } else {
	        if (lng < 21.5) {
	         return 332;
	        } else {
	         if (lat < -2.25) {
	          return 353;
	         } else {
	          return 332;
	         }
	        }
	       }
	      } else {
	       if (lat < -2.25) {
	        return 353;
	       } else {
	        if (lng < 22.25) {
	         return 332;
	        } else {
	         return 353;
	        }
	       }
	      }
	     } else {
	      return 332;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup84(double lat, double lng)
	{
	 if (lat < -22.5) {
	  if (lng < 11.25) {
	   return 0;
	  } else {
	   if (lat < -33.75) {
	    return 170;
	   } else {
	    if (lng < 16.75) {
	     if (lat < -28.5) {
	      return 170;
	     } else {
	      return 190;
	     }
	    } else {
	     if (lat < -28.25) {
	      if (lng < 19.5) {
	       if (lat < -28.75) {
	        return 170;
	       } else {
	        if (lng < 18.0) {
	         if (lng < 17.5) {
	          return 170;
	         } else {
	          if (lng < 17.75) {
	           if (lat < -28.5) {
	            return 170;
	           } else {
	            return 190;
	           }
	          } else {
	           if (lat < -28.5) {
	            return 170;
	           } else {
	            return 190;
	           }
	          }
	         }
	        } else {
	         return 190;
	        }
	       }
	      } else {
	       if (lat < -28.5) {
	        return 170;
	       } else {
	        if (lng < 19.75) {
	         return 190;
	        } else {
	         return 170;
	        }
	       }
	      }
	     } else {
	      if (lng < 20.0) {
	       if (lat < -28.0) {
	        if (lng < 19.75) {
	         if (lng < 19.5) {
	          if (lng < 19.25) {
	           if (lng < 19.0) {
	            if (lng < 18.75) {
	             if (lng < 18.5) {
	              if (lng < 18.25) {
	               if (lng < 18.0) {
	                if (lng < 17.25) {
	                 if (lng < 17.0) {
	                  return 190;
	                 } else {
	                  return 170;
	                 }
	                } else {
	                 if (lng < 17.5) {
	                  return 170;
	                 } else {
	                  return 190;
	                 }
	                }
	               } else {
	                return 190;
	               }
	              } else {
	               return 190;
	              }
	             } else {
	              return 190;
	             }
	            } else {
	             return 190;
	            }
	           } else {
	            return 190;
	           }
	          } else {
	           return 190;
	          }
	         } else {
	          return 190;
	         }
	        } else {
	         return 190;
	        }
	       } else {
	        return 190;
	       }
	      } else {
	       if (lat < -25.5) {
	        if (lat < -26.75) {
	         return 170;
	        } else {
	         if (lng < 21.25) {
	          if (lng < 20.75) {
	           return 170;
	          } else {
	           if (lat < -26.25) {
	            return 12;
	           } else {
	            if (lat < -26.0) {
	             if (lng < 21.0) {
	              return 170;
	             } else {
	              return 12;
	             }
	            } else {
	             if (lng < 21.0) {
	              if (lat < -25.75) {
	               return 170;
	              } else {
	               return 12;
	              }
	             } else {
	              return 12;
	             }
	            }
	           }
	          }
	         } else {
	          if (lng < 22.0) {
	           return 12;
	          } else {
	           if (lat < -26.25) {
	            if (lng < 22.25) {
	             if (lat < -26.5) {
	              return 170;
	             } else {
	              return 12;
	             }
	            } else {
	             return 170;
	            }
	           } else {
	            return 12;
	           }
	          }
	         }
	        }
	       } else {
	        if (lat < -24.5) {
	         if (lng < 20.75) {
	          if (lat < -25.0) {
	           if (lng < 20.5) {
	            return 170;
	           } else {
	            if (lat < -25.25) {
	             return 170;
	            } else {
	             return 12;
	            }
	           }
	          } else {
	           if (lng < 20.25) {
	            return 190;
	           } else {
	            if (lng < 20.5) {
	             if (lat < -24.75) {
	              return 170;
	             } else {
	              return 12;
	             }
	            } else {
	             return 12;
	            }
	           }
	          }
	         } else {
	          return 12;
	         }
	        } else {
	         return 12;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 11.25) {
	   if (lat < -11.25) {
	    return 0;
	   } else {
	    if (lng < 5.5) {
	     return 0;
	    } else {
	     if (lat < -5.75) {
	      return 0;
	     } else {
	      if (lng < 8.25) {
	       return 0;
	      } else {
	       if (lat < -3.5) {
	        if (lng < 9.75) {
	         return 0;
	        } else {
	         if (lat < -4.75) {
	          return 0;
	         } else {
	          if (lng < 10.5) {
	           return 0;
	          } else {
	           if (lat < -4.25) {
	            return 0;
	           } else {
	            if (lng < 10.75) {
	             return 0;
	            } else {
	             if (lat < -4.0) {
	              return 0;
	             } else {
	              if (lng < 11.0) {
	               return 156;
	              } else {
	               return 155;
	              }
	             }
	            }
	           }
	          }
	         }
	        }
	       } else {
	        return 156;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < -11.25) {
	    if (lng < 16.75) {
	     if (lat < -17.0) {
	      if (lng < 14.0) {
	       if (lat < -19.75) {
	        return 190;
	       } else {
	        if (lng < 12.5) {
	         return 190;
	        } else {
	         if (lat < -17.25) {
	          return 190;
	         } else {
	          if (lng < 13.75) {
	           return 190;
	          } else {
	           return 300;
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < -17.25) {
	        return 190;
	       } else {
	        return 300;
	       }
	      }
	     } else {
	      if (lat < -16.75) {
	       if (lng < 13.5) {
	        if (lng < 12.25) {
	         return 300;
	        } else {
	         if (lng < 13.0) {
	          return 300;
	         } else {
	          return 190;
	         }
	        }
	       } else {
	        return 300;
	       }
	      } else {
	       return 300;
	      }
	     }
	    } else {
	     return kdLookup81(lat,lng);
	    }
	   } else {
	    if (lng < 16.75) {
	     if (lat < -5.75) {
	      if (lng < 13.25) {
	       if (lat < -8.5) {
	        return 300;
	       } else {
	        if (lat < -7.25) {
	         return 300;
	        } else {
	         if (lng < 12.25) {
	          return 0;
	         } else {
	          if (lat < -6.0) {
	           return 300;
	          } else {
	           if (lng < 12.75) {
	            return 332;
	           } else {
	            return 300;
	           }
	          }
	         }
	        }
	       }
	      } else {
	       return 300;
	      }
	     } else {
	      return kdLookup82(lat,lng);
	     }
	    } else {
	     return kdLookup83(lat,lng);
	    }
	   }
	  }
	 }
	}

	private  int kdLookup85(double lat, double lng)
	{
	 if (lat < -28.25) {
	  if (lng < 27.25) {
	   return 170;
	  } else {
	   if (lat < -33.25) {
	    return 170;
	   } else {
	    if (lat < -30.5) {
	     return 170;
	    } else {
	     if (lat < -29.5) {
	      if (lat < -30.0) {
	       if (lng < 27.5) {
	        return 170;
	       } else {
	        if (lng < 27.75) {
	         if (lat < -30.25) {
	          return 170;
	         } else {
	          return 340;
	         }
	        } else {
	         return 340;
	        }
	       }
	      } else {
	       return 340;
	      }
	     } else {
	      if (lat < -29.0) {
	       if (lng < 27.5) {
	        return 170;
	       } else {
	        if (lng < 27.75) {
	         if (lat < -29.25) {
	          return 340;
	         } else {
	          return 170;
	         }
	        } else {
	         return 340;
	        }
	       }
	      } else {
	       if (lng < 27.75) {
	        return 170;
	       } else {
	        if (lat < -28.75) {
	         return 340;
	        } else {
	         return 170;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < -25.5) {
	   if (lng < 25.0) {
	    if (lat < -26.0) {
	     return 170;
	    } else {
	     if (lng < 23.75) {
	      if (lng < 23.0) {
	       if (lng < 22.75) {
	        return 12;
	       } else {
	        if (lat < -25.75) {
	         return 170;
	        } else {
	         return 12;
	        }
	       }
	      } else {
	       return 170;
	      }
	     } else {
	      if (lng < 24.5) {
	       return 170;
	      } else {
	       if (lng < 24.75) {
	        if (lat < -25.75) {
	         return 170;
	        } else {
	         return 12;
	        }
	       } else {
	        if (lat < -25.75) {
	         return 170;
	        } else {
	         return 12;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 27.75) {
	     if (lng < 27.5) {
	      if (lat < -25.75) {
	       return 170;
	      } else {
	       if (lng < 27.25) {
	        if (lng < 27.0) {
	         if (lng < 26.75) {
	          if (lng < 26.5) {
	           if (lng < 26.25) {
	            if (lng < 26.0) {
	             if (lng < 25.75) {
	              if (lng < 25.25) {
	               return 170;
	              } else {
	               if (lng < 25.5) {
	                return 12;
	               } else {
	                return 170;
	               }
	              }
	             } else {
	              return 170;
	             }
	            } else {
	             return 170;
	            }
	           } else {
	            return 170;
	           }
	          } else {
	           return 170;
	          }
	         } else {
	          return 170;
	         }
	        } else {
	         return 170;
	        }
	       } else {
	        return 170;
	       }
	      }
	     } else {
	      return 170;
	     }
	    } else {
	     return 170;
	    }
	   }
	  } else {
	   if (lng < 25.75) {
	    if (lng < 25.5) {
	     if (lng < 25.25) {
	      if (lat < -25.25) {
	       if (lng < 23.75) {
	        if (lng < 23.0) {
	         return 12;
	        } else {
	         return 170;
	        }
	       } else {
	        if (lng < 24.0) {
	         return 170;
	        } else {
	         return 12;
	        }
	       }
	      } else {
	       return 12;
	      }
	     } else {
	      return 12;
	     }
	    } else {
	     return 12;
	    }
	   } else {
	    if (lat < -24.0) {
	     if (lng < 26.75) {
	      if (lat < -24.75) {
	       if (lng < 26.0) {
	        if (lat < -25.0) {
	         return 170;
	        } else {
	         return 12;
	        }
	       } else {
	        return 170;
	       }
	      } else {
	       if (lng < 26.25) {
	        if (lat < -24.5) {
	         if (lng < 26.0) {
	          return 12;
	         } else {
	          return 170;
	         }
	        } else {
	         return 12;
	        }
	       } else {
	        if (lat < -24.5) {
	         return 170;
	        } else {
	         return 12;
	        }
	       }
	      }
	     } else {
	      if (lat < -24.25) {
	       return 170;
	      } else {
	       if (lng < 27.0) {
	        return 12;
	       } else {
	        return 170;
	       }
	      }
	     }
	    } else {
	     if (lng < 27.0) {
	      return 12;
	     } else {
	      if (lat < -23.25) {
	       if (lng < 27.25) {
	        if (lat < -23.5) {
	         return 170;
	        } else {
	         return 12;
	        }
	       } else {
	        return 170;
	       }
	      } else {
	       if (lng < 27.75) {
	        return 12;
	       } else {
	        if (lat < -23.0) {
	         return 170;
	        } else {
	         return 12;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup86(double lat, double lng)
	{
	 if (lat < -28.25) {
	  if (lng < 30.75) {
	   if (lat < -31.0) {
	    return 170;
	   } else {
	    if (lng < 29.25) {
	     if (lat < -29.75) {
	      if (lng < 28.5) {
	       if (lat < -30.5) {
	        return 170;
	       } else {
	        if (lat < -30.25) {
	         if (lng < 28.25) {
	          return 340;
	         } else {
	          return 170;
	         }
	        } else {
	         return 340;
	        }
	       }
	      } else {
	       if (lat < -30.0) {
	        return 170;
	       } else {
	        return 340;
	       }
	      }
	     } else {
	      if (lat < -28.75) {
	       return 340;
	      } else {
	       if (lng < 28.5) {
	        if (lng < 28.25) {
	         return 170;
	        } else {
	         if (lat < -28.5) {
	          return 340;
	         } else {
	          return 170;
	         }
	        }
	       } else {
	        if (lng < 28.75) {
	         if (lat < -28.5) {
	          return 340;
	         } else {
	          return 170;
	         }
	        } else {
	         if (lng < 29.0) {
	          if (lat < -28.5) {
	           return 340;
	          } else {
	           return 170;
	          }
	         } else {
	          return 170;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < -29.5) {
	      return 170;
	     } else {
	      if (lng < 29.5) {
	       if (lat < -29.0) {
	        return 340;
	       } else {
	        return 170;
	       }
	      } else {
	       return 170;
	      }
	     }
	    }
	   }
	  } else {
	   return 170;
	  }
	 } else {
	  if (lng < 30.75) {
	   if (lat < -22.75) {
	    return 170;
	   } else {
	    if (lng < 28.25) {
	     return 12;
	    } else {
	     return 170;
	    }
	   }
	  } else {
	   if (lat < -25.5) {
	    if (lng < 32.25) {
	     if (lat < -27.0) {
	      if (lng < 31.5) {
	       return 170;
	      } else {
	       if (lat < -27.25) {
	        return 170;
	       } else {
	        if (lng < 32.0) {
	         return 117;
	        } else {
	         return 170;
	        }
	       }
	      }
	     } else {
	      if (lng < 31.5) {
	       if (lat < -26.25) {
	        if (lng < 31.0) {
	         return 170;
	        } else {
	         return 117;
	        }
	       } else {
	        if (lng < 31.0) {
	         return 170;
	        } else {
	         if (lat < -26.0) {
	          return 117;
	         } else {
	          if (lng < 31.25) {
	           return 170;
	          } else {
	           if (lat < -25.75) {
	            return 117;
	           } else {
	            return 170;
	           }
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < -26.25) {
	        if (lng < 32.0) {
	         return 117;
	        } else {
	         if (lat < -26.75) {
	          return 170;
	         } else {
	          return 117;
	         }
	        }
	       } else {
	        if (lng < 31.75) {
	         if (lat < -25.75) {
	          return 117;
	         } else {
	          return 170;
	         }
	        } else {
	         if (lat < -25.75) {
	          return 117;
	         } else {
	          if (lng < 32.0) {
	           return 170;
	          } else {
	           return 365;
	          }
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < -27.0) {
	      return 170;
	     } else {
	      if (lng < 33.0) {
	       if (lat < -26.75) {
	        return 170;
	       } else {
	        return 365;
	       }
	      } else {
	       if (lat < -26.25) {
	        if (lng < 33.25) {
	         if (lat < -26.75) {
	          return 170;
	         } else {
	          return 365;
	         }
	        } else {
	         return 0;
	        }
	       } else {
	        return 365;
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 32.25) {
	     if (lat < -24.0) {
	      if (lng < 32.0) {
	       return 170;
	      } else {
	       if (lat < -24.75) {
	        if (lat < -25.25) {
	         return 365;
	        } else {
	         return 170;
	        }
	       } else {
	        if (lat < -24.25) {
	         return 170;
	        } else {
	         return 365;
	        }
	       }
	      }
	     } else {
	      if (lng < 31.5) {
	       return 170;
	      } else {
	       if (lat < -23.25) {
	        if (lng < 31.75) {
	         return 170;
	        } else {
	         if (lat < -23.75) {
	          if (lng < 32.0) {
	           return 170;
	          } else {
	           return 365;
	          }
	         } else {
	          return 365;
	         }
	        }
	       } else {
	        if (lng < 31.75) {
	         if (lat < -23.0) {
	          return 170;
	         } else {
	          return 365;
	         }
	        } else {
	         return 365;
	        }
	       }
	      }
	     }
	    } else {
	     return 365;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup87(double lat, double lng)
	{
	 if (lng < 25.25) {
	  if (lat < -18.25) {
	   return 12;
	  } else {
	   if (lng < 23.75) {
	    if (lng < 23.0) {
	     if (lat < -17.75) {
	      if (lng < 22.75) {
	       if (lat < -18.0) {
	        return 12;
	       } else {
	        return 190;
	       }
	      } else {
	       if (lat < -18.0) {
	        return 12;
	       } else {
	        return 190;
	       }
	      }
	     } else {
	      return 300;
	     }
	    } else {
	     if (lat < -17.75) {
	      if (lng < 23.25) {
	       if (lat < -18.0) {
	        return 12;
	       } else {
	        return 190;
	       }
	      } else {
	       if (lng < 23.5) {
	        return 12;
	       } else {
	        if (lat < -18.0) {
	         return 12;
	        } else {
	         return 190;
	        }
	       }
	      }
	     } else {
	      if (lng < 23.25) {
	       if (lat < -17.5) {
	        return 190;
	       } else {
	        if (lat < -17.25) {
	         return 300;
	        } else {
	         return 268;
	        }
	       }
	      } else {
	       if (lat < -17.5) {
	        return 190;
	       } else {
	        return 268;
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 24.5) {
	     if (lat < -17.75) {
	      if (lng < 24.0) {
	       return 190;
	      } else {
	       if (lng < 24.25) {
	        if (lat < -18.0) {
	         return 12;
	        } else {
	         return 190;
	        }
	       } else {
	        if (lat < -18.0) {
	         return 12;
	        } else {
	         return 190;
	        }
	       }
	      }
	     } else {
	      if (lng < 24.0) {
	       if (lat < -17.5) {
	        return 190;
	       } else {
	        return 268;
	       }
	      } else {
	       if (lat < -17.5) {
	        return 190;
	       } else {
	        if (lng < 24.25) {
	         return 268;
	        } else {
	         if (lat < -17.25) {
	          return 190;
	         } else {
	          return 268;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < -17.75) {
	      if (lng < 24.75) {
	       if (lat < -18.0) {
	        return 12;
	       } else {
	        return 190;
	       }
	      } else {
	       return 12;
	      }
	     } else {
	      if (lng < 24.75) {
	       if (lat < -17.5) {
	        return 190;
	       } else {
	        return 268;
	       }
	      } else {
	       if (lat < -17.5) {
	        return 190;
	       } else {
	        return 268;
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < -19.75) {
	   if (lng < 27.0) {
	    return 12;
	   } else {
	    if (lat < -21.0) {
	     return 12;
	    } else {
	     if (lat < -20.5) {
	      if (lng < 27.75) {
	       return 12;
	      } else {
	       return 270;
	      }
	     } else {
	      if (lng < 27.5) {
	       if (lat < -20.0) {
	        return 12;
	       } else {
	        return 270;
	       }
	      } else {
	       if (lat < -20.25) {
	        if (lng < 27.75) {
	         return 12;
	        } else {
	         return 270;
	        }
	       } else {
	        return 270;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 26.5) {
	    if (lat < -18.5) {
	     if (lng < 26.0) {
	      return 12;
	     } else {
	      if (lat < -19.25) {
	       if (lng < 26.25) {
	        return 12;
	       } else {
	        if (lat < -19.5) {
	         return 12;
	        } else {
	         return 270;
	        }
	       }
	      } else {
	       if (lat < -19.0) {
	        if (lng < 26.25) {
	         return 12;
	        } else {
	         return 270;
	        }
	       } else {
	        return 270;
	       }
	      }
	     }
	    } else {
	     if (lat < -17.75) {
	      if (lng < 25.75) {
	       if (lat < -18.25) {
	        return 12;
	       } else {
	        if (lng < 25.5) {
	         return 12;
	        } else {
	         return 270;
	        }
	       }
	      } else {
	       return 270;
	      }
	     } else {
	      return 268;
	     }
	    }
	   } else {
	    if (lat < -18.0) {
	     return 270;
	    } else {
	     if (lng < 27.25) {
	      if (lat < -17.75) {
	       if (lng < 26.75) {
	        return 270;
	       } else {
	        if (lng < 27.0) {
	         return 268;
	        } else {
	         return 270;
	        }
	       }
	      } else {
	       return 268;
	      }
	     } else {
	      if (lat < -17.5) {
	       return 270;
	      } else {
	       if (lng < 27.5) {
	        return 268;
	       } else {
	        if (lng < 27.75) {
	         if (lat < -17.25) {
	          return 270;
	         } else {
	          return 268;
	         }
	        } else {
	         return 270;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup88(double lat, double lng)
	{
	 if (lng < 30.75) {
	  if (lat < -14.25) {
	   if (lng < 29.25) {
	    if (lat < -15.75) {
	     if (lng < 28.5) {
	      if (lat < -16.5) {
	       if (lng < 28.25) {
	        if (lat < -16.75) {
	         return 270;
	        } else {
	         return 268;
	        }
	       } else {
	        return 270;
	       }
	      } else {
	       return 268;
	      }
	     } else {
	      if (lat < -16.5) {
	       return 270;
	      } else {
	       if (lng < 29.0) {
	        return 268;
	       } else {
	        return 270;
	       }
	      }
	     }
	    } else {
	     return 268;
	    }
	   } else {
	    if (lat < -15.75) {
	     if (lng < 30.5) {
	      return 270;
	     } else {
	      if (lat < -16.0) {
	       return 270;
	      } else {
	       return 365;
	      }
	     }
	    } else {
	     if (lng < 30.0) {
	      if (lat < -15.5) {
	       if (lng < 29.5) {
	        return 268;
	       } else {
	        return 270;
	       }
	      } else {
	       return 268;
	      }
	     } else {
	      if (lat < -15.0) {
	       if (lng < 30.25) {
	        if (lat < -15.5) {
	         return 270;
	        } else {
	         return 268;
	        }
	       } else {
	        if (lat < -15.5) {
	         if (lng < 30.5) {
	          return 270;
	         } else {
	          return 365;
	         }
	        } else {
	         if (lng < 30.5) {
	          return 268;
	         } else {
	          return 365;
	         }
	        }
	       }
	      } else {
	       if (lng < 30.25) {
	        return 268;
	       } else {
	        if (lat < -14.75) {
	         return 365;
	        } else {
	         return 268;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < -12.75) {
	    if (lng < 29.25) {
	     if (lat < -13.25) {
	      return 268;
	     } else {
	      if (lng < 29.0) {
	       return 268;
	      } else {
	       return 353;
	      }
	     }
	    } else {
	     if (lng < 30.0) {
	      if (lat < -13.25) {
	       return 268;
	      } else {
	       return 353;
	      }
	     } else {
	      return 268;
	     }
	    }
	   } else {
	    if (lng < 29.25) {
	     if (lat < -12.0) {
	      if (lng < 28.5) {
	       if (lat < -12.25) {
	        return 268;
	       } else {
	        return 353;
	       }
	      } else {
	       if (lng < 29.0) {
	        return 353;
	       } else {
	        if (lat < -12.25) {
	         return 353;
	        } else {
	         return 268;
	        }
	       }
	      }
	     } else {
	      if (lng < 28.5) {
	       return 353;
	      } else {
	       if (lng < 28.75) {
	        if (lat < -11.75) {
	         return 353;
	        } else {
	         return 268;
	        }
	       } else {
	        if (lat < -11.75) {
	         if (lng < 29.0) {
	          return 353;
	         } else {
	          return 268;
	         }
	        } else {
	         return 268;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 30.0) {
	      if (lat < -12.0) {
	       if (lng < 29.5) {
	        if (lat < -12.25) {
	         return 353;
	        } else {
	         return 268;
	        }
	       } else {
	        return 353;
	       }
	      } else {
	       return 268;
	      }
	     } else {
	      return 268;
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < -14.25) {
	   if (lng < 32.25) {
	    if (lat < -15.75) {
	     if (lng < 31.5) {
	      if (lat < -16.0) {
	       return 270;
	      } else {
	       return 365;
	      }
	     } else {
	      if (lat < -16.25) {
	       return 270;
	      } else {
	       if (lng < 31.75) {
	        if (lat < -16.0) {
	         return 270;
	        } else {
	         return 365;
	        }
	       } else {
	        if (lng < 32.0) {
	         if (lat < -16.0) {
	          return 270;
	         } else {
	          return 365;
	         }
	        } else {
	         return 365;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 31.5) {
	      if (lat < -14.75) {
	       return 365;
	      } else {
	       if (lng < 31.0) {
	        return 268;
	       } else {
	        if (lng < 31.25) {
	         if (lat < -14.5) {
	          return 365;
	         } else {
	          return 268;
	         }
	        } else {
	         if (lat < -14.5) {
	          return 365;
	         } else {
	          return 268;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < -14.5) {
	       return 365;
	      } else {
	       if (lng < 31.75) {
	        return 268;
	       } else {
	        return 365;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < -16.25) {
	     if (lng < 33.0) {
	      if (lng < 32.5) {
	       return 270;
	      } else {
	       if (lat < -16.5) {
	        return 270;
	       } else {
	        return 365;
	       }
	      }
	     } else {
	      return 365;
	     }
	    } else {
	     return 365;
	    }
	   }
	  } else {
	   if (lng < 32.75) {
	    return 268;
	   } else {
	    if (lat < -12.75) {
	     if (lat < -13.5) {
	      if (lng < 33.25) {
	       if (lat < -14.0) {
	        return 365;
	       } else {
	        if (lng < 33.0) {
	         return 268;
	        } else {
	         return 207;
	        }
	       }
	      } else {
	       if (lat < -14.0) {
	        if (lng < 33.5) {
	         return 365;
	        } else {
	         return 207;
	        }
	       } else {
	        return 207;
	       }
	      }
	     } else {
	      if (lng < 33.25) {
	       if (lat < -13.25) {
	        if (lng < 33.0) {
	         return 268;
	        } else {
	         return 207;
	        }
	       } else {
	        if (lng < 33.0) {
	         return 268;
	        } else {
	         if (lat < -13.0) {
	          return 207;
	         } else {
	          return 268;
	         }
	        }
	       }
	      } else {
	       return 207;
	      }
	     }
	    } else {
	     if (lat < -12.0) {
	      if (lng < 33.25) {
	       if (lat < -12.5) {
	        if (lng < 33.0) {
	         return 268;
	        } else {
	         return 207;
	        }
	       } else {
	        return 268;
	       }
	      } else {
	       if (lat < -12.5) {
	        return 207;
	       } else {
	        if (lng < 33.5) {
	         return 268;
	        } else {
	         return 207;
	        }
	       }
	      }
	     } else {
	      if (lng < 33.25) {
	       return 268;
	      } else {
	       if (lat < -11.75) {
	        if (lng < 33.5) {
	         return 268;
	        } else {
	         return 207;
	        }
	       } else {
	        if (lng < 33.5) {
	         if (lat < -11.5) {
	          return 268;
	         } else {
	          return 207;
	         }
	        } else {
	         return 207;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup89(double lat, double lng)
	{
	 if (lat < -17.0) {
	  if (lng < 30.75) {
	   if (lat < -21.5) {
	    if (lng < 29.25) {
	     if (lng < 29.0) {
	      return 12;
	     } else {
	      if (lat < -22.0) {
	       if (lat < -22.25) {
	        return 170;
	       } else {
	        return 12;
	       }
	      } else {
	       if (lat < -21.75) {
	        return 12;
	       } else {
	        return 270;
	       }
	      }
	     }
	    } else {
	     if (lng < 30.0) {
	      if (lat < -22.0) {
	       return 170;
	      } else {
	       return 270;
	      }
	     } else {
	      if (lat < -22.0) {
	       if (lng < 30.25) {
	        return 170;
	       } else {
	        if (lng < 30.5) {
	         if (lat < -22.25) {
	          return 170;
	         } else {
	          return 270;
	         }
	        } else {
	         if (lat < -22.25) {
	          return 170;
	         } else {
	          return 270;
	         }
	        }
	       }
	      } else {
	       return 270;
	      }
	     }
	    }
	   } else {
	    return 270;
	   }
	  } else {
	   if (lat < -19.75) {
	    if (lng < 32.25) {
	     if (lat < -21.5) {
	      if (lng < 31.5) {
	       if (lat < -22.25) {
	        return 170;
	       } else {
	        return 270;
	       }
	      } else {
	       if (lat < -22.0) {
	        if (lng < 31.75) {
	         if (lat < -22.25) {
	          return 365;
	         } else {
	          return 270;
	         }
	        } else {
	         return 365;
	        }
	       } else {
	        if (lng < 31.75) {
	         return 270;
	        } else {
	         if (lng < 32.0) {
	          if (lat < -21.75) {
	           return 365;
	          } else {
	           return 270;
	          }
	         } else {
	          return 365;
	         }
	        }
	       }
	      }
	     } else {
	      return 270;
	     }
	    } else {
	     if (lat < -21.25) {
	      return 365;
	     } else {
	      if (lng < 33.0) {
	       if (lat < -20.5) {
	        if (lng < 32.5) {
	         return 270;
	        } else {
	         return 365;
	        }
	       } else {
	        if (lng < 32.75) {
	         return 270;
	        } else {
	         if (lat < -20.25) {
	          return 365;
	         } else {
	          return 270;
	         }
	        }
	       }
	      } else {
	       if (lat < -20.0) {
	        return 365;
	       } else {
	        if (lng < 33.25) {
	         return 270;
	        } else {
	         return 365;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 32.75) {
	     return 270;
	    } else {
	     if (lat < -18.5) {
	      if (lat < -19.25) {
	       if (lng < 33.0) {
	        return 270;
	       } else {
	        return 365;
	       }
	      } else {
	       if (lng < 33.0) {
	        if (lat < -19.0) {
	         return 270;
	        } else {
	         if (lat < -18.75) {
	          return 365;
	         } else {
	          return 270;
	         }
	        }
	       } else {
	        return 365;
	       }
	      }
	     } else {
	      if (lat < -17.75) {
	       if (lng < 33.25) {
	        if (lat < -18.25) {
	         if (lng < 33.0) {
	          return 270;
	         } else {
	          return 365;
	         }
	        } else {
	         if (lng < 33.0) {
	          return 270;
	         } else {
	          if (lat < -18.0) {
	           return 270;
	          } else {
	           return 365;
	          }
	         }
	        }
	       } else {
	        return 365;
	       }
	      } else {
	       if (lng < 33.25) {
	        if (lat < -17.5) {
	         return 270;
	        } else {
	         if (lng < 33.0) {
	          return 270;
	         } else {
	          return 365;
	         }
	        }
	       } else {
	        return 365;
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  return kdLookup88(lat,lng);
	 }
	}

	private  int kdLookup90(double lat, double lng)
	{
	 if (lng < 30.75) {
	  if (lat < -8.5) {
	   if (lng < 29.0) {
	    if (lat < -10.0) {
	     if (lat < -10.75) {
	      if (lng < 28.5) {
	       return 353;
	      } else {
	       if (lng < 28.75) {
	        if (lat < -11.0) {
	         return 268;
	        } else {
	         return 353;
	        }
	       } else {
	        return 268;
	       }
	      }
	     } else {
	      if (lng < 28.75) {
	       return 353;
	      } else {
	       return 268;
	      }
	     }
	    } else {
	     if (lat < -9.25) {
	      if (lng < 28.75) {
	       return 353;
	      } else {
	       return 268;
	      }
	     } else {
	      if (lng < 28.5) {
	       return 353;
	      } else {
	       if (lat < -9.0) {
	        return 268;
	       } else {
	        if (lng < 28.75) {
	         return 353;
	        } else {
	         if (lat < -8.75) {
	          return 268;
	         } else {
	          return 353;
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return 268;
	   }
	  } else {
	   if (lng < 29.25) {
	    if (lat < -8.25) {
	     if (lng < 29.0) {
	      return 353;
	     } else {
	      return 268;
	     }
	    } else {
	     return 353;
	    }
	   } else {
	    if (lat < -7.25) {
	     if (lng < 30.0) {
	      if (lat < -8.25) {
	       return 268;
	      } else {
	       return 353;
	      }
	     } else {
	      if (lat < -8.0) {
	       if (lng < 30.25) {
	        if (lat < -8.25) {
	         return 268;
	        } else {
	         return 353;
	        }
	       } else {
	        if (lng < 30.5) {
	         if (lat < -8.25) {
	          return 268;
	         } else {
	          return 353;
	         }
	        } else {
	         if (lat < -8.25) {
	          return 268;
	         } else {
	          return 353;
	         }
	        }
	       }
	      } else {
	       if (lng < 30.5) {
	        return 353;
	       } else {
	        if (lat < -7.5) {
	         return 353;
	        } else {
	         return 17;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 30.0) {
	      if (lat < -6.5) {
	       return 353;
	      } else {
	       if (lng < 29.75) {
	        return 353;
	       } else {
	        return 17;
	       }
	      }
	     } else {
	      if (lat < -6.75) {
	       if (lng < 30.25) {
	        return 353;
	       } else {
	        if (lng < 30.5) {
	         if (lat < -7.0) {
	          return 353;
	         } else {
	          return 17;
	         }
	        } else {
	         return 17;
	        }
	       }
	      } else {
	       return 17;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < -8.5) {
	   if (lng < 32.25) {
	    if (lat < -9.0) {
	     return 268;
	    } else {
	     if (lng < 31.75) {
	      return 268;
	     } else {
	      if (lng < 32.0) {
	       if (lat < -8.75) {
	        return 268;
	       } else {
	        return 17;
	       }
	      } else {
	       return 17;
	      }
	     }
	    }
	   } else {
	    if (lat < -10.0) {
	     if (lng < 33.5) {
	      return 268;
	     } else {
	      if (lat < -10.75) {
	       return 207;
	      } else {
	       return 268;
	      }
	     }
	    } else {
	     if (lng < 33.0) {
	      if (lat < -9.25) {
	       return 268;
	      } else {
	       if (lng < 32.5) {
	        if (lat < -9.0) {
	         return 268;
	        } else {
	         return 17;
	        }
	       } else {
	        if (lat < -9.0) {
	         if (lng < 32.75) {
	          return 268;
	         } else {
	          return 17;
	         }
	        } else {
	         return 17;
	        }
	       }
	      }
	     } else {
	      if (lat < -9.25) {
	       if (lng < 33.25) {
	        return 268;
	       } else {
	        if (lat < -9.75) {
	         if (lng < 33.5) {
	          return 268;
	         } else {
	          return 207;
	         }
	        } else {
	         if (lng < 33.5) {
	          if (lat < -9.5) {
	           return 268;
	          } else {
	           return 207;
	          }
	         } else {
	          if (lat < -9.5) {
	           return 207;
	          } else {
	           return 17;
	          }
	         }
	        }
	       }
	      } else {
	       return 17;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 31.0) {
	    if (lat < -8.0) {
	     return 268;
	    } else {
	     return 17;
	    }
	   } else {
	    return 17;
	   }
	  }
	 }
	}

	private  int kdLookup91(double lat, double lng)
	{
	 if (lng < 30.75) {
	  if (lat < -3.0) {
	   if (lng < 29.25) {
	    return 353;
	   } else {
	    if (lat < -4.5) {
	     if (lng < 29.75) {
	      if (lat < -5.25) {
	       return 353;
	      } else {
	       if (lat < -5.0) {
	        if (lng < 29.5) {
	         return 353;
	        } else {
	         return 17;
	        }
	       } else {
	        if (lng < 29.5) {
	         return 353;
	        } else {
	         return 17;
	        }
	       }
	      }
	     } else {
	      return 17;
	     }
	    } else {
	     if (lng < 30.0) {
	      if (lat < -3.75) {
	       if (lng < 29.5) {
	        return 353;
	       } else {
	        if (lat < -4.25) {
	         return 17;
	        } else {
	         return 83;
	        }
	       }
	      } else {
	       return 83;
	      }
	     } else {
	      if (lat < -3.75) {
	       if (lng < 30.25) {
	        if (lat < -4.25) {
	         return 17;
	        } else {
	         return 83;
	        }
	       } else {
	        return 17;
	       }
	      } else {
	       if (lng < 30.5) {
	        return 83;
	       } else {
	        if (lat < -3.5) {
	         return 17;
	        } else {
	         return 83;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < -1.5) {
	    if (lng < 29.25) {
	     if (lat < -1.75) {
	      if (lng < 29.0) {
	       return 353;
	      } else {
	       if (lat < -2.5) {
	        return 353;
	       } else {
	        if (lat < -2.25) {
	         return 294;
	        } else {
	         return 353;
	        }
	       }
	      }
	     } else {
	      return 353;
	     }
	    } else {
	     if (lng < 30.0) {
	      if (lat < -2.5) {
	       if (lng < 29.5) {
	        return 83;
	       } else {
	        if (lng < 29.75) {
	         if (lat < -2.75) {
	          return 83;
	         } else {
	          return 294;
	         }
	        } else {
	         if (lat < -2.75) {
	          return 83;
	         } else {
	          return 294;
	         }
	        }
	       }
	      } else {
	       return 294;
	      }
	     } else {
	      if (lat < -2.25) {
	       if (lng < 30.5) {
	        return 83;
	       } else {
	        if (lat < -2.75) {
	         return 83;
	        } else {
	         if (lat < -2.5) {
	          return 17;
	         } else {
	          return 83;
	         }
	        }
	       }
	      } else {
	       return 294;
	      }
	     }
	    }
	   } else {
	    if (lng < 29.5) {
	     return 353;
	    } else {
	     if (lat < -0.75) {
	      if (lng < 30.0) {
	       if (lat < -1.25) {
	        return 294;
	       } else {
	        if (lng < 29.75) {
	         return 353;
	        } else {
	         return 180;
	        }
	       }
	      } else {
	       if (lng < 30.25) {
	        if (lat < -1.25) {
	         return 294;
	        } else {
	         return 180;
	        }
	       } else {
	        if (lat < -1.0) {
	         return 294;
	        } else {
	         return 180;
	        }
	       }
	      }
	     } else {
	      if (lng < 29.75) {
	       return 353;
	      } else {
	       return 180;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < -3.0) {
	   if (lng < 31.0) {
	    if (lat < -3.25) {
	     return 17;
	    } else {
	     return 83;
	    }
	   } else {
	    return 17;
	   }
	  } else {
	   if (lng < 32.25) {
	    if (lat < -1.5) {
	     if (lng < 31.0) {
	      if (lat < -2.25) {
	       if (lat < -2.75) {
	        return 83;
	       } else {
	        return 17;
	       }
	      } else {
	       return 294;
	      }
	     } else {
	      return 17;
	     }
	    } else {
	     if (lng < 31.5) {
	      if (lat < -0.75) {
	       if (lng < 31.0) {
	        if (lat < -1.0) {
	         return 17;
	        } else {
	         return 180;
	        }
	       } else {
	        return 17;
	       }
	      } else {
	       return 180;
	      }
	     } else {
	      if (lat < -0.75) {
	       return 17;
	      } else {
	       return 180;
	      }
	     }
	    }
	   } else {
	    if (lat < -0.75) {
	     return 17;
	    } else {
	     return 180;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup92(double lat, double lng)
	{
	 if (lat < -11.25) {
	  if (lng < 28.0) {
	   if (lat < -17.0) {
	    return kdLookup87(lat,lng);
	   } else {
	    if (lat < -14.25) {
	     if (lng < 22.75) {
	      if (lat < -16.75) {
	       return 300;
	      } else {
	       return 268;
	      }
	     } else {
	      return 268;
	     }
	    } else {
	     if (lng < 25.25) {
	      if (lat < -12.75) {
	       if (lng < 23.0) {
	        if (lat < -13.0) {
	         return 268;
	        } else {
	         return 300;
	        }
	       } else {
	        return 268;
	       }
	      } else {
	       if (lng < 24.0) {
	        return 300;
	       } else {
	        if (lat < -11.5) {
	         if (lng < 24.25) {
	          if (lat < -12.25) {
	           return 268;
	          } else {
	           if (lat < -12.0) {
	            return 300;
	           } else {
	            return 268;
	           }
	          }
	         } else {
	          return 268;
	         }
	        } else {
	         if (lng < 24.25) {
	          return 300;
	         } else {
	          return 268;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < -12.25) {
	       return 268;
	      } else {
	       if (lng < 26.5) {
	        if (lng < 25.75) {
	         if (lat < -11.75) {
	          return 268;
	         } else {
	          if (lng < 25.5) {
	           return 268;
	          } else {
	           return 353;
	          }
	         }
	        } else {
	         if (lat < -11.75) {
	          return 268;
	         } else {
	          return 353;
	         }
	        }
	       } else {
	        if (lng < 27.25) {
	         if (lat < -11.75) {
	          return 268;
	         } else {
	          return 353;
	         }
	        } else {
	         if (lat < -11.75) {
	          if (lng < 27.5) {
	           return 268;
	          } else {
	           if (lng < 27.75) {
	            if (lat < -12.0) {
	             return 268;
	            } else {
	             return 353;
	            }
	           } else {
	            return 353;
	           }
	          }
	         } else {
	          return 353;
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   return kdLookup89(lat,lng);
	  }
	 } else {
	  if (lng < 28.0) {
	   if (lat < -5.75) {
	    if (lng < 25.25) {
	     if (lat < -10.75) {
	      if (lng < 23.75) {
	       if (lng < 23.0) {
	        if (lng < 22.75) {
	         if (lat < -11.0) {
	          return 300;
	         } else {
	          return 353;
	         }
	        } else {
	         if (lat < -11.0) {
	          return 300;
	         } else {
	          return 353;
	         }
	        }
	       } else {
	        if (lng < 23.25) {
	         if (lat < -11.0) {
	          return 300;
	         } else {
	          return 353;
	         }
	        } else {
	         if (lng < 23.5) {
	          if (lat < -11.0) {
	           return 300;
	          } else {
	           return 353;
	          }
	         } else {
	          return 300;
	         }
	        }
	       }
	      } else {
	       if (lng < 24.5) {
	        if (lng < 24.25) {
	         return 300;
	        } else {
	         if (lat < -11.0) {
	          return 268;
	         } else {
	          return 353;
	         }
	        }
	       } else {
	        if (lng < 25.0) {
	         return 353;
	        } else {
	         if (lat < -11.0) {
	          return 268;
	         } else {
	          return 353;
	         }
	        }
	       }
	      }
	     } else {
	      return 353;
	     }
	    } else {
	     if (lat < -11.0) {
	      if (lng < 25.5) {
	       return 268;
	      } else {
	       return 353;
	      }
	     } else {
	      return 353;
	     }
	    }
	   } else {
	    if (lat < -2.0) {
	     return 353;
	    } else {
	     if (lng < 24.5) {
	      if (lng < 23.5) {
	       if (lat < -1.75) {
	        if (lng < 23.25) {
	         return 353;
	        } else {
	         return 332;
	        }
	       } else {
	        return 332;
	       }
	      } else {
	       if (lat < -1.0) {
	        if (lng < 24.0) {
	         if (lat < -1.75) {
	          if (lng < 23.75) {
	           return 332;
	          } else {
	           return 353;
	          }
	         } else {
	          return 332;
	         }
	        } else {
	         if (lat < -1.5) {
	          if (lng < 24.25) {
	           if (lat < -1.75) {
	            return 353;
	           } else {
	            return 332;
	           }
	          } else {
	           if (lat < -1.75) {
	            return 353;
	           } else {
	            return 332;
	           }
	          }
	         } else {
	          if (lng < 24.25) {
	           if (lat < -1.25) {
	            return 332;
	           } else {
	            return 353;
	           }
	          } else {
	           if (lat < -1.25) {
	            return 332;
	           } else {
	            return 353;
	           }
	          }
	         }
	        }
	       } else {
	        if (lng < 23.75) {
	         if (lat < -0.75) {
	          return 332;
	         } else {
	          if (lat < -0.5) {
	           return 353;
	          } else {
	           if (lat < -0.25) {
	            return 332;
	           } else {
	            return 353;
	           }
	          }
	         }
	        } else {
	         return 353;
	        }
	       }
	      }
	     } else {
	      return 353;
	     }
	    }
	   }
	  } else {
	   if (lat < -5.75) {
	    return kdLookup90(lat,lng);
	   } else {
	    return kdLookup91(lat,lng);
	   }
	  }
	 }
	}

	private  int kdLookup93(double lat, double lng)
	{
	 if (lat < -14.25) {
	  if (lng < 36.0) {
	   if (lat < -15.75) {
	    if (lng < 34.75) {
	     if (lat < -16.25) {
	      return 365;
	     } else {
	      if (lng < 34.5) {
	       return 365;
	      } else {
	       return 207;
	      }
	     }
	    } else {
	     if (lng < 35.25) {
	      if (lat < -16.5) {
	       if (lng < 35.0) {
	        return 365;
	       } else {
	        if (lat < -16.75) {
	         return 365;
	        } else {
	         return 207;
	        }
	       }
	      } else {
	       return 207;
	      }
	     } else {
	      if (lat < -16.5) {
	       if (lng < 35.5) {
	        return 207;
	       } else {
	        return 365;
	       }
	      } else {
	       if (lng < 35.5) {
	        if (lat < -16.25) {
	         return 365;
	        } else {
	         return 207;
	        }
	       } else {
	        if (lat < -16.0) {
	         return 365;
	        } else {
	         return 207;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 34.75) {
	     if (lat < -15.0) {
	      if (lng < 34.5) {
	       return 365;
	      } else {
	       if (lat < -15.25) {
	        return 207;
	       } else {
	        return 365;
	       }
	      }
	     } else {
	      if (lng < 34.25) {
	       if (lat < -14.5) {
	        return 365;
	       } else {
	        if (lng < 34.0) {
	         return 207;
	        } else {
	         return 365;
	        }
	       }
	      } else {
	       if (lat < -14.5) {
	        return 365;
	       } else {
	        if (lng < 34.5) {
	         return 365;
	        } else {
	         return 207;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < -14.5) {
	      return 207;
	     } else {
	      if (lng < 35.75) {
	       return 207;
	      } else {
	       return 365;
	      }
	     }
	    }
	   }
	  } else {
	   return 365;
	  }
	 } else {
	  if (lng < 36.5) {
	   if (lat < -12.75) {
	    if (lng < 35.0) {
	     if (lat < -13.25) {
	      return 207;
	     } else {
	      if (lng < 34.75) {
	       return 207;
	      } else {
	       return 365;
	      }
	     }
	    } else {
	     if (lng < 35.75) {
	      if (lat < -13.5) {
	       if (lng < 35.25) {
	        return 207;
	       } else {
	        if (lat < -14.0) {
	         return 207;
	        } else {
	         if (lng < 35.5) {
	          if (lat < -13.75) {
	           return 207;
	          } else {
	           return 365;
	          }
	         } else {
	          return 365;
	         }
	        }
	       }
	      } else {
	       return 365;
	      }
	     } else {
	      return 365;
	     }
	    }
	   } else {
	    if (lng < 35.0) {
	     if (lat < -12.0) {
	      if (lng < 34.5) {
	       return 207;
	      } else {
	       if (lat < -12.5) {
	        if (lng < 34.75) {
	         return 207;
	        } else {
	         return 365;
	        }
	       } else {
	        return 365;
	       }
	      }
	     } else {
	      if (lng < 34.5) {
	       return 207;
	      } else {
	       if (lat < -11.75) {
	        return 365;
	       } else {
	        if (lng < 34.75) {
	         return 207;
	        } else {
	         if (lat < -11.5) {
	          return 365;
	         } else {
	          return 207;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 35.75) {
	      if (lat < -11.5) {
	       return 365;
	      } else {
	       return 17;
	      }
	     } else {
	      if (lat < -11.5) {
	       return 365;
	      } else {
	       if (lng < 36.25) {
	        return 365;
	       } else {
	        return 17;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < -11.5) {
	    return 365;
	   } else {
	    if (lng < 38.0) {
	     return 17;
	    } else {
	     return 365;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup94(double lat, double lng)
	{
	 if (lat < -5.75) {
	  if (lng < 36.5) {
	   if (lat < -9.5) {
	    if (lng < 35.0) {
	     if (lat < -10.5) {
	      if (lng < 34.75) {
	       return 207;
	      } else {
	       if (lat < -11.0) {
	        return 207;
	       } else {
	        return 17;
	       }
	      }
	     } else {
	      if (lng < 34.5) {
	       return 207;
	      } else {
	       if (lat < -10.0) {
	        if (lng < 34.75) {
	         return 207;
	        } else {
	         return 17;
	        }
	       } else {
	        if (lng < 34.75) {
	         if (lat < -9.75) {
	          return 207;
	         } else {
	          return 17;
	         }
	        } else {
	         return 17;
	        }
	       }
	      }
	     }
	    } else {
	     return 17;
	    }
	   } else {
	    return 17;
	   }
	  } else {
	   if (lat < -11.0) {
	    if (lng < 39.0) {
	     return 17;
	    } else {
	     return 365;
	    }
	   } else {
	    return 17;
	   }
	  }
	 } else {
	  if (lat < -3.0) {
	   if (lng < 37.75) {
	    return 17;
	   } else {
	    if (lat < -4.5) {
	     return 17;
	    } else {
	     if (lng < 38.5) {
	      if (lat < -3.75) {
	       if (lng < 38.25) {
	        return 17;
	       } else {
	        if (lat < -4.0) {
	         return 17;
	        } else {
	         return 123;
	        }
	       }
	      } else {
	       if (lng < 38.0) {
	        if (lat < -3.5) {
	         return 17;
	        } else {
	         return 123;
	        }
	       } else {
	        return 123;
	       }
	      }
	     } else {
	      if (lat < -4.0) {
	       if (lng < 38.75) {
	        return 17;
	       } else {
	        if (lng < 39.0) {
	         if (lat < -4.25) {
	          return 17;
	         } else {
	          return 123;
	         }
	        } else {
	         return 123;
	        }
	       }
	      } else {
	       return 123;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 36.5) {
	    if (lat < -1.5) {
	     if (lng < 35.5) {
	      return 17;
	     } else {
	      if (lat < -2.0) {
	       return 17;
	      } else {
	       if (lng < 36.0) {
	        if (lng < 35.75) {
	         if (lat < -1.75) {
	          return 17;
	         } else {
	          return 123;
	         }
	        } else {
	         if (lat < -1.75) {
	          return 17;
	         } else {
	          return 123;
	         }
	        }
	       } else {
	        return 123;
	       }
	      }
	     }
	    } else {
	     if (lng < 35.0) {
	      if (lat < -0.75) {
	       if (lng < 34.25) {
	        return 17;
	       } else {
	        if (lng < 34.5) {
	         if (lat < -1.0) {
	          return 17;
	         } else {
	          return 123;
	         }
	        } else {
	         if (lat < -1.25) {
	          return 17;
	         } else {
	          if (lng < 34.75) {
	           if (lat < -1.0) {
	            return 17;
	           } else {
	            return 123;
	           }
	          } else {
	           return 123;
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < 34.0) {
	        return 180;
	       } else {
	        return 123;
	       }
	      }
	     } else {
	      return 123;
	     }
	    }
	   } else {
	    if (lat < -2.25) {
	     if (lng < 37.75) {
	      if (lng < 37.0) {
	       if (lat < -2.5) {
	        return 17;
	       } else {
	        if (lng < 36.75) {
	         return 17;
	        } else {
	         return 123;
	        }
	       }
	      } else {
	       if (lng < 37.25) {
	        if (lat < -2.5) {
	         return 17;
	        } else {
	         return 123;
	        }
	       } else {
	        if (lat < -2.75) {
	         return 17;
	        } else {
	         return 123;
	        }
	       }
	      }
	     } else {
	      return 123;
	     }
	    } else {
	     return 123;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup95(double lat, double lng)
	{
	 if (lng < 45.0) {
	  if (lat < -45.0) {
	   return 122;
	  } else {
	   if (lng < 22.5) {
	    return kdLookup84(lat,lng);
	   } else {
	    if (lat < -22.5) {
	     if (lng < 33.75) {
	      if (lat < -33.75) {
	       return 170;
	      } else {
	       if (lng < 28.0) {
	        return kdLookup85(lat,lng);
	       } else {
	        return kdLookup86(lat,lng);
	       }
	      }
	     } else {
	      if (lat < -33.75) {
	       return 0;
	      } else {
	       if (lng < 39.25) {
	        return 365;
	       } else {
	        return 101;
	       }
	      }
	     }
	    } else {
	     if (lng < 33.75) {
	      return kdLookup92(lat,lng);
	     } else {
	      if (lat < -11.25) {
	       if (lng < 39.25) {
	        if (lat < -17.0) {
	         return 365;
	        } else {
	         return kdLookup93(lat,lng);
	        }
	       } else {
	        if (lat < -17.0) {
	         return 101;
	        } else {
	         if (lng < 42.0) {
	          return 365;
	         } else {
	          if (lat < -14.25) {
	           return 101;
	          } else {
	           return 219;
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < 39.25) {
	        return kdLookup94(lat,lng);
	       } else {
	        if (lat < -5.75) {
	         if (lng < 42.0) {
	          if (lat < -8.5) {
	           if (lng < 40.5) {
	            if (lat < -10.0) {
	             if (lng < 39.75) {
	              if (lat < -11.0) {
	               return 365;
	              } else {
	               return 17;
	              }
	             } else {
	              if (lat < -10.75) {
	               return 365;
	              } else {
	               if (lng < 40.25) {
	                return 17;
	               } else {
	                if (lat < -10.5) {
	                 return 365;
	                } else {
	                 return 17;
	                }
	               }
	              }
	             }
	            } else {
	             return 17;
	            }
	           } else {
	            return 365;
	           }
	          } else {
	           return 17;
	          }
	         } else {
	          return 0;
	         }
	        } else {
	         if (lng < 42.0) {
	          if (lat < -3.0) {
	           if (lng < 40.5) {
	            if (lat < -4.5) {
	             return 17;
	            } else {
	             return 123;
	            }
	           } else {
	            return 0;
	           }
	          } else {
	           if (lat < -1.5) {
	            return 123;
	           } else {
	            if (lng < 41.0) {
	             return 123;
	            } else {
	             if (lat < -0.75) {
	              if (lng < 41.5) {
	               if (lat < -1.0) {
	                return 123;
	               } else {
	                if (lng < 41.25) {
	                 return 123;
	                } else {
	                 return 251;
	                }
	               }
	              } else {
	               if (lat < -1.25) {
	                return 123;
	               } else {
	                return 251;
	               }
	              }
	             } else {
	              if (lng < 41.25) {
	               if (lat < -0.5) {
	                return 251;
	               } else {
	                return 123;
	               }
	              } else {
	               return 251;
	              }
	             }
	            }
	           }
	          }
	         } else {
	          return 251;
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < -45.0) {
	   if (lng < 67.5) {
	    return 122;
	   } else {
	    if (lat < -67.5) {
	     return 122;
	    } else {
	     if (lng < 78.75) {
	      if (lat < -56.25) {
	       return 122;
	      } else {
	       return 237;
	      }
	     } else {
	      return 122;
	     }
	    }
	   }
	  } else {
	   if (lng < 67.5) {
	    if (lat < -22.5) {
	     return 101;
	    } else {
	     if (lng < 56.25) {
	      if (lat < -11.25) {
	       if (lng < 50.5) {
	        return 101;
	       } else {
	        if (lat < -17.0) {
	         return 275;
	        } else {
	         return 101;
	        }
	       }
	      } else {
	       return 66;
	      }
	     } else {
	      return 234;
	     }
	    }
	   } else {
	    return 0;
	   }
	  }
	 }
	}

	private  int kdLookup96(double lat, double lng)
	{
	 if (lat < 8.25) {
	  if (lng < 2.75) {
	   if (lng < 1.25) {
	    if (lat < 6.75) {
	     if (lng < 0.75) {
	      return 70;
	     } else {
	      if (lat < 6.0) {
	       return 70;
	      } else {
	       if (lat < 6.5) {
	        return 70;
	       } else {
	        return 273;
	       }
	      }
	     }
	    } else {
	     if (lat < 7.5) {
	      if (lng < 0.75) {
	       return 70;
	      } else {
	       return 273;
	      }
	     } else {
	      if (lng < 0.75) {
	       return 70;
	      } else {
	       return 273;
	      }
	     }
	    }
	   } else {
	    if (lat < 6.75) {
	     if (lng < 1.75) {
	      return 273;
	     } else {
	      return 84;
	     }
	    } else {
	     if (lng < 1.75) {
	      return 273;
	     } else {
	      return 84;
	     }
	    }
	   }
	  } else {
	   if (lng < 3.0) {
	    if (lat < 6.25) {
	     return 0;
	    } else {
	     if (lat < 7.25) {
	      if (lat < 6.75) {
	       return 96;
	      } else {
	       if (lat < 7.0) {
	        return 84;
	       } else {
	        return 96;
	       }
	      }
	     } else {
	      if (lat < 7.75) {
	       return 84;
	      } else {
	       return 96;
	      }
	     }
	    }
	   } else {
	    return 96;
	   }
	  }
	 } else {
	  if (lng < 2.75) {
	   if (lat < 9.75) {
	    if (lng < 1.25) {
	     if (lat < 9.0) {
	      if (lng < 0.5) {
	       return 70;
	      } else {
	       if (lng < 0.75) {
	        if (lat < 8.75) {
	         return 70;
	        } else {
	         return 273;
	        }
	       } else {
	        return 273;
	       }
	      }
	     } else {
	      if (lng < 0.5) {
	       return 70;
	      } else {
	       if (lng < 0.75) {
	        if (lat < 9.25) {
	         return 273;
	        } else {
	         if (lat < 9.5) {
	          return 70;
	         } else {
	          return 273;
	         }
	        }
	       } else {
	        return 273;
	       }
	      }
	     }
	    } else {
	     if (lng < 1.75) {
	      if (lat < 9.25) {
	       return 273;
	      } else {
	       if (lng < 1.5) {
	        return 273;
	       } else {
	        return 84;
	       }
	      }
	     } else {
	      return 84;
	     }
	    }
	   } else {
	    if (lng < 1.25) {
	     if (lat < 10.5) {
	      if (lng < 0.5) {
	       return 70;
	      } else {
	       if (lng < 1.0) {
	        return 273;
	       } else {
	        if (lat < 10.25) {
	         return 273;
	        } else {
	         return 84;
	        }
	       }
	      }
	     } else {
	      if (lng < 0.5) {
	       if (lat < 10.75) {
	        if (lng < 0.25) {
	         return 70;
	        } else {
	         return 273;
	        }
	       } else {
	        if (lng < 0.25) {
	         if (lat < 11.0) {
	          return 273;
	         } else {
	          return 70;
	         }
	        } else {
	         return 273;
	        }
	       }
	      } else {
	       if (lng < 0.75) {
	        if (lat < 11.0) {
	         return 273;
	        } else {
	         return 263;
	        }
	       } else {
	        if (lat < 10.75) {
	         if (lng < 1.0) {
	          return 273;
	         } else {
	          return 84;
	         }
	        } else {
	         if (lng < 1.0) {
	          if (lat < 11.0) {
	           return 273;
	          } else {
	           return 263;
	          }
	         } else {
	          return 84;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 1.5) {
	      if (lat < 10.25) {
	       return 273;
	      } else {
	       return 84;
	      }
	     } else {
	      return 84;
	     }
	    }
	   }
	  } else {
	   if (lat < 9.75) {
	    if (lng < 3.25) {
	     if (lat < 9.0) {
	      if (lat < 8.5) {
	       return 96;
	      } else {
	       if (lng < 3.0) {
	        if (lat < 8.75) {
	         return 84;
	        } else {
	         return 96;
	        }
	       } else {
	        return 96;
	       }
	      }
	     } else {
	      if (lat < 9.25) {
	       if (lng < 3.0) {
	        return 84;
	       } else {
	        return 96;
	       }
	      } else {
	       return 84;
	      }
	     }
	    } else {
	     return 96;
	    }
	   } else {
	    if (lng < 4.0) {
	     if (lat < 10.5) {
	      if (lng < 3.5) {
	       return 84;
	      } else {
	       if (lat < 10.0) {
	        return 96;
	       } else {
	        if (lng < 3.75) {
	         return 84;
	        } else {
	         return 96;
	        }
	       }
	      }
	     } else {
	      return 84;
	     }
	    } else {
	     return 96;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup97(double lat, double lng)
	{
	 if (lng < 2.75) {
	  if (lat < 14.0) {
	   if (lng < 1.25) {
	    if (lat < 13.25) {
	     return 263;
	    } else {
	     if (lng < 0.75) {
	      return 263;
	     } else {
	      if (lat < 13.5) {
	       if (lng < 1.0) {
	        return 263;
	       } else {
	        return 261;
	       }
	      } else {
	       if (lng < 1.0) {
	        if (lat < 13.75) {
	         return 263;
	        } else {
	         return 261;
	        }
	       } else {
	        if (lat < 13.75) {
	         return 263;
	        } else {
	         return 261;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 12.5) {
	     if (lng < 2.0) {
	      if (lat < 11.5) {
	       if (lng < 1.5) {
	        return 263;
	       } else {
	        return 84;
	       }
	      } else {
	       return 263;
	      }
	     } else {
	      if (lat < 11.75) {
	       if (lng < 2.25) {
	        if (lat < 11.5) {
	         return 84;
	        } else {
	         return 263;
	        }
	       } else {
	        return 84;
	       }
	      } else {
	       if (lng < 2.25) {
	        return 263;
	       } else {
	        if (lat < 12.0) {
	         if (lng < 2.5) {
	          return 263;
	         } else {
	          return 84;
	         }
	        } else {
	         if (lng < 2.5) {
	          if (lat < 12.25) {
	           return 263;
	          } else {
	           return 261;
	          }
	         } else {
	          return 84;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 2.0) {
	      if (lat < 13.0) {
	       if (lng < 1.5) {
	        return 263;
	       } else {
	        if (lng < 1.75) {
	         if (lat < 12.75) {
	          return 263;
	         } else {
	          return 261;
	         }
	        } else {
	         if (lat < 12.75) {
	          return 263;
	         } else {
	          return 261;
	         }
	        }
	       }
	      } else {
	       return 261;
	      }
	     } else {
	      if (lat < 12.75) {
	       if (lng < 2.25) {
	        return 263;
	       } else {
	        return 261;
	       }
	      } else {
	       return 261;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 1.25) {
	    if (lat < 15.25) {
	     if (lng < 0.5) {
	      if (lat < 14.5) {
	       return 263;
	      } else {
	       if (lat < 14.75) {
	        if (lng < 0.25) {
	         return 263;
	        } else {
	         return 261;
	        }
	       } else {
	        if (lng < 0.25) {
	         if (lat < 15.0) {
	          return 263;
	         } else {
	          return 182;
	         }
	        } else {
	         if (lat < 15.0) {
	          return 261;
	         } else {
	          return 182;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 15.0) {
	       return 261;
	      } else {
	       if (lng < 1.0) {
	        return 182;
	       } else {
	        return 261;
	       }
	      }
	     }
	    } else {
	     return 182;
	    }
	   } else {
	    if (lat < 15.25) {
	     return 261;
	    } else {
	     if (lng < 2.0) {
	      if (lat < 15.5) {
	       if (lng < 1.5) {
	        return 182;
	       } else {
	        return 261;
	       }
	      } else {
	       return 182;
	      }
	     } else {
	      if (lat < 15.5) {
	       return 261;
	      } else {
	       return 182;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 14.0) {
	   if (lng < 4.0) {
	    if (lat < 12.5) {
	     if (lng < 3.25) {
	      return 84;
	     } else {
	      if (lat < 11.75) {
	       if (lng < 3.75) {
	        return 84;
	       } else {
	        return 96;
	       }
	      } else {
	       if (lng < 3.5) {
	        if (lat < 12.25) {
	         return 84;
	        } else {
	         return 261;
	        }
	       } else {
	        if (lat < 12.0) {
	         if (lng < 3.75) {
	          return 84;
	         } else {
	          return 96;
	         }
	        } else {
	         if (lng < 3.75) {
	          return 261;
	         } else {
	          return 96;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 12.75) {
	      if (lng < 3.75) {
	       return 261;
	      } else {
	       return 96;
	      }
	     } else {
	      return 261;
	     }
	    }
	   } else {
	    if (lat < 13.0) {
	     return 96;
	    } else {
	     if (lng < 4.75) {
	      if (lat < 13.5) {
	       if (lng < 4.25) {
	        return 261;
	       } else {
	        return 96;
	       }
	      } else {
	       if (lng < 4.25) {
	        return 261;
	       } else {
	        if (lng < 4.5) {
	         if (lat < 13.75) {
	          return 96;
	         } else {
	          return 261;
	         }
	        } else {
	         if (lat < 13.75) {
	          return 96;
	         } else {
	          return 261;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 13.75) {
	       return 96;
	      } else {
	       if (lng < 5.0) {
	        return 96;
	       } else {
	        return 261;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 4.0) {
	    if (lat < 15.5) {
	     return 261;
	    } else {
	     if (lng < 3.75) {
	      return 182;
	     } else {
	      if (lat < 15.75) {
	       return 261;
	      } else {
	       return 182;
	      }
	     }
	    }
	   } else {
	    if (lat < 16.0) {
	     return 261;
	    } else {
	     if (lng < 4.25) {
	      return 182;
	     } else {
	      return 261;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup98(double lat, double lng)
	{
	 if (lat < 16.75) {
	  if (lng < 8.25) {
	   if (lat < 14.0) {
	    if (lng < 6.75) {
	     if (lat < 13.75) {
	      return 96;
	     } else {
	      if (lng < 6.0) {
	       return 96;
	      } else {
	       return 261;
	      }
	     }
	    } else {
	     if (lat < 13.0) {
	      return 96;
	     } else {
	      if (lng < 7.5) {
	       if (lat < 13.25) {
	        if (lng < 7.0) {
	         return 96;
	        } else {
	         if (lng < 7.25) {
	          return 261;
	         } else {
	          return 96;
	         }
	        }
	       } else {
	        return 261;
	       }
	      } else {
	       if (lat < 13.5) {
	        if (lng < 7.75) {
	         if (lat < 13.25) {
	          return 96;
	         } else {
	          return 261;
	         }
	        } else {
	         return 96;
	        }
	       } else {
	        return 261;
	       }
	      }
	     }
	    }
	   } else {
	    return 261;
	   }
	  } else {
	   if (lat < 13.5) {
	    if (lng < 9.75) {
	     if (lat < 13.0) {
	      return 96;
	     } else {
	      if (lng < 8.75) {
	       if (lng < 8.5) {
	        if (lat < 13.25) {
	         return 96;
	        } else {
	         return 261;
	        }
	       } else {
	        if (lat < 13.25) {
	         return 96;
	        } else {
	         return 261;
	        }
	       }
	      } else {
	       return 261;
	      }
	     }
	    } else {
	     if (lat < 13.0) {
	      return 96;
	     } else {
	      if (lng < 10.25) {
	       if (lng < 10.0) {
	        return 261;
	       } else {
	        if (lat < 13.25) {
	         return 96;
	        } else {
	         return 261;
	        }
	       }
	      } else {
	       return 96;
	      }
	     }
	    }
	   } else {
	    return 261;
	   }
	  }
	 } else {
	  if (lng < 8.25) {
	   if (lat < 19.5) {
	    return 261;
	   } else {
	    if (lat < 21.0) {
	     if (lng < 6.75) {
	      if (lat < 20.25) {
	       if (lng < 6.0) {
	        return 381;
	       } else {
	        if (lng < 6.25) {
	         if (lat < 19.75) {
	          return 261;
	         } else {
	          return 381;
	         }
	        } else {
	         if (lat < 20.0) {
	          return 261;
	         } else {
	          if (lng < 6.5) {
	           return 381;
	          } else {
	           return 261;
	          }
	         }
	        }
	       }
	      } else {
	       return 381;
	      }
	     } else {
	      if (lng < 7.5) {
	       if (lat < 20.5) {
	        return 261;
	       } else {
	        if (lng < 7.25) {
	         return 381;
	        } else {
	         if (lat < 20.75) {
	          return 261;
	         } else {
	          return 381;
	         }
	        }
	       }
	      } else {
	       return 261;
	      }
	     }
	    } else {
	     if (lng < 7.75) {
	      return 381;
	     } else {
	      if (lat < 21.25) {
	       return 261;
	      } else {
	       return 381;
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 21.5) {
	    return 261;
	   } else {
	    if (lng < 9.75) {
	     if (lng < 9.0) {
	      if (lat < 21.75) {
	       if (lng < 8.75) {
	        return 381;
	       } else {
	        return 261;
	       }
	      } else {
	       return 381;
	      }
	     } else {
	      if (lat < 22.0) {
	       return 261;
	      } else {
	       if (lng < 9.5) {
	        return 381;
	       } else {
	        if (lat < 22.25) {
	         return 261;
	        } else {
	         return 381;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 10.0) {
	      if (lat < 22.25) {
	       return 261;
	      } else {
	       return 381;
	      }
	     } else {
	      return 261;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup99(double lat, double lng)
	{
	 if (lat < 11.25) {
	  if (lng < 5.5) {
	   if (lat < 5.5) {
	    return 96;
	   } else {
	    return kdLookup96(lat,lng);
	   }
	  } else {
	   if (lat < 5.5) {
	    if (lng < 8.5) {
	     if (lat < 2.75) {
	      return 162;
	     } else {
	      return 96;
	     }
	    } else {
	     if (lat < 2.75) {
	      if (lng < 9.75) {
	       if (lat < 1.25) {
	        return 156;
	       } else {
	        return 202;
	       }
	      } else {
	       if (lat < 1.25) {
	        if (lng < 10.5) {
	         if (lat < 1.0) {
	          return 156;
	         } else {
	          if (lng < 10.0) {
	           return 156;
	          } else {
	           return 202;
	          }
	         }
	        } else {
	         if (lat < 1.0) {
	          return 156;
	         } else {
	          return 202;
	         }
	        }
	       } else {
	        if (lng < 10.5) {
	         if (lat < 2.25) {
	          return 202;
	         } else {
	          return 327;
	         }
	        } else {
	         if (lat < 2.25) {
	          return 202;
	         } else {
	          return 327;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < 9.25) {
	       if (lat < 4.0) {
	        return 202;
	       } else {
	        if (lat < 4.75) {
	         if (lng < 8.75) {
	          return 96;
	         } else {
	          if (lat < 4.25) {
	           if (lng < 9.0) {
	            return 202;
	           } else {
	            return 327;
	           }
	          } else {
	           return 327;
	          }
	         }
	        } else {
	         if (lng < 8.75) {
	          return 96;
	         } else {
	          if (lat < 5.25) {
	           return 327;
	          } else {
	           if (lng < 9.0) {
	            return 96;
	           } else {
	            return 327;
	           }
	          }
	         }
	        }
	       }
	      } else {
	       return 327;
	      }
	     }
	    }
	   } else {
	    if (lng < 9.0) {
	     return 96;
	    } else {
	     if (lat < 7.0) {
	      if (lng < 10.0) {
	       if (lat < 6.25) {
	        if (lng < 9.25) {
	         if (lat < 6.0) {
	          return 327;
	         } else {
	          return 96;
	         }
	        } else {
	         return 327;
	        }
	       } else {
	        if (lng < 9.5) {
	         return 96;
	        } else {
	         if (lat < 6.5) {
	          return 327;
	         } else {
	          if (lng < 9.75) {
	           return 96;
	          } else {
	           if (lat < 6.75) {
	            return 327;
	           } else {
	            return 96;
	           }
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < 6.75) {
	        return 327;
	       } else {
	        if (lng < 11.0) {
	         return 327;
	        } else {
	         return 96;
	        }
	       }
	      }
	     } else {
	      return 96;
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 5.5) {
	   if (lat < 16.75) {
	    return kdLookup97(lat,lng);
	   } else {
	    if (lat < 19.5) {
	     if (lng < 3.25) {
	      return 182;
	     } else {
	      if (lat < 18.0) {
	       if (lng < 4.25) {
	        return 182;
	       } else {
	        return 261;
	       }
	      } else {
	       if (lng < 4.25) {
	        if (lat < 19.0) {
	         return 182;
	        } else {
	         if (lng < 3.75) {
	          if (lng < 3.5) {
	           if (lat < 19.25) {
	            return 182;
	           } else {
	            return 381;
	           }
	          } else {
	           return 381;
	          }
	         } else {
	          if (lng < 4.0) {
	           if (lat < 19.25) {
	            return 182;
	           } else {
	            return 381;
	           }
	          } else {
	           if (lat < 19.25) {
	            return 182;
	           } else {
	            return 381;
	           }
	          }
	         }
	        }
	       } else {
	        if (lat < 19.25) {
	         return 261;
	        } else {
	         if (lng < 5.0) {
	          return 381;
	         } else {
	          return 261;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 2.75) {
	      if (lat < 21.0) {
	       if (lng < 1.25) {
	        return 182;
	       } else {
	        if (lng < 2.0) {
	         if (lat < 20.5) {
	          return 182;
	         } else {
	          if (lng < 1.5) {
	           if (lat < 20.75) {
	            return 182;
	           } else {
	            return 381;
	           }
	          } else {
	           if (lng < 1.75) {
	            if (lat < 20.75) {
	             return 182;
	            } else {
	             return 381;
	            }
	           } else {
	            return 381;
	           }
	          }
	         }
	        } else {
	         if (lat < 20.25) {
	          return 182;
	         } else {
	          if (lng < 2.25) {
	           return 381;
	          } else {
	           if (lat < 20.5) {
	            if (lng < 2.5) {
	             return 182;
	            } else {
	             return 381;
	            }
	           } else {
	            return 381;
	           }
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < 1.25) {
	        if (lat < 21.75) {
	         if (lng < 0.75) {
	          return 182;
	         } else {
	          if (lat < 21.25) {
	           return 182;
	          } else {
	           if (lng < 1.0) {
	            if (lat < 21.5) {
	             return 182;
	            } else {
	             return 381;
	            }
	           } else {
	            return 381;
	           }
	          }
	         }
	        } else {
	         if (lng < 0.25) {
	          if (lat < 22.0) {
	           return 182;
	          } else {
	           return 381;
	          }
	         } else {
	          return 381;
	         }
	        }
	       } else {
	        return 381;
	       }
	      }
	     } else {
	      if (lat < 20.0) {
	       if (lng < 3.25) {
	        return 182;
	       } else {
	        return 381;
	       }
	      } else {
	       return 381;
	      }
	     }
	    }
	   }
	  } else {
	   return kdLookup98(lat,lng);
	  }
	 }
	}

	private  int kdLookup100(double lat, double lng)
	{
	 if (lng < 14.0) {
	  if (lat < 2.5) {
	   if (lng < 12.5) {
	    if (lat < 1.25) {
	     if (lng < 11.5) {
	      if (lat < 1.0) {
	       return 156;
	      } else {
	       return 202;
	      }
	     } else {
	      return 156;
	     }
	    } else {
	     if (lng < 11.5) {
	      if (lat < 2.25) {
	       return 202;
	      } else {
	       return 327;
	      }
	     } else {
	      return 156;
	     }
	    }
	   } else {
	    if (lat < 1.25) {
	     return 156;
	    } else {
	     if (lng < 13.25) {
	      if (lat < 2.25) {
	       return 156;
	      } else {
	       if (lng < 12.75) {
	        return 156;
	       } else {
	        return 327;
	       }
	      }
	     } else {
	      if (lat < 1.75) {
	       if (lng < 13.5) {
	        return 155;
	       } else {
	        if (lng < 13.75) {
	         if (lat < 1.5) {
	          return 156;
	         } else {
	          return 155;
	         }
	        } else {
	         if (lat < 1.5) {
	          return 156;
	         } else {
	          return 155;
	         }
	        }
	       }
	      } else {
	       if (lng < 13.5) {
	        if (lat < 2.25) {
	         return 155;
	        } else {
	         return 156;
	        }
	       } else {
	        if (lat < 2.25) {
	         return 155;
	        } else {
	         return 327;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   return 327;
	  }
	 } else {
	  if (lat < 2.75) {
	   if (lng < 15.25) {
	    if (lat < 1.25) {
	     if (lng < 14.5) {
	      if (lat < 0.5) {
	       return 155;
	      } else {
	       if (lat < 0.75) {
	        if (lng < 14.25) {
	         return 156;
	        } else {
	         return 155;
	        }
	       } else {
	        return 156;
	       }
	      }
	     } else {
	      return 155;
	     }
	    } else {
	     if (lat < 2.0) {
	      if (lng < 14.5) {
	       if (lat < 1.5) {
	        return 156;
	       } else {
	        return 155;
	       }
	      } else {
	       return 155;
	      }
	     } else {
	      if (lng < 14.5) {
	       if (lat < 2.25) {
	        return 155;
	       } else {
	        return 327;
	       }
	      } else {
	       if (lng < 14.75) {
	        if (lat < 2.25) {
	         return 155;
	        } else {
	         return 327;
	        }
	       } else {
	        if (lat < 2.25) {
	         return 155;
	        } else {
	         return 327;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 2.0) {
	     return 155;
	    } else {
	     if (lng < 16.0) {
	      if (lng < 15.5) {
	       if (lat < 2.25) {
	        return 155;
	       } else {
	        return 327;
	       }
	      } else {
	       return 327;
	      }
	     } else {
	      if (lng < 16.25) {
	       return 327;
	      } else {
	       if (lat < 2.5) {
	        return 155;
	       } else {
	        if (lng < 16.5) {
	         return 215;
	        } else {
	         return 155;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 15.25) {
	    if (lat < 4.5) {
	     return 327;
	    } else {
	     if (lng < 14.75) {
	      return 327;
	     } else {
	      if (lat < 4.75) {
	       if (lng < 15.0) {
	        return 327;
	       } else {
	        return 215;
	       }
	      } else {
	       return 215;
	      }
	     }
	    }
	   } else {
	    if (lat < 3.75) {
	     if (lng < 16.0) {
	      if (lat < 3.25) {
	       return 327;
	      } else {
	       if (lng < 15.5) {
	        return 327;
	       } else {
	        if (lng < 15.75) {
	         if (lat < 3.5) {
	          return 327;
	         } else {
	          return 215;
	         }
	        } else {
	         return 215;
	        }
	       }
	      }
	     } else {
	      if (lat < 3.25) {
	       if (lng < 16.25) {
	        return 327;
	       } else {
	        if (lng < 16.5) {
	         return 215;
	        } else {
	         return 155;
	        }
	       }
	      } else {
	       return 215;
	      }
	     }
	    } else {
	     return 215;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup101(double lat, double lng)
	{
	 if (lat < 8.25) {
	  if (lng < 14.0) {
	   if (lng < 12.25) {
	    if (lat < 6.75) {
	     return 327;
	    } else {
	     if (lat < 7.5) {
	      if (lng < 11.75) {
	       return 96;
	      } else {
	       if (lat < 7.25) {
	        return 327;
	       } else {
	        if (lng < 12.0) {
	         return 96;
	        } else {
	         return 327;
	        }
	       }
	      }
	     } else {
	      if (lng < 12.0) {
	       return 96;
	      } else {
	       if (lat < 7.75) {
	        return 327;
	       } else {
	        return 96;
	       }
	      }
	     }
	    }
	   } else {
	    return 327;
	   }
	  } else {
	   if (lng < 15.25) {
	    if (lat < 6.75) {
	     if (lng < 14.5) {
	      return 327;
	     } else {
	      if (lat < 6.0) {
	       if (lng < 14.75) {
	        return 327;
	       } else {
	        return 215;
	       }
	      } else {
	       if (lng < 14.75) {
	        if (lat < 6.25) {
	         return 215;
	        } else {
	         return 327;
	        }
	       } else {
	        if (lat < 6.5) {
	         return 215;
	        } else {
	         if (lng < 15.0) {
	          return 327;
	         } else {
	          return 215;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 7.0) {
	      if (lng < 15.0) {
	       return 327;
	      } else {
	       return 215;
	      }
	     } else {
	      return 327;
	     }
	    }
	   } else {
	    if (lat < 7.5) {
	     return 215;
	    } else {
	     if (lng < 16.0) {
	      if (lng < 15.5) {
	       return 327;
	      } else {
	       if (lat < 7.75) {
	        if (lng < 15.75) {
	         return 215;
	        } else {
	         return 203;
	        }
	       } else {
	        if (lng < 15.75) {
	         if (lat < 8.0) {
	          return 327;
	         } else {
	          return 203;
	         }
	        } else {
	         return 203;
	        }
	       }
	      }
	     } else {
	      if (lng < 16.25) {
	       return 203;
	      } else {
	       if (lat < 7.75) {
	        return 215;
	       } else {
	        if (lng < 16.5) {
	         return 203;
	        } else {
	         if (lat < 8.0) {
	          return 215;
	         } else {
	          return 203;
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 14.0) {
	   if (lat < 9.75) {
	    if (lng < 12.5) {
	     return 96;
	    } else {
	     if (lng < 13.25) {
	      if (lat < 9.0) {
	       if (lng < 12.75) {
	        if (lat < 8.75) {
	         return 327;
	        } else {
	         return 96;
	        }
	       } else {
	        return 327;
	       }
	      } else {
	       if (lng < 13.0) {
	        return 96;
	       } else {
	        if (lat < 9.5) {
	         return 327;
	        } else {
	         return 96;
	        }
	       }
	      }
	     } else {
	      return 327;
	     }
	    }
	   } else {
	    if (lng < 13.5) {
	     return 96;
	    } else {
	     if (lat < 10.5) {
	      return 327;
	     } else {
	      if (lat < 10.75) {
	       if (lng < 13.75) {
	        return 96;
	       } else {
	        return 327;
	       }
	      } else {
	       if (lng < 13.75) {
	        return 96;
	       } else {
	        if (lat < 11.0) {
	         return 327;
	        } else {
	         return 96;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 9.75) {
	    if (lng < 15.25) {
	     if (lat < 9.0) {
	      if (lng < 15.0) {
	       return 327;
	      } else {
	       if (lat < 8.75) {
	        return 327;
	       } else {
	        return 203;
	       }
	      }
	     } else {
	      if (lng < 14.5) {
	       if (lat < 9.5) {
	        return 327;
	       } else {
	        if (lng < 14.25) {
	         return 327;
	        } else {
	         return 203;
	        }
	       }
	      } else {
	       if (lng < 14.75) {
	        if (lat < 9.25) {
	         return 327;
	        } else {
	         return 203;
	        }
	       } else {
	        return 203;
	       }
	      }
	     }
	    } else {
	     if (lng < 15.5) {
	      if (lat < 8.5) {
	       return 327;
	      } else {
	       return 203;
	      }
	     } else {
	      return 203;
	     }
	    }
	   } else {
	    if (lng < 15.25) {
	     if (lat < 10.25) {
	      if (lng < 14.5) {
	       if (lng < 14.25) {
	        return 327;
	       } else {
	        if (lat < 10.0) {
	         return 203;
	        } else {
	         return 327;
	        }
	       }
	      } else {
	       if (lng < 14.75) {
	        return 203;
	       } else {
	        if (lng < 15.0) {
	         if (lat < 10.0) {
	          return 203;
	         } else {
	          return 327;
	         }
	        } else {
	         return 203;
	        }
	       }
	      }
	     } else {
	      return 327;
	     }
	    } else {
	     if (lng < 15.75) {
	      if (lat < 11.0) {
	       if (lat < 10.25) {
	        if (lng < 15.5) {
	         if (lat < 10.0) {
	          return 203;
	         } else {
	          return 327;
	         }
	        } else {
	         if (lat < 10.0) {
	          return 203;
	         } else {
	          return 327;
	         }
	        }
	       } else {
	        if (lat < 10.5) {
	         if (lng < 15.5) {
	          return 327;
	         } else {
	          return 203;
	         }
	        } else {
	         return 203;
	        }
	       }
	      } else {
	       return 203;
	      }
	     } else {
	      return 203;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup102(double lat, double lng)
	{
	 if (lat < 5.5) {
	  if (lng < 19.5) {
	   if (lat < 2.75) {
	    if (lng < 18.0) {
	     return 155;
	    } else {
	     if (lat < 1.5) {
	      return 332;
	     } else {
	      if (lng < 18.25) {
	       return 155;
	      } else {
	       return 332;
	      }
	     }
	    }
	   } else {
	    if (lng < 18.0) {
	     if (lat < 3.75) {
	      return 155;
	     } else {
	      return 215;
	     }
	    } else {
	     if (lat < 4.0) {
	      if (lng < 18.75) {
	       if (lat < 3.25) {
	        if (lng < 18.5) {
	         return 155;
	        } else {
	         if (lat < 3.0) {
	          return 332;
	         } else {
	          return 155;
	         }
	        }
	       } else {
	        if (lng < 18.25) {
	         if (lat < 3.75) {
	          return 155;
	         } else {
	          return 215;
	         }
	        } else {
	         if (lat < 3.75) {
	          return 155;
	         } else {
	          return 215;
	         }
	        }
	       }
	      } else {
	       return 332;
	      }
	     } else {
	      if (lng < 18.75) {
	       return 215;
	      } else {
	       if (lat < 4.75) {
	        if (lng < 19.0) {
	         if (lat < 4.5) {
	          return 332;
	         } else {
	          return 215;
	         }
	        } else {
	         return 332;
	        }
	       } else {
	        if (lng < 19.25) {
	         return 215;
	        } else {
	         if (lat < 5.25) {
	          return 332;
	         } else {
	          return 215;
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 4.25) {
	    return 332;
	   } else {
	    if (lng < 21.0) {
	     if (lng < 20.25) {
	      if (lat < 5.0) {
	       return 332;
	      } else {
	       if (lng < 19.75) {
	        if (lat < 5.25) {
	         return 332;
	        } else {
	         return 215;
	        }
	       } else {
	        if (lng < 20.0) {
	         if (lat < 5.25) {
	          return 332;
	         } else {
	          return 215;
	         }
	        } else {
	         return 215;
	        }
	       }
	      }
	     } else {
	      if (lat < 4.75) {
	       if (lng < 20.5) {
	        return 332;
	       } else {
	        if (lng < 20.75) {
	         if (lat < 4.5) {
	          return 332;
	         } else {
	          return 215;
	         }
	        } else {
	         if (lat < 4.5) {
	          return 332;
	         } else {
	          return 215;
	         }
	        }
	       }
	      } else {
	       if (lng < 20.5) {
	        if (lat < 5.0) {
	         return 332;
	        } else {
	         return 215;
	        }
	       } else {
	        return 215;
	       }
	      }
	     }
	    } else {
	     if (lng < 21.75) {
	      if (lat < 4.5) {
	       return 332;
	      } else {
	       return 215;
	      }
	     } else {
	      if (lat < 4.5) {
	       if (lng < 22.0) {
	        return 332;
	       } else {
	        return 215;
	       }
	      } else {
	       return 215;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 19.5) {
	   if (lat < 8.25) {
	    if (lng < 18.0) {
	     if (lat < 7.75) {
	      return 215;
	     } else {
	      if (lng < 17.25) {
	       return 203;
	      } else {
	       if (lng < 17.5) {
	        if (lat < 8.0) {
	         return 215;
	        } else {
	         return 203;
	        }
	       } else {
	        if (lng < 17.75) {
	         if (lat < 8.0) {
	          return 215;
	         } else {
	          return 203;
	         }
	        } else {
	         if (lat < 8.0) {
	          return 215;
	         } else {
	          return 203;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 8.0) {
	      return 215;
	     } else {
	      if (lng < 18.25) {
	       return 203;
	      } else {
	       return 215;
	      }
	     }
	    }
	   } else {
	    if (lat < 9.25) {
	     if (lng < 19.0) {
	      return 203;
	     } else {
	      if (lat < 8.75) {
	       return 215;
	      } else {
	       if (lng < 19.25) {
	        return 203;
	       } else {
	        return 215;
	       }
	      }
	     }
	    } else {
	     return 203;
	    }
	   }
	  } else {
	   if (lat < 9.25) {
	    return 215;
	   } else {
	    if (lng < 21.0) {
	     if (lat < 9.5) {
	      if (lng < 20.5) {
	       return 203;
	      } else {
	       return 215;
	      }
	     } else {
	      return 203;
	     }
	    } else {
	     if (lat < 10.25) {
	      if (lng < 21.5) {
	       if (lat < 9.75) {
	        return 215;
	       } else {
	        if (lng < 21.25) {
	         return 203;
	        } else {
	         if (lat < 10.0) {
	          return 215;
	         } else {
	          return 203;
	         }
	        }
	       }
	      } else {
	       return 215;
	      }
	     } else {
	      if (lng < 21.75) {
	       return 203;
	      } else {
	       if (lat < 10.75) {
	        return 215;
	       } else {
	        if (lng < 22.0) {
	         return 203;
	        } else {
	         if (lng < 22.25) {
	          if (lat < 11.0) {
	           return 215;
	          } else {
	           return 203;
	          }
	         } else {
	          if (lat < 11.0) {
	           return 215;
	          } else {
	           return 203;
	          }
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup103(double lat, double lng)
	{
	 if (lng < 14.0) {
	  if (lat < 14.0) {
	   if (lng < 12.5) {
	    if (lat < 13.25) {
	     return 96;
	    } else {
	     if (lng < 11.75) {
	      if (lat < 13.5) {
	       return 96;
	      } else {
	       return 261;
	      }
	     } else {
	      if (lng < 12.0) {
	       if (lat < 13.5) {
	        return 96;
	       } else {
	        return 261;
	       }
	      } else {
	       return 261;
	      }
	     }
	    }
	   } else {
	    if (lat < 13.25) {
	     return 96;
	    } else {
	     if (lng < 13.25) {
	      if (lng < 12.75) {
	       return 261;
	      } else {
	       if (lat < 13.5) {
	        return 96;
	       } else {
	        if (lng < 13.0) {
	         return 261;
	        } else {
	         if (lat < 13.75) {
	          return 96;
	         } else {
	          return 261;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < 13.5) {
	       if (lat < 13.75) {
	        return 96;
	       } else {
	        return 261;
	       }
	      } else {
	       if (lat < 13.75) {
	        return 96;
	       } else {
	        if (lng < 13.75) {
	         return 261;
	        } else {
	         return 203;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 13.75) {
	    return 261;
	   } else {
	    if (lat < 14.75) {
	     return 203;
	    } else {
	     return 261;
	    }
	   }
	  }
	 } else {
	  if (lat < 14.0) {
	   if (lng < 15.25) {
	    if (lat < 12.5) {
	     if (lng < 14.5) {
	      if (lat < 11.5) {
	       return 327;
	      } else {
	       return 96;
	      }
	     } else {
	      if (lat < 11.75) {
	       if (lng < 14.75) {
	        if (lat < 11.5) {
	         return 327;
	        } else {
	         return 96;
	        }
	       } else {
	        return 327;
	       }
	      } else {
	       if (lng < 14.75) {
	        return 96;
	       } else {
	        if (lat < 12.25) {
	         return 327;
	        } else {
	         if (lng < 15.0) {
	          return 327;
	         } else {
	          return 203;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 13.25) {
	      if (lng < 14.5) {
	       if (lat < 12.75) {
	        if (lng < 14.25) {
	         return 96;
	        } else {
	         return 327;
	        }
	       } else {
	        if (lng < 14.25) {
	         return 96;
	        } else {
	         return 327;
	        }
	       }
	      } else {
	       if (lng < 14.75) {
	        return 327;
	       } else {
	        if (lat < 12.75) {
	         if (lng < 15.0) {
	          return 327;
	         } else {
	          return 203;
	         }
	        } else {
	         return 203;
	        }
	       }
	      }
	     } else {
	      return 203;
	     }
	    }
	   } else {
	    return 203;
	   }
	  } else {
	   if (lng < 15.25) {
	    if (lat < 15.25) {
	     return 203;
	    } else {
	     if (lat < 16.0) {
	      if (lng < 14.5) {
	       if (lat < 15.5) {
	        if (lng < 14.25) {
	         return 261;
	        } else {
	         return 203;
	        }
	       } else {
	        if (lng < 14.25) {
	         return 261;
	        } else {
	         if (lat < 15.75) {
	          return 203;
	         } else {
	          return 261;
	         }
	        }
	       }
	      } else {
	       return 203;
	      }
	     } else {
	      if (lng < 14.75) {
	       return 261;
	      } else {
	       if (lat < 16.25) {
	        return 203;
	       } else {
	        if (lng < 15.0) {
	         return 261;
	        } else {
	         if (lat < 16.5) {
	          return 203;
	         } else {
	          return 261;
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return 203;
	   }
	  }
	 }
	}

	private  int kdLookup104(double lat, double lng)
	{
	 if (lng < 16.75) {
	  if (lat < 16.75) {
	   return kdLookup103(lat,lng);
	  } else {
	   if (lat < 19.5) {
	    if (lng < 15.5) {
	     return 261;
	    } else {
	     if (lat < 18.0) {
	      if (lng < 15.75) {
	       if (lat < 17.0) {
	        return 203;
	       } else {
	        return 261;
	       }
	      } else {
	       return 203;
	      }
	     } else {
	      if (lat < 18.75) {
	       if (lng < 15.75) {
	        return 261;
	       } else {
	        return 203;
	       }
	      } else {
	       if (lng < 15.75) {
	        return 261;
	       } else {
	        return 203;
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 15.25) {
	     return 261;
	    } else {
	     if (lat < 21.0) {
	      if (lng < 16.0) {
	       if (lat < 20.25) {
	        if (lng < 15.75) {
	         return 261;
	        } else {
	         if (lat < 20.0) {
	          return 203;
	         } else {
	          return 261;
	         }
	        }
	       } else {
	        if (lng < 15.75) {
	         return 261;
	        } else {
	         if (lat < 20.75) {
	          return 261;
	         } else {
	          return 203;
	         }
	        }
	       }
	      } else {
	       return 203;
	      }
	     } else {
	      if (lng < 15.75) {
	       if (lat < 21.5) {
	        if (lng < 15.5) {
	         return 261;
	        } else {
	         if (lat < 21.25) {
	          return 261;
	         } else {
	          return 203;
	         }
	        }
	       } else {
	        return 203;
	       }
	      } else {
	       return 203;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 20.5) {
	   if (lat < 20.25) {
	    if (lat < 20.0) {
	     if (lat < 19.75) {
	      if (lat < 19.5) {
	       if (lat < 19.25) {
	        if (lat < 19.0) {
	         if (lat < 18.75) {
	          if (lat < 18.5) {
	           if (lat < 18.25) {
	            if (lat < 18.0) {
	             if (lat < 17.75) {
	              if (lat < 17.5) {
	               if (lat < 17.25) {
	                if (lat < 17.0) {
	                 if (lng < 22.0) {
	                  return 203;
	                 } else {
	                  if (lat < 16.75) {
	                   if (lat < 16.5) {
	                    if (lat < 13.75) {
	                     if (lat < 12.75) {
	                      return 203;
	                     } else {
	                      if (lat < 13.25) {
	                       return 323;
	                      } else {
	                       if (lng < 22.25) {
	                        return 203;
	                       } else {
	                        return 323;
	                       }
	                      }
	                     }
	                    } else {
	                     if (lat < 14.0) {
	                      if (lng < 22.25) {
	                       return 203;
	                      } else {
	                       return 323;
	                      }
	                     } else {
	                      return 203;
	                     }
	                    }
	                   } else {
	                    return 203;
	                   }
	                  } else {
	                   return 203;
	                  }
	                 }
	                } else {
	                 return 203;
	                }
	               } else {
	                return 203;
	               }
	              } else {
	               return 203;
	              }
	             } else {
	              return 203;
	             }
	            } else {
	             return 203;
	            }
	           } else {
	            return 203;
	           }
	          } else {
	           return 203;
	          }
	         } else {
	          return 203;
	         }
	        } else {
	         return 203;
	        }
	       } else {
	        return 203;
	       }
	      } else {
	       return 203;
	      }
	     } else {
	      return 203;
	     }
	    } else {
	     return 203;
	    }
	   } else {
	    return 203;
	   }
	  } else {
	   if (lng < 19.5) {
	    if (lng < 18.75) {
	     return 203;
	    } else {
	     if (lat < 22.0) {
	      return 203;
	     } else {
	      if (lng < 19.0) {
	       if (lat < 22.25) {
	        return 203;
	       } else {
	        return 334;
	       }
	      } else {
	       if (lng < 19.25) {
	        if (lat < 22.25) {
	         return 203;
	        } else {
	         return 334;
	        }
	       } else {
	        return 334;
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 21.0) {
	     if (lat < 21.5) {
	      if (lng < 20.75) {
	       return 203;
	      } else {
	       if (lat < 21.25) {
	        return 203;
	       } else {
	        return 334;
	       }
	      }
	     } else {
	      if (lng < 20.25) {
	       if (lat < 22.0) {
	        if (lng < 19.75) {
	         return 203;
	        } else {
	         if (lng < 20.0) {
	          if (lat < 21.75) {
	           return 203;
	          } else {
	           return 334;
	          }
	         } else {
	          if (lat < 21.75) {
	           return 203;
	          } else {
	           return 334;
	          }
	         }
	        }
	       } else {
	        return 334;
	       }
	      } else {
	       return 334;
	      }
	     }
	    } else {
	     if (lat < 21.25) {
	      if (lng < 21.75) {
	       if (lng < 21.25) {
	        return 203;
	       } else {
	        if (lat < 21.0) {
	         return 203;
	        } else {
	         return 334;
	        }
	       }
	      } else {
	       if (lng < 22.0) {
	        if (lat < 20.75) {
	         return 203;
	        } else {
	         return 334;
	        }
	       } else {
	        if (lat < 20.75) {
	         if (lng < 22.25) {
	          return 203;
	         } else {
	          return 334;
	         }
	        } else {
	         return 334;
	        }
	       }
	      }
	     } else {
	      return 334;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup105(double lat, double lng)
	{
	 if (lat < 28.0) {
	  if (lat < 25.25) {
	   if (lng < 10.25) {
	    return 381;
	   } else {
	    if (lat < 23.75) {
	     if (lat < 23.0) {
	      if (lng < 10.75) {
	       if (lng < 10.5) {
	        if (lat < 22.75) {
	         return 261;
	        } else {
	         return 381;
	        }
	       } else {
	        if (lat < 22.75) {
	         return 261;
	        } else {
	         return 381;
	        }
	       }
	      } else {
	       return 261;
	      }
	     } else {
	      return 381;
	     }
	    } else {
	     if (lat < 24.5) {
	      return 381;
	     } else {
	      if (lng < 10.5) {
	       if (lat < 24.75) {
	        return 381;
	       } else {
	        return 334;
	       }
	      } else {
	       if (lng < 10.75) {
	        return 334;
	       } else {
	        if (lat < 24.75) {
	         if (lng < 11.0) {
	          return 381;
	         } else {
	          return 334;
	         }
	        } else {
	         return 334;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 9.5) {
	    return 381;
	   } else {
	    if (lat < 26.5) {
	     if (lng < 10.25) {
	      if (lat < 25.75) {
	       if (lng < 10.0) {
	        return 381;
	       } else {
	        if (lat < 25.5) {
	         return 381;
	        } else {
	         return 334;
	        }
	       }
	      } else {
	       if (lng < 9.75) {
	        if (lat < 26.25) {
	         return 381;
	        } else {
	         return 334;
	        }
	       } else {
	        return 334;
	       }
	      }
	     } else {
	      return 334;
	     }
	    } else {
	     if (lng < 10.0) {
	      return 381;
	     } else {
	      return 334;
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 30.75) {
	   if (lng < 9.5) {
	    return 381;
	   } else {
	    if (lat < 29.25) {
	     if (lng < 10.0) {
	      return 381;
	     } else {
	      return 334;
	     }
	    } else {
	     if (lng < 10.25) {
	      if (lat < 30.0) {
	       if (lng < 9.75) {
	        return 381;
	       } else {
	        if (lat < 29.5) {
	         if (lng < 10.0) {
	          return 381;
	         } else {
	          return 334;
	         }
	        } else {
	         if (lng < 10.0) {
	          if (lat < 29.75) {
	           return 381;
	          } else {
	           return 334;
	          }
	         } else {
	          return 334;
	         }
	        }
	       }
	      } else {
	       if (lng < 9.75) {
	        if (lat < 30.25) {
	         return 334;
	        } else {
	         return 381;
	        }
	       } else {
	        if (lat < 30.5) {
	         return 334;
	        } else {
	         return 319;
	        }
	       }
	      }
	     } else {
	      return 334;
	     }
	    }
	   }
	  } else {
	   if (lng < 9.5) {
	    if (lat < 32.25) {
	     if (lng < 9.25) {
	      return 381;
	     } else {
	      if (lat < 31.5) {
	       return 381;
	      } else {
	       return 319;
	      }
	     }
	    } else {
	     if (lng < 8.5) {
	      if (lat < 33.0) {
	       return 381;
	      } else {
	       if (lng < 8.0) {
	        if (lat < 33.25) {
	         return 381;
	        } else {
	         return 319;
	        }
	       } else {
	        if (lat < 33.25) {
	         if (lng < 8.25) {
	          return 381;
	         } else {
	          return 319;
	         }
	        } else {
	         return 319;
	        }
	       }
	      }
	     } else {
	      if (lat < 32.5) {
	       if (lng < 9.0) {
	        return 381;
	       } else {
	        return 319;
	       }
	      } else {
	       return 319;
	      }
	     }
	    }
	   } else {
	    if (lat < 32.25) {
	     if (lng < 10.25) {
	      return 319;
	     } else {
	      if (lat < 31.75) {
	       if (lng < 10.5) {
	        if (lat < 31.5) {
	         if (lat < 31.0) {
	          return 334;
	         } else {
	          if (lat < 31.25) {
	           return 319;
	          } else {
	           return 334;
	          }
	         }
	        } else {
	         return 334;
	        }
	       } else {
	        return 334;
	       }
	      } else {
	       if (lng < 10.75) {
	        return 319;
	       } else {
	        if (lng < 11.0) {
	         if (lat < 32.0) {
	          return 334;
	         } else {
	          return 319;
	         }
	        } else {
	         return 334;
	        }
	       }
	      }
	     }
	    } else {
	     return 319;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup106(double lat, double lng)
	{
	 if (lng < 5.5) {
	  if (lat < 39.25) {
	   if (lng < 2.75) {
	    if (lat < 37.0) {
	     return 381;
	    } else {
	     return 335;
	    }
	   } else {
	    return 381;
	   }
	  } else {
	   if (lat < 42.0) {
	    return 335;
	   } else {
	    if (lng < 2.75) {
	     if (lat < 43.0) {
	      if (lng < 1.25) {
	       if (lng < 0.5) {
	        if (lat < 42.75) {
	         return 335;
	        } else {
	         return 298;
	        }
	       } else {
	        if (lat < 42.75) {
	         return 335;
	        } else {
	         if (lng < 0.75) {
	          return 298;
	         } else {
	          return 335;
	         }
	        }
	       }
	      } else {
	       if (lng < 2.0) {
	        if (lat < 42.5) {
	         return 335;
	        } else {
	         if (lng < 1.5) {
	          if (lat < 42.75) {
	           return 335;
	          } else {
	           return 298;
	          }
	         } else {
	          if (lng < 1.75) {
	           if (lat < 42.75) {
	            return 137;
	           } else {
	            return 298;
	           }
	          } else {
	           return 298;
	          }
	         }
	        }
	       } else {
	        if (lat < 42.5) {
	         return 335;
	        } else {
	         return 298;
	        }
	       }
	      }
	     } else {
	      return 298;
	     }
	    } else {
	     if (lat < 43.5) {
	      if (lng < 3.75) {
	       if (lat < 42.75) {
	        if (lng < 3.25) {
	         if (lat < 42.5) {
	          return 335;
	         } else {
	          return 298;
	         }
	        } else {
	         return 335;
	        }
	       } else {
	        return 298;
	       }
	      } else {
	       return 298;
	      }
	     } else {
	      return 298;
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 39.25) {
	   if (lng < 8.25) {
	    if (lat < 36.5) {
	     if (lng < 7.75) {
	      return 381;
	     } else {
	      if (lat < 34.5) {
	       if (lat < 34.25) {
	        return 319;
	       } else {
	        if (lng < 8.0) {
	         return 381;
	        } else {
	         return 319;
	        }
	       }
	      } else {
	       return 381;
	      }
	     }
	    } else {
	     return 381;
	    }
	   } else {
	    if (lat < 36.75) {
	     if (lng < 9.75) {
	      if (lat < 35.25) {
	       if (lng < 8.5) {
	        if (lat < 34.75) {
	         return 319;
	        } else {
	         return 381;
	        }
	       } else {
	        return 319;
	       }
	      } else {
	       if (lng < 8.5) {
	        if (lat < 36.5) {
	         return 381;
	        } else {
	         return 319;
	        }
	       } else {
	        return 319;
	       }
	      }
	     } else {
	      return 319;
	     }
	    } else {
	     if (lng < 9.75) {
	      if (lat < 38.0) {
	       if (lng < 9.0) {
	        if (lat < 37.25) {
	         if (lng < 8.5) {
	          return 381;
	         } else {
	          return 319;
	         }
	        } else {
	         return 0;
	        }
	       } else {
	        return 319;
	       }
	      } else {
	       return 272;
	      }
	     } else {
	      return 319;
	     }
	    }
	   }
	  } else {
	   if (lng < 8.25) {
	    if (lat < 42.0) {
	     return 272;
	    } else {
	     if (lat < 43.5) {
	      return 298;
	     } else {
	      if (lng < 7.0) {
	       return 298;
	      } else {
	       if (lat < 44.25) {
	        if (lng < 7.75) {
	         return 298;
	        } else {
	         return 272;
	        }
	       } else {
	        if (lng < 7.25) {
	         if (lat < 44.75) {
	          return 272;
	         } else {
	          return 298;
	         }
	        } else {
	         return 272;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 42.0) {
	     if (lng < 9.75) {
	      if (lat < 41.25) {
	       return 272;
	      } else {
	       if (lng < 9.0) {
	        if (lng < 8.5) {
	         return 272;
	        } else {
	         return 298;
	        }
	       } else {
	        if (lng < 9.5) {
	         return 298;
	        } else {
	         if (lat < 41.5) {
	          return 272;
	         } else {
	          return 298;
	         }
	        }
	       }
	      }
	     } else {
	      return 272;
	     }
	    } else {
	     if (lng < 9.75) {
	      if (lat < 43.5) {
	       return 298;
	      } else {
	       return 272;
	      }
	     } else {
	      if (lat < 42.75) {
	       if (lng < 10.5) {
	        return 298;
	       } else {
	        return 272;
	       }
	      } else {
	       return 272;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup107(double lat, double lng)
	{
	 if (lng < 16.75) {
	  if (lat < 28.0) {
	   if (lng < 14.0) {
	    if (lat < 24.5) {
	     if (lng < 12.5) {
	      if (lat < 23.5) {
	       if (lng < 11.5) {
	        if (lat < 23.25) {
	         return 261;
	        } else {
	         return 381;
	        }
	       } else {
	        return 261;
	       }
	      } else {
	       if (lng < 11.75) {
	        if (lat < 24.25) {
	         return 381;
	        } else {
	         if (lng < 11.5) {
	          return 381;
	         } else {
	          return 334;
	         }
	        }
	       } else {
	        if (lat < 24.0) {
	         if (lng < 12.0) {
	          return 381;
	         } else {
	          if (lng < 12.25) {
	           if (lat < 23.75) {
	            return 261;
	           } else {
	            return 334;
	           }
	          } else {
	           return 334;
	          }
	         }
	        } else {
	         return 334;
	        }
	       }
	      }
	     } else {
	      if (lat < 23.5) {
	       if (lng < 13.25) {
	        return 261;
	       } else {
	        if (lat < 23.25) {
	         return 261;
	        } else {
	         return 334;
	        }
	       }
	      } else {
	       return 334;
	      }
	     }
	    } else {
	     return 334;
	    }
	   } else {
	    if (lat < 23.5) {
	     if (lng < 15.25) {
	      if (lng < 14.5) {
	       if (lat < 23.0) {
	        if (lng < 14.25) {
	         return 261;
	        } else {
	         if (lat < 22.75) {
	          return 261;
	         } else {
	          return 334;
	         }
	        }
	       } else {
	        return 334;
	       }
	      } else {
	       if (lat < 23.0) {
	        return 261;
	       } else {
	        if (lng < 15.0) {
	         return 334;
	        } else {
	         if (lat < 23.25) {
	          return 203;
	         } else {
	          return 334;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < 16.0) {
	       if (lat < 23.25) {
	        return 203;
	       } else {
	        if (lng < 15.75) {
	         return 334;
	        } else {
	         return 203;
	        }
	       }
	      } else {
	       if (lat < 23.25) {
	        return 203;
	       } else {
	        if (lng < 16.5) {
	         return 203;
	        } else {
	         return 334;
	        }
	       }
	      }
	     }
	    } else {
	     return 334;
	    }
	   }
	  } else {
	   if (lat < 31.5) {
	    return 334;
	   } else {
	    if (lng < 14.0) {
	     if (lng < 12.5) {
	      if (lat < 32.5) {
	       return 334;
	      } else {
	       if (lng < 11.75) {
	        if (lat < 33.5) {
	         if (lat < 33.25) {
	          if (lat < 32.75) {
	           return 319;
	          } else {
	           if (lng < 11.5) {
	            return 319;
	           } else {
	            if (lat < 33.0) {
	             return 334;
	            } else {
	             return 319;
	            }
	           }
	          }
	         } else {
	          return 319;
	         }
	        } else {
	         return 319;
	        }
	       } else {
	        return 334;
	       }
	      }
	     } else {
	      return 334;
	     }
	    } else {
	     return 334;
	    }
	   }
	  }
	 } else {
	  if (lat < 28.0) {
	   if (lng < 18.25) {
	    if (lat < 23.25) {
	     if (lng < 17.5) {
	      if (lng < 17.0) {
	       return 203;
	      } else {
	       if (lat < 23.0) {
	        return 203;
	       } else {
	        return 334;
	       }
	      }
	     } else {
	      if (lng < 17.75) {
	       if (lat < 23.0) {
	        return 203;
	       } else {
	        return 334;
	       }
	      } else {
	       if (lat < 22.75) {
	        return 203;
	       } else {
	        return 334;
	       }
	      }
	     }
	    } else {
	     return 334;
	    }
	   } else {
	    return 334;
	   }
	  } else {
	   return 334;
	  }
	 }
	}

	private  int kdLookup108(double lat, double lng)
	{
	 if (lat < 42.0) {
	  if (lng < 21.0) {
	   if (lat < 40.5) {
	    if (lng < 20.25) {
	     if (lat < 39.75) {
	      if (lng < 19.75) {
	       return 0;
	      } else {
	       if (lng < 20.0) {
	        return 153;
	       } else {
	        return 75;
	       }
	      }
	     } else {
	      if (lng < 19.75) {
	       if (lat < 40.0) {
	        return 153;
	       } else {
	        return 75;
	       }
	      } else {
	       if (lat < 40.0) {
	        if (lng < 20.0) {
	         return 153;
	        } else {
	         return 75;
	        }
	       } else {
	        return 75;
	       }
	      }
	     }
	    } else {
	     if (lat < 39.75) {
	      return 153;
	     } else {
	      if (lng < 20.5) {
	       return 75;
	      } else {
	       if (lat < 40.25) {
	        return 153;
	       } else {
	        if (lng < 20.75) {
	         return 75;
	        } else {
	         return 153;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 20.5) {
	     return 75;
	    } else {
	     if (lat < 41.25) {
	      if (lat < 41.0) {
	       return 75;
	      } else {
	       if (lng < 20.75) {
	        return 75;
	       } else {
	        return 338;
	       }
	      }
	     } else {
	      if (lat < 41.5) {
	       if (lng < 20.75) {
	        return 75;
	       } else {
	        return 338;
	       }
	      } else {
	       if (lng < 20.75) {
	        if (lat < 41.75) {
	         return 338;
	        } else {
	         return 75;
	        }
	       } else {
	        return 338;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 41.0) {
	    return 153;
	   } else {
	    if (lng < 22.0) {
	     return 338;
	    } else {
	     if (lat < 41.25) {
	      return 153;
	     } else {
	      return 338;
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 21.0) {
	   if (lat < 43.5) {
	    if (lng < 20.25) {
	     if (lat < 42.75) {
	      if (lng < 19.75) {
	       if (lat < 42.5) {
	        return 75;
	       } else {
	        return 213;
	       }
	      } else {
	       return 75;
	      }
	     } else {
	      if (lng < 19.75) {
	       return 213;
	      } else {
	       if (lat < 43.0) {
	        if (lng < 20.0) {
	         return 213;
	        } else {
	         return 210;
	        }
	       } else {
	        if (lng < 20.0) {
	         if (lat < 43.25) {
	          return 213;
	         } else {
	          return 210;
	         }
	        } else {
	         if (lat < 43.25) {
	          return 213;
	         } else {
	          return 210;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 42.5) {
	      if (lng < 20.5) {
	       return 75;
	      } else {
	       if (lng < 20.75) {
	        if (lat < 42.25) {
	         return 75;
	        } else {
	         return 210;
	        }
	       } else {
	        if (lat < 42.25) {
	         return 338;
	        } else {
	         return 210;
	        }
	       }
	      }
	     } else {
	      return 210;
	     }
	    }
	   } else {
	    if (lng < 19.75) {
	     if (lat < 44.75) {
	      if (lat < 44.0) {
	       return 210;
	      } else {
	       if (lat < 44.25) {
	        return 349;
	       } else {
	        return 210;
	       }
	      }
	     } else {
	      return 210;
	     }
	    } else {
	     return 210;
	    }
	   }
	  } else {
	   if (lat < 43.5) {
	    if (lng < 21.75) {
	     if (lat < 42.5) {
	      if (lng < 21.25) {
	       if (lat < 42.25) {
	        return 338;
	       } else {
	        return 210;
	       }
	      } else {
	       if (lng < 21.5) {
	        if (lat < 42.25) {
	         return 338;
	        } else {
	         return 210;
	        }
	       } else {
	        return 338;
	       }
	      }
	     } else {
	      return 210;
	     }
	    } else {
	     if (lat < 42.5) {
	      return 338;
	     } else {
	      return 210;
	     }
	    }
	   } else {
	    if (lng < 21.75) {
	     return 210;
	    } else {
	     if (lat < 44.75) {
	      return 210;
	     } else {
	      return 267;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup109(double lat, double lng)
	{
	 if (lng < 16.75) {
	  if (lat < 39.25) {
	   if (lng < 14.0) {
	    if (lat < 36.5) {
	     return 319;
	    } else {
	     if (lng < 12.5) {
	      if (lat < 37.75) {
	       if (lng < 11.75) {
	        return 319;
	       } else {
	        return 272;
	       }
	      } else {
	       return 272;
	      }
	     } else {
	      return 272;
	     }
	    }
	   } else {
	    return 272;
	   }
	  } else {
	   if (lat < 42.0) {
	    return 272;
	   } else {
	    if (lng < 14.0) {
	     if (lat < 43.5) {
	      return 272;
	     } else {
	      if (lng < 12.5) {
	       return 272;
	      } else {
	       if (lng < 13.25) {
	        return 272;
	       } else {
	        if (lat < 44.25) {
	         return 272;
	        } else {
	         return 30;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 43.5) {
	      if (lng < 15.25) {
	       return 272;
	      } else {
	       if (lng < 16.0) {
	        return 272;
	       } else {
	        if (lat < 42.75) {
	         return 272;
	        } else {
	         return 30;
	        }
	       }
	      }
	     } else {
	      if (lng < 16.0) {
	       return 30;
	      } else {
	       if (lat < 44.25) {
	        return 30;
	       } else {
	        if (lng < 16.25) {
	         if (lat < 44.75) {
	          return 30;
	         } else {
	          return 349;
	         }
	        } else {
	         return 349;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 39.25) {
	   if (lng < 19.5) {
	    return 272;
	   } else {
	    return 153;
	   }
	  } else {
	   if (lng < 19.5) {
	    if (lat < 42.0) {
	     if (lng < 18.0) {
	      return 272;
	     } else {
	      if (lat < 40.5) {
	       if (lng < 18.75) {
	        return 272;
	       } else {
	        return 75;
	       }
	      } else {
	       if (lng < 18.75) {
	        return 272;
	       } else {
	        if (lat < 41.25) {
	         return 75;
	        } else {
	         return 213;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 43.5) {
	      if (lng < 18.0) {
	       if (lat < 42.75) {
	        return 30;
	       } else {
	        if (lng < 17.5) {
	         return 30;
	        } else {
	         if (lat < 43.0) {
	          return 30;
	         } else {
	          if (lng < 17.75) {
	           if (lat < 43.25) {
	            return 30;
	           } else {
	            return 349;
	           }
	          } else {
	           return 349;
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < 18.75) {
	        if (lat < 42.75) {
	         if (lng < 18.25) {
	          return 30;
	         } else {
	          return 213;
	         }
	        } else {
	         if (lng < 18.25) {
	          if (lat < 43.0) {
	           return 30;
	          } else {
	           return 349;
	          }
	         } else {
	          if (lat < 43.0) {
	           if (lng < 18.5) {
	            return 349;
	           } else {
	            return 213;
	           }
	          } else {
	           return 349;
	          }
	         }
	        }
	       } else {
	        return 213;
	       }
	      }
	     } else {
	      if (lng < 18.0) {
	       if (lat < 44.0) {
	        if (lng < 17.25) {
	         if (lng < 17.0) {
	          return 30;
	         } else {
	          if (lat < 43.75) {
	           return 30;
	          } else {
	           return 349;
	          }
	         }
	        } else {
	         return 349;
	        }
	       } else {
	        return 349;
	       }
	      } else {
	       if (lng < 19.0) {
	        return 349;
	       } else {
	        if (lat < 44.25) {
	         if (lat < 43.75) {
	          if (lng < 19.25) {
	           return 213;
	          } else {
	           return 210;
	          }
	         } else {
	          if (lng < 19.25) {
	           return 349;
	          } else {
	           if (lat < 44.0) {
	            return 349;
	           } else {
	            return 210;
	           }
	          }
	         }
	        } else {
	         if (lat < 44.5) {
	          return 349;
	         } else {
	          if (lng < 19.25) {
	           return 349;
	          } else {
	           if (lat < 44.75) {
	            return 210;
	           } else {
	            return 349;
	           }
	          }
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return kdLookup108(lat,lng);
	   }
	  }
	 }
	}

	private  int kdLookup110(double lat, double lng)
	{
	 if (lng < 25.25) {
	  if (lat < 2.75) {
	   if (lng < 23.75) {
	    if (lat < 1.25) {
	     if (lng < 23.0) {
	      return 332;
	     } else {
	      if (lat < 0.5) {
	       if (lng < 23.25) {
	        return 332;
	       } else {
	        if (lng < 23.5) {
	         if (lat < 0.25) {
	          return 332;
	         } else {
	          return 353;
	         }
	        } else {
	         return 353;
	        }
	       }
	      } else {
	       return 353;
	      }
	     }
	    } else {
	     if (lat < 2.0) {
	      if (lng < 22.75) {
	       if (lat < 1.5) {
	        return 332;
	       } else {
	        return 353;
	       }
	      } else {
	       return 353;
	      }
	     } else {
	      if (lng < 23.0) {
	       if (lat < 2.25) {
	        if (lng < 22.75) {
	         return 332;
	        } else {
	         return 353;
	        }
	       } else {
	        return 332;
	       }
	      } else {
	       if (lng < 23.25) {
	        if (lat < 2.25) {
	         return 353;
	        } else {
	         return 332;
	        }
	       } else {
	        if (lat < 2.25) {
	         return 353;
	        } else {
	         if (lng < 23.5) {
	          return 332;
	         } else {
	          if (lat < 2.5) {
	           return 332;
	          } else {
	           return 353;
	          }
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return 353;
	   }
	  } else {
	   if (lng < 23.75) {
	    if (lat < 4.0) {
	     if (lng < 23.0) {
	      return 332;
	     } else {
	      if (lat < 3.25) {
	       if (lng < 23.25) {
	        return 332;
	       } else {
	        return 353;
	       }
	      } else {
	       if (lng < 23.25) {
	        if (lat < 3.75) {
	         return 353;
	        } else {
	         return 332;
	        }
	       } else {
	        if (lat < 3.75) {
	         return 353;
	        } else {
	         if (lng < 23.5) {
	          return 332;
	         } else {
	          return 353;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 4.75) {
	      if (lng < 22.75) {
	       if (lat < 4.25) {
	        return 332;
	       } else {
	        return 215;
	       }
	      } else {
	       return 353;
	      }
	     } else {
	      if (lng < 23.0) {
	       return 215;
	      } else {
	       if (lng < 23.25) {
	        if (lat < 5.0) {
	         return 353;
	        } else {
	         return 215;
	        }
	       } else {
	        return 215;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 5.0) {
	     return 353;
	    } else {
	     if (lng < 24.5) {
	      return 215;
	     } else {
	      if (lng < 24.75) {
	       if (lat < 5.25) {
	        return 353;
	       } else {
	        return 215;
	       }
	      } else {
	       return 215;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 5.0) {
	   return 353;
	  } else {
	   if (lng < 26.5) {
	    if (lng < 25.75) {
	     if (lng < 25.5) {
	      if (lat < 5.25) {
	       return 353;
	      } else {
	       return 215;
	      }
	     } else {
	      return 353;
	     }
	    } else {
	     if (lng < 26.0) {
	      if (lat < 5.25) {
	       return 353;
	      } else {
	       return 215;
	      }
	     } else {
	      if (lng < 26.25) {
	       if (lat < 5.25) {
	        return 353;
	       } else {
	        return 215;
	       }
	      } else {
	       if (lat < 5.25) {
	        return 353;
	       } else {
	        return 215;
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 27.25) {
	     if (lng < 26.75) {
	      if (lat < 5.25) {
	       return 353;
	      } else {
	       return 215;
	      }
	     } else {
	      if (lng < 27.0) {
	       if (lat < 5.25) {
	        return 353;
	       } else {
	        return 215;
	       }
	      } else {
	       if (lat < 5.25) {
	        return 353;
	       } else {
	        return 215;
	       }
	      }
	     }
	    } else {
	     if (lng < 27.5) {
	      if (lat < 5.25) {
	       return 353;
	      } else {
	       return 215;
	      }
	     } else {
	      return 396;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup111(double lat, double lng)
	{
	 if (lat < 8.25) {
	  if (lng < 25.25) {
	   if (lng < 25.0) {
	    return 215;
	   } else {
	    if (lat < 8.0) {
	     return 215;
	    } else {
	     return 396;
	    }
	   }
	  } else {
	   if (lng < 26.5) {
	    if (lat < 6.75) {
	     return 215;
	    } else {
	     if (lat < 7.5) {
	      if (lng < 25.75) {
	       return 215;
	      } else {
	       if (lng < 26.0) {
	        if (lat < 7.25) {
	         return 215;
	        } else {
	         return 396;
	        }
	       } else {
	        if (lat < 7.0) {
	         if (lng < 26.25) {
	          return 215;
	         } else {
	          return 396;
	         }
	        } else {
	         if (lng < 26.25) {
	          if (lat < 7.25) {
	           return 215;
	          } else {
	           return 396;
	          }
	         } else {
	          return 396;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < 25.5) {
	       if (lat < 7.75) {
	        return 396;
	       } else {
	        if (lat < 8.0) {
	         return 215;
	        } else {
	         return 396;
	        }
	       }
	      } else {
	       return 396;
	      }
	     }
	    }
	   } else {
	    if (lat < 6.25) {
	     if (lng < 27.25) {
	      if (lng < 27.0) {
	       return 215;
	      } else {
	       if (lat < 6.0) {
	        return 215;
	       } else {
	        return 396;
	       }
	      }
	     } else {
	      if (lng < 27.5) {
	       if (lat < 5.75) {
	        return 215;
	       } else {
	        return 396;
	       }
	      } else {
	       return 396;
	      }
	     }
	    } else {
	     return 396;
	    }
	   }
	  }
	 } else {
	  if (lng < 25.25) {
	   if (lat < 9.75) {
	    if (lng < 23.75) {
	     if (lat < 9.0) {
	      return 215;
	     } else {
	      if (lng < 23.5) {
	       return 215;
	      } else {
	       if (lat < 9.25) {
	        return 323;
	       } else {
	        return 215;
	       }
	      }
	     }
	    } else {
	     if (lng < 24.5) {
	      if (lat < 9.0) {
	       if (lng < 24.0) {
	        if (lat < 8.75) {
	         return 215;
	        } else {
	         return 323;
	        }
	       } else {
	        if (lat < 8.5) {
	         return 215;
	        } else {
	         if (lng < 24.25) {
	          if (lat < 8.75) {
	           return 215;
	          } else {
	           return 323;
	          }
	         } else {
	          return 396;
	         }
	        }
	       }
	      } else {
	       return 323;
	      }
	     } else {
	      if (lat < 9.0) {
	       return 396;
	      } else {
	       if (lng < 24.75) {
	        return 323;
	       } else {
	        if (lat < 9.5) {
	         return 396;
	        } else {
	         if (lng < 25.0) {
	          return 323;
	         } else {
	          return 396;
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 23.75) {
	     if (lat < 10.5) {
	      if (lng < 23.5) {
	       return 215;
	      } else {
	       if (lat < 10.25) {
	        return 215;
	       } else {
	        return 323;
	       }
	      }
	     } else {
	      if (lng < 23.0) {
	       if (lat < 11.0) {
	        return 215;
	       } else {
	        if (lng < 22.75) {
	         return 215;
	        } else {
	         return 203;
	        }
	       }
	      } else {
	       if (lng < 23.25) {
	        if (lat < 10.75) {
	         return 215;
	        } else {
	         return 323;
	        }
	       } else {
	        if (lat < 10.75) {
	         if (lng < 23.5) {
	          return 215;
	         } else {
	          return 323;
	         }
	        } else {
	         return 323;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 25.0) {
	      return 323;
	     } else {
	      if (lat < 10.25) {
	       return 396;
	      } else {
	       return 323;
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 9.75) {
	    if (lng < 26.75) {
	     return 396;
	    } else {
	     if (lat < 9.5) {
	      return 396;
	     } else {
	      if (lng < 27.0) {
	       return 323;
	      } else {
	       return 396;
	      }
	     }
	    }
	   } else {
	    if (lng < 26.25) {
	     if (lat < 10.5) {
	      if (lng < 26.0) {
	       return 396;
	      } else {
	       if (lat < 10.25) {
	        return 396;
	       } else {
	        return 323;
	       }
	      }
	     } else {
	      return 323;
	     }
	    } else {
	     return 323;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup112(double lat, double lng)
	{
	 if (lng < 30.75) {
	  if (lat < 2.75) {
	   if (lng < 29.75) {
	    return 353;
	   } else {
	    if (lat < 1.25) {
	     if (lat < 0.5) {
	      if (lng < 30.0) {
	       if (lat < 0.25) {
	        return 180;
	       } else {
	        return 353;
	       }
	      } else {
	       return 180;
	      }
	     } else {
	      if (lng < 30.25) {
	       if (lat < 0.75) {
	        if (lng < 30.0) {
	         return 353;
	        } else {
	         return 180;
	        }
	       } else {
	        if (lng < 30.0) {
	         return 353;
	        } else {
	         if (lat < 1.0) {
	          return 180;
	         } else {
	          return 353;
	         }
	        }
	       }
	      } else {
	       return 180;
	      }
	     }
	    } else {
	     if (lat < 1.5) {
	      if (lng < 30.5) {
	       return 353;
	      } else {
	       return 180;
	      }
	     } else {
	      return 353;
	     }
	    }
	   }
	  } else {
	   if (lng < 29.25) {
	    if (lat < 4.5) {
	     return 353;
	    } else {
	     if (lng < 28.25) {
	      if (lat < 4.75) {
	       return 353;
	      } else {
	       return 396;
	      }
	     } else {
	      if (lng < 28.75) {
	       return 396;
	      } else {
	       if (lat < 4.75) {
	        if (lng < 29.0) {
	         return 353;
	        } else {
	         return 396;
	        }
	       } else {
	        return 396;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 4.0) {
	     return 353;
	    } else {
	     if (lng < 30.0) {
	      if (lat < 4.75) {
	       if (lng < 29.5) {
	        if (lat < 4.5) {
	         return 353;
	        } else {
	         return 396;
	        }
	       } else {
	        return 353;
	       }
	      } else {
	       return 396;
	      }
	     } else {
	      if (lat < 4.25) {
	       if (lng < 30.25) {
	        return 353;
	       } else {
	        return 396;
	       }
	      } else {
	       return 396;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 2.75) {
	   if (lng < 31.25) {
	    if (lat < 1.75) {
	     return 180;
	    } else {
	     if (lat < 2.5) {
	      return 353;
	     } else {
	      if (lng < 31.0) {
	       return 353;
	      } else {
	       return 180;
	      }
	     }
	    }
	   } else {
	    return 180;
	   }
	  } else {
	   if (lng < 32.25) {
	    if (lat < 4.0) {
	     if (lng < 31.5) {
	      if (lat < 3.25) {
	       if (lng < 31.0) {
	        return 353;
	       } else {
	        return 180;
	       }
	      } else {
	       if (lng < 31.0) {
	        if (lat < 3.75) {
	         return 353;
	        } else {
	         return 396;
	        }
	       } else {
	        if (lat < 3.75) {
	         return 180;
	        } else {
	         if (lng < 31.25) {
	          return 396;
	         } else {
	          return 180;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 3.75) {
	       return 180;
	      } else {
	       if (lng < 31.75) {
	        return 396;
	       } else {
	        if (lng < 32.0) {
	         return 180;
	        } else {
	         return 396;
	        }
	       }
	      }
	     }
	    } else {
	     return 396;
	    }
	   } else {
	    if (lat < 4.0) {
	     if (lng < 33.0) {
	      if (lat < 3.75) {
	       return 180;
	      } else {
	       if (lng < 32.5) {
	        return 396;
	       } else {
	        return 180;
	       }
	      }
	     } else {
	      if (lat < 3.75) {
	       return 180;
	      } else {
	       if (lng < 33.5) {
	        return 180;
	       } else {
	        return 396;
	       }
	      }
	     }
	    } else {
	     return 396;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup113(double lat, double lng)
	{
	 if (lng < 28.0) {
	  if (lat < 16.75) {
	   if (lng < 24.25) {
	    if (lat < 14.0) {
	     if (lat < 12.25) {
	      if (lng < 23.0) {
	       if (lat < 11.75) {
	        if (lng < 22.75) {
	         return 203;
	        } else {
	         if (lat < 11.5) {
	          return 203;
	         } else {
	          return 323;
	         }
	        }
	       } else {
	        if (lng < 22.75) {
	         return 203;
	        } else {
	         return 323;
	        }
	       }
	      } else {
	       return 323;
	      }
	     } else {
	      return 323;
	     }
	    } else {
	     if (lat < 15.25) {
	      if (lng < 23.0) {
	       if (lat < 14.75) {
	        return 323;
	       } else {
	        if (lng < 22.75) {
	         return 203;
	        } else {
	         if (lat < 15.0) {
	          return 323;
	         } else {
	          return 203;
	         }
	        }
	       }
	      } else {
	       return 323;
	      }
	     } else {
	      if (lng < 23.25) {
	       if (lat < 15.75) {
	        if (lng < 23.0) {
	         return 203;
	        } else {
	         return 323;
	        }
	       } else {
	        return 203;
	       }
	      } else {
	       if (lat < 16.0) {
	        if (lng < 23.75) {
	         if (lat < 15.75) {
	          return 323;
	         } else {
	          return 203;
	         }
	        } else {
	         if (lat < 15.75) {
	          return 323;
	         } else {
	          return 203;
	         }
	        }
	       } else {
	        if (lng < 24.0) {
	         return 203;
	        } else {
	         return 323;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return 323;
	   }
	  } else {
	   if (lat < 19.5) {
	    if (lng < 24.0) {
	     return 203;
	    } else {
	     return 323;
	    }
	   } else {
	    if (lng < 25.25) {
	     if (lat < 21.0) {
	      if (lng < 23.75) {
	       if (lat < 20.25) {
	        if (lng < 23.25) {
	         return 203;
	        } else {
	         if (lat < 20.0) {
	          return 203;
	         } else {
	          return 334;
	         }
	        }
	       } else {
	        if (lng < 22.75) {
	         if (lat < 20.5) {
	          return 203;
	         } else {
	          return 334;
	         }
	        } else {
	         return 334;
	        }
	       }
	      } else {
	       if (lng < 24.5) {
	        if (lat < 20.0) {
	         if (lng < 24.0) {
	          if (lat < 19.75) {
	           return 203;
	          } else {
	           return 334;
	          }
	         } else {
	          return 323;
	         }
	        } else {
	         return 334;
	        }
	       } else {
	        if (lat < 20.25) {
	         if (lng < 24.75) {
	          if (lat < 20.0) {
	           return 323;
	          } else {
	           return 334;
	          }
	         } else {
	          return 323;
	         }
	        } else {
	         if (lng < 25.0) {
	          return 334;
	         } else {
	          return 323;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < 25.0) {
	       return 334;
	      } else {
	       if (lat < 22.0) {
	        return 323;
	       } else {
	        return 112;
	       }
	      }
	     }
	    } else {
	     if (lat < 22.25) {
	      return 323;
	     } else {
	      return 112;
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 16.75) {
	   if (lng < 32.5) {
	    return 323;
	   } else {
	    if (lat < 12.25) {
	     if (lng < 33.0) {
	      if (lat < 12.0) {
	       return 396;
	      } else {
	       if (lng < 32.75) {
	        return 323;
	       } else {
	        return 396;
	       }
	      }
	     } else {
	      if (lat < 11.75) {
	       if (lng < 33.25) {
	        return 396;
	       } else {
	        return 323;
	       }
	      } else {
	       if (lng < 33.25) {
	        return 396;
	       } else {
	        return 323;
	       }
	      }
	     }
	    } else {
	     return 323;
	    }
	   }
	  } else {
	   if (lng < 30.75) {
	    if (lat < 22.25) {
	     return 323;
	    } else {
	     return 112;
	    }
	   } else {
	    if (lat < 22.0) {
	     return 323;
	    } else {
	     if (lng < 32.25) {
	      if (lng < 31.5) {
	       if (lng < 31.0) {
	        if (lat < 22.25) {
	         return 323;
	        } else {
	         return 112;
	        }
	       } else {
	        if (lng < 31.25) {
	         if (lat < 22.25) {
	          return 323;
	         } else {
	          return 112;
	         }
	        } else {
	         if (lat < 22.25) {
	          return 323;
	         } else {
	          return 112;
	         }
	        }
	       }
	      } else {
	       if (lng < 31.75) {
	        if (lat < 22.25) {
	         return 323;
	        } else {
	         return 112;
	        }
	       } else {
	        if (lng < 32.0) {
	         if (lat < 22.25) {
	          return 323;
	         } else {
	          return 112;
	         }
	        } else {
	         if (lat < 22.25) {
	          return 323;
	         } else {
	          return 112;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < 33.0) {
	       if (lng < 32.5) {
	        if (lat < 22.25) {
	         return 323;
	        } else {
	         return 112;
	        }
	       } else {
	        if (lng < 32.75) {
	         if (lat < 22.25) {
	          return 323;
	         } else {
	          return 112;
	         }
	        } else {
	         if (lat < 22.25) {
	          return 323;
	         } else {
	          return 112;
	         }
	        }
	       }
	      } else {
	       if (lng < 33.25) {
	        if (lat < 22.25) {
	         return 323;
	        } else {
	         return 112;
	        }
	       } else {
	        return 112;
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup114(double lat, double lng)
	{
	 if (lat < 5.5) {
	  if (lng < 36.5) {
	   if (lat < 2.75) {
	    if (lng < 35.0) {
	     if (lat < 1.25) {
	      if (lng < 34.25) {
	       if (lat < 0.25) {
	        if (lng < 34.0) {
	         return 180;
	        } else {
	         return 123;
	        }
	       } else {
	        return 180;
	       }
	      } else {
	       if (lat < 0.75) {
	        return 123;
	       } else {
	        if (lng < 34.5) {
	         return 180;
	        } else {
	         return 123;
	        }
	       }
	      }
	     } else {
	      return 180;
	     }
	    } else {
	     if (lat < 1.75) {
	      return 123;
	     } else {
	      if (lng < 35.25) {
	       if (lat < 2.0) {
	        return 180;
	       } else {
	        return 123;
	       }
	      } else {
	       return 123;
	      }
	     }
	    }
	   } else {
	    if (lng < 35.0) {
	     if (lat < 4.0) {
	      if (lng < 34.5) {
	       return 180;
	      } else {
	       if (lat < 3.25) {
	        if (lng < 34.75) {
	         return 180;
	        } else {
	         if (lat < 3.0) {
	          return 180;
	         } else {
	          return 123;
	         }
	        }
	       } else {
	        return 123;
	       }
	      }
	     } else {
	      if (lat < 4.75) {
	       if (lng < 34.25) {
	        if (lat < 4.25) {
	         if (lng < 34.0) {
	          return 396;
	         } else {
	          return 180;
	         }
	        } else {
	         return 396;
	        }
	       } else {
	        if (lng < 34.5) {
	         if (lat < 4.5) {
	          return 123;
	         } else {
	          return 396;
	         }
	        } else {
	         return 123;
	        }
	       }
	      } else {
	       return 396;
	      }
	     }
	    } else {
	     if (lat < 4.5) {
	      return 123;
	     } else {
	      if (lng < 35.75) {
	       if (lat < 4.75) {
	        return 123;
	       } else {
	        return 396;
	       }
	      } else {
	       if (lat < 5.0) {
	        if (lng < 36.0) {
	         if (lat < 4.75) {
	          return 123;
	         } else {
	          return 396;
	         }
	        } else {
	         return 351;
	        }
	       } else {
	        if (lng < 36.0) {
	         return 396;
	        } else {
	         return 351;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 3.75) {
	    return 123;
	   } else {
	    if (lng < 37.75) {
	     if (lat < 4.5) {
	      if (lng < 37.25) {
	       return 123;
	      } else {
	       if (lat < 4.25) {
	        return 123;
	       } else {
	        return 351;
	       }
	      }
	     } else {
	      return 351;
	     }
	    } else {
	     if (lat < 4.0) {
	      if (lng < 38.0) {
	       return 123;
	      } else {
	       return 351;
	      }
	     } else {
	      return 351;
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 8.25) {
	   if (lng < 35.5) {
	    if (lat < 6.75) {
	     if (lng < 35.0) {
	      return 396;
	     } else {
	      if (lat < 6.0) {
	       if (lng < 35.25) {
	        return 396;
	       } else {
	        if (lat < 5.75) {
	         return 396;
	        } else {
	         return 351;
	        }
	       }
	      } else {
	       if (lat < 6.25) {
	        if (lng < 35.25) {
	         return 396;
	        } else {
	         return 351;
	        }
	       } else {
	        return 351;
	       }
	      }
	     }
	    } else {
	     if (lng < 34.5) {
	      if (lat < 7.5) {
	       if (lng < 34.25) {
	        return 396;
	       } else {
	        if (lat < 7.25) {
	         return 396;
	        } else {
	         return 351;
	        }
	       }
	      } else {
	       if (lng < 34.0) {
	        if (lat < 7.75) {
	         return 396;
	        } else {
	         return 351;
	        }
	       } else {
	        return 351;
	       }
	      }
	     } else {
	      if (lat < 7.0) {
	       if (lng < 34.75) {
	        return 396;
	       } else {
	        return 351;
	       }
	      } else {
	       return 351;
	      }
	     }
	    }
	   } else {
	    return 351;
	   }
	  } else {
	   if (lng < 35.0) {
	    if (lat < 9.75) {
	     if (lat < 9.0) {
	      if (lng < 34.25) {
	       if (lat < 8.5) {
	        return 351;
	       } else {
	        return 396;
	       }
	      } else {
	       return 351;
	      }
	     } else {
	      if (lng < 34.25) {
	       if (lat < 9.5) {
	        return 396;
	       } else {
	        if (lng < 34.0) {
	         return 396;
	        } else {
	         return 323;
	        }
	       }
	      } else {
	       return 351;
	      }
	     }
	    } else {
	     if (lat < 10.5) {
	      if (lng < 34.25) {
	       if (lat < 10.0) {
	        if (lng < 34.0) {
	         return 396;
	        } else {
	         return 323;
	        }
	       } else {
	        if (lng < 34.0) {
	         return 396;
	        } else {
	         return 323;
	        }
	       }
	      } else {
	       if (lng < 34.5) {
	        if (lat < 10.25) {
	         return 351;
	        } else {
	         return 323;
	        }
	       } else {
	        return 351;
	       }
	      }
	     } else {
	      if (lng < 34.5) {
	       return 323;
	      } else {
	       if (lat < 11.0) {
	        return 351;
	       } else {
	        return 323;
	       }
	      }
	     }
	    }
	   } else {
	    return 351;
	   }
	  }
	 }
	}

	private  int kdLookup115(double lat, double lng)
	{
	 if (lat < 5.5) {
	  if (lng < 42.0) {
	   if (lat < 2.75) {
	    if (lng < 41.0) {
	     return 123;
	    } else {
	     if (lat < 0.25) {
	      if (lng < 41.25) {
	       return 123;
	      } else {
	       return 251;
	      }
	     } else {
	      return 251;
	     }
	    }
	   } else {
	    if (lng < 40.5) {
	     if (lat < 4.0) {
	      if (lng < 39.75) {
	       if (lat < 3.5) {
	        return 123;
	       } else {
	        return 351;
	       }
	      } else {
	       if (lat < 3.75) {
	        return 123;
	       } else {
	        if (lng < 40.0) {
	         return 351;
	        } else {
	         return 123;
	        }
	       }
	      }
	     } else {
	      if (lat < 4.25) {
	       if (lng < 40.25) {
	        return 351;
	       } else {
	        return 123;
	       }
	      } else {
	       return 351;
	      }
	     }
	    } else {
	     if (lat < 4.0) {
	      if (lng < 41.25) {
	       if (lat < 3.0) {
	        if (lng < 41.0) {
	         return 123;
	        } else {
	         return 251;
	        }
	       } else {
	        return 123;
	       }
	      } else {
	       if (lat < 3.25) {
	        return 251;
	       } else {
	        if (lng < 41.5) {
	         return 123;
	        } else {
	         if (lat < 3.5) {
	          return 251;
	         } else {
	          if (lng < 41.75) {
	           return 123;
	          } else {
	           return 251;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < 41.25) {
	       if (lat < 4.5) {
	        if (lng < 40.75) {
	         if (lat < 4.25) {
	          return 123;
	         } else {
	          return 351;
	         }
	        } else {
	         if (lng < 41.0) {
	          return 123;
	         } else {
	          if (lat < 4.25) {
	           return 123;
	          } else {
	           return 351;
	          }
	         }
	        }
	       } else {
	        return 351;
	       }
	      } else {
	       return 351;
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 2.75) {
	    return 251;
	   } else {
	    if (lng < 43.5) {
	     if (lat < 4.25) {
	      return 251;
	     } else {
	      if (lng < 42.75) {
	       return 351;
	      } else {
	       if (lat < 4.75) {
	        if (lng < 43.0) {
	         if (lat < 4.5) {
	          return 251;
	         } else {
	          return 351;
	         }
	        } else {
	         if (lng < 43.25) {
	          if (lat < 4.5) {
	           return 251;
	          } else {
	           return 351;
	          }
	         } else {
	          return 251;
	         }
	        }
	       } else {
	        return 351;
	       }
	      }
	     }
	    } else {
	     if (lat < 5.0) {
	      return 251;
	     } else {
	      return 351;
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 42.0) {
	   return 351;
	  } else {
	   if (lat < 9.0) {
	    return 351;
	   } else {
	    if (lng < 43.5) {
	     if (lat < 10.0) {
	      return 351;
	     } else {
	      if (lng < 42.75) {
	       if (lat < 11.0) {
	        return 351;
	       } else {
	        if (lng < 42.5) {
	         return 312;
	        } else {
	         return 351;
	        }
	       }
	      } else {
	       if (lat < 10.5) {
	        if (lng < 43.0) {
	         return 351;
	        } else {
	         if (lng < 43.25) {
	          if (lat < 10.25) {
	           return 351;
	          } else {
	           return 251;
	          }
	         } else {
	          return 251;
	         }
	        }
	       } else {
	        if (lng < 43.0) {
	         if (lat < 10.75) {
	          return 251;
	         } else {
	          return 351;
	         }
	        } else {
	         return 251;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 10.0) {
	      if (lng < 44.25) {
	       if (lat < 9.5) {
	        if (lng < 44.0) {
	         return 351;
	        } else {
	         if (lat < 9.25) {
	          return 351;
	         } else {
	          return 251;
	         }
	        }
	       } else {
	        return 251;
	       }
	      } else {
	       return 251;
	      }
	     } else {
	      return 251;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup116(double lat, double lng)
	{
	 if (lat < 16.75) {
	  if (lng < 36.5) {
	   if (lat < 13.5) {
	    if (lng < 35.0) {
	     return 323;
	    } else {
	     if (lat < 12.25) {
	      if (lng < 35.5) {
	       if (lat < 11.75) {
	        if (lng < 35.25) {
	         if (lat < 11.5) {
	          return 351;
	         } else {
	          return 323;
	         }
	        } else {
	         return 351;
	        }
	       } else {
	        if (lng < 35.25) {
	         return 323;
	        } else {
	         if (lat < 12.0) {
	          return 351;
	         } else {
	          return 323;
	         }
	        }
	       }
	      } else {
	       return 351;
	      }
	     } else {
	      if (lng < 35.75) {
	       if (lat < 12.5) {
	        if (lng < 35.5) {
	         return 323;
	        } else {
	         return 351;
	        }
	       } else {
	        return 323;
	       }
	      } else {
	       if (lat < 12.75) {
	        return 351;
	       } else {
	        if (lng < 36.25) {
	         return 323;
	        } else {
	         return 351;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return 323;
	   }
	  } else {
	   if (lat < 14.25) {
	    return 351;
	   } else {
	    if (lng < 37.75) {
	     if (lat < 15.5) {
	      if (lng < 37.0) {
	       if (lat < 14.75) {
	        if (lng < 36.75) {
	         return 323;
	        } else {
	         if (lat < 14.5) {
	          return 351;
	         } else {
	          return 150;
	         }
	        }
	       } else {
	        if (lat < 15.25) {
	         return 150;
	        } else {
	         if (lng < 36.75) {
	          return 323;
	         } else {
	          return 150;
	         }
	        }
	       }
	      } else {
	       if (lat < 14.5) {
	        if (lng < 37.5) {
	         return 351;
	        } else {
	         return 150;
	        }
	       } else {
	        return 150;
	       }
	      }
	     } else {
	      if (lng < 37.0) {
	       if (lat < 16.0) {
	        if (lng < 36.75) {
	         return 323;
	        } else {
	         return 150;
	        }
	       } else {
	        return 323;
	       }
	      } else {
	       return 150;
	      }
	     }
	    } else {
	     if (lat < 15.0) {
	      if (lng < 38.5) {
	       if (lng < 38.0) {
	        if (lat < 14.75) {
	         return 351;
	        } else {
	         return 150;
	        }
	       } else {
	        if (lat < 14.75) {
	         return 351;
	        } else {
	         if (lng < 38.25) {
	          return 351;
	         } else {
	          return 150;
	         }
	        }
	       }
	      } else {
	       if (lng < 38.75) {
	        if (lat < 14.5) {
	         return 351;
	        } else {
	         return 150;
	        }
	       } else {
	        if (lat < 14.5) {
	         return 351;
	        } else {
	         if (lng < 39.0) {
	          return 150;
	         } else {
	          if (lat < 14.75) {
	           return 351;
	          } else {
	           return 150;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      return 150;
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 19.5) {
	   if (lng < 37.0) {
	    return 323;
	   } else {
	    if (lat < 18.0) {
	     if (lng < 38.0) {
	      if (lat < 17.25) {
	       return 150;
	      } else {
	       if (lng < 37.5) {
	        return 323;
	       } else {
	        if (lat < 17.5) {
	         return 150;
	        } else {
	         return 323;
	        }
	       }
	      }
	     } else {
	      if (lng < 38.5) {
	       if (lat < 17.75) {
	        return 150;
	       } else {
	        return 323;
	       }
	      } else {
	       return 150;
	      }
	     }
	    } else {
	     return 323;
	    }
	   }
	  } else {
	   if (lng < 36.5) {
	    if (lat < 21.75) {
	     return 323;
	    } else {
	     if (lng < 34.5) {
	      if (lng < 34.0) {
	       return 112;
	      } else {
	       if (lat < 22.0) {
	        return 323;
	       } else {
	        if (lng < 34.25) {
	         return 112;
	        } else {
	         if (lat < 22.25) {
	          return 323;
	         } else {
	          return 112;
	         }
	        }
	       }
	      }
	     } else {
	      return 323;
	     }
	    }
	   } else {
	    if (lat < 21.0) {
	     return 323;
	    } else {
	     if (lng < 37.75) {
	      return 323;
	     } else {
	      return 164;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup117(double lat, double lng)
	{
	 if (lat < 16.75) {
	  if (lng < 42.0) {
	   if (lat < 14.0) {
	    if (lng < 41.25) {
	     return 351;
	    } else {
	     if (lat < 13.25) {
	      return 351;
	     } else {
	      if (lng < 41.5) {
	       if (lat < 13.75) {
	        return 351;
	       } else {
	        return 150;
	       }
	      } else {
	       if (lat < 13.5) {
	        if (lng < 41.75) {
	         return 351;
	        } else {
	         return 150;
	        }
	       } else {
	        return 150;
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 40.5) {
	     if (lat < 15.25) {
	      if (lng < 39.75) {
	       if (lat < 14.5) {
	        return 351;
	       } else {
	        return 150;
	       }
	      } else {
	       if (lat < 14.5) {
	        return 351;
	       } else {
	        return 150;
	       }
	      }
	     } else {
	      return 150;
	     }
	    } else {
	     if (lat < 15.25) {
	      if (lng < 41.25) {
	       if (lat < 14.5) {
	        if (lng < 40.75) {
	         return 351;
	        } else {
	         if (lng < 41.0) {
	          if (lat < 14.25) {
	           return 351;
	          } else {
	           return 150;
	          }
	         } else {
	          return 150;
	         }
	        }
	       } else {
	        return 150;
	       }
	      } else {
	       return 150;
	      }
	     } else {
	      return 150;
	     }
	    }
	   }
	  } else {
	   if (lat < 14.0) {
	    if (lng < 43.5) {
	     if (lat < 12.5) {
	      if (lng < 42.75) {
	       if (lat < 12.0) {
	        return 312;
	       } else {
	        if (lng < 42.25) {
	         return 351;
	        } else {
	         if (lng < 42.5) {
	          if (lat < 12.25) {
	           return 312;
	          } else {
	           return 351;
	          }
	         } else {
	          return 312;
	         }
	        }
	       }
	      } else {
	       if (lat < 11.75) {
	        if (lng < 43.25) {
	         return 312;
	        } else {
	         return 251;
	        }
	       } else {
	        return 312;
	       }
	      }
	     } else {
	      if (lng < 42.75) {
	       if (lat < 13.25) {
	        if (lng < 42.25) {
	         if (lat < 13.0) {
	          return 351;
	         } else {
	          return 150;
	         }
	        } else {
	         if (lat < 12.75) {
	          if (lng < 42.5) {
	           return 351;
	          } else {
	           return 312;
	          }
	         } else {
	          return 150;
	         }
	        }
	       } else {
	        if (lng < 42.5) {
	         return 150;
	        } else {
	         if (lat < 13.75) {
	          return 150;
	         } else {
	          return 216;
	         }
	        }
	       }
	      } else {
	       if (lat < 13.25) {
	        if (lng < 43.0) {
	         return 150;
	        } else {
	         if (lat < 12.75) {
	          return 312;
	         } else {
	          if (lng < 43.25) {
	           return 150;
	          } else {
	           return 216;
	          }
	         }
	        }
	       } else {
	        return 216;
	       }
	      }
	     }
	    } else {
	     if (lat < 12.5) {
	      if (lng < 44.25) {
	       if (lat < 11.75) {
	        return 251;
	       } else {
	        return 312;
	       }
	      } else {
	       return 0;
	      }
	     } else {
	      return 216;
	     }
	    }
	   } else {
	    if (lng < 43.0) {
	     if (lat < 15.25) {
	      if (lat < 14.25) {
	       if (lng < 42.5) {
	        return 150;
	       } else {
	        return 216;
	       }
	      } else {
	       return 216;
	      }
	     } else {
	      if (lat < 16.25) {
	       return 216;
	      } else {
	       return 164;
	      }
	     }
	    } else {
	     return 216;
	    }
	   }
	  }
	 } else {
	  if (lng < 42.0) {
	   if (lat < 17.75) {
	    return 150;
	   } else {
	    return 164;
	   }
	  } else {
	   if (lat < 17.75) {
	    if (lng < 43.5) {
	     if (lng < 43.25) {
	      return 164;
	     } else {
	      if (lat < 17.0) {
	       return 164;
	      } else {
	       return 216;
	      }
	     }
	    } else {
	     if (lng < 44.25) {
	      if (lat < 17.5) {
	       return 216;
	      } else {
	       if (lng < 43.75) {
	        return 216;
	       } else {
	        return 164;
	       }
	      }
	     } else {
	      if (lat < 17.25) {
	       return 216;
	      } else {
	       if (lng < 44.5) {
	        if (lat < 17.5) {
	         return 216;
	        } else {
	         return 164;
	        }
	       } else {
	        if (lng < 44.75) {
	         if (lat < 17.5) {
	          return 216;
	         } else {
	          return 164;
	         }
	        } else {
	         return 164;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return 164;
	   }
	  }
	 }
	}

	private  int kdLookup118(double lat, double lng)
	{
	 if (lat < 42.0) {
	  if (lng < 25.25) {
	   if (lng < 23.75) {
	    if (lat < 41.25) {
	     return 153;
	    } else {
	     if (lng < 23.0) {
	      return 338;
	     } else {
	      if (lng < 23.25) {
	       if (lat < 41.5) {
	        return 153;
	       } else {
	        if (lat < 41.75) {
	         return 341;
	        } else {
	         return 338;
	        }
	       }
	      } else {
	       if (lat < 41.5) {
	        return 153;
	       } else {
	        return 341;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 40.5) {
	     return 153;
	    } else {
	     if (lng < 24.5) {
	      if (lat < 41.5) {
	       return 153;
	      } else {
	       if (lng < 24.25) {
	        return 341;
	       } else {
	        if (lat < 41.75) {
	         return 153;
	        } else {
	         return 341;
	        }
	       }
	      }
	     } else {
	      if (lat < 41.25) {
	       return 153;
	      } else {
	       if (lng < 24.75) {
	        if (lat < 41.75) {
	         return 153;
	        } else {
	         return 341;
	        }
	       } else {
	        if (lat < 41.5) {
	         return 153;
	        } else {
	         return 341;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 26.5) {
	    if (lat < 40.5) {
	     if (lng < 25.75) {
	      return 153;
	     } else {
	      if (lat < 39.75) {
	       if (lng < 26.0) {
	        return 153;
	       } else {
	        if (lng < 26.25) {
	         return 153;
	        } else {
	         if (lat < 39.5) {
	          return 153;
	         } else {
	          return 206;
	         }
	        }
	       }
	      } else {
	       return 206;
	      }
	     }
	    } else {
	     if (lat < 41.25) {
	      if (lng < 26.25) {
	       return 153;
	      } else {
	       if (lat < 41.0) {
	        return 206;
	       } else {
	        return 153;
	       }
	      }
	     } else {
	      if (lng < 25.75) {
	       if (lat < 41.5) {
	        return 153;
	       } else {
	        return 341;
	       }
	      } else {
	       if (lng < 26.0) {
	        if (lat < 41.5) {
	         return 153;
	        } else {
	         return 341;
	        }
	       } else {
	        if (lat < 41.5) {
	         return 153;
	        } else {
	         if (lng < 26.25) {
	          return 341;
	         } else {
	          if (lat < 41.75) {
	           return 153;
	          } else {
	           return 341;
	          }
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 41.5) {
	     return 206;
	    } else {
	     if (lng < 26.75) {
	      if (lat < 41.75) {
	       return 153;
	      } else {
	       return 206;
	      }
	     } else {
	      return 206;
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 25.25) {
	   if (lat < 43.5) {
	    if (lng < 23.0) {
	     if (lat < 42.75) {
	      if (lat < 42.25) {
	       return 338;
	      } else {
	       if (lng < 22.75) {
	        if (lat < 42.5) {
	         return 341;
	        } else {
	         return 210;
	        }
	       } else {
	        return 341;
	       }
	      }
	     } else {
	      if (lat < 43.0) {
	       return 341;
	      } else {
	       return 210;
	      }
	     }
	    } else {
	     return 341;
	    }
	   } else {
	    if (lng < 23.75) {
	     if (lat < 44.25) {
	      if (lng < 23.0) {
	       if (lat < 43.75) {
	        if (lng < 22.75) {
	         return 210;
	        } else {
	         return 341;
	        }
	       } else {
	        return 341;
	       }
	      } else {
	       if (lng < 23.25) {
	        if (lat < 44.0) {
	         return 341;
	        } else {
	         return 267;
	        }
	       } else {
	        if (lat < 44.0) {
	         return 341;
	        } else {
	         return 267;
	        }
	       }
	      }
	     } else {
	      if (lng < 22.75) {
	       if (lat < 44.75) {
	        return 210;
	       } else {
	        return 267;
	       }
	      } else {
	       return 267;
	      }
	     }
	    } else {
	     if (lng < 24.5) {
	      if (lat < 44.0) {
	       if (lng < 24.0) {
	        return 341;
	       } else {
	        if (lng < 24.25) {
	         if (lat < 43.75) {
	          return 341;
	         } else {
	          return 267;
	         }
	        } else {
	         if (lat < 43.75) {
	          return 341;
	         } else {
	          return 267;
	         }
	        }
	       }
	      } else {
	       return 267;
	      }
	     } else {
	      if (lat < 44.0) {
	       if (lng < 24.75) {
	        return 341;
	       } else {
	        if (lng < 25.0) {
	         if (lat < 43.75) {
	          return 341;
	         } else {
	          return 267;
	         }
	        } else {
	         if (lat < 43.75) {
	          return 341;
	         } else {
	          return 267;
	         }
	        }
	       }
	      } else {
	       return 267;
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 43.5) {
	    if (lng < 27.0) {
	     return 341;
	    } else {
	     if (lat < 42.25) {
	      if (lng < 27.5) {
	       return 206;
	      } else {
	       return 341;
	      }
	     } else {
	      return 341;
	     }
	    }
	   } else {
	    if (lng < 26.5) {
	     if (lat < 44.25) {
	      if (lng < 25.75) {
	       if (lat < 43.75) {
	        return 341;
	       } else {
	        return 267;
	       }
	      } else {
	       if (lng < 26.0) {
	        if (lat < 43.75) {
	         return 341;
	        } else {
	         return 267;
	        }
	       } else {
	        if (lat < 44.0) {
	         return 341;
	        } else {
	         if (lng < 26.25) {
	          return 267;
	         } else {
	          return 341;
	         }
	        }
	       }
	      }
	     } else {
	      return 267;
	     }
	    } else {
	     if (lng < 27.25) {
	      if (lat < 44.25) {
	       return 341;
	      } else {
	       return 267;
	      }
	     } else {
	      if (lat < 44.25) {
	       if (lng < 27.75) {
	        return 341;
	       } else {
	        if (lat < 44.0) {
	         return 341;
	        } else {
	         return 267;
	        }
	       }
	      } else {
	       return 267;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup119(double lat, double lng)
	{
	 if (lng < 36.5) {
	  if (lat < 32.25) {
	   if (lng < 35.0) {
	    if (lat < 31.5) {
	     if (lng < 34.5) {
	      return 112;
	     } else {
	      return 8;
	     }
	    } else {
	     if (lng < 34.25) {
	      return 0;
	     } else {
	      if (lng < 34.5) {
	       return 0;
	      } else {
	       if (lat < 31.75) {
	        if (lng < 34.75) {
	         return 256;
	        } else {
	         return 8;
	        }
	       } else {
	        return 8;
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 35.75) {
	     if (lat < 31.5) {
	      if (lng < 35.5) {
	       return 8;
	      } else {
	       return 194;
	      }
	     } else {
	      if (lng < 35.25) {
	       if (lat < 31.75) {
	        return 345;
	       } else {
	        if (lat < 32.0) {
	         return 8;
	        } else {
	         return 345;
	        }
	       }
	      } else {
	       if (lat < 31.75) {
	        if (lng < 35.5) {
	         return 345;
	        } else {
	         return 194;
	        }
	       } else {
	        return 345;
	       }
	      }
	     }
	    } else {
	     return 194;
	    }
	   }
	  } else {
	   if (lng < 35.0) {
	    return 8;
	   } else {
	    if (lng < 35.75) {
	     if (lat < 33.0) {
	      if (lng < 35.25) {
	       return 8;
	      } else {
	       if (lat < 32.5) {
	        return 345;
	       } else {
	        if (lng < 35.5) {
	         if (lat < 32.75) {
	          return 345;
	         } else {
	          return 8;
	         }
	        } else {
	         return 8;
	        }
	       }
	      }
	     } else {
	      if (lng < 35.25) {
	       if (lat < 33.25) {
	        return 8;
	       } else {
	        return 405;
	       }
	      } else {
	       if (lat < 33.25) {
	        return 8;
	       } else {
	        return 405;
	       }
	      }
	     }
	    } else {
	     if (lat < 33.0) {
	      if (lng < 36.0) {
	       if (lat < 32.75) {
	        return 194;
	       } else {
	        return 369;
	       }
	      } else {
	       if (lat < 32.5) {
	        return 194;
	       } else {
	        if (lng < 36.25) {
	         if (lat < 32.75) {
	          return 194;
	         } else {
	          return 369;
	         }
	        } else {
	         return 369;
	        }
	       }
	      }
	     } else {
	      if (lng < 36.0) {
	       if (lat < 33.5) {
	        return 369;
	       } else {
	        return 405;
	       }
	      } else {
	       return 369;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 32.25) {
	   if (lng < 37.75) {
	    if (lat < 31.5) {
	     if (lng < 37.5) {
	      return 194;
	     } else {
	      if (lat < 31.25) {
	       return 194;
	      } else {
	       return 164;
	      }
	     }
	    } else {
	     if (lng < 37.25) {
	      return 194;
	     } else {
	      if (lat < 31.75) {
	       return 164;
	      } else {
	       return 194;
	      }
	     }
	    }
	   } else {
	    if (lng < 38.5) {
	     if (lat < 31.5) {
	      if (lng < 38.0) {
	       if (lat < 31.0) {
	        return 194;
	       } else {
	        return 164;
	       }
	      } else {
	       return 164;
	      }
	     } else {
	      if (lng < 38.0) {
	       if (lat < 31.75) {
	        return 164;
	       } else {
	        return 194;
	       }
	      } else {
	       if (lat < 32.0) {
	        return 164;
	       } else {
	        return 194;
	       }
	      }
	     }
	    } else {
	     if (lat < 32.0) {
	      return 164;
	     } else {
	      return 194;
	     }
	    }
	   }
	  } else {
	   if (lng < 37.75) {
	    if (lat < 32.75) {
	     if (lng < 37.0) {
	      if (lng < 36.75) {
	       if (lat < 32.5) {
	        return 194;
	       } else {
	        return 369;
	       }
	      } else {
	       if (lat < 32.5) {
	        return 194;
	       } else {
	        return 369;
	       }
	      }
	     } else {
	      if (lng < 37.25) {
	       if (lat < 32.5) {
	        return 194;
	       } else {
	        return 369;
	       }
	      } else {
	       return 194;
	      }
	     }
	    } else {
	     return 369;
	    }
	   } else {
	    if (lng < 38.5) {
	     if (lat < 33.0) {
	      return 194;
	     } else {
	      if (lng < 38.25) {
	       return 369;
	      } else {
	       if (lat < 33.25) {
	        return 194;
	       } else {
	        return 369;
	       }
	      }
	     }
	    } else {
	     if (lat < 33.0) {
	      return 194;
	     } else {
	      if (lng < 38.75) {
	       if (lat < 33.25) {
	        return 194;
	       } else {
	        return 369;
	       }
	      } else {
	       if (lat < 33.25) {
	        if (lng < 39.0) {
	         return 194;
	        } else {
	         return 28;
	        }
	       } else {
	        if (lng < 39.0) {
	         if (lat < 33.5) {
	          return 194;
	         } else {
	          return 369;
	         }
	        } else {
	         if (lat < 33.5) {
	          return 28;
	         } else {
	          return 369;
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup120(double lat, double lng)
	{
	 if (lng < 39.25) {
	  if (lat < 28.0) {
	   if (lng < 36.5) {
	    if (lat < 25.25) {
	     if (lng < 35.0) {
	      return 112;
	     } else {
	      if (lat < 23.75) {
	       if (lng < 35.75) {
	        if (lat < 23.0) {
	         return 323;
	        } else {
	         if (lng < 35.5) {
	          return 112;
	         } else {
	          if (lat < 23.25) {
	           return 323;
	          } else {
	           return 112;
	          }
	         }
	        }
	       } else {
	        if (lat < 23.0) {
	         return 323;
	        } else {
	         if (lng < 36.0) {
	          if (lat < 23.25) {
	           return 323;
	          } else {
	           return 112;
	          }
	         } else {
	          return 0;
	         }
	        }
	       }
	      } else {
	       return 112;
	      }
	     }
	    } else {
	     if (lng < 35.0) {
	      return 112;
	     } else {
	      if (lat < 26.25) {
	       return 112;
	      } else {
	       return 164;
	      }
	     }
	    }
	   } else {
	    if (lat < 22.75) {
	     if (lng < 37.75) {
	      return 323;
	     } else {
	      return 164;
	     }
	    } else {
	     return 164;
	    }
	   }
	  } else {
	   if (lat < 30.75) {
	    if (lng < 36.5) {
	     if (lng < 35.0) {
	      if (lat < 29.25) {
	       if (lng < 34.5) {
	        return 112;
	       } else {
	        if (lat < 28.5) {
	         if (lng < 34.75) {
	          return 112;
	         } else {
	          return 164;
	         }
	        } else {
	         return 112;
	        }
	       }
	      } else {
	       if (lat < 30.0) {
	        return 112;
	       } else {
	        if (lng < 34.75) {
	         return 112;
	        } else {
	         if (lat < 30.25) {
	          return 112;
	         } else {
	          return 8;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 29.25) {
	       return 164;
	      } else {
	       if (lng < 35.75) {
	        if (lat < 30.0) {
	         if (lng < 35.25) {
	          if (lat < 29.5) {
	           return 164;
	          } else {
	           if (lat < 29.75) {
	            return 194;
	           } else {
	            return 8;
	           }
	          }
	         } else {
	          if (lat < 29.5) {
	           return 164;
	          } else {
	           return 194;
	          }
	         }
	        } else {
	         if (lng < 35.25) {
	          return 8;
	         } else {
	          return 194;
	         }
	        }
	       } else {
	        if (lat < 29.5) {
	         if (lng < 36.25) {
	          return 194;
	         } else {
	          return 164;
	         }
	        } else {
	         return 194;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 37.75) {
	      if (lat < 29.5) {
	       return 164;
	      } else {
	       if (lng < 37.0) {
	        if (lat < 30.0) {
	         if (lng < 36.75) {
	          return 194;
	         } else {
	          return 164;
	         }
	        } else {
	         return 194;
	        }
	       } else {
	        if (lat < 30.0) {
	         return 164;
	        } else {
	         if (lng < 37.5) {
	          return 194;
	         } else {
	          if (lat < 30.25) {
	           return 164;
	          } else {
	           return 194;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 30.5) {
	       return 164;
	      } else {
	       if (lng < 38.0) {
	        return 194;
	       } else {
	        return 164;
	       }
	      }
	     }
	    }
	   } else {
	    return kdLookup119(lat,lng);
	   }
	  }
	 } else {
	  if (lat < 29.25) {
	   return 164;
	  } else {
	   if (lng < 42.0) {
	    if (lat < 31.5) {
	     if (lng < 41.75) {
	      return 164;
	     } else {
	      if (lat < 31.25) {
	       return 164;
	      } else {
	       return 28;
	      }
	     }
	    } else {
	     if (lng < 40.5) {
	      if (lat < 32.5) {
	       if (lng < 39.75) {
	        if (lat < 32.25) {
	         return 164;
	        } else {
	         if (lng < 39.5) {
	          return 194;
	         } else {
	          return 28;
	         }
	        }
	       } else {
	        if (lat < 32.0) {
	         return 164;
	        } else {
	         if (lng < 40.0) {
	          if (lat < 32.25) {
	           return 164;
	          } else {
	           return 28;
	          }
	         } else {
	          if (lng < 40.25) {
	           if (lat < 32.25) {
	            return 164;
	           } else {
	            return 28;
	           }
	          } else {
	           return 28;
	          }
	         }
	        }
	       }
	      } else {
	       return 28;
	      }
	     } else {
	      if (lat < 32.0) {
	       if (lng < 41.25) {
	        if (lng < 41.0) {
	         return 164;
	        } else {
	         if (lat < 31.75) {
	          return 164;
	         } else {
	          return 28;
	         }
	        }
	       } else {
	        return 28;
	       }
	      } else {
	       return 28;
	      }
	     }
	    }
	   } else {
	    if (lat < 31.25) {
	     if (lng < 43.5) {
	      if (lat < 30.5) {
	       return 164;
	      } else {
	       if (lng < 42.75) {
	        if (lng < 42.25) {
	         return 164;
	        } else {
	         if (lat < 31.0) {
	          return 164;
	         } else {
	          return 28;
	         }
	        }
	       } else {
	        if (lng < 43.0) {
	         if (lat < 30.75) {
	          return 164;
	         } else {
	          return 28;
	         }
	        } else {
	         return 28;
	        }
	       }
	      }
	     } else {
	      if (lat < 30.25) {
	       if (lng < 44.25) {
	        if (lat < 29.75) {
	         return 164;
	        } else {
	         if (lng < 43.75) {
	          return 164;
	         } else {
	          if (lng < 44.0) {
	           if (lat < 30.0) {
	            return 164;
	           } else {
	            return 28;
	           }
	          } else {
	           return 28;
	          }
	         }
	        }
	       } else {
	        if (lat < 29.75) {
	         if (lng < 44.5) {
	          return 164;
	         } else {
	          if (lng < 44.75) {
	           if (lat < 29.5) {
	            return 164;
	           } else {
	            return 28;
	           }
	          } else {
	           return 28;
	          }
	         }
	        } else {
	         return 28;
	        }
	       }
	      } else {
	       return 28;
	      }
	     }
	    } else {
	     return 28;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup121(double lat, double lng)
	{
	 if (lng < 42.0) {
	  if (lat < 36.5) {
	   if (lng < 40.5) {
	    if (lat < 34.25) {
	     if (lng < 39.75) {
	      return 369;
	     } else {
	      if (lng < 40.0) {
	       if (lat < 34.0) {
	        return 28;
	       } else {
	        return 369;
	       }
	      } else {
	       if (lng < 40.25) {
	        if (lat < 34.0) {
	         return 28;
	        } else {
	         return 369;
	        }
	       } else {
	        return 28;
	       }
	      }
	     }
	    } else {
	     return 369;
	    }
	   } else {
	    if (lat < 35.0) {
	     if (lng < 41.25) {
	      if (lat < 34.25) {
	       return 28;
	      } else {
	       if (lng < 40.75) {
	        return 369;
	       } else {
	        if (lat < 34.5) {
	         return 28;
	        } else {
	         return 369;
	        }
	       }
	      }
	     } else {
	      return 28;
	     }
	    } else {
	     if (lng < 41.25) {
	      return 369;
	     } else {
	      if (lat < 35.75) {
	       if (lng < 41.5) {
	        if (lat < 35.5) {
	         return 28;
	        } else {
	         return 369;
	        }
	       } else {
	        return 28;
	       }
	      } else {
	       if (lng < 41.5) {
	        return 369;
	       } else {
	        return 28;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 40.5) {
	    if (lat < 37.0) {
	     if (lng < 39.75) {
	      if (lng < 39.5) {
	       if (lat < 36.75) {
	        return 369;
	       } else {
	        return 206;
	       }
	      } else {
	       if (lat < 36.75) {
	        return 369;
	       } else {
	        return 206;
	       }
	      }
	     } else {
	      if (lng < 40.0) {
	       if (lat < 36.75) {
	        return 369;
	       } else {
	        return 206;
	       }
	      } else {
	       return 369;
	      }
	     }
	    } else {
	     return 206;
	    }
	   } else {
	    if (lat < 37.25) {
	     if (lng < 41.5) {
	      return 369;
	     } else {
	      if (lat < 36.75) {
	       return 28;
	      } else {
	       return 369;
	      }
	     }
	    } else {
	     return 206;
	    }
	   }
	  }
	 } else {
	  if (lat < 36.75) {
	   return 28;
	  } else {
	   if (lng < 43.5) {
	    if (lat < 37.5) {
	     if (lng < 42.75) {
	      if (lng < 42.25) {
	       if (lat < 37.25) {
	        return 369;
	       } else {
	        return 206;
	       }
	      } else {
	       if (lat < 37.0) {
	        return 28;
	       } else {
	        if (lng < 42.5) {
	         return 369;
	        } else {
	         if (lat < 37.25) {
	          return 28;
	         } else {
	          return 206;
	         }
	        }
	       }
	      }
	     } else {
	      return 28;
	     }
	    } else {
	     return 206;
	    }
	   } else {
	    if (lat < 38.0) {
	     if (lng < 44.25) {
	      if (lat < 37.25) {
	       return 28;
	      } else {
	       if (lng < 44.0) {
	        return 206;
	       } else {
	        if (lat < 37.5) {
	         return 28;
	        } else {
	         return 206;
	        }
	       }
	      }
	     } else {
	      if (lat < 37.25) {
	       return 28;
	      } else {
	       if (lng < 44.5) {
	        if (lat < 37.5) {
	         return 28;
	        } else {
	         return 206;
	        }
	       } else {
	        if (lat < 37.5) {
	         return 206;
	        } else {
	         if (lng < 44.75) {
	          return 206;
	         } else {
	          return 55;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 44.25) {
	      return 206;
	     } else {
	      if (lat < 38.5) {
	       if (lng < 44.5) {
	        return 206;
	       } else {
	        return 55;
	       }
	      } else {
	       if (lng < 44.5) {
	        if (lat < 39.0) {
	         return 206;
	        } else {
	         return 55;
	        }
	       } else {
	        return 55;
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup122(double lat, double lng)
	{
	 if (lng < 42.0) {
	  if (lat < 42.0) {
	   if (lng < 40.5) {
	    return 206;
	   } else {
	    if (lat < 41.5) {
	     return 206;
	    } else {
	     if (lng < 41.25) {
	      return 0;
	     } else {
	      if (lng < 41.5) {
	       return 206;
	      } else {
	       return 47;
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 43.5) {
	    if (lng < 40.5) {
	     if (lat < 42.75) {
	      return 0;
	     } else {
	      if (lng < 39.75) {
	       return 0;
	      } else {
	       if (lng < 40.0) {
	        return 0;
	       } else {
	        if (lat < 43.0) {
	         return 0;
	        } else {
	         if (lng < 40.25) {
	          return 184;
	         } else {
	          return 47;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 41.75) {
	      return 47;
	     } else {
	      if (lat < 43.25) {
	       return 47;
	      } else {
	       return 184;
	      }
	     }
	    }
	   } else {
	    if (lng < 40.5) {
	     if (lat < 43.75) {
	      if (lng < 39.75) {
	       return 0;
	      } else {
	       if (lng < 40.25) {
	        return 184;
	       } else {
	        return 47;
	       }
	      }
	     } else {
	      return 184;
	     }
	    } else {
	     if (lng < 41.0) {
	      if (lat < 43.75) {
	       return 47;
	      } else {
	       return 184;
	      }
	     } else {
	      return 184;
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 42.0) {
	   if (lng < 43.5) {
	    if (lat < 41.25) {
	     return 206;
	    } else {
	     if (lng < 42.75) {
	      if (lng < 42.25) {
	       if (lat < 41.75) {
	        return 206;
	       } else {
	        return 47;
	       }
	      } else {
	       if (lat < 41.5) {
	        return 206;
	       } else {
	        return 47;
	       }
	      }
	     } else {
	      if (lng < 43.0) {
	       if (lat < 41.75) {
	        return 206;
	       } else {
	        return 47;
	       }
	      } else {
	       if (lat < 41.5) {
	        if (lng < 43.25) {
	         return 206;
	        } else {
	         return 47;
	        }
	       } else {
	        return 47;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 40.5) {
	     if (lng < 44.25) {
	      if (lat < 40.25) {
	       return 206;
	      } else {
	       if (lng < 43.75) {
	        return 206;
	       } else {
	        return 48;
	       }
	      }
	     } else {
	      if (lat < 39.75) {
	       if (lng < 44.5) {
	        if (lat < 39.5) {
	         return 55;
	        } else {
	         return 206;
	        }
	       } else {
	        return 55;
	       }
	      } else {
	       if (lng < 44.5) {
	        if (lat < 40.25) {
	         return 206;
	        } else {
	         return 48;
	        }
	       } else {
	        if (lat < 40.0) {
	         if (lng < 44.75) {
	          return 206;
	         } else {
	          return 48;
	         }
	        } else {
	         return 48;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 44.25) {
	      if (lat < 41.25) {
	       if (lng < 43.75) {
	        return 206;
	       } else {
	        return 48;
	       }
	      } else {
	       return 47;
	      }
	     } else {
	      if (lat < 41.25) {
	       return 48;
	      } else {
	       return 47;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 43.5) {
	    if (lat < 43.25) {
	     if (lng < 43.25) {
	      return 47;
	     } else {
	      if (lat < 43.0) {
	       return 47;
	      } else {
	       return 184;
	      }
	     }
	    } else {
	     return 184;
	    }
	   } else {
	    if (lat < 43.0) {
	     if (lng < 44.25) {
	      if (lat < 42.75) {
	       return 47;
	      } else {
	       if (lng < 44.0) {
	        return 47;
	       } else {
	        return 184;
	       }
	      }
	     } else {
	      if (lat < 42.75) {
	       return 47;
	      } else {
	       return 184;
	      }
	     }
	    } else {
	     return 184;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup123(double lat, double lng)
	{
	 if (lng < 33.75) {
	  if (lat < 33.75) {
	   if (lng < 28.0) {
	    if (lat < 28.0) {
	     if (lng < 25.0) {
	      return 334;
	     } else {
	      return 112;
	     }
	    } else {
	     if (lat < 30.75) {
	      if (lng < 25.0) {
	       return 334;
	      } else {
	       return 112;
	      }
	     } else {
	      if (lng < 25.25) {
	       if (lat < 32.25) {
	        if (lng < 25.0) {
	         return 334;
	        } else {
	         if (lat < 31.5) {
	          if (lat < 31.0) {
	           return 334;
	          } else {
	           return 112;
	          }
	         } else {
	          if (lat < 31.75) {
	           return 112;
	          } else {
	           return 334;
	          }
	         }
	        }
	       } else {
	        return 334;
	       }
	      } else {
	       return 112;
	      }
	     }
	    }
	   } else {
	    return 112;
	   }
	  } else {
	   if (lng < 28.0) {
	    if (lat < 39.25) {
	     if (lng < 25.25) {
	      return 153;
	     } else {
	      if (lat < 36.5) {
	       return 153;
	      } else {
	       if (lng < 26.5) {
	        return 153;
	       } else {
	        if (lat < 37.75) {
	         if (lng < 27.25) {
	          return 153;
	         } else {
	          return 206;
	         }
	        } else {
	         if (lng < 27.25) {
	          if (lat < 38.5) {
	           if (lng < 26.75) {
	            if (lat < 38.0) {
	             return 153;
	            } else {
	             return 206;
	            }
	           } else {
	            if (lat < 38.0) {
	             return 153;
	            } else {
	             return 206;
	            }
	           }
	          } else {
	           if (lng < 26.75) {
	            if (lat < 38.75) {
	             return 206;
	            } else {
	             return 153;
	            }
	           } else {
	            return 206;
	           }
	          }
	         } else {
	          return 206;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     return kdLookup118(lat,lng);
	    }
	   } else {
	    if (lat < 39.25) {
	     if (lng < 30.75) {
	      if (lat < 36.5) {
	       if (lng < 29.25) {
	        return 153;
	       } else {
	        return 206;
	       }
	      } else {
	       if (lng < 28.25) {
	        if (lat < 36.75) {
	         return 153;
	        } else {
	         return 206;
	        }
	       } else {
	        return 206;
	       }
	      }
	     } else {
	      if (lat < 36.0) {
	       return 175;
	      } else {
	       return 206;
	      }
	     }
	    } else {
	     if (lng < 30.75) {
	      if (lat < 42.0) {
	       return 206;
	      } else {
	       if (lat < 43.5) {
	        return 341;
	       } else {
	        if (lng < 29.25) {
	         if (lat < 44.25) {
	          if (lng < 28.5) {
	           if (lat < 44.0) {
	            return 341;
	           } else {
	            return 267;
	           }
	          } else {
	           if (lng < 28.75) {
	            if (lat < 43.75) {
	             return 341;
	            } else {
	             return 267;
	            }
	           } else {
	            if (lat < 43.75) {
	             return 341;
	            } else {
	             return 267;
	            }
	           }
	          }
	         } else {
	          return 267;
	         }
	        } else {
	         return 267;
	        }
	       }
	      }
	     } else {
	      if (lat < 42.5) {
	       return 206;
	      } else {
	       return 198;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 33.75) {
	   return kdLookup120(lat,lng);
	  } else {
	   if (lng < 39.25) {
	    if (lat < 39.25) {
	     if (lng < 36.5) {
	      if (lat < 36.5) {
	       if (lng < 35.0) {
	        if (lat < 35.0) {
	         return 175;
	        } else {
	         if (lat < 35.75) {
	          return 175;
	         } else {
	          if (lng < 34.25) {
	           return 206;
	          } else {
	           return 175;
	          }
	         }
	        }
	       } else {
	        if (lat < 35.0) {
	         if (lng < 35.75) {
	          return 405;
	         } else {
	          if (lat < 34.25) {
	           if (lng < 36.25) {
	            return 405;
	           } else {
	            if (lat < 34.0) {
	             return 369;
	            } else {
	             return 405;
	            }
	           }
	          } else {
	           if (lng < 36.0) {
	            if (lat < 34.75) {
	             return 405;
	            } else {
	             return 369;
	            }
	           } else {
	            if (lat < 34.75) {
	             return 405;
	            } else {
	             return 369;
	            }
	           }
	          }
	         }
	        } else {
	         if (lng < 35.75) {
	          return 0;
	         } else {
	          if (lat < 36.0) {
	           return 369;
	          } else {
	           return 206;
	          }
	         }
	        }
	       }
	      } else {
	       return 206;
	      }
	     } else {
	      if (lat < 36.5) {
	       if (lng < 36.75) {
	        if (lat < 36.25) {
	         if (lat < 36.0) {
	          if (lat < 35.75) {
	           if (lat < 35.5) {
	            if (lat < 35.25) {
	             if (lat < 35.0) {
	              if (lat < 34.25) {
	               return 369;
	              } else {
	               if (lat < 34.5) {
	                return 405;
	               } else {
	                return 369;
	               }
	              }
	             } else {
	              return 369;
	             }
	            } else {
	             return 369;
	            }
	           } else {
	            return 369;
	           }
	          } else {
	           return 369;
	          }
	         } else {
	          return 369;
	         }
	        } else {
	         return 206;
	        }
	       } else {
	        return 369;
	       }
	      } else {
	       if (lng < 37.75) {
	        if (lat < 37.0) {
	         if (lng < 37.0) {
	          if (lng < 36.75) {
	           return 206;
	          } else {
	           return 369;
	          }
	         } else {
	          if (lng < 37.25) {
	           return 369;
	          } else {
	           if (lng < 37.5) {
	            if (lat < 36.75) {
	             return 369;
	            } else {
	             return 206;
	            }
	           } else {
	            if (lat < 36.75) {
	             return 369;
	            } else {
	             return 206;
	            }
	           }
	          }
	         }
	        } else {
	         return 206;
	        }
	       } else {
	        if (lat < 37.0) {
	         if (lng < 38.5) {
	          if (lng < 38.0) {
	           if (lat < 36.75) {
	            return 369;
	           } else {
	            return 206;
	           }
	          } else {
	           return 369;
	          }
	         } else {
	          if (lng < 38.75) {
	           return 369;
	          } else {
	           if (lng < 39.0) {
	            if (lat < 36.75) {
	             return 369;
	            } else {
	             return 206;
	            }
	           } else {
	            if (lat < 36.75) {
	             return 369;
	            } else {
	             return 206;
	            }
	           }
	          }
	         }
	        } else {
	         return 206;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 42.0) {
	      return 206;
	     } else {
	      if (lng < 36.5) {
	       if (lat < 43.5) {
	        return 206;
	       } else {
	        return 198;
	       }
	      } else {
	       return 184;
	      }
	     }
	    }
	   } else {
	    if (lat < 39.25) {
	     return kdLookup121(lat,lng);
	    } else {
	     return kdLookup122(lat,lng);
	    }
	   }
	  }
	 }
	}

	private  int kdLookup124(double lat, double lng)
	{
	 if (lng < 22.5) {
	  if (lat < 22.5) {
	   if (lng < 11.25) {
	    return kdLookup99(lat,lng);
	   } else {
	    if (lat < 11.25) {
	     if (lng < 16.75) {
	      if (lat < 5.5) {
	       return kdLookup100(lat,lng);
	      } else {
	       return kdLookup101(lat,lng);
	      }
	     } else {
	      return kdLookup102(lat,lng);
	     }
	    } else {
	     return kdLookup104(lat,lng);
	    }
	   }
	  } else {
	   if (lng < 11.25) {
	    if (lat < 33.75) {
	     if (lng < 7.75) {
	      return 381;
	     } else {
	      return kdLookup105(lat,lng);
	     }
	    } else {
	     return kdLookup106(lat,lng);
	    }
	   } else {
	    if (lat < 33.75) {
	     return kdLookup107(lat,lng);
	    } else {
	     return kdLookup109(lat,lng);
	    }
	   }
	  }
	 } else {
	  if (lat < 22.5) {
	   if (lng < 33.75) {
	    if (lat < 11.25) {
	     if (lng < 28.0) {
	      if (lat < 5.5) {
	       return kdLookup110(lat,lng);
	      } else {
	       return kdLookup111(lat,lng);
	      }
	     } else {
	      if (lat < 5.5) {
	       return kdLookup112(lat,lng);
	      } else {
	       if (lng < 30.75) {
	        if (lat < 9.5) {
	         return 396;
	        } else {
	         if (lng < 29.25) {
	          if (lat < 9.75) {
	           if (lng < 28.75) {
	            return 323;
	           } else {
	            return 396;
	           }
	          } else {
	           return 323;
	          }
	         } else {
	          if (lat < 10.25) {
	           if (lng < 30.0) {
	            if (lng < 29.5) {
	             if (lat < 10.0) {
	              return 396;
	             } else {
	              return 323;
	             }
	            } else {
	             return 396;
	            }
	           } else {
	            if (lng < 30.5) {
	             return 396;
	            } else {
	             if (lat < 10.0) {
	              return 396;
	             } else {
	              return 323;
	             }
	            }
	           }
	          } else {
	           return 323;
	          }
	         }
	        }
	       } else {
	        if (lat < 8.25) {
	         if (lng < 33.25) {
	          return 396;
	         } else {
	          if (lat < 7.75) {
	           return 396;
	          } else {
	           if (lng < 33.5) {
	            if (lat < 8.0) {
	             return 396;
	            } else {
	             return 351;
	            }
	           } else {
	            return 351;
	           }
	          }
	         }
	        } else {
	         if (lng < 32.25) {
	          if (lat < 9.75) {
	           return 396;
	          } else {
	           if (lng < 31.5) {
	            if (lat < 10.0) {
	             if (lng < 31.0) {
	              return 323;
	             } else {
	              return 396;
	             }
	            } else {
	             return 323;
	            }
	           } else {
	            if (lat < 10.5) {
	             if (lng < 31.75) {
	              if (lat < 10.25) {
	               return 396;
	              } else {
	               return 323;
	              }
	             } else {
	              return 396;
	             }
	            } else {
	             if (lng < 32.0) {
	              return 323;
	             } else {
	              if (lat < 10.75) {
	               return 396;
	              } else {
	               return 323;
	              }
	             }
	            }
	           }
	          }
	         } else {
	          if (lat < 9.75) {
	           if (lng < 33.25) {
	            return 396;
	           } else {
	            if (lat < 8.5) {
	             return 351;
	            } else {
	             return 396;
	            }
	           }
	          } else {
	           if (lng < 33.0) {
	            if (lat < 11.0) {
	             return 396;
	            } else {
	             if (lng < 32.5) {
	              return 323;
	             } else {
	              return 396;
	             }
	            }
	           } else {
	            if (lat < 10.75) {
	             return 396;
	            } else {
	             if (lng < 33.25) {
	              return 396;
	             } else {
	              return 323;
	             }
	            }
	           }
	          }
	         }
	        }
	       }
	      }
	     }
	    } else {
	     return kdLookup113(lat,lng);
	    }
	   } else {
	    if (lat < 11.25) {
	     if (lng < 39.25) {
	      return kdLookup114(lat,lng);
	     } else {
	      return kdLookup115(lat,lng);
	     }
	    } else {
	     if (lng < 39.25) {
	      return kdLookup116(lat,lng);
	     } else {
	      return kdLookup117(lat,lng);
	     }
	    }
	   }
	  } else {
	   return kdLookup123(lat,lng);
	  }
	 }
	}

	private  int kdLookup125(double lat, double lng)
	{
	 if (lat < 47.75) {
	  if (lng < 6.75) {
	   if (lat < 46.25) {
	    return 298;
	   } else {
	    if (lat < 47.0) {
	     if (lng < 6.25) {
	      return 298;
	     } else {
	      if (lat < 46.5) {
	       if (lng < 6.5) {
	        return 173;
	       } else {
	        return 298;
	       }
	      } else {
	       if (lng < 6.5) {
	        if (lat < 46.75) {
	         return 173;
	        } else {
	         return 298;
	        }
	       } else {
	        return 173;
	       }
	      }
	     }
	    } else {
	     return 298;
	    }
	   }
	  } else {
	   if (lat < 46.25) {
	    if (lng < 7.5) {
	     if (lat < 45.5) {
	      if (lng < 7.0) {
	       if (lat < 45.25) {
	        return 272;
	       } else {
	        return 298;
	       }
	      } else {
	       if (lng < 7.25) {
	        if (lat < 45.25) {
	         return 272;
	        } else {
	         return 298;
	        }
	       } else {
	        return 272;
	       }
	      }
	     } else {
	      if (lng < 7.0) {
	       return 298;
	      } else {
	       if (lat < 45.75) {
	        if (lng < 7.25) {
	         return 298;
	        } else {
	         return 272;
	        }
	       } else {
	        if (lng < 7.25) {
	         if (lat < 46.0) {
	          return 272;
	         } else {
	          return 298;
	         }
	        } else {
	         if (lat < 46.0) {
	          return 272;
	         } else {
	          return 173;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 46.0) {
	      return 272;
	     } else {
	      if (lng < 8.0) {
	       return 173;
	      } else {
	       return 272;
	      }
	     }
	    }
	   } else {
	    if (lng < 7.5) {
	     if (lat < 47.0) {
	      if (lng < 7.0) {
	       if (lat < 46.5) {
	        return 298;
	       } else {
	        return 173;
	       }
	      } else {
	       return 173;
	      }
	     } else {
	      if (lng < 7.0) {
	       if (lat < 47.25) {
	        return 173;
	       } else {
	        return 298;
	       }
	      } else {
	       if (lat < 47.5) {
	        return 173;
	       } else {
	        if (lng < 7.25) {
	         return 173;
	        } else {
	         return 298;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 47.5) {
	      return 173;
	     } else {
	      if (lng < 7.75) {
	       return 298;
	      } else {
	       return 173;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 6.75) {
	   if (lat < 49.5) {
	    return 298;
	   } else {
	    if (lng < 6.0) {
	     if (lat < 49.75) {
	      return 298;
	     } else {
	      return 255;
	     }
	    } else {
	     if (lat < 50.0) {
	      if (lng < 6.25) {
	       return 212;
	      } else {
	       if (lng < 6.5) {
	        if (lat < 49.75) {
	         return 298;
	        } else {
	         return 212;
	        }
	       } else {
	        if (lat < 49.75) {
	         return 6;
	        } else {
	         return 212;
	        }
	       }
	      }
	     } else {
	      if (lng < 6.25) {
	       if (lat < 50.25) {
	        return 212;
	       } else {
	        return 255;
	       }
	      } else {
	       return 6;
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 49.0) {
	    if (lng < 7.75) {
	     return 298;
	    } else {
	     if (lat < 48.5) {
	      return 6;
	     } else {
	      if (lng < 8.0) {
	       return 298;
	      } else {
	       return 6;
	      }
	     }
	    }
	   } else {
	    if (lng < 7.5) {
	     if (lat < 49.25) {
	      return 298;
	     } else {
	      return 6;
	     }
	    } else {
	     if (lat < 49.25) {
	      return 298;
	     } else {
	      return 6;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup126(double lat, double lng)
	{
	 if (lat < 50.5) {
	  if (lng < 8.25) {
	   return kdLookup125(lat,lng);
	  } else {
	   if (lat < 47.75) {
	    if (lng < 9.75) {
	     if (lat < 46.25) {
	      if (lng < 9.0) {
	       return 272;
	      } else {
	       if (lat < 46.0) {
	        return 272;
	       } else {
	        if (lng < 9.25) {
	         return 173;
	        } else {
	         return 272;
	        }
	       }
	      }
	     } else {
	      if (lng < 9.0) {
	       if (lat < 46.5) {
	        if (lng < 8.5) {
	         return 272;
	        } else {
	         return 173;
	        }
	       } else {
	        return 173;
	       }
	      } else {
	       if (lat < 46.5) {
	        if (lng < 9.25) {
	         return 173;
	        } else {
	         return 272;
	        }
	       } else {
	        return 173;
	       }
	      }
	     }
	    } else {
	     if (lat < 46.5) {
	      return 272;
	     } else {
	      if (lng < 10.5) {
	       if (lat < 47.0) {
	        if (lng < 10.25) {
	         return 173;
	        } else {
	         if (lat < 46.75) {
	          return 272;
	         } else {
	          return 173;
	         }
	        }
	       } else {
	        if (lng < 10.0) {
	         if (lat < 47.25) {
	          return 173;
	         } else {
	          return 277;
	         }
	        } else {
	         if (lat < 47.5) {
	          return 277;
	         } else {
	          return 6;
	         }
	        }
	       }
	      } else {
	       if (lat < 47.0) {
	        return 272;
	       } else {
	        if (lng < 11.0) {
	         return 277;
	        } else {
	         if (lat < 47.5) {
	          return 277;
	         } else {
	          return 6;
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 11.0) {
	     if (lng < 10.75) {
	      if (lat < 48.0) {
	       if (lng < 10.5) {
	        if (lng < 10.25) {
	         if (lng < 10.0) {
	          if (lng < 9.75) {
	           if (lng < 9.5) {
	            if (lng < 9.25) {
	             if (lng < 9.0) {
	              if (lng < 8.5) {
	               return 6;
	              } else {
	               if (lng < 8.75) {
	                return 173;
	               } else {
	                return 6;
	               }
	              }
	             } else {
	              return 6;
	             }
	            } else {
	             return 6;
	            }
	           } else {
	            return 6;
	           }
	          } else {
	           return 6;
	          }
	         } else {
	          return 6;
	         }
	        } else {
	         return 6;
	        }
	       } else {
	        return 6;
	       }
	      } else {
	       return 6;
	      }
	     } else {
	      return 6;
	     }
	    } else {
	     return 6;
	    }
	   }
	  }
	 } else {
	  if (lng < 8.25) {
	   if (lat < 53.25) {
	    if (lng < 6.75) {
	     if (lat < 51.75) {
	      if (lng < 6.0) {
	       if (lat < 51.25) {
	        return 255;
	       } else {
	        if (lng < 5.75) {
	         if (lat < 51.5) {
	          return 255;
	         } else {
	          return 386;
	         }
	        } else {
	         return 386;
	        }
	       }
	      } else {
	       if (lat < 51.0) {
	        if (lng < 6.25) {
	         return 255;
	        } else {
	         if (lng < 6.5) {
	          if (lat < 50.75) {
	           return 255;
	          } else {
	           return 6;
	          }
	         } else {
	          return 6;
	         }
	        }
	       } else {
	        if (lng < 6.25) {
	         if (lat < 51.25) {
	          return 6;
	         } else {
	          return 386;
	         }
	        } else {
	         return 6;
	        }
	       }
	      }
	     } else {
	      if (lat < 52.0) {
	       if (lng < 6.0) {
	        return 386;
	       } else {
	        return 6;
	       }
	      } else {
	       return 386;
	      }
	     }
	    } else {
	     if (lat < 52.0) {
	      return 6;
	     } else {
	      if (lng < 7.25) {
	       if (lat < 52.25) {
	        if (lng < 7.0) {
	         return 386;
	        } else {
	         return 6;
	        }
	       } else {
	        if (lat < 53.0) {
	         if (lat < 52.5) {
	          return 386;
	         } else {
	          if (lng < 7.0) {
	           if (lat < 52.75) {
	            return 6;
	           } else {
	            return 386;
	           }
	          } else {
	           if (lat < 52.75) {
	            return 6;
	           } else {
	            return 386;
	           }
	          }
	         }
	        } else {
	         return 386;
	        }
	       }
	      } else {
	       return 6;
	      }
	     }
	    }
	   } else {
	    if (lat < 54.75) {
	     if (lng < 6.75) {
	      return 386;
	     } else {
	      if (lng < 7.5) {
	       if (lat < 54.0) {
	        if (lng < 7.0) {
	         return 386;
	        } else {
	         if (lat < 53.5) {
	          if (lng < 7.25) {
	           return 386;
	          } else {
	           return 6;
	          }
	         } else {
	          return 6;
	         }
	        }
	       } else {
	        return 0;
	       }
	      } else {
	       return 6;
	      }
	     }
	    } else {
	     return 320;
	    }
	   }
	  } else {
	   if (lat < 54.75) {
	    return 6;
	   } else {
	    if (lng < 9.75) {
	     if (lng < 9.0) {
	      if (lat < 55.0) {
	       return 6;
	      } else {
	       return 320;
	      }
	     } else {
	      if (lat < 55.0) {
	       return 6;
	      } else {
	       return 320;
	      }
	     }
	    } else {
	     if (lng < 10.5) {
	      if (lat < 55.0) {
	       return 6;
	      } else {
	       return 320;
	      }
	     } else {
	      return 320;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup127(double lat, double lng)
	{
	 if (lng < 14.0) {
	  if (lat < 47.75) {
	   if (lng < 12.5) {
	    if (lat < 47.0) {
	     return 272;
	    } else {
	     if (lng < 11.75) {
	      if (lat < 47.25) {
	       if (lng < 11.5) {
	        return 277;
	       } else {
	        return 272;
	       }
	      } else {
	       if (lng < 11.5) {
	        if (lat < 47.5) {
	         return 277;
	        } else {
	         return 6;
	        }
	       } else {
	        return 277;
	       }
	      }
	     } else {
	      if (lng < 12.0) {
	       return 277;
	      } else {
	       if (lat < 47.25) {
	        if (lng < 12.25) {
	         return 272;
	        } else {
	         return 277;
	        }
	       } else {
	        return 277;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 46.25) {
	     if (lng < 13.5) {
	      return 272;
	     } else {
	      if (lat < 45.5) {
	       return 30;
	      } else {
	       if (lat < 45.75) {
	        return 178;
	       } else {
	        if (lng < 13.75) {
	         if (lat < 46.0) {
	          return 272;
	         } else {
	          return 178;
	         }
	        } else {
	         if (lat < 46.0) {
	          return 272;
	         } else {
	          return 178;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 13.25) {
	      if (lat < 47.0) {
	       if (lng < 12.75) {
	        if (lat < 46.75) {
	         return 272;
	        } else {
	         return 277;
	        }
	       } else {
	        if (lat < 46.75) {
	         return 272;
	        } else {
	         return 277;
	        }
	       }
	      } else {
	       if (lng < 13.0) {
	        return 277;
	       } else {
	        if (lat < 47.5) {
	         return 277;
	        } else {
	         return 6;
	        }
	       }
	      }
	     } else {
	      if (lat < 46.75) {
	       if (lng < 13.5) {
	        return 272;
	       } else {
	        if (lng < 13.75) {
	         if (lat < 46.5) {
	          return 178;
	         } else {
	          return 272;
	         }
	        } else {
	         return 178;
	        }
	       }
	      } else {
	       return 277;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 12.5) {
	    return 6;
	   } else {
	    if (lat < 49.0) {
	     if (lng < 13.25) {
	      if (lat < 48.25) {
	       if (lng < 13.0) {
	        return 6;
	       } else {
	        return 277;
	       }
	      } else {
	       return 6;
	      }
	     } else {
	      if (lat < 48.5) {
	       return 277;
	      } else {
	       if (lng < 13.5) {
	        return 6;
	       } else {
	        if (lng < 13.75) {
	         if (lat < 48.75) {
	          return 277;
	         } else {
	          return 6;
	         }
	        } else {
	         if (lat < 48.75) {
	          return 277;
	         } else {
	          return 6;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 13.25) {
	      if (lat < 49.75) {
	       if (lng < 12.75) {
	        return 6;
	       } else {
	        if (lat < 49.5) {
	         return 6;
	        } else {
	         return 221;
	        }
	       }
	      } else {
	       return 221;
	      }
	     } else {
	      if (lat < 49.25) {
	       if (lng < 13.5) {
	        return 6;
	       } else {
	        return 221;
	       }
	      } else {
	       return 221;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 47.75) {
	   if (lng < 15.25) {
	    if (lat < 46.25) {
	     if (lng < 14.5) {
	      if (lat < 45.5) {
	       return 30;
	      } else {
	       return 178;
	      }
	     } else {
	      if (lat < 45.5) {
	       return 30;
	      } else {
	       if (lng < 14.75) {
	        return 178;
	       } else {
	        if (lat < 45.75) {
	         if (lng < 15.0) {
	          return 30;
	         } else {
	          return 178;
	         }
	        } else {
	         return 178;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 46.75) {
	      if (lng < 14.5) {
	       if (lng < 14.25) {
	        if (lat < 46.5) {
	         return 178;
	        } else {
	         return 277;
	        }
	       } else {
	        if (lat < 46.5) {
	         return 178;
	        } else {
	         return 277;
	        }
	       }
	      } else {
	       if (lng < 14.75) {
	        if (lat < 46.5) {
	         return 178;
	        } else {
	         return 277;
	        }
	       } else {
	        return 178;
	       }
	      }
	     } else {
	      return 277;
	     }
	    }
	   } else {
	    if (lat < 46.25) {
	     if (lng < 16.0) {
	      if (lat < 45.5) {
	       return 30;
	      } else {
	       if (lng < 15.5) {
	        return 178;
	       } else {
	        if (lat < 46.0) {
	         return 30;
	        } else {
	         if (lng < 15.75) {
	          return 178;
	         } else {
	          return 30;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 45.25) {
	       return 349;
	      } else {
	       return 30;
	      }
	     }
	    } else {
	     if (lng < 16.0) {
	      if (lat < 46.75) {
	       return 178;
	      } else {
	       return 277;
	      }
	     } else {
	      if (lat < 47.0) {
	       if (lng < 16.25) {
	        if (lat < 46.5) {
	         return 30;
	        } else {
	         return 178;
	        }
	       } else {
	        if (lat < 46.5) {
	         return 30;
	        } else {
	         if (lng < 16.5) {
	          return 178;
	         } else {
	          if (lat < 46.75) {
	           return 30;
	          } else {
	           return 199;
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < 16.5) {
	        return 277;
	       } else {
	        if (lat < 47.5) {
	         return 199;
	        } else {
	         return 277;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 15.25) {
	    if (lat < 49.0) {
	     if (lng < 14.5) {
	      if (lat < 48.75) {
	       return 277;
	      } else {
	       return 221;
	      }
	     } else {
	      if (lat < 48.75) {
	       return 277;
	      } else {
	       if (lng < 15.0) {
	        return 221;
	       } else {
	        return 277;
	       }
	      }
	     }
	    } else {
	     return 221;
	    }
	   } else {
	    if (lat < 49.0) {
	     if (lng < 16.25) {
	      return 277;
	     } else {
	      if (lat < 48.75) {
	       return 277;
	      } else {
	       if (lng < 16.5) {
	        return 221;
	       } else {
	        return 277;
	       }
	      }
	     }
	    } else {
	     return 221;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup128(double lat, double lng)
	{
	 if (lat < 53.25) {
	  if (lng < 14.0) {
	   if (lng < 13.25) {
	    return 6;
	   } else {
	    if (lat < 50.75) {
	     return 221;
	    } else {
	     return 6;
	    }
	   }
	  } else {
	   if (lng < 15.25) {
	    if (lat < 51.75) {
	     if (lng < 14.5) {
	      if (lat < 51.0) {
	       return 221;
	      } else {
	       return 6;
	      }
	     } else {
	      if (lat < 51.0) {
	       return 221;
	      } else {
	       if (lng < 14.75) {
	        if (lat < 51.25) {
	         return 221;
	        } else {
	         return 6;
	        }
	       } else {
	        if (lat < 51.25) {
	         if (lng < 15.0) {
	          return 6;
	         } else {
	          return 221;
	         }
	        } else {
	         if (lng < 15.0) {
	          return 6;
	         } else {
	          if (lat < 51.5) {
	           return 6;
	          } else {
	           return 187;
	          }
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 52.5) {
	      if (lng < 14.75) {
	       return 6;
	      } else {
	       return 187;
	      }
	     } else {
	      if (lng < 14.5) {
	       if (lat < 53.0) {
	        return 6;
	       } else {
	        if (lng < 14.25) {
	         return 6;
	        } else {
	         return 187;
	        }
	       }
	      } else {
	       if (lng < 14.75) {
	        if (lat < 52.75) {
	         return 6;
	        } else {
	         return 187;
	        }
	       } else {
	        return 187;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 51.0) {
	     if (lng < 16.0) {
	      if (lng < 15.75) {
	       return 221;
	      } else {
	       if (lat < 50.75) {
	        return 221;
	       } else {
	        return 187;
	       }
	      }
	     } else {
	      if (lng < 16.25) {
	       if (lat < 50.75) {
	        return 221;
	       } else {
	        return 187;
	       }
	      } else {
	       if (lng < 16.5) {
	        if (lat < 50.75) {
	         return 221;
	        } else {
	         return 187;
	        }
	       } else {
	        return 187;
	       }
	      }
	     }
	    } else {
	     return 187;
	    }
	   }
	  }
	 } else {
	  if (lng < 14.0) {
	   if (lat < 54.75) {
	    if (lng < 12.5) {
	     if (lat < 54.25) {
	      return 6;
	     } else {
	      if (lng < 11.75) {
	       if (lng < 11.5) {
	        if (lat < 54.5) {
	         return 6;
	        } else {
	         return 320;
	        }
	       } else {
	        return 320;
	       }
	      } else {
	       if (lng < 12.0) {
	        if (lat < 54.5) {
	         return 6;
	        } else {
	         return 320;
	        }
	       } else {
	        if (lng < 12.25) {
	         if (lat < 54.5) {
	          return 6;
	         } else {
	          return 320;
	         }
	        } else {
	         return 6;
	        }
	       }
	      }
	     }
	    } else {
	     return 6;
	    }
	   } else {
	    if (lng < 12.5) {
	     return 320;
	    } else {
	     if (lng < 13.0) {
	      if (lat < 55.5) {
	       return 320;
	      } else {
	       if (lat < 55.75) {
	        if (lng < 12.75) {
	         return 320;
	        } else {
	         return 376;
	        }
	       } else {
	        if (lng < 12.75) {
	         return 320;
	        } else {
	         return 376;
	        }
	       }
	      }
	     } else {
	      if (lat < 55.25) {
	       return 6;
	      } else {
	       return 376;
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 54.75) {
	    if (lng < 15.25) {
	     if (lat < 54.0) {
	      if (lng < 14.5) {
	       return 6;
	      } else {
	       return 187;
	      }
	     } else {
	      if (lng < 14.5) {
	       return 6;
	      } else {
	       return 187;
	      }
	     }
	    } else {
	     return 187;
	    }
	   } else {
	    if (lng < 15.25) {
	     if (lat < 55.5) {
	      if (lng < 14.5) {
	       return 376;
	      } else {
	       return 320;
	      }
	     } else {
	      if (lng < 14.5) {
	       return 376;
	      } else {
	       if (lng < 14.75) {
	        return 376;
	       } else {
	        if (lat < 55.75) {
	         return 320;
	        } else {
	         return 376;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 16.0) {
	      if (lat < 55.5) {
	       return 320;
	      } else {
	       return 376;
	      }
	     } else {
	      if (lat < 55.5) {
	       return 187;
	      } else {
	       return 376;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup129(double lat, double lng)
	{
	 if (lat < 47.75) {
	  if (lng < 18.0) {
	   if (lat < 46.25) {
	    if (lng < 17.25) {
	     if (lat < 45.25) {
	      return 349;
	     } else {
	      return 30;
	     }
	    } else {
	     if (lat < 45.5) {
	      if (lng < 17.5) {
	       if (lat < 45.25) {
	        return 349;
	       } else {
	        return 30;
	       }
	      } else {
	       if (lng < 17.75) {
	        if (lat < 45.25) {
	         return 349;
	        } else {
	         return 30;
	        }
	       } else {
	        if (lat < 45.25) {
	         return 349;
	        } else {
	         return 30;
	        }
	       }
	      }
	     } else {
	      if (lng < 17.5) {
	       return 30;
	      } else {
	       if (lat < 46.0) {
	        return 30;
	       } else {
	        return 199;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 46.5) {
	     if (lng < 17.0) {
	      return 30;
	     } else {
	      return 199;
	     }
	    } else {
	     return 199;
	    }
	   }
	  } else {
	   if (lat < 46.0) {
	    if (lng < 18.75) {
	     if (lat < 45.25) {
	      return 349;
	     } else {
	      return 30;
	     }
	    } else {
	     if (lat < 45.5) {
	      if (lng < 19.25) {
	       return 30;
	      } else {
	       return 210;
	      }
	     } else {
	      if (lng < 19.0) {
	       return 30;
	      } else {
	       if (lng < 19.25) {
	        if (lat < 45.75) {
	         return 30;
	        } else {
	         return 210;
	        }
	       } else {
	        return 210;
	       }
	      }
	     }
	    }
	   } else {
	    return 199;
	   }
	  }
	 } else {
	  if (lng < 18.0) {
	   if (lat < 49.0) {
	    if (lng < 17.25) {
	     if (lat < 48.25) {
	      return 277;
	     } else {
	      if (lat < 48.5) {
	       if (lng < 17.0) {
	        return 277;
	       } else {
	        return 163;
	       }
	      } else {
	       if (lng < 17.0) {
	        if (lat < 48.75) {
	         return 277;
	        } else {
	         return 221;
	        }
	       } else {
	        if (lat < 48.75) {
	         return 163;
	        } else {
	         return 221;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 48.25) {
	      if (lng < 17.5) {
	       return 199;
	      } else {
	       if (lng < 17.75) {
	        if (lat < 48.0) {
	         return 199;
	        } else {
	         return 163;
	        }
	       } else {
	        return 163;
	       }
	      }
	     } else {
	      return 163;
	     }
	    }
	   } else {
	    if (lat < 50.25) {
	     return 221;
	    } else {
	     if (lng < 17.25) {
	      return 187;
	     } else {
	      if (lng < 17.75) {
	       return 221;
	      } else {
	       return 187;
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 49.0) {
	    if (lng < 18.75) {
	     return 163;
	    } else {
	     if (lat < 48.25) {
	      if (lng < 19.0) {
	       if (lat < 48.0) {
	        return 199;
	       } else {
	        return 163;
	       }
	      } else {
	       return 199;
	      }
	     } else {
	      return 163;
	     }
	    }
	   } else {
	    if (lng < 18.75) {
	     if (lat < 49.75) {
	      if (lng < 18.25) {
	       if (lat < 49.25) {
	        return 163;
	       } else {
	        return 221;
	       }
	      } else {
	       if (lat < 49.5) {
	        return 163;
	       } else {
	        return 221;
	       }
	      }
	     } else {
	      if (lng < 18.25) {
	       if (lat < 50.25) {
	        return 221;
	       } else {
	        return 187;
	       }
	      } else {
	       if (lat < 50.0) {
	        return 221;
	       } else {
	        return 187;
	       }
	      }
	     }
	    } else {
	     if (lat < 49.75) {
	      if (lng < 19.0) {
	       if (lat < 49.5) {
	        return 163;
	       } else {
	        return 221;
	       }
	      } else {
	       if (lat < 49.5) {
	        return 163;
	       } else {
	        if (lng < 19.25) {
	         return 187;
	        } else {
	         return 163;
	        }
	       }
	      }
	     } else {
	      return 187;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup130(double lat, double lng)
	{
	 if (lat < 50.5) {
	  if (lng < 19.5) {
	   return kdLookup129(lat,lng);
	  } else {
	   if (lat < 47.75) {
	    if (lng < 21.0) {
	     if (lat < 46.25) {
	      if (lng < 20.5) {
	       return 210;
	      } else {
	       if (lat < 46.0) {
	        return 210;
	       } else {
	        return 267;
	       }
	      }
	     } else {
	      return 199;
	     }
	    } else {
	     if (lat < 46.25) {
	      if (lng < 21.5) {
	       if (lat < 45.5) {
	        if (lng < 21.25) {
	         return 210;
	        } else {
	         if (lat < 45.25) {
	          return 210;
	         } else {
	          return 267;
	         }
	        }
	       } else {
	        return 267;
	       }
	      } else {
	       return 267;
	      }
	     } else {
	      if (lng < 21.75) {
	       if (lat < 46.75) {
	        if (lng < 21.25) {
	         if (lat < 46.5) {
	          return 267;
	         } else {
	          return 199;
	         }
	        } else {
	         if (lng < 21.5) {
	          if (lat < 46.5) {
	           return 267;
	          } else {
	           return 199;
	          }
	         } else {
	          return 267;
	         }
	        }
	       } else {
	        return 199;
	       }
	      } else {
	       if (lat < 47.25) {
	        return 267;
	       } else {
	        if (lng < 22.0) {
	         return 199;
	        } else {
	         if (lng < 22.25) {
	          if (lat < 47.5) {
	           return 267;
	          } else {
	           return 199;
	          }
	         } else {
	          return 267;
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 21.0) {
	     if (lat < 49.0) {
	      if (lng < 20.25) {
	       if (lat < 48.25) {
	        return 199;
	       } else {
	        return 163;
	       }
	      } else {
	       if (lat < 48.5) {
	        return 199;
	       } else {
	        if (lng < 20.75) {
	         return 163;
	        } else {
	         if (lat < 48.75) {
	          return 199;
	         } else {
	          return 163;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < 20.25) {
	       if (lat < 49.75) {
	        if (lng < 19.75) {
	         return 163;
	        } else {
	         if (lat < 49.25) {
	          return 163;
	         } else {
	          if (lng < 20.0) {
	           if (lat < 49.5) {
	            return 163;
	           } else {
	            return 187;
	           }
	          } else {
	           return 187;
	          }
	         }
	        }
	       } else {
	        return 187;
	       }
	      } else {
	       if (lat < 49.5) {
	        return 163;
	       } else {
	        return 187;
	       }
	      }
	     }
	    } else {
	     if (lat < 49.0) {
	      if (lng < 21.75) {
	       if (lat < 48.75) {
	        return 199;
	       } else {
	        return 163;
	       }
	      } else {
	       if (lat < 48.5) {
	        return 199;
	       } else {
	        if (lng < 22.25) {
	         return 163;
	        } else {
	         if (lat < 48.75) {
	          return 388;
	         } else {
	          return 163;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < 21.75) {
	       if (lat < 49.5) {
	        return 163;
	       } else {
	        return 187;
	       }
	      } else {
	       if (lat < 49.5) {
	        if (lng < 22.25) {
	         return 163;
	        } else {
	         if (lat < 49.25) {
	          return 163;
	         } else {
	          return 187;
	         }
	        }
	       } else {
	        return 187;
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 19.5) {
	   return 187;
	  } else {
	   if (lat < 54.5) {
	    return 187;
	   } else {
	    if (lng < 21.0) {
	     if (lat < 55.25) {
	      if (lng < 20.0) {
	       if (lat < 54.75) {
	        return 187;
	       } else {
	        return 176;
	       }
	      } else {
	       return 176;
	      }
	     } else {
	      return 0;
	     }
	    } else {
	     if (lat < 55.25) {
	      return 176;
	     } else {
	      return 53;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup131(double lat, double lng)
	{
	 if (lat < 61.75) {
	  if (lng < 13.0) {
	   if (lat < 59.0) {
	    if (lat < 57.5) {
	     if (lng < 12.0) {
	      return 320;
	     } else {
	      if (lat < 56.5) {
	       if (lng < 12.75) {
	        return 320;
	       } else {
	        return 376;
	       }
	      } else {
	       return 376;
	      }
	     }
	    } else {
	     return 376;
	    }
	   } else {
	    if (lat < 60.25) {
	     if (lng < 12.0) {
	      if (lat < 59.25) {
	       if (lng < 11.5) {
	        return 376;
	       } else {
	        if (lng < 11.75) {
	         return 356;
	        } else {
	         return 376;
	        }
	       }
	      } else {
	       if (lat < 60.0) {
	        if (lng < 11.75) {
	         return 356;
	        } else {
	         if (lat < 59.5) {
	          return 356;
	         } else {
	          if (lat < 59.75) {
	           return 376;
	          } else {
	           return 356;
	          }
	         }
	        }
	       } else {
	        return 356;
	       }
	      }
	     } else {
	      if (lat < 60.0) {
	       return 376;
	      } else {
	       if (lng < 12.5) {
	        return 356;
	       } else {
	        return 376;
	       }
	      }
	     }
	    } else {
	     if (lng < 12.25) {
	      return 356;
	     } else {
	      if (lat < 61.0) {
	       if (lng < 12.5) {
	        return 356;
	       } else {
	        if (lat < 60.5) {
	         if (lng < 12.75) {
	          return 356;
	         } else {
	          return 376;
	         }
	        } else {
	         if (lng < 12.75) {
	          if (lat < 60.75) {
	           return 356;
	          } else {
	           return 376;
	          }
	         } else {
	          return 376;
	         }
	        }
	       }
	      } else {
	       if (lng < 12.5) {
	        if (lat < 61.25) {
	         return 376;
	        } else {
	         return 356;
	        }
	       } else {
	        if (lat < 61.25) {
	         return 376;
	        } else {
	         if (lng < 12.75) {
	          return 356;
	         } else {
	          if (lat < 61.5) {
	           return 356;
	          } else {
	           return 376;
	          }
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   return 376;
	  }
	 } else {
	  if (lat < 64.5) {
	   if (lng < 14.0) {
	    if (lng < 12.5) {
	     if (lat < 63.0) {
	      if (lng < 12.25) {
	       return 356;
	      } else {
	       if (lat < 62.25) {
	        return 376;
	       } else {
	        if (lat < 62.5) {
	         return 356;
	        } else {
	         return 376;
	        }
	       }
	      }
	     } else {
	      if (lat < 63.75) {
	       if (lng < 12.0) {
	        return 356;
	       } else {
	        if (lat < 63.25) {
	         if (lng < 12.25) {
	          return 356;
	         } else {
	          return 376;
	         }
	        } else {
	         if (lng < 12.25) {
	          if (lat < 63.5) {
	           return 376;
	          } else {
	           return 356;
	          }
	         } else {
	          return 376;
	         }
	        }
	       }
	      } else {
	       return 356;
	      }
	     }
	    } else {
	     if (lat < 64.0) {
	      return 376;
	     } else {
	      if (lng < 13.25) {
	       if (lng < 12.75) {
	        return 356;
	       } else {
	        if (lng < 13.0) {
	         if (lat < 64.25) {
	          return 376;
	         } else {
	          return 356;
	         }
	        } else {
	         if (lat < 64.25) {
	          return 376;
	         } else {
	          return 356;
	         }
	        }
	       }
	      } else {
	       if (lng < 13.5) {
	        if (lat < 64.25) {
	         return 376;
	        } else {
	         return 356;
	        }
	       } else {
	        if (lng < 13.75) {
	         if (lat < 64.25) {
	          return 376;
	         } else {
	          return 356;
	         }
	        } else {
	         if (lat < 64.25) {
	          return 376;
	         } else {
	          return 356;
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 14.25) {
	     if (lat < 64.25) {
	      return 376;
	     } else {
	      return 356;
	     }
	    } else {
	     return 376;
	    }
	   }
	  } else {
	   if (lng < 14.0) {
	    return 356;
	   } else {
	    if (lat < 66.0) {
	     if (lng < 14.75) {
	      if (lat < 65.25) {
	       if (lng < 14.25) {
	        if (lat < 65.0) {
	         return 376;
	        } else {
	         return 356;
	        }
	       } else {
	        return 376;
	       }
	      } else {
	       if (lng < 14.5) {
	        return 356;
	       } else {
	        if (lat < 65.5) {
	         return 376;
	        } else {
	         return 356;
	        }
	       }
	      }
	     } else {
	      return 376;
	     }
	    } else {
	     if (lng < 15.25) {
	      if (lat < 66.25) {
	       if (lng < 14.75) {
	        return 356;
	       } else {
	        return 376;
	       }
	      } else {
	       return 356;
	      }
	     } else {
	      if (lng < 16.0) {
	       if (lat < 66.75) {
	        if (lng < 15.5) {
	         if (lat < 66.25) {
	          return 376;
	         } else {
	          return 356;
	         }
	        } else {
	         return 376;
	        }
	       } else {
	        return 356;
	       }
	      } else {
	       if (lat < 67.0) {
	        return 376;
	       } else {
	        if (lng < 16.5) {
	         return 356;
	        } else {
	         return 376;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup132(double lat, double lng)
	{
	 if (lng < 11.25) {
	  if (lat < 56.25) {
	   if (lng < 5.5) {
	    if (lat < 50.5) {
	     if (lng < 2.75) {
	      return 298;
	     } else {
	      if (lat < 49.75) {
	       return 298;
	      } else {
	       if (lng < 4.25) {
	        return 298;
	       } else {
	        if (lng < 4.75) {
	         if (lat < 50.0) {
	          return 298;
	         } else {
	          return 255;
	         }
	        } else {
	         if (lng < 5.0) {
	          if (lat < 50.25) {
	           return 298;
	          } else {
	           return 255;
	          }
	         } else {
	          if (lat < 50.0) {
	           if (lng < 5.25) {
	            return 298;
	           } else {
	            return 255;
	           }
	          } else {
	           return 255;
	          }
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 53.25) {
	      if (lng < 2.75) {
	       if (lng < 1.5) {
	        return 304;
	       } else {
	        if (lat < 51.75) {
	         if (lng < 2.0) {
	          if (lat < 51.25) {
	           return 298;
	          } else {
	           return 304;
	          }
	         } else {
	          return 298;
	         }
	        } else {
	         return 304;
	        }
	       }
	      } else {
	       if (lng < 4.0) {
	        if (lat < 51.75) {
	         if (lng < 3.25) {
	          if (lat < 51.0) {
	           return 298;
	          } else {
	           return 255;
	          }
	         } else {
	          if (lat < 51.0) {
	           if (lng < 3.5) {
	            if (lat < 50.75) {
	             return 298;
	            } else {
	             return 255;
	            }
	           } else {
	            return 255;
	           }
	          } else {
	           if (lng < 3.5) {
	            return 255;
	           } else {
	            if (lat < 51.25) {
	             return 255;
	            } else {
	             if (lng < 3.75) {
	              return 386;
	             } else {
	              if (lat < 51.5) {
	               return 255;
	              } else {
	               return 386;
	              }
	             }
	            }
	           }
	          }
	         }
	        } else {
	         return 386;
	        }
	       } else {
	        if (lat < 51.75) {
	         if (lng < 4.75) {
	          if (lat < 51.25) {
	           return 255;
	          } else {
	           if (lng < 4.25) {
	            return 386;
	           } else {
	            if (lng < 4.5) {
	             return 255;
	            } else {
	             if (lat < 51.5) {
	              return 255;
	             } else {
	              return 386;
	             }
	            }
	           }
	          }
	         } else {
	          if (lat < 51.5) {
	           return 255;
	          } else {
	           return 386;
	          }
	         }
	        } else {
	         return 386;
	        }
	       }
	      }
	     } else {
	      if (lng < 2.75) {
	       return 304;
	      } else {
	       return 386;
	      }
	     }
	    }
	   } else {
	    return kdLookup126(lat,lng);
	   }
	  } else {
	   if (lng < 5.5) {
	    return 356;
	   } else {
	    if (lat < 61.75) {
	     if (lng < 8.25) {
	      if (lat < 58.0) {
	       return 320;
	      } else {
	       return 356;
	      }
	     } else {
	      if (lat < 59.0) {
	       if (lng < 9.75) {
	        if (lat < 57.5) {
	         return 320;
	        } else {
	         return 356;
	        }
	       } else {
	        if (lat < 57.75) {
	         return 320;
	        } else {
	         if (lng < 10.5) {
	          if (lat < 58.25) {
	           return 320;
	          } else {
	           return 356;
	          }
	         } else {
	          if (lat < 58.25) {
	           return 320;
	          } else {
	           return 376;
	          }
	         }
	        }
	       }
	      } else {
	       return 356;
	      }
	     }
	    } else {
	     return 356;
	    }
	   }
	  }
	 } else {
	  if (lat < 56.25) {
	   if (lng < 16.75) {
	    if (lat < 50.5) {
	     return kdLookup127(lat,lng);
	    } else {
	     return kdLookup128(lat,lng);
	    }
	   } else {
	    return kdLookup130(lat,lng);
	   }
	  } else {
	   if (lng < 16.75) {
	    return kdLookup131(lat,lng);
	   } else {
	    if (lat < 61.75) {
	     if (lng < 19.5) {
	      return 376;
	     } else {
	      if (lat < 59.0) {
	       if (lng < 21.0) {
	        return 333;
	       } else {
	        if (lat < 57.5) {
	         if (lng < 21.75) {
	          if (lat < 56.5) {
	           if (lng < 21.5) {
	            return 333;
	           } else {
	            return 53;
	           }
	          } else {
	           return 333;
	          }
	         } else {
	          if (lat < 56.5) {
	           return 53;
	          } else {
	           return 333;
	          }
	         }
	        } else {
	         if (lng < 21.75) {
	          return 333;
	         } else {
	          if (lat < 58.0) {
	           return 333;
	          } else {
	           return 49;
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < 21.0) {
	        return 189;
	       } else {
	        if (lat < 60.0) {
	         return 49;
	        } else {
	         return 324;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 21.0) {
	      return 376;
	     } else {
	      if (lat < 64.5) {
	       if (lat < 63.75) {
	        return 324;
	       } else {
	        return 376;
	       }
	      } else {
	       return 376;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup133(double lat, double lng)
	{
	 if (lng < 25.25) {
	  if (lat < 47.75) {
	   return 267;
	  } else {
	   if (lng < 23.75) {
	    if (lat < 49.0) {
	     if (lng < 23.0) {
	      if (lat < 48.25) {
	       if (lng < 22.75) {
	        if (lat < 48.0) {
	         return 267;
	        } else {
	         return 199;
	        }
	       } else {
	        if (lat < 48.0) {
	         return 267;
	        } else {
	         return 199;
	        }
	       }
	      } else {
	       if (lat < 48.5) {
	        if (lng < 22.75) {
	         return 199;
	        } else {
	         return 388;
	        }
	       } else {
	        return 388;
	       }
	      }
	     } else {
	      if (lat < 48.25) {
	       if (lng < 23.25) {
	        if (lat < 48.0) {
	         return 267;
	        } else {
	         return 388;
	        }
	       } else {
	        if (lng < 23.5) {
	         return 267;
	        } else {
	         if (lat < 48.0) {
	          return 267;
	         } else {
	          return 388;
	         }
	        }
	       }
	      } else {
	       if (lng < 23.5) {
	        return 388;
	       } else {
	        if (lat < 48.75) {
	         return 388;
	        } else {
	         return 276;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 49.75) {
	      if (lng < 23.0) {
	       if (lat < 49.25) {
	        if (lng < 22.75) {
	         return 163;
	        } else {
	         return 388;
	        }
	       } else {
	        if (lng < 22.75) {
	         return 187;
	        } else {
	         return 276;
	        }
	       }
	      } else {
	       return 276;
	      }
	     } else {
	      if (lng < 23.0) {
	       return 187;
	      } else {
	       if (lng < 23.25) {
	        if (lat < 50.0) {
	         return 276;
	        } else {
	         return 187;
	        }
	       } else {
	        if (lat < 50.25) {
	         return 276;
	        } else {
	         return 187;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 48.75) {
	     if (lng < 24.5) {
	      if (lat < 48.25) {
	       if (lng < 24.0) {
	        if (lat < 48.0) {
	         return 267;
	        } else {
	         return 388;
	        }
	       } else {
	        if (lng < 24.25) {
	         if (lat < 48.0) {
	          return 267;
	         } else {
	          return 388;
	         }
	        } else {
	         if (lat < 48.0) {
	          return 267;
	         } else {
	          return 388;
	         }
	        }
	       }
	      } else {
	       if (lng < 24.0) {
	        return 388;
	       } else {
	        if (lng < 24.25) {
	         if (lat < 48.5) {
	          return 388;
	         } else {
	          return 276;
	         }
	        } else {
	         if (lat < 48.5) {
	          return 388;
	         } else {
	          return 276;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 48.25) {
	       if (lng < 24.75) {
	        if (lat < 48.0) {
	         return 267;
	        } else {
	         return 388;
	        }
	       } else {
	        if (lng < 25.0) {
	         if (lat < 48.0) {
	          return 267;
	         } else {
	          return 276;
	         }
	        } else {
	         return 276;
	        }
	       }
	      } else {
	       if (lng < 24.75) {
	        if (lat < 48.5) {
	         return 388;
	        } else {
	         return 276;
	        }
	       } else {
	        return 276;
	       }
	      }
	     }
	    } else {
	     return 276;
	    }
	   }
	  }
	 } else {
	  if (lat < 47.75) {
	   if (lng < 27.5) {
	    return 267;
	   } else {
	    if (lat < 47.5) {
	     return 267;
	    } else {
	     return 295;
	    }
	   }
	  } else {
	   if (lng < 26.5) {
	    if (lat < 48.25) {
	     if (lng < 25.75) {
	      if (lng < 25.5) {
	       if (lat < 48.0) {
	        return 267;
	       } else {
	        return 276;
	       }
	      } else {
	       if (lat < 48.0) {
	        return 267;
	       } else {
	        return 276;
	       }
	      }
	     } else {
	      if (lng < 26.0) {
	       if (lat < 48.0) {
	        return 267;
	       } else {
	        return 276;
	       }
	      } else {
	       if (lng < 26.25) {
	        if (lat < 48.0) {
	         return 267;
	        } else {
	         return 276;
	        }
	       } else {
	        return 267;
	       }
	      }
	     }
	    } else {
	     return 276;
	    }
	   } else {
	    if (lat < 48.5) {
	     if (lng < 27.25) {
	      if (lng < 26.75) {
	       if (lat < 48.25) {
	        return 267;
	       } else {
	        return 276;
	       }
	      } else {
	       if (lat < 48.25) {
	        return 267;
	       } else {
	        return 295;
	       }
	      }
	     } else {
	      if (lng < 27.5) {
	       if (lat < 48.0) {
	        return 267;
	       } else {
	        return 295;
	       }
	      } else {
	       return 295;
	      }
	     }
	    } else {
	     return 276;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup134(double lat, double lng)
	{
	 if (lng < 25.25) {
	  if (lat < 54.75) {
	   if (lng < 23.75) {
	    if (lat < 54.0) {
	     return 187;
	    } else {
	     if (lng < 23.0) {
	      if (lat < 54.5) {
	       return 187;
	      } else {
	       if (lng < 22.75) {
	        return 176;
	       } else {
	        return 53;
	       }
	      }
	     } else {
	      if (lng < 23.25) {
	       if (lat < 54.5) {
	        return 187;
	       } else {
	        return 53;
	       }
	      } else {
	       if (lat < 54.25) {
	        if (lng < 23.5) {
	         return 187;
	        } else {
	         return 53;
	        }
	       } else {
	        if (lng < 23.5) {
	         if (lat < 54.5) {
	          return 187;
	         } else {
	          return 53;
	         }
	        } else {
	         return 53;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 24.5) {
	     if (lat < 54.0) {
	      if (lng < 24.0) {
	       if (lat < 53.5) {
	        return 187;
	       } else {
	        return 400;
	       }
	      } else {
	       return 400;
	      }
	     } else {
	      return 53;
	     }
	    } else {
	     if (lat < 54.0) {
	      return 400;
	     } else {
	      if (lng < 25.0) {
	       return 53;
	      } else {
	       if (lat < 54.25) {
	        return 400;
	       } else {
	        return 53;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 23.0) {
	    if (lat < 55.25) {
	     if (lng < 22.75) {
	      return 176;
	     } else {
	      if (lat < 55.0) {
	       return 176;
	      } else {
	       return 53;
	      }
	     }
	    } else {
	     return 53;
	    }
	   } else {
	    return 53;
	   }
	  }
	 } else {
	  if (lat < 54.75) {
	   if (lng < 26.0) {
	    if (lat < 54.25) {
	     return 400;
	    } else {
	     if (lng < 25.5) {
	      if (lat < 54.5) {
	       return 400;
	      } else {
	       return 53;
	      }
	     } else {
	      if (lng < 25.75) {
	       if (lat < 54.5) {
	        return 400;
	       } else {
	        return 53;
	       }
	      } else {
	       if (lat < 54.5) {
	        return 53;
	       } else {
	        return 400;
	       }
	      }
	     }
	    }
	   } else {
	    return 400;
	   }
	  } else {
	   if (lng < 26.5) {
	    if (lat < 55.5) {
	     if (lng < 26.0) {
	      return 53;
	     } else {
	      if (lat < 55.0) {
	       return 400;
	      } else {
	       if (lng < 26.25) {
	        return 53;
	       } else {
	        if (lat < 55.25) {
	         return 400;
	        } else {
	         return 53;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 26.0) {
	      return 53;
	     } else {
	      if (lat < 56.0) {
	       return 53;
	      } else {
	       return 333;
	      }
	     }
	    }
	   } else {
	    if (lng < 27.25) {
	     if (lat < 55.5) {
	      if (lng < 26.75) {
	       if (lat < 55.25) {
	        return 400;
	       } else {
	        return 53;
	       }
	      } else {
	       return 400;
	      }
	     } else {
	      if (lng < 26.75) {
	       if (lat < 55.75) {
	        return 53;
	       } else {
	        return 333;
	       }
	      } else {
	       if (lat < 55.75) {
	        return 400;
	       } else {
	        if (lng < 27.0) {
	         return 333;
	        } else {
	         if (lat < 56.0) {
	          return 400;
	         } else {
	          return 333;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 56.0) {
	      return 400;
	     } else {
	      return 333;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup135(double lat, double lng)
	{
	 if (lat < 47.75) {
	  if (lng < 29.25) {
	   if (lat < 46.25) {
	    if (lng < 28.5) {
	     if (lat < 45.5) {
	      return 267;
	     } else {
	      if (lat < 45.75) {
	       if (lng < 28.25) {
	        return 267;
	       } else {
	        return 276;
	       }
	      } else {
	       if (lng < 28.25) {
	        return 267;
	       } else {
	        return 295;
	       }
	      }
	     }
	    } else {
	     if (lat < 45.5) {
	      if (lng < 28.75) {
	       return 267;
	      } else {
	       if (lng < 29.0) {
	        if (lat < 45.25) {
	         return 267;
	        } else {
	         return 276;
	        }
	       } else {
	        return 267;
	       }
	      }
	     } else {
	      if (lng < 28.75) {
	       return 295;
	      } else {
	       if (lat < 46.0) {
	        return 276;
	       } else {
	        if (lng < 29.0) {
	         return 295;
	        } else {
	         return 276;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 47.0) {
	     if (lng < 28.5) {
	      if (lat < 46.5) {
	       if (lng < 28.25) {
	        return 267;
	       } else {
	        return 295;
	       }
	      } else {
	       if (lng < 28.25) {
	        return 267;
	       } else {
	        return 295;
	       }
	      }
	     } else {
	      if (lng < 29.0) {
	       return 295;
	      } else {
	       if (lat < 46.5) {
	        return 276;
	       } else {
	        return 295;
	       }
	      }
	     }
	    } else {
	     if (lng < 28.25) {
	      if (lat < 47.25) {
	       return 267;
	      } else {
	       return 295;
	      }
	     } else {
	      return 295;
	     }
	    }
	   }
	  } else {
	   if (lat < 46.0) {
	    if (lng < 30.0) {
	     if (lat < 45.5) {
	      return 267;
	     } else {
	      return 276;
	     }
	    } else {
	     return 276;
	    }
	   } else {
	    if (lat < 46.75) {
	     if (lng < 30.0) {
	      if (lng < 29.5) {
	       if (lat < 46.5) {
	        return 276;
	       } else {
	        return 295;
	       }
	      } else {
	       if (lat < 46.5) {
	        return 276;
	       } else {
	        return 295;
	       }
	      }
	     } else {
	      return 276;
	     }
	    } else {
	     if (lng < 30.0) {
	      if (lat < 47.25) {
	       if (lng < 29.75) {
	        return 295;
	       } else {
	        if (lat < 47.0) {
	         return 295;
	        } else {
	         return 276;
	        }
	       }
	      } else {
	       if (lng < 29.5) {
	        if (lat < 47.5) {
	         return 295;
	        } else {
	         return 276;
	        }
	       } else {
	        if (lng < 29.75) {
	         if (lat < 47.5) {
	          return 295;
	         } else {
	          return 276;
	         }
	        } else {
	         return 276;
	        }
	       }
	      }
	     } else {
	      return 276;
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 29.25) {
	   if (lat < 48.5) {
	    if (lng < 28.5) {
	     if (lat < 48.25) {
	      return 295;
	     } else {
	      if (lng < 28.25) {
	       return 295;
	      } else {
	       return 276;
	      }
	     }
	    } else {
	     if (lng < 28.75) {
	      if (lat < 48.25) {
	       return 295;
	      } else {
	       return 276;
	      }
	     } else {
	      if (lat < 48.0) {
	       return 295;
	      } else {
	       if (lng < 29.0) {
	        if (lat < 48.25) {
	         return 295;
	        } else {
	         return 276;
	        }
	       } else {
	        return 276;
	       }
	      }
	     }
	    }
	   } else {
	    return 276;
	   }
	  } else {
	   return 276;
	  }
	 }
	}

	private  int kdLookup136(double lat, double lng)
	{
	 if (lng < 30.75) {
	  if (lat < 53.25) {
	   if (lng < 29.25) {
	    if (lat < 51.75) {
	     if (lng < 28.75) {
	      return 276;
	     } else {
	      if (lat < 51.5) {
	       return 276;
	      } else {
	       if (lng < 29.0) {
	        return 400;
	       } else {
	        return 276;
	       }
	      }
	     }
	    } else {
	     return 400;
	    }
	   } else {
	    if (lat < 51.5) {
	     return 276;
	    } else {
	     return 400;
	    }
	   }
	  } else {
	   if (lat < 55.75) {
	    return 400;
	   } else {
	    if (lng < 29.25) {
	     if (lng < 28.75) {
	      return 400;
	     } else {
	      if (lng < 29.0) {
	       if (lat < 56.0) {
	        return 400;
	       } else {
	        return 184;
	       }
	      } else {
	       return 400;
	      }
	     }
	    } else {
	     if (lng < 30.0) {
	      if (lng < 29.5) {
	       if (lat < 56.0) {
	        return 400;
	       } else {
	        return 184;
	       }
	      } else {
	       if (lng < 29.75) {
	        return 184;
	       } else {
	        if (lat < 56.0) {
	         return 400;
	        } else {
	         return 184;
	        }
	       }
	      }
	     } else {
	      if (lng < 30.25) {
	       if (lat < 56.0) {
	        return 400;
	       } else {
	        return 184;
	       }
	      } else {
	       if (lng < 30.5) {
	        if (lat < 56.0) {
	         return 400;
	        } else {
	         return 184;
	        }
	       } else {
	        if (lat < 56.0) {
	         return 400;
	        } else {
	         return 184;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 53.25) {
	   if (lng < 32.25) {
	    if (lat < 52.0) {
	     return 276;
	    } else {
	     if (lng < 31.5) {
	      if (lat < 52.25) {
	       if (lng < 31.0) {
	        return 400;
	       } else {
	        return 276;
	       }
	      } else {
	       return 400;
	      }
	     } else {
	      if (lat < 52.5) {
	       if (lng < 31.75) {
	        if (lat < 52.25) {
	         return 276;
	        } else {
	         return 400;
	        }
	       } else {
	        if (lng < 32.0) {
	         if (lat < 52.25) {
	          return 276;
	         } else {
	          return 184;
	         }
	        } else {
	         if (lat < 52.25) {
	          return 276;
	         } else {
	          return 184;
	         }
	        }
	       }
	      } else {
	       if (lng < 31.75) {
	        if (lat < 53.0) {
	         return 400;
	        } else {
	         return 184;
	        }
	       } else {
	        return 184;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 52.25) {
	     return 276;
	    } else {
	     if (lng < 33.0) {
	      if (lat < 52.5) {
	       if (lng < 32.5) {
	        return 184;
	       } else {
	        return 276;
	       }
	      } else {
	       return 184;
	      }
	     } else {
	      if (lat < 52.5) {
	       return 276;
	      } else {
	       return 184;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 32.25) {
	    if (lat < 54.75) {
	     if (lng < 31.5) {
	      if (lat < 54.5) {
	       return 400;
	      } else {
	       if (lng < 31.25) {
	        return 400;
	       } else {
	        return 184;
	       }
	      }
	     } else {
	      if (lat < 54.0) {
	       return 400;
	      } else {
	       if (lng < 31.75) {
	        if (lat < 54.25) {
	         return 400;
	        } else {
	         return 184;
	        }
	       } else {
	        if (lat < 54.25) {
	         if (lng < 32.0) {
	          return 400;
	         } else {
	          return 184;
	         }
	        } else {
	         return 184;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 31.0) {
	      if (lat < 55.75) {
	       return 400;
	      } else {
	       return 184;
	      }
	     } else {
	      return 184;
	     }
	    }
	   } else {
	    if (lat < 54.0) {
	     if (lng < 32.75) {
	      if (lat < 53.5) {
	       if (lng < 32.5) {
	        return 400;
	       } else {
	        return 184;
	       }
	      } else {
	       if (lng < 32.5) {
	        return 400;
	       } else {
	        if (lat < 53.75) {
	         return 400;
	        } else {
	         return 184;
	        }
	       }
	      }
	     } else {
	      return 184;
	     }
	    } else {
	     return 184;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup137(double lat, double lng)
	{
	 if (lng < 25.25) {
	  if (lat < 59.0) {
	   if (lng < 23.75) {
	    if (lat < 57.5) {
	     if (lng < 23.0) {
	      if (lat < 56.5) {
	       return 53;
	      } else {
	       return 333;
	      }
	     } else {
	      if (lat < 56.5) {
	       return 53;
	      } else {
	       return 333;
	      }
	     }
	    } else {
	     if (lat < 58.25) {
	      if (lng < 23.0) {
	       if (lat < 58.0) {
	        return 333;
	       } else {
	        return 49;
	       }
	      } else {
	       return 333;
	      }
	     } else {
	      return 49;
	     }
	    }
	   } else {
	    if (lat < 57.5) {
	     if (lng < 24.5) {
	      if (lat < 56.5) {
	       return 53;
	      } else {
	       return 333;
	      }
	     } else {
	      if (lat < 56.5) {
	       return 53;
	      } else {
	       return 333;
	      }
	     }
	    } else {
	     if (lng < 24.5) {
	      if (lat < 58.0) {
	       return 333;
	      } else {
	       return 49;
	      }
	     } else {
	      if (lat < 58.25) {
	       if (lng < 24.75) {
	        if (lat < 58.0) {
	         return 333;
	        } else {
	         return 49;
	        }
	       } else {
	        if (lat < 58.0) {
	         return 333;
	        } else {
	         if (lng < 25.0) {
	          return 49;
	         } else {
	          return 333;
	         }
	        }
	       }
	      } else {
	       return 49;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 23.75) {
	    if (lat < 59.75) {
	     return 49;
	    } else {
	     return 324;
	    }
	   } else {
	    if (lat < 60.0) {
	     if (lng < 24.5) {
	      if (lat < 59.75) {
	       return 49;
	      } else {
	       return 324;
	      }
	     } else {
	      return 49;
	     }
	    } else {
	     return 324;
	    }
	   }
	  }
	 } else {
	  if (lat < 59.0) {
	   if (lng < 26.5) {
	    if (lat < 57.75) {
	     return 333;
	    } else {
	     if (lng < 25.75) {
	      if (lat < 58.25) {
	       if (lng < 25.5) {
	        return 333;
	       } else {
	        if (lat < 58.0) {
	         return 333;
	        } else {
	         return 49;
	        }
	       }
	      } else {
	       return 49;
	      }
	     } else {
	      if (lat < 58.0) {
	       if (lng < 26.25) {
	        return 333;
	       } else {
	        return 49;
	       }
	      } else {
	       return 49;
	      }
	     }
	    }
	   } else {
	    if (lat < 57.5) {
	     if (lng < 27.75) {
	      return 333;
	     } else {
	      if (lat < 57.0) {
	       return 333;
	      } else {
	       if (lat < 57.25) {
	        return 184;
	       } else {
	        return 333;
	       }
	      }
	     }
	    } else {
	     if (lng < 27.25) {
	      if (lat < 57.75) {
	       return 333;
	      } else {
	       return 49;
	      }
	     } else {
	      if (lat < 58.25) {
	       if (lng < 27.5) {
	        if (lat < 57.75) {
	         return 333;
	        } else {
	         return 49;
	        }
	       } else {
	        if (lat < 57.75) {
	         if (lng < 27.75) {
	          return 333;
	         } else {
	          return 184;
	         }
	        } else {
	         if (lng < 27.75) {
	          return 49;
	         } else {
	          return 184;
	         }
	        }
	       }
	      } else {
	       if (lng < 27.5) {
	        return 49;
	       } else {
	        if (lat < 58.5) {
	         if (lng < 27.75) {
	          return 49;
	         } else {
	          return 184;
	         }
	        } else {
	         if (lng < 27.75) {
	          if (lat < 58.75) {
	           return 184;
	          } else {
	           return 49;
	          }
	         } else {
	          return 184;
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 26.5) {
	    if (lat < 60.25) {
	     if (lng < 25.75) {
	      return 49;
	     } else {
	      if (lat < 60.0) {
	       return 49;
	      } else {
	       return 324;
	      }
	     }
	    } else {
	     return 324;
	    }
	   } else {
	    if (lat < 60.25) {
	     if (lng < 27.25) {
	      return 49;
	     } else {
	      if (lat < 59.5) {
	       if (lng < 27.75) {
	        return 49;
	       } else {
	        if (lat < 59.25) {
	         return 184;
	        } else {
	         return 49;
	        }
	       }
	      } else {
	       return 49;
	      }
	     }
	    } else {
	     return 324;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup138(double lat, double lng)
	{
	 if (lng < 30.75) {
	  if (lat < 64.5) {
	   if (lng < 30.0) {
	    return 324;
	   } else {
	    if (lat < 63.0) {
	     if (lat < 62.25) {
	      if (lng < 30.25) {
	       return 324;
	      } else {
	       if (lng < 30.5) {
	        if (lat < 62.0) {
	         return 184;
	        } else {
	         return 324;
	        }
	       } else {
	        return 184;
	       }
	      }
	     } else {
	      return 324;
	     }
	    } else {
	     if (lat < 63.75) {
	      if (lng < 30.5) {
	       return 324;
	      } else {
	       if (lat < 63.5) {
	        return 324;
	       } else {
	        return 184;
	       }
	      }
	     } else {
	      if (lng < 30.25) {
	       if (lat < 64.0) {
	        return 184;
	       } else {
	        return 324;
	       }
	      } else {
	       if (lat < 64.0) {
	        return 184;
	       } else {
	        if (lng < 30.5) {
	         return 324;
	        } else {
	         return 184;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 66.0) {
	    if (lng < 29.75) {
	     return 324;
	    } else {
	     if (lat < 65.25) {
	      if (lng < 30.25) {
	       if (lat < 65.0) {
	        return 324;
	       } else {
	        return 184;
	       }
	      } else {
	       return 184;
	      }
	     } else {
	      if (lng < 30.25) {
	       if (lat < 65.5) {
	        return 184;
	       } else {
	        if (lng < 30.0) {
	         return 324;
	        } else {
	         if (lat < 65.75) {
	          return 184;
	         } else {
	          return 324;
	         }
	        }
	       }
	      } else {
	       return 184;
	      }
	     }
	    }
	   } else {
	    if (lng < 29.25) {
	     return 324;
	    } else {
	     if (lng < 30.0) {
	      if (lat < 66.75) {
	       if (lng < 29.75) {
	        return 324;
	       } else {
	        if (lat < 66.25) {
	         return 324;
	        } else {
	         return 184;
	        }
	       }
	      } else {
	       if (lng < 29.5) {
	        if (lat < 67.25) {
	         return 184;
	        } else {
	         return 324;
	        }
	       } else {
	        return 184;
	       }
	      }
	     } else {
	      return 184;
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 67.25) {
	   if (lat < 67.0) {
	    if (lat < 66.75) {
	     if (lat < 66.5) {
	      if (lat < 66.25) {
	       if (lat < 66.0) {
	        if (lat < 65.75) {
	         if (lat < 65.5) {
	          if (lat < 65.25) {
	           if (lat < 65.0) {
	            if (lat < 63.25) {
	             if (lng < 31.5) {
	              if (lat < 62.5) {
	               if (lng < 31.0) {
	                if (lat < 62.25) {
	                 return 184;
	                } else {
	                 return 324;
	                }
	               } else {
	                return 184;
	               }
	              } else {
	               if (lng < 31.25) {
	                return 324;
	               } else {
	                if (lat < 62.75) {
	                 return 184;
	                } else {
	                 return 324;
	                }
	               }
	              }
	             } else {
	              return 184;
	             }
	            } else {
	             if (lng < 31.25) {
	              if (lat < 63.5) {
	               return 324;
	              } else {
	               return 184;
	              }
	             } else {
	              return 184;
	             }
	            }
	           } else {
	            return 184;
	           }
	          } else {
	           return 184;
	          }
	         } else {
	          return 184;
	         }
	        } else {
	         return 184;
	        }
	       } else {
	        return 184;
	       }
	      } else {
	       return 184;
	      }
	     } else {
	      return 184;
	     }
	    } else {
	     return 184;
	    }
	   } else {
	    return 184;
	   }
	  } else {
	   return 184;
	  }
	 }
	}

	private  int kdLookup139(double lat, double lng)
	{
	 if (lat < 56.25) {
	  if (lng < 28.0) {
	   if (lat < 50.5) {
	    return kdLookup133(lat,lng);
	   } else {
	    if (lat < 53.25) {
	     if (lng < 25.25) {
	      if (lng < 23.75) {
	       if (lat < 52.25) {
	        return 187;
	       } else {
	        if (lng < 23.25) {
	         return 187;
	        } else {
	         if (lat < 52.75) {
	          if (lng < 23.5) {
	           if (lat < 52.5) {
	            return 400;
	           } else {
	            return 187;
	           }
	          } else {
	           return 400;
	          }
	         } else {
	          return 187;
	         }
	        }
	       }
	      } else {
	       if (lat < 51.75) {
	        if (lng < 24.25) {
	         if (lat < 51.0) {
	          return 187;
	         } else {
	          if (lat < 51.25) {
	           if (lng < 24.0) {
	            return 187;
	           } else {
	            return 276;
	           }
	          } else {
	           return 276;
	          }
	         }
	        } else {
	         return 276;
	        }
	       } else {
	        if (lng < 24.5) {
	         if (lat < 52.75) {
	          return 400;
	         } else {
	          if (lng < 24.0) {
	           return 187;
	          } else {
	           return 400;
	          }
	         }
	        } else {
	         if (lat < 52.0) {
	          return 276;
	         } else {
	          return 400;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < 26.5) {
	       if (lat < 52.0) {
	        return 276;
	       } else {
	        return 400;
	       }
	      } else {
	       if (lat < 51.75) {
	        if (lng < 27.75) {
	         return 276;
	        } else {
	         if (lat < 51.5) {
	          return 276;
	         } else {
	          return 400;
	         }
	        }
	       } else {
	        if (lng < 27.25) {
	         if (lat < 52.0) {
	          return 276;
	         } else {
	          return 400;
	         }
	        } else {
	         return 400;
	        }
	       }
	      }
	     }
	    } else {
	     return kdLookup134(lat,lng);
	    }
	   }
	  } else {
	   if (lat < 50.5) {
	    if (lng < 30.75) {
	     return kdLookup135(lat,lng);
	    } else {
	     if (lat < 46.5) {
	      if (lng < 32.5) {
	       return 276;
	      } else {
	       if (lat < 46.0) {
	        return 198;
	       } else {
	        return 276;
	       }
	      }
	     } else {
	      return 276;
	     }
	    }
	   } else {
	    return kdLookup136(lat,lng);
	   }
	  }
	 } else {
	  if (lng < 28.0) {
	   if (lat < 61.75) {
	    return kdLookup137(lat,lng);
	   } else {
	    if (lat < 65.25) {
	     return 324;
	    } else {
	     if (lng < 24.25) {
	      if (lat < 66.25) {
	       return 376;
	      } else {
	       if (lng < 23.75) {
	        return 376;
	       } else {
	        if (lat < 66.75) {
	         return 324;
	        } else {
	         if (lat < 67.0) {
	          if (lng < 24.0) {
	           return 376;
	          } else {
	           return 324;
	          }
	         } else {
	          if (lng < 24.0) {
	           if (lat < 67.25) {
	            return 376;
	           } else {
	            return 324;
	           }
	          } else {
	           return 324;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      return 324;
	     }
	    }
	   }
	  } else {
	   if (lat < 61.75) {
	    if (lng < 29.75) {
	     if (lat < 59.0) {
	      if (lat < 56.75) {
	       if (lng < 28.25) {
	        return 333;
	       } else {
	        return 184;
	       }
	      } else {
	       return 184;
	      }
	     } else {
	      if (lat < 60.5) {
	       return 184;
	      } else {
	       if (lng < 28.75) {
	        if (lat < 61.0) {
	         if (lng < 28.25) {
	          return 324;
	         } else {
	          return 184;
	         }
	        } else {
	         return 324;
	        }
	       } else {
	        if (lat < 61.25) {
	         return 184;
	        } else {
	         if (lng < 29.25) {
	          return 324;
	         } else {
	          if (lng < 29.5) {
	           if (lat < 61.5) {
	            return 184;
	           } else {
	            return 324;
	           }
	          } else {
	           if (lat < 61.5) {
	            return 184;
	           } else {
	            return 324;
	           }
	          }
	         }
	        }
	       }
	      }
	     }
	    } else {
	     return 184;
	    }
	   } else {
	    return kdLookup138(lat,lng);
	   }
	  }
	 }
	}

	private  int kdLookup140(double lat, double lng)
	{
	 if (lat < 50.5) {
	  if (lng < 36.5) {
	   if (lat < 47.75) {
	    if (lng < 35.0) {
	     if (lat < 46.25) {
	      if (lng < 34.5) {
	       return 198;
	      } else {
	       if (lat < 46.0) {
	        return 198;
	       } else {
	        if (lng < 34.75) {
	         return 276;
	        } else {
	         return 198;
	        }
	       }
	      }
	     } else {
	      if (lat < 47.0) {
	       if (lng < 34.75) {
	        return 276;
	       } else {
	        if (lat < 46.75) {
	         return 276;
	        } else {
	         return 147;
	        }
	       }
	      } else {
	       if (lng < 34.5) {
	        return 276;
	       } else {
	        if (lat < 47.25) {
	         if (lng < 34.75) {
	          return 276;
	         } else {
	          return 147;
	         }
	        } else {
	         if (lng < 34.75) {
	          if (lat < 47.5) {
	           return 276;
	          } else {
	           return 147;
	          }
	         } else {
	          return 147;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 46.25) {
	      return 198;
	     } else {
	      if (lng < 35.25) {
	       if (lat < 46.5) {
	        return 276;
	       } else {
	        return 147;
	       }
	      } else {
	       return 147;
	      }
	     }
	    }
	   } else {
	    if (lng < 35.0) {
	     return 276;
	    } else {
	     if (lat < 48.25) {
	      if (lng < 36.25) {
	       return 147;
	      } else {
	       if (lat < 48.0) {
	        return 147;
	       } else {
	        return 276;
	       }
	      }
	     } else {
	      return 276;
	     }
	    }
	   }
	  } else {
	   if (lat < 47.75) {
	    if (lng < 37.75) {
	     if (lat < 46.25) {
	      if (lng < 37.0) {
	       if (lat < 45.5) {
	        if (lng < 36.75) {
	         return 198;
	        } else {
	         return 184;
	        }
	       } else {
	        return 0;
	       }
	      } else {
	       return 184;
	      }
	     } else {
	      if (lat < 47.0) {
	       if (lng < 37.25) {
	        return 147;
	       } else {
	        return 276;
	       }
	      } else {
	       if (lng < 37.0) {
	        return 147;
	       } else {
	        if (lng < 37.25) {
	         if (lat < 47.25) {
	          return 147;
	         } else {
	          if (lat < 47.5) {
	           return 276;
	          } else {
	           return 147;
	          }
	         }
	        } else {
	         return 276;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 47.0) {
	      return 184;
	     } else {
	      if (lng < 38.5) {
	       return 276;
	      } else {
	       return 184;
	      }
	     }
	    }
	   } else {
	    if (lng < 37.75) {
	     if (lat < 48.0) {
	      if (lng < 36.75) {
	       return 147;
	      } else {
	       return 276;
	      }
	     } else {
	      return 276;
	     }
	    } else {
	     if (lat < 49.0) {
	      if (lng < 39.0) {
	       return 276;
	      } else {
	       if (lat < 48.0) {
	        return 184;
	       } else {
	        return 276;
	       }
	      }
	     } else {
	      if (lng < 38.5) {
	       if (lat < 50.0) {
	        return 276;
	       } else {
	        if (lng < 38.0) {
	         if (lat < 50.25) {
	          return 276;
	         } else {
	          return 184;
	         }
	        } else {
	         if (lng < 38.25) {
	          return 184;
	         } else {
	          if (lat < 50.25) {
	           return 276;
	          } else {
	           return 184;
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < 50.0) {
	        return 276;
	       } else {
	        return 184;
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 52.5) {
	   if (lng < 35.5) {
	    if (lat < 51.5) {
	     if (lng < 34.5) {
	      return 276;
	     } else {
	      if (lng < 35.0) {
	       if (lat < 51.25) {
	        return 276;
	       } else {
	        return 184;
	       }
	      } else {
	       if (lat < 51.25) {
	        return 276;
	       } else {
	        return 184;
	       }
	      }
	     }
	    } else {
	     if (lng < 34.5) {
	      if (lat < 52.0) {
	       return 276;
	      } else {
	       if (lng < 34.0) {
	        return 276;
	       } else {
	        if (lng < 34.25) {
	         if (lat < 52.25) {
	          return 276;
	         } else {
	          return 184;
	         }
	        } else {
	         return 184;
	        }
	       }
	      }
	     } else {
	      return 184;
	     }
	    }
	   } else {
	    return 184;
	   }
	  } else {
	   return 184;
	  }
	 }
	}

	private  int kdLookup141(double lat, double lng)
	{
	 if (lat < 50.5) {
	  if (lng < 42.0) {
	   if (lat < 48.0) {
	    return 184;
	   } else {
	    if (lng < 40.5) {
	     if (lat < 49.25) {
	      if (lng < 39.75) {
	       return 276;
	      } else {
	       if (lat < 48.5) {
	        if (lng < 40.0) {
	         return 276;
	        } else {
	         if (lng < 40.25) {
	          if (lat < 48.25) {
	           return 184;
	          } else {
	           return 276;
	          }
	         } else {
	          return 184;
	         }
	        }
	       } else {
	        if (lng < 40.0) {
	         if (lat < 48.75) {
	          return 276;
	         } else {
	          return 184;
	         }
	        } else {
	         return 184;
	        }
	       }
	      }
	     } else {
	      if (lng < 39.75) {
	       if (lat < 50.0) {
	        return 276;
	       } else {
	        return 184;
	       }
	      } else {
	       if (lat < 49.75) {
	        if (lng < 40.25) {
	         return 276;
	        } else {
	         return 184;
	        }
	       } else {
	        return 184;
	       }
	      }
	     }
	    } else {
	     if (lat < 50.0) {
	      return 184;
	     } else {
	      if (lng < 41.5) {
	       return 184;
	      } else {
	       if (lng < 41.75) {
	        if (lat < 50.25) {
	         return 184;
	        } else {
	         return 99;
	        }
	       } else {
	        return 99;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 47.75) {
	    if (lng < 43.5) {
	     if (lat < 47.5) {
	      return 184;
	     } else {
	      if (lng < 43.25) {
	       return 184;
	      } else {
	       return 99;
	      }
	     }
	    } else {
	     if (lat < 47.5) {
	      return 184;
	     } else {
	      if (lng < 43.75) {
	       return 99;
	      } else {
	       return 184;
	      }
	     }
	    }
	   } else {
	    if (lng < 43.5) {
	     if (lat < 49.0) {
	      if (lng < 42.75) {
	       if (lat < 48.25) {
	        return 184;
	       } else {
	        if (lng < 42.25) {
	         return 184;
	        } else {
	         if (lat < 48.5) {
	          return 99;
	         } else {
	          if (lng < 42.5) {
	           return 184;
	          } else {
	           if (lat < 48.75) {
	            return 99;
	           } else {
	            return 184;
	           }
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < 48.0) {
	        if (lng < 43.0) {
	         return 184;
	        } else {
	         return 99;
	        }
	       } else {
	        return 99;
	       }
	      }
	     } else {
	      if (lng < 42.75) {
	       if (lat < 49.75) {
	        if (lng < 42.25) {
	         return 184;
	        } else {
	         if (lat < 49.25) {
	          return 184;
	         } else {
	          return 99;
	         }
	        }
	       } else {
	        if (lng < 42.25) {
	         if (lat < 50.0) {
	          return 184;
	         } else {
	          return 99;
	         }
	        } else {
	         return 99;
	        }
	       }
	      } else {
	       return 99;
	      }
	     }
	    } else {
	     if (lat < 48.25) {
	      if (lng < 44.25) {
	       if (lng < 44.0) {
	        return 99;
	       } else {
	        if (lat < 48.0) {
	         return 184;
	        } else {
	         return 99;
	        }
	       }
	      } else {
	       if (lng < 44.5) {
	        if (lat < 48.0) {
	         return 184;
	        } else {
	         return 99;
	        }
	       } else {
	        return 184;
	       }
	      }
	     } else {
	      return 99;
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 42.0) {
	   if (lat < 51.25) {
	    if (lng < 41.25) {
	     return 184;
	    } else {
	     if (lng < 41.5) {
	      if (lat < 50.75) {
	       return 184;
	      } else {
	       if (lat < 51.0) {
	        return 99;
	       } else {
	        return 184;
	       }
	      }
	     } else {
	      if (lat < 51.0) {
	       return 99;
	      } else {
	       if (lng < 41.75) {
	        return 184;
	       } else {
	        return 99;
	       }
	      }
	     }
	    }
	   } else {
	    return 184;
	   }
	  } else {
	   if (lat < 52.5) {
	    if (lng < 43.0) {
	     if (lat < 51.25) {
	      return 99;
	     } else {
	      if (lat < 51.75) {
	       if (lng < 42.75) {
	        return 184;
	       } else {
	        if (lat < 51.5) {
	         return 184;
	        } else {
	         return 99;
	        }
	       }
	      } else {
	       if (lng < 42.75) {
	        return 184;
	       } else {
	        if (lat < 52.25) {
	         return 99;
	        } else {
	         return 184;
	        }
	       }
	      }
	     }
	    } else {
	     return 99;
	    }
	   } else {
	    return 184;
	   }
	  }
	 }
	}

	private  int kdLookup142(double lat, double lng)
	{
	 if (lng < 28.0) {
	  if (lat < 73.0) {
	   if (lng < 25.25) {
	    if (lat < 70.25) {
	     if (lng < 23.75) {
	      if (lat < 68.75) {
	       if (lng < 23.0) {
	        if (lat < 68.5) {
	         return 376;
	        } else {
	         return 324;
	        }
	       } else {
	        if (lat < 68.0) {
	         if (lng < 23.5) {
	          return 376;
	         } else {
	          return 324;
	         }
	        } else {
	         if (lng < 23.25) {
	          if (lat < 68.5) {
	           return 376;
	          } else {
	           return 324;
	          }
	         } else {
	          if (lat < 68.25) {
	           return 376;
	          } else {
	           return 324;
	          }
	         }
	        }
	       }
	      } else {
	       return 356;
	      }
	     } else {
	      if (lat < 68.75) {
	       return 324;
	      } else {
	       if (lng < 24.25) {
	        if (lat < 69.0) {
	         return 324;
	        } else {
	         return 356;
	        }
	       } else {
	        return 356;
	       }
	      }
	     }
	    } else {
	     return 356;
	    }
	   } else {
	    if (lat < 70.25) {
	     if (lng < 26.5) {
	      if (lat < 69.0) {
	       return 324;
	      } else {
	       if (lng < 25.75) {
	        return 356;
	       } else {
	        if (lat < 69.5) {
	         return 324;
	        } else {
	         if (lng < 26.0) {
	          return 356;
	         } else {
	          if (lat < 69.75) {
	           return 324;
	          } else {
	           if (lng < 26.25) {
	            return 356;
	           } else {
	            if (lat < 70.0) {
	             return 324;
	            } else {
	             return 356;
	            }
	           }
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 70.0) {
	       return 324;
	      } else {
	       if (lng < 27.5) {
	        return 356;
	       } else {
	        return 324;
	       }
	      }
	     }
	    } else {
	     return 356;
	    }
	   }
	  } else {
	   return 111;
	  }
	 } else {
	  if (lat < 73.0) {
	   if (lng < 30.75) {
	    if (lat < 70.25) {
	     if (lng < 29.25) {
	      if (lat < 68.75) {
	       if (lng < 28.5) {
	        return 324;
	       } else {
	        if (lat < 68.25) {
	         return 324;
	        } else {
	         if (lng < 28.75) {
	          if (lat < 68.5) {
	           return 324;
	          } else {
	           return 184;
	          }
	         } else {
	          return 184;
	         }
	        }
	       }
	      } else {
	       if (lat < 69.5) {
	        if (lng < 28.75) {
	         return 324;
	        } else {
	         if (lat < 69.25) {
	          return 184;
	         } else {
	          if (lng < 29.0) {
	           return 324;
	          } else {
	           return 356;
	          }
	         }
	        }
	       } else {
	        if (lng < 28.5) {
	         if (lat < 70.0) {
	          return 324;
	         } else {
	          if (lng < 28.25) {
	           return 324;
	          } else {
	           return 356;
	          }
	         }
	        } else {
	         if (lng < 28.75) {
	          if (lat < 70.0) {
	           return 324;
	          } else {
	           return 356;
	          }
	         } else {
	          if (lat < 69.75) {
	           return 324;
	          } else {
	           if (lng < 29.0) {
	            if (lat < 70.0) {
	             return 324;
	            } else {
	             return 356;
	            }
	           } else {
	            return 356;
	           }
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 68.75) {
	       if (lng < 30.0) {
	        if (lat < 68.0) {
	         return 324;
	        } else {
	         if (lng < 29.5) {
	          if (lat < 68.25) {
	           return 324;
	          } else {
	           return 184;
	          }
	         } else {
	          return 184;
	         }
	        }
	       } else {
	        return 184;
	       }
	      } else {
	       if (lng < 30.0) {
	        if (lat < 69.5) {
	         if (lng < 29.5) {
	          if (lat < 69.25) {
	           return 184;
	          } else {
	           return 356;
	          }
	         } else {
	          return 184;
	         }
	        } else {
	         if (lng < 29.5) {
	          if (lat < 69.75) {
	           return 324;
	          } else {
	           return 356;
	          }
	         } else {
	          return 356;
	         }
	        }
	       } else {
	        if (lat < 69.5) {
	         return 184;
	        } else {
	         if (lng < 30.25) {
	          return 356;
	         } else {
	          if (lat < 69.75) {
	           return 184;
	          } else {
	           return 356;
	          }
	         }
	        }
	       }
	      }
	     }
	    } else {
	     return 356;
	    }
	   } else {
	    if (lat < 70.25) {
	     if (lng < 32.25) {
	      if (lat < 69.75) {
	       return 184;
	      } else {
	       if (lng < 31.5) {
	        if (lng < 31.0) {
	         return 356;
	        } else {
	         return 184;
	        }
	       } else {
	        return 184;
	       }
	      }
	     } else {
	      return 184;
	     }
	    } else {
	     return 356;
	    }
	   }
	  } else {
	   return 0;
	  }
	 }
	}

	private  int kdLookup143(double lat, double lng)
	{
	 if (lng < 22.5) {
	  if (lat < 67.5) {
	   return kdLookup132(lat,lng);
	  } else {
	   if (lng < 11.25) {
	    return 111;
	   } else {
	    if (lat < 78.75) {
	     if (lng < 16.75) {
	      if (lat < 73.0) {
	       if (lng < 14.0) {
	        return 356;
	       } else {
	        if (lat < 70.25) {
	         if (lng < 15.25) {
	          return 356;
	         } else {
	          if (lat < 68.75) {
	           if (lng < 16.25) {
	            return 356;
	           } else {
	            if (lat < 67.75) {
	             return 376;
	            } else {
	             return 356;
	            }
	           }
	          } else {
	           return 356;
	          }
	         }
	        } else {
	         return 0;
	        }
	       }
	      } else {
	       return 111;
	      }
	     } else {
	      if (lat < 73.0) {
	       if (lng < 19.5) {
	        if (lat < 70.25) {
	         if (lng < 18.0) {
	          if (lat < 68.75) {
	           if (lng < 17.25) {
	            if (lat < 68.0) {
	             return 376;
	            } else {
	             return 356;
	            }
	           } else {
	            if (lat < 68.25) {
	             return 376;
	            } else {
	             return 356;
	            }
	           }
	          } else {
	           return 356;
	          }
	         } else {
	          if (lat < 68.75) {
	           if (lng < 18.75) {
	            if (lat < 68.25) {
	             return 376;
	            } else {
	             if (lng < 18.25) {
	              return 356;
	             } else {
	              return 376;
	             }
	            }
	           } else {
	            if (lat < 68.5) {
	             return 376;
	            } else {
	             if (lng < 19.25) {
	              return 376;
	             } else {
	              return 356;
	             }
	            }
	           }
	          } else {
	           return 356;
	          }
	         }
	        } else {
	         return 356;
	        }
	       } else {
	        if (lat < 70.25) {
	         if (lng < 21.0) {
	          if (lat < 68.75) {
	           if (lng < 20.25) {
	            if (lat < 68.5) {
	             return 376;
	            } else {
	             return 356;
	            }
	           } else {
	            return 376;
	           }
	          } else {
	           if (lng < 20.25) {
	            return 356;
	           } else {
	            if (lat < 69.25) {
	             if (lng < 20.5) {
	              if (lat < 69.0) {
	               return 356;
	              } else {
	               return 376;
	              }
	             } else {
	              return 376;
	             }
	            } else {
	             return 356;
	            }
	           }
	          }
	         } else {
	          if (lat < 68.75) {
	           if (lng < 22.25) {
	            return 376;
	           } else {
	            if (lat < 68.5) {
	             return 376;
	            } else {
	             return 324;
	            }
	           }
	          } else {
	           if (lng < 21.75) {
	            if (lat < 69.5) {
	             if (lng < 21.25) {
	              if (lat < 69.0) {
	               return 376;
	              } else {
	               if (lat < 69.25) {
	                return 324;
	               } else {
	                return 356;
	               }
	              }
	             } else {
	              if (lat < 69.0) {
	               if (lng < 21.5) {
	                return 376;
	               } else {
	                return 324;
	               }
	              } else {
	               return 324;
	              }
	             }
	            } else {
	             return 356;
	            }
	           } else {
	            if (lat < 69.25) {
	             if (lng < 22.25) {
	              return 324;
	             } else {
	              if (lat < 69.0) {
	               return 324;
	              } else {
	               return 356;
	              }
	             }
	            } else {
	             return 356;
	            }
	           }
	          }
	         }
	        } else {
	         return 356;
	        }
	       }
	      } else {
	       return 111;
	      }
	     }
	    } else {
	     return 111;
	    }
	   }
	  }
	 } else {
	  if (lat < 67.5) {
	   if (lng < 33.75) {
	    return kdLookup139(lat,lng);
	   } else {
	    if (lat < 56.25) {
	     if (lng < 39.25) {
	      return kdLookup140(lat,lng);
	     } else {
	      return kdLookup141(lat,lng);
	     }
	    } else {
	     return 184;
	    }
	   }
	  } else {
	   if (lng < 33.75) {
	    if (lat < 78.75) {
	     return kdLookup142(lat,lng);
	    } else {
	     return 111;
	    }
	   } else {
	    return 184;
	   }
	  }
	 }
	}

	private  int kdLookup144(double lat, double lng)
	{
	 if (lng < 53.25) {
	  if (lat < 19.5) {
	   if (lng < 51.75) {
	    if (lat < 18.75) {
	     return 216;
	    } else {
	     if (lng < 51.0) {
	      return 164;
	     } else {
	      if (lng < 51.25) {
	       if (lat < 19.0) {
	        return 216;
	       } else {
	        return 164;
	       }
	      } else {
	       if (lat < 19.0) {
	        return 216;
	       } else {
	        return 164;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 18.0) {
	     if (lng < 52.75) {
	      return 216;
	     } else {
	      if (lat < 17.25) {
	       if (lng < 53.0) {
	        return 216;
	       } else {
	        if (lat < 17.0) {
	         return 216;
	        } else {
	         return 358;
	        }
	       }
	      } else {
	       if (lat < 17.5) {
	        if (lng < 53.0) {
	         return 216;
	        } else {
	         return 358;
	        }
	       } else {
	        return 358;
	       }
	      }
	     }
	    } else {
	     if (lng < 52.5) {
	      if (lat < 18.75) {
	       if (lng < 52.25) {
	        return 216;
	       } else {
	        if (lat < 18.5) {
	         return 216;
	        } else {
	         return 358;
	        }
	       }
	      } else {
	       if (lng < 52.0) {
	        if (lat < 19.0) {
	         return 216;
	        } else {
	         return 164;
	        }
	       } else {
	        if (lat < 19.0) {
	         if (lng < 52.25) {
	          return 216;
	         } else {
	          return 358;
	         }
	        } else {
	         if (lng < 52.25) {
	          if (lat < 19.25) {
	           return 358;
	          } else {
	           return 164;
	          }
	         } else {
	          if (lat < 19.25) {
	           return 358;
	          } else {
	           return 164;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 19.25) {
	       return 358;
	      } else {
	       if (lng < 52.75) {
	        return 164;
	       } else {
	        return 358;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   return 164;
	  }
	 } else {
	  if (lat < 19.5) {
	   return 358;
	  } else {
	   if (lng < 54.75) {
	    if (lat < 20.0) {
	     if (lng < 54.0) {
	      if (lng < 53.5) {
	       return 164;
	      } else {
	       if (lng < 53.75) {
	        if (lat < 19.75) {
	         return 358;
	        } else {
	         return 164;
	        }
	       } else {
	        if (lat < 19.75) {
	         return 358;
	        } else {
	         return 164;
	        }
	       }
	      }
	     } else {
	      if (lng < 54.25) {
	       if (lat < 19.75) {
	        return 358;
	       } else {
	        return 164;
	       }
	      } else {
	       return 358;
	      }
	     }
	    } else {
	     return 164;
	    }
	   } else {
	    if (lat < 21.0) {
	     if (lng < 55.5) {
	      if (lat < 20.25) {
	       if (lng < 55.0) {
	        if (lat < 20.0) {
	         return 358;
	        } else {
	         return 164;
	        }
	       } else {
	        return 358;
	       }
	      } else {
	       if (lng < 55.25) {
	        return 164;
	       } else {
	        if (lat < 20.75) {
	         return 358;
	        } else {
	         return 164;
	        }
	       }
	      }
	     } else {
	      return 358;
	     }
	    } else {
	     if (lng < 55.5) {
	      return 164;
	     } else {
	      if (lat < 21.75) {
	       if (lng < 55.75) {
	        if (lat < 21.5) {
	         return 358;
	        } else {
	         return 164;
	        }
	       } else {
	        return 358;
	       }
	      } else {
	       if (lng < 55.75) {
	        return 164;
	       } else {
	        return 358;
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup145(double lat, double lng)
	{
	 if (lat < 11.25) {
	  if (lng < 50.5) {
	   if (lat < 5.5) {
	    if (lng < 47.75) {
	     if (lat < 2.75) {
	      return 251;
	     } else {
	      if (lng < 45.5) {
	       if (lat < 5.0) {
	        return 251;
	       } else {
	        if (lng < 45.25) {
	         return 351;
	        } else {
	         if (lat < 5.25) {
	          return 251;
	         } else {
	          return 351;
	         }
	        }
	       }
	      } else {
	       return 251;
	      }
	     }
	    } else {
	     return 251;
	    }
	   } else {
	    if (lat < 8.25) {
	     if (lng < 47.75) {
	      if (lng < 46.25) {
	       if (lat < 6.0) {
	        if (lng < 45.75) {
	         return 351;
	        } else {
	         if (lng < 46.0) {
	          if (lat < 5.75) {
	           return 251;
	          } else {
	           return 351;
	          }
	         } else {
	          return 251;
	         }
	        }
	       } else {
	        return 351;
	       }
	      } else {
	       if (lat < 6.75) {
	        if (lng < 46.75) {
	         if (lat < 6.25) {
	          return 251;
	         } else {
	          if (lng < 46.5) {
	           return 351;
	          } else {
	           if (lat < 6.5) {
	            return 251;
	           } else {
	            return 351;
	           }
	          }
	         }
	        } else {
	         return 251;
	        }
	       } else {
	        if (lng < 47.0) {
	         return 351;
	        } else {
	         if (lat < 7.5) {
	          if (lng < 47.25) {
	           if (lat < 7.0) {
	            return 251;
	           } else {
	            return 351;
	           }
	          } else {
	           if (lat < 7.25) {
	            return 251;
	           } else {
	            if (lng < 47.5) {
	             return 351;
	            } else {
	             return 251;
	            }
	           }
	          }
	         } else {
	          if (lng < 47.25) {
	           if (lat < 8.0) {
	            return 351;
	           } else {
	            return 251;
	           }
	          } else {
	           if (lat < 7.75) {
	            if (lng < 47.5) {
	             return 351;
	            } else {
	             return 251;
	            }
	           } else {
	            if (lng < 47.5) {
	             if (lat < 8.0) {
	              return 351;
	             } else {
	              return 251;
	             }
	            } else {
	             if (lat < 8.0) {
	              return 351;
	             } else {
	              return 251;
	             }
	            }
	           }
	          }
	         }
	        }
	       }
	      }
	     } else {
	      return 251;
	     }
	    } else {
	     if (lng < 46.75) {
	      if (lat < 9.75) {
	       if (lng < 45.75) {
	        if (lat < 8.75) {
	         return 351;
	        } else {
	         return 251;
	        }
	       } else {
	        if (lat < 8.5) {
	         if (lng < 46.5) {
	          return 351;
	         } else {
	          return 251;
	         }
	        } else {
	         return 251;
	        }
	       }
	      } else {
	       return 251;
	      }
	     } else {
	      return 251;
	     }
	    }
	   }
	  } else {
	   return 251;
	  }
	 } else {
	  if (lng < 50.5) {
	   if (lat < 16.75) {
	    if (lng < 47.75) {
	     if (lat < 14.0) {
	      if (lng < 47.25) {
	       return 216;
	      } else {
	       if (lat < 12.5) {
	        return 251;
	       } else {
	        return 216;
	       }
	      }
	     } else {
	      if (lng < 46.25) {
	       return 216;
	      } else {
	       if (lat < 16.0) {
	        return 216;
	       } else {
	        if (lng < 47.0) {
	         if (lng < 46.5) {
	          if (lat < 16.5) {
	           return 216;
	          } else {
	           return 164;
	          }
	         } else {
	          if (lat < 16.25) {
	           if (lng < 46.75) {
	            return 164;
	           } else {
	            return 216;
	           }
	          } else {
	           return 164;
	          }
	         }
	        } else {
	         if (lng < 47.25) {
	          if (lat < 16.5) {
	           return 216;
	          } else {
	           return 164;
	          }
	         } else {
	          return 216;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 14.0) {
	      if (lng < 49.0) {
	       if (lat < 12.5) {
	        return 251;
	       } else {
	        return 216;
	       }
	      } else {
	       return 251;
	      }
	     } else {
	      return 216;
	     }
	    }
	   } else {
	    if (lat < 18.75) {
	     if (lng < 47.75) {
	      if (lng < 46.25) {
	       if (lat < 17.25) {
	        if (lng < 45.5) {
	         if (lng < 45.25) {
	          return 216;
	         } else {
	          if (lat < 17.0) {
	           return 216;
	          } else {
	           return 164;
	          }
	         }
	        } else {
	         if (lng < 45.75) {
	          if (lat < 17.0) {
	           return 216;
	          } else {
	           return 164;
	          }
	         } else {
	          return 164;
	         }
	        }
	       } else {
	        return 164;
	       }
	      } else {
	       if (lat < 17.0) {
	        if (lng < 47.5) {
	         return 164;
	        } else {
	         return 216;
	        }
	       } else {
	        return 164;
	       }
	      }
	     } else {
	      if (lng < 49.0) {
	       if (lat < 17.75) {
	        if (lng < 48.25) {
	         if (lat < 17.25) {
	          return 216;
	         } else {
	          if (lng < 48.0) {
	           return 164;
	          } else {
	           if (lat < 17.5) {
	            return 216;
	           } else {
	            return 164;
	           }
	          }
	         }
	        } else {
	         return 216;
	        }
	       } else {
	        if (lng < 48.5) {
	         return 164;
	        } else {
	         if (lat < 18.25) {
	          if (lng < 48.75) {
	           if (lat < 18.0) {
	            return 216;
	           } else {
	            return 164;
	           }
	          } else {
	           return 216;
	          }
	         } else {
	          if (lng < 48.75) {
	           return 164;
	          } else {
	           if (lat < 18.5) {
	            return 216;
	           } else {
	            return 164;
	           }
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < 18.5) {
	        return 216;
	       } else {
	        if (lng < 50.0) {
	         return 164;
	        } else {
	         return 216;
	        }
	       }
	      }
	     }
	    } else {
	     return 164;
	    }
	   }
	  } else {
	   if (lat < 16.75) {
	    if (lng < 53.25) {
	     if (lat < 14.0) {
	      return 251;
	     } else {
	      return 216;
	     }
	    } else {
	     if (lat < 14.0) {
	      return 216;
	     } else {
	      return 358;
	     }
	    }
	   } else {
	    return kdLookup144(lat,lng);
	   }
	  }
	 }
	}

	private  int kdLookup146(double lat, double lng)
	{
	 if (lng < 53.25) {
	  if (lat < 24.5) {
	   if (lng < 51.75) {
	    return 164;
	   } else {
	    if (lat < 23.5) {
	     if (lng < 52.5) {
	      return 164;
	     } else {
	      if (lat < 23.0) {
	       return 164;
	      } else {
	       if (lng < 52.75) {
	        if (lat < 23.25) {
	         return 164;
	        } else {
	         return 322;
	        }
	       } else {
	        return 322;
	       }
	      }
	     }
	    } else {
	     if (lng < 52.5) {
	      if (lat < 24.0) {
	       if (lng < 52.0) {
	        return 164;
	       } else {
	        if (lng < 52.25) {
	         if (lat < 23.75) {
	          return 164;
	         } else {
	          return 322;
	         }
	        } else {
	         return 322;
	        }
	       }
	      } else {
	       return 322;
	      }
	     } else {
	      return 322;
	     }
	    }
	   }
	  } else {
	   if (lng < 51.75) {
	    if (lat < 25.5) {
	     if (lng < 51.0) {
	      return 164;
	     } else {
	      if (lat < 24.75) {
	       return 164;
	      } else {
	       return 100;
	      }
	     }
	    } else {
	     if (lng < 51.0) {
	      if (lat < 26.0) {
	       if (lng < 50.75) {
	        if (lat < 25.75) {
	         return 164;
	        } else {
	         return 109;
	        }
	       } else {
	        return 100;
	       }
	      } else {
	       return 109;
	      }
	     } else {
	      return 100;
	     }
	    }
	   } else {
	    if (lat < 25.5) {
	     if (lng < 52.5) {
	      if (lat < 25.0) {
	       return 322;
	      } else {
	       return 100;
	      }
	     } else {
	      return 0;
	     }
	    } else {
	     return 100;
	    }
	   }
	  }
	 } else {
	  if (lat < 24.5) {
	   if (lng < 54.75) {
	    if (lat < 23.0) {
	     if (lng < 54.25) {
	      return 164;
	     } else {
	      if (lng < 54.5) {
	       if (lat < 22.75) {
	        return 164;
	       } else {
	        return 322;
	       }
	      } else {
	       if (lat < 22.75) {
	        return 164;
	       } else {
	        return 322;
	       }
	      }
	     }
	    } else {
	     return 322;
	    }
	   } else {
	    if (lat < 23.5) {
	     if (lng < 55.5) {
	      if (lat < 23.0) {
	       if (lng < 55.0) {
	        if (lat < 22.75) {
	         return 164;
	        } else {
	         return 322;
	        }
	       } else {
	        if (lng < 55.25) {
	         if (lat < 22.75) {
	          return 164;
	         } else {
	          return 322;
	         }
	        } else {
	         if (lat < 22.75) {
	          return 164;
	         } else {
	          return 358;
	         }
	        }
	       }
	      } else {
	       if (lng < 55.25) {
	        return 322;
	       } else {
	        if (lat < 23.25) {
	         return 358;
	        } else {
	         return 322;
	        }
	       }
	      }
	     } else {
	      return 358;
	     }
	    } else {
	     if (lng < 55.5) {
	      return 322;
	     } else {
	      if (lat < 24.0) {
	       if (lng < 55.75) {
	        if (lat < 23.75) {
	         return 358;
	        } else {
	         return 322;
	        }
	       } else {
	        return 358;
	       }
	      } else {
	       if (lng < 55.75) {
	        return 322;
	       } else {
	        if (lng < 56.0) {
	         if (lat < 24.25) {
	          return 358;
	         } else {
	          return 322;
	         }
	        } else {
	         return 358;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 54.75) {
	    if (lat < 25.5) {
	     return 322;
	    } else {
	     return 55;
	    }
	   } else {
	    if (lat < 25.5) {
	     if (lng < 55.5) {
	      return 322;
	     } else {
	      if (lat < 25.0) {
	       if (lng < 56.0) {
	        return 322;
	       } else {
	        return 358;
	       }
	      } else {
	       return 322;
	      }
	     }
	    } else {
	     if (lng < 55.5) {
	      return 55;
	     } else {
	      if (lat < 26.0) {
	       return 322;
	      } else {
	       return 55;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup147(double lat, double lng)
	{
	 if (lat < 42.0) {
	  if (lng < 47.75) {
	   if (lng < 46.25) {
	    if (lat < 40.5) {
	     if (lng < 45.5) {
	      if (lat < 39.75) {
	       if (lng < 45.25) {
	        if (lat < 39.5) {
	         return 55;
	        } else {
	         return 51;
	        }
	       } else {
	        return 51;
	       }
	      } else {
	       return 48;
	      }
	     } else {
	      if (lat < 39.75) {
	       if (lng < 46.0) {
	        return 51;
	       } else {
	        return 48;
	       }
	      } else {
	       if (lng < 45.75) {
	        return 48;
	       } else {
	        if (lat < 40.0) {
	         return 48;
	        } else {
	         if (lng < 46.0) {
	          if (lat < 40.25) {
	           return 51;
	          } else {
	           return 48;
	          }
	         } else {
	          return 51;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 41.25) {
	      if (lng < 45.5) {
	       return 48;
	      } else {
	       return 51;
	      }
	     } else {
	      if (lng < 45.5) {
	       if (lat < 41.5) {
	        if (lng < 45.25) {
	         return 48;
	        } else {
	         return 51;
	        }
	       } else {
	        return 47;
	       }
	      } else {
	       if (lng < 45.75) {
	        if (lat < 41.5) {
	         return 51;
	        } else {
	         return 47;
	        }
	       } else {
	        return 47;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 40.5) {
	     if (lng < 47.0) {
	      if (lat < 39.75) {
	       if (lng < 46.75) {
	        return 48;
	       } else {
	        return 51;
	       }
	      } else {
	       return 51;
	      }
	     } else {
	      if (lat < 39.5) {
	       if (lng < 47.25) {
	        return 51;
	       } else {
	        return 55;
	       }
	      } else {
	       return 51;
	      }
	     }
	    } else {
	     if (lng < 47.0) {
	      if (lat < 41.25) {
	       return 51;
	      } else {
	       if (lng < 46.5) {
	        return 47;
	       } else {
	        if (lat < 41.5) {
	         if (lng < 46.75) {
	          return 47;
	         } else {
	          return 51;
	         }
	        } else {
	         return 51;
	        }
	       }
	      }
	     } else {
	      if (lat < 41.25) {
	       return 51;
	      } else {
	       if (lng < 47.25) {
	        if (lat < 41.75) {
	         return 51;
	        } else {
	         return 184;
	        }
	       } else {
	        if (lat < 41.5) {
	         if (lng < 47.5) {
	          return 51;
	         } else {
	          return 184;
	         }
	        } else {
	         return 184;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 49.0) {
	    if (lat < 40.5) {
	     if (lng < 48.25) {
	      if (lat < 39.75) {
	       return 55;
	      } else {
	       return 51;
	      }
	     } else {
	      return 51;
	     }
	    } else {
	     if (lat < 41.25) {
	      return 51;
	     } else {
	      if (lng < 48.25) {
	       if (lat < 41.5) {
	        if (lng < 48.0) {
	         return 184;
	        } else {
	         return 51;
	        }
	       } else {
	        return 184;
	       }
	      } else {
	       if (lng < 48.5) {
	        if (lat < 41.75) {
	         return 51;
	        } else {
	         return 184;
	        }
	       } else {
	        return 51;
	       }
	      }
	     }
	    }
	   } else {
	    return 51;
	   }
	  }
	 } else {
	  if (lng < 47.75) {
	   if (lat < 43.5) {
	    if (lng < 46.25) {
	     if (lat < 42.75) {
	      if (lng < 45.75) {
	       return 47;
	      } else {
	       if (lat < 42.25) {
	        return 47;
	       } else {
	        return 184;
	       }
	      }
	     } else {
	      return 184;
	     }
	    } else {
	     return 184;
	    }
	   } else {
	    return 184;
	   }
	  } else {
	   if (lat < 43.5) {
	    return 184;
	   } else {
	    if (lng < 49.0) {
	     return 184;
	    } else {
	     return 120;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup148(double lat, double lng)
	{
	 if (lng < 50.5) {
	  if (lat < 39.25) {
	   if (lng < 47.75) {
	    if (lat < 36.5) {
	     if (lng < 46.25) {
	      if (lat < 35.0) {
	       if (lng < 45.5) {
	        return 28;
	       } else {
	        if (lat < 34.25) {
	         if (lng < 45.75) {
	          if (lat < 34.0) {
	           return 28;
	          } else {
	           return 55;
	          }
	         } else {
	          return 55;
	         }
	        } else {
	         if (lng < 45.75) {
	          return 28;
	         } else {
	          return 55;
	         }
	        }
	       }
	      } else {
	       if (lat < 35.75) {
	        if (lng < 46.0) {
	         return 28;
	        } else {
	         if (lat < 35.25) {
	          return 55;
	         } else {
	          if (lat < 35.5) {
	           return 28;
	          } else {
	           return 55;
	          }
	         }
	        }
	       } else {
	        if (lng < 45.5) {
	         return 28;
	        } else {
	         if (lng < 45.75) {
	          if (lat < 36.25) {
	           return 28;
	          } else {
	           return 55;
	          }
	         } else {
	          if (lat < 36.0) {
	           return 28;
	          } else {
	           return 55;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 35.75) {
	       return 55;
	      } else {
	       if (lng < 46.5) {
	        if (lat < 36.0) {
	         return 28;
	        } else {
	         return 55;
	        }
	       } else {
	        return 55;
	       }
	      }
	     }
	    } else {
	     if (lng < 46.25) {
	      if (lat < 37.75) {
	       if (lng < 45.25) {
	        if (lat < 37.0) {
	         return 28;
	        } else {
	         return 55;
	        }
	       } else {
	        return 55;
	       }
	      } else {
	       if (lat < 39.0) {
	        return 55;
	       } else {
	        if (lng < 45.5) {
	         return 55;
	        } else {
	         return 51;
	        }
	       }
	      }
	     } else {
	      if (lat < 39.0) {
	       return 55;
	      } else {
	       if (lng < 46.75) {
	        return 48;
	       } else {
	        return 55;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 37.75) {
	     return 55;
	    } else {
	     if (lng < 49.0) {
	      if (lat < 38.5) {
	       return 55;
	      } else {
	       if (lng < 48.25) {
	        return 55;
	       } else {
	        if (lng < 48.5) {
	         if (lat < 38.75) {
	          return 55;
	         } else {
	          if (lat < 39.0) {
	           return 51;
	          } else {
	           return 55;
	          }
	         }
	        } else {
	         if (lat < 38.75) {
	          if (lng < 48.75) {
	           return 55;
	          } else {
	           return 51;
	          }
	         } else {
	          return 51;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < 49.75) {
	       if (lat < 38.5) {
	        return 55;
	       } else {
	        return 51;
	       }
	      } else {
	       return 0;
	      }
	     }
	    }
	   }
	  } else {
	   return kdLookup147(lat,lng);
	  }
	 } else {
	  if (lat < 39.25) {
	   if (lng < 53.25) {
	    return 55;
	   } else {
	    if (lat < 37.5) {
	     return 55;
	    } else {
	     if (lng < 54.75) {
	      return 76;
	     } else {
	      if (lat < 38.25) {
	       if (lng < 55.5) {
	        if (lng < 55.0) {
	         return 76;
	        } else {
	         if (lat < 38.0) {
	          return 55;
	         } else {
	          return 76;
	         }
	        }
	       } else {
	        return 55;
	       }
	      } else {
	       return 76;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 53.25) {
	    if (lat < 42.0) {
	     return 76;
	    } else {
	     if (lat < 42.25) {
	      if (lng < 51.75) {
	       return 0;
	      } else {
	       if (lng < 52.5) {
	        return 120;
	       } else {
	        if (lng < 53.0) {
	         return 120;
	        } else {
	         return 76;
	        }
	       }
	      }
	     } else {
	      return 120;
	     }
	    }
	   } else {
	    if (lat < 42.0) {
	     if (lng < 55.25) {
	      return 76;
	     } else {
	      if (lat < 41.5) {
	       return 76;
	      } else {
	       return 120;
	      }
	     }
	    } else {
	     if (lng < 54.75) {
	      if (lat < 42.5) {
	       if (lng < 54.0) {
	        if (lng < 53.5) {
	         if (lat < 42.25) {
	          return 76;
	         } else {
	          return 120;
	         }
	        } else {
	         return 76;
	        }
	       } else {
	        if (lng < 54.5) {
	         return 76;
	        } else {
	         if (lat < 42.25) {
	          return 76;
	         } else {
	          return 120;
	         }
	        }
	       }
	      } else {
	       return 120;
	      }
	     } else {
	      if (lat < 43.5) {
	       if (lng < 55.5) {
	        if (lat < 42.25) {
	         if (lng < 55.0) {
	          return 76;
	         } else {
	          return 120;
	         }
	        } else {
	         return 120;
	        }
	       } else {
	        if (lat < 42.75) {
	         if (lng < 56.0) {
	          return 120;
	         } else {
	          if (lat < 42.25) {
	           return 120;
	          } else {
	           return 168;
	          }
	         }
	        } else {
	         if (lng < 56.0) {
	          return 120;
	         } else {
	          return 168;
	         }
	        }
	       }
	      } else {
	       if (lng < 56.0) {
	        return 120;
	       } else {
	        return 168;
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup149(double lat, double lng)
	{
	 if (lng < 61.75) {
	  if (lat < 28.0) {
	   if (lng < 59.0) {
	    if (lat < 25.25) {
	     if (lng < 57.5) {
	      if (lat < 24.25) {
	       return 358;
	      } else {
	       if (lng < 56.75) {
	        if (lat < 25.0) {
	         return 358;
	        } else {
	         return 322;
	        }
	       } else {
	        return 358;
	       }
	      }
	     } else {
	      return 358;
	     }
	    } else {
	     if (lng < 56.75) {
	      if (lat < 26.5) {
	       if (lat < 25.75) {
	        return 322;
	       } else {
	        return 358;
	       }
	      } else {
	       return 55;
	      }
	     } else {
	      return 55;
	     }
	    }
	   } else {
	    if (lat < 25.0) {
	     return 358;
	    } else {
	     return 55;
	    }
	   }
	  } else {
	   if (lat < 31.5) {
	    if (lng < 61.0) {
	     return 55;
	    } else {
	     if (lat < 29.75) {
	      if (lat < 29.25) {
	       return 55;
	      } else {
	       if (lng < 61.25) {
	        return 55;
	       } else {
	        if (lng < 61.5) {
	         if (lat < 29.5) {
	          return 55;
	         } else {
	          return 211;
	         }
	        } else {
	         return 211;
	        }
	       }
	      }
	     } else {
	      if (lat < 30.5) {
	       if (lng < 61.25) {
	        if (lat < 30.0) {
	         return 211;
	        } else {
	         return 55;
	        }
	       } else {
	        return 367;
	       }
	      } else {
	       if (lat < 30.75) {
	        if (lng < 61.5) {
	         return 55;
	        } else {
	         return 367;
	        }
	       } else {
	        return 55;
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 60.75) {
	     return 55;
	    } else {
	     if (lat < 32.5) {
	      if (lng < 61.0) {
	       return 55;
	      } else {
	       return 367;
	      }
	     } else {
	      if (lat < 33.0) {
	       if (lng < 61.0) {
	        if (lat < 32.75) {
	         return 55;
	        } else {
	         return 367;
	        }
	       } else {
	        return 367;
	       }
	      } else {
	       if (lng < 61.0) {
	        if (lat < 33.5) {
	         return 367;
	        } else {
	         return 55;
	        }
	       } else {
	        return 367;
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 28.0) {
	   if (lng < 63.5) {
	    if (lat < 25.25) {
	     return 211;
	    } else {
	     if (lat < 26.5) {
	      if (lng < 62.0) {
	       if (lat < 26.0) {
	        return 211;
	       } else {
	        return 55;
	       }
	      } else {
	       return 211;
	      }
	     } else {
	      if (lng < 62.5) {
	       return 55;
	      } else {
	       if (lat < 27.25) {
	        if (lng < 63.0) {
	         if (lat < 26.75) {
	          return 211;
	         } else {
	          return 55;
	         }
	        } else {
	         if (lat < 26.75) {
	          return 211;
	         } else {
	          if (lng < 63.25) {
	           return 55;
	          } else {
	           if (lat < 27.0) {
	            return 211;
	           } else {
	            return 55;
	           }
	          }
	         }
	        }
	       } else {
	        if (lng < 63.0) {
	         return 55;
	        } else {
	         return 211;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return 211;
	   }
	  } else {
	   if (lng < 64.5) {
	    if (lat < 29.75) {
	     if (lng < 63.0) {
	      if (lat < 28.75) {
	       if (lng < 62.25) {
	        return 55;
	       } else {
	        if (lng < 62.5) {
	         if (lat < 28.5) {
	          return 55;
	         } else {
	          return 211;
	         }
	        } else {
	         if (lat < 28.5) {
	          return 55;
	         } else {
	          return 211;
	         }
	        }
	       }
	      } else {
	       if (lng < 62.25) {
	        return 211;
	       } else {
	        if (lat < 29.5) {
	         return 211;
	        } else {
	         return 367;
	        }
	       }
	      }
	     } else {
	      if (lat < 29.5) {
	       return 211;
	      } else {
	       if (lng < 64.25) {
	        return 367;
	       } else {
	        return 211;
	       }
	      }
	     }
	    } else {
	     if (lat < 33.5) {
	      if (lat < 33.25) {
	       if (lat < 33.0) {
	        if (lat < 31.25) {
	         if (lng < 62.0) {
	          if (lat < 31.0) {
	           return 367;
	          } else {
	           return 55;
	          }
	         } else {
	          return 367;
	         }
	        } else {
	         if (lng < 62.0) {
	          if (lat < 31.5) {
	           return 55;
	          } else {
	           return 367;
	          }
	         } else {
	          return 367;
	         }
	        }
	       } else {
	        return 367;
	       }
	      } else {
	       return 367;
	      }
	     } else {
	      return 367;
	     }
	    }
	   } else {
	    if (lat < 30.75) {
	     if (lng < 66.0) {
	      if (lat < 29.75) {
	       return 211;
	      } else {
	       return 367;
	      }
	     } else {
	      if (lat < 30.0) {
	       return 211;
	      } else {
	       if (lng < 66.5) {
	        return 367;
	       } else {
	        return 211;
	       }
	      }
	     }
	    } else {
	     if (lng < 66.5) {
	      return 367;
	     } else {
	      if (lat < 31.5) {
	       if (lng < 67.0) {
	        if (lat < 31.0) {
	         return 211;
	        } else {
	         if (lng < 66.75) {
	          return 367;
	         } else {
	          if (lat < 31.25) {
	           return 211;
	          } else {
	           return 367;
	          }
	         }
	        }
	       } else {
	        if (lat < 31.25) {
	         return 211;
	        } else {
	         if (lng < 67.25) {
	          return 211;
	         } else {
	          return 367;
	         }
	        }
	       }
	      } else {
	       return 367;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup150(double lat, double lng)
	{
	 if (lat < 39.25) {
	  if (lng < 59.0) {
	   if (lat < 37.75) {
	    return 55;
	   } else {
	    if (lng < 57.5) {
	     if (lat < 38.5) {
	      if (lng < 56.5) {
	       if (lat < 38.25) {
	        return 55;
	       } else {
	        return 76;
	       }
	      } else {
	       if (lng < 57.0) {
	        return 55;
	       } else {
	        if (lat < 38.25) {
	         return 55;
	        } else {
	         if (lng < 57.25) {
	          return 76;
	         } else {
	          return 55;
	         }
	        }
	       }
	      }
	     } else {
	      return 76;
	     }
	    } else {
	     if (lng < 58.25) {
	      if (lat < 38.0) {
	       return 55;
	      } else {
	       return 76;
	      }
	     } else {
	      return 76;
	     }
	    }
	   }
	  } else {
	   if (lat < 36.5) {
	    if (lng < 60.75) {
	     return 55;
	    } else {
	     if (lat < 35.0) {
	      if (lat < 34.5) {
	       return 367;
	      } else {
	       if (lng < 61.25) {
	        if (lng < 61.0) {
	         return 55;
	        } else {
	         if (lat < 34.75) {
	          return 367;
	         } else {
	          return 55;
	         }
	        }
	       } else {
	        return 367;
	       }
	      }
	     } else {
	      if (lat < 35.75) {
	       if (lng < 61.25) {
	        return 55;
	       } else {
	        if (lat < 35.5) {
	         return 367;
	        } else {
	         if (lng < 61.5) {
	          return 55;
	         } else {
	          return 76;
	         }
	        }
	       }
	      } else {
	       if (lng < 61.25) {
	        return 55;
	       } else {
	        if (lat < 36.0) {
	         if (lng < 61.5) {
	          return 55;
	         } else {
	          return 76;
	         }
	        } else {
	         return 76;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 60.25) {
	     if (lat < 37.75) {
	      if (lng < 59.5) {
	       if (lat < 37.5) {
	        return 55;
	       } else {
	        if (lng < 59.25) {
	         return 55;
	        } else {
	         return 76;
	        }
	       }
	      } else {
	       if (lat < 37.25) {
	        return 55;
	       } else {
	        return 76;
	       }
	      }
	     } else {
	      return 76;
	     }
	    } else {
	     if (lat < 37.0) {
	      if (lng < 61.0) {
	       if (lng < 60.5) {
	        return 55;
	       } else {
	        if (lng < 60.75) {
	         if (lat < 36.75) {
	          return 55;
	         } else {
	          return 76;
	         }
	        } else {
	         if (lat < 36.75) {
	          return 55;
	         } else {
	          return 76;
	         }
	        }
	       }
	      } else {
	       if (lng < 61.25) {
	        if (lat < 36.75) {
	         return 55;
	        } else {
	         return 76;
	        }
	       } else {
	        return 76;
	       }
	      }
	     } else {
	      return 76;
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 42.0) {
	   if (lng < 59.0) {
	    if (lng < 57.25) {
	     if (lat < 41.5) {
	      return 76;
	     } else {
	      if (lng < 57.0) {
	       return 168;
	      } else {
	       if (lat < 41.75) {
	        return 168;
	       } else {
	        return 76;
	       }
	      }
	     }
	    } else {
	     return 76;
	    }
	   } else {
	    if (lng < 60.25) {
	     return 76;
	    } else {
	     if (lat < 41.25) {
	      return 76;
	     } else {
	      if (lng < 61.0) {
	       if (lng < 60.5) {
	        if (lat < 41.5) {
	         return 76;
	        } else {
	         return 168;
	        }
	       } else {
	        return 168;
	       }
	      } else {
	       if (lng < 61.5) {
	        return 168;
	       } else {
	        if (lat < 41.5) {
	         return 76;
	        } else {
	         return 168;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 59.0) {
	    if (lat < 43.5) {
	     if (lng < 57.5) {
	      if (lat < 42.25) {
	       if (lng < 57.25) {
	        return 168;
	       } else {
	        return 76;
	       }
	      } else {
	       return 168;
	      }
	     } else {
	      if (lng < 58.25) {
	       if (lat < 42.5) {
	        if (lng < 57.75) {
	         if (lat < 42.25) {
	          return 76;
	         } else {
	          return 168;
	         }
	        } else {
	         if (lng < 58.0) {
	          if (lat < 42.25) {
	           return 76;
	          } else {
	           return 168;
	          }
	         } else {
	          return 76;
	         }
	        }
	       } else {
	        return 168;
	       }
	      } else {
	       if (lat < 42.75) {
	        if (lng < 58.5) {
	         if (lat < 42.5) {
	          return 76;
	         } else {
	          return 168;
	         }
	        } else {
	         return 76;
	        }
	       } else {
	        return 168;
	       }
	      }
	     }
	    } else {
	     return 168;
	    }
	   } else {
	    if (lat < 43.5) {
	     if (lng < 60.0) {
	      if (lat < 42.75) {
	       if (lng < 59.5) {
	        if (lat < 42.5) {
	         return 76;
	        } else {
	         if (lng < 59.25) {
	          return 76;
	         } else {
	          return 168;
	         }
	        }
	       } else {
	        if (lat < 42.5) {
	         return 76;
	        } else {
	         return 168;
	        }
	       }
	      } else {
	       return 168;
	      }
	     } else {
	      return 168;
	     }
	    } else {
	     if (lng < 60.25) {
	      return 168;
	     } else {
	      if (lng < 61.0) {
	       return 168;
	      } else {
	       if (lat < 44.25) {
	        if (lng < 61.5) {
	         return 168;
	        } else {
	         if (lat < 44.0) {
	          return 168;
	         } else {
	          return 240;
	         }
	        }
	       } else {
	        if (lng < 61.25) {
	         if (lat < 44.5) {
	          return 168;
	         } else {
	          return 240;
	         }
	        } else {
	         return 240;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup151(double lat, double lng)
	{
	 if (lng < 64.5) {
	  if (lat < 42.0) {
	   if (lng < 63.0) {
	    if (lat < 40.5) {
	     if (lng < 62.5) {
	      return 76;
	     } else {
	      if (lat < 40.0) {
	       return 76;
	      } else {
	       return 168;
	      }
	     }
	    } else {
	     if (lat < 41.25) {
	      if (lng < 62.25) {
	       if (lat < 41.0) {
	        return 76;
	       } else {
	        if (lng < 62.0) {
	         return 76;
	        } else {
	         return 168;
	        }
	       }
	      } else {
	       return 168;
	      }
	     } else {
	      return 168;
	     }
	    }
	   } else {
	    if (lat < 39.75) {
	     if (lng < 63.75) {
	      if (lng < 63.5) {
	       return 76;
	      } else {
	       if (lat < 39.5) {
	        return 76;
	       } else {
	        return 168;
	       }
	      }
	     } else {
	      return 168;
	     }
	    } else {
	     return 168;
	    }
	   }
	  } else {
	   if (lat < 43.5) {
	    return 168;
	   } else {
	    if (lng < 63.0) {
	     if (lat < 43.75) {
	      if (lng < 62.75) {
	       if (lng < 62.5) {
	        if (lng < 62.0) {
	         return 168;
	        } else {
	         if (lng < 62.25) {
	          return 240;
	         } else {
	          return 168;
	         }
	        }
	       } else {
	        return 168;
	       }
	      } else {
	       return 168;
	      }
	     } else {
	      return 240;
	     }
	    } else {
	     if (lng < 63.75) {
	      if (lat < 43.75) {
	       return 168;
	      } else {
	       return 240;
	      }
	     } else {
	      if (lat < 43.75) {
	       return 168;
	      } else {
	       return 240;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 42.0) {
	   if (lng < 66.75) {
	    return 168;
	   } else {
	    if (lat < 40.5) {
	     if (lat < 40.0) {
	      return 168;
	     } else {
	      if (lng < 67.0) {
	       return 168;
	      } else {
	       if (lng < 67.25) {
	        if (lat < 40.25) {
	         return 168;
	        } else {
	         return 247;
	        }
	       } else {
	        return 247;
	       }
	      }
	     }
	    } else {
	     if (lat < 41.25) {
	      if (lng < 67.0) {
	       if (lat < 40.75) {
	        return 247;
	       } else {
	        if (lat < 41.0) {
	         return 168;
	        } else {
	         return 247;
	        }
	       }
	      } else {
	       return 247;
	      }
	     } else {
	      return 348;
	     }
	    }
	   }
	  } else {
	   if (lng < 66.0) {
	    if (lat < 43.5) {
	     if (lng < 65.75) {
	      return 168;
	     } else {
	      if (lat < 43.25) {
	       return 168;
	      } else {
	       return 240;
	      }
	     }
	    } else {
	     if (lng < 65.25) {
	      if (lat < 44.0) {
	       if (lng < 64.75) {
	        if (lat < 43.75) {
	         return 168;
	        } else {
	         return 240;
	        }
	       } else {
	        if (lng < 65.0) {
	         if (lat < 43.75) {
	          return 168;
	         } else {
	          return 240;
	         }
	        } else {
	         return 168;
	        }
	       }
	      } else {
	       return 240;
	      }
	     } else {
	      if (lat < 43.75) {
	       if (lng < 65.5) {
	        return 168;
	       } else {
	        return 240;
	       }
	      } else {
	       return 240;
	      }
	     }
	    }
	   } else {
	    if (lat < 43.0) {
	     if (lng < 66.75) {
	      if (lat < 42.5) {
	       if (lng < 66.25) {
	        return 168;
	       } else {
	        return 348;
	       }
	      } else {
	       if (lng < 66.25) {
	        return 168;
	       } else {
	        if (lng < 66.5) {
	         return 240;
	        } else {
	         if (lat < 42.75) {
	          return 348;
	         } else {
	          return 240;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 42.75) {
	       return 348;
	      } else {
	       if (lng < 67.0) {
	        return 240;
	       } else {
	        return 348;
	       }
	      }
	     }
	    } else {
	     return 240;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup152(double lat, double lng)
	{
	 if (lng < 56.25) {
	  if (lat < 33.75) {
	   if (lng < 50.5) {
	    if (lat < 28.0) {
	     return 164;
	    } else {
	     if (lat < 30.75) {
	      if (lng < 47.75) {
	       if (lng < 46.25) {
	        if (lat < 29.25) {
	         return 164;
	        } else {
	         return 28;
	        }
	       } else {
	        if (lat < 29.25) {
	         if (lng < 47.5) {
	          return 164;
	         } else {
	          if (lat < 29.0) {
	           return 164;
	          } else {
	           return 232;
	          }
	         }
	        } else {
	         if (lng < 47.0) {
	          if (lat < 29.5) {
	           if (lng < 46.75) {
	            return 28;
	           } else {
	            return 232;
	           }
	          } else {
	           return 28;
	          }
	         } else {
	          if (lat < 30.0) {
	           if (lng < 47.25) {
	            if (lat < 29.75) {
	             return 232;
	            } else {
	             return 28;
	            }
	           } else {
	            return 232;
	           }
	          } else {
	           if (lng < 47.25) {
	            return 28;
	           } else {
	            if (lat < 30.25) {
	             return 232;
	            } else {
	             return 28;
	            }
	           }
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < 49.0) {
	        if (lat < 29.25) {
	         if (lng < 48.25) {
	          if (lat < 28.75) {
	           return 164;
	          } else {
	           return 232;
	          }
	         } else {
	          if (lat < 28.5) {
	           return 164;
	          } else {
	           if (lng < 48.5) {
	            if (lat < 28.75) {
	             return 164;
	            } else {
	             return 232;
	            }
	           } else {
	            if (lat < 28.75) {
	             return 164;
	            } else {
	             return 232;
	            }
	           }
	          }
	         }
	        } else {
	         if (lat < 30.0) {
	          return 232;
	         } else {
	          if (lng < 48.25) {
	           if (lat < 30.25) {
	            return 232;
	           } else {
	            return 28;
	           }
	          } else {
	           if (lng < 48.5) {
	            if (lat < 30.25) {
	             return 0;
	            } else {
	             if (lat < 30.5) {
	              return 28;
	             } else {
	              return 55;
	             }
	            }
	           } else {
	            return 55;
	           }
	          }
	         }
	        }
	       } else {
	        if (lat < 29.25) {
	         return 164;
	        } else {
	         return 55;
	        }
	       }
	      }
	     } else {
	      if (lng < 47.75) {
	       if (lat < 32.5) {
	        return 28;
	       } else {
	        if (lng < 46.25) {
	         return 28;
	        } else {
	         if (lng < 47.0) {
	          if (lat < 33.0) {
	           return 28;
	          } else {
	           return 55;
	          }
	         } else {
	          if (lat < 32.75) {
	           if (lng < 47.25) {
	            return 28;
	           } else {
	            return 55;
	           }
	          } else {
	           return 55;
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < 32.0) {
	        if (lng < 48.25) {
	         if (lat < 31.25) {
	          if (lng < 48.0) {
	           if (lat < 31.0) {
	            return 28;
	           } else {
	            return 55;
	           }
	          } else {
	           if (lat < 31.0) {
	            return 28;
	           } else {
	            return 55;
	           }
	          }
	         } else {
	          if (lat < 31.75) {
	           return 55;
	          } else {
	           if (lng < 48.0) {
	            return 28;
	           } else {
	            return 55;
	           }
	          }
	         }
	        } else {
	         return 55;
	        }
	       } else {
	        return 55;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 26.75) {
	     return kdLookup146(lat,lng);
	    } else {
	     return 55;
	    }
	   }
	  } else {
	   return kdLookup148(lat,lng);
	  }
	 } else {
	  if (lat < 33.75) {
	   return kdLookup149(lat,lng);
	  } else {
	   if (lng < 61.75) {
	    return kdLookup150(lat,lng);
	   } else {
	    if (lat < 39.25) {
	     if (lng < 64.5) {
	      if (lat < 36.5) {
	       if (lng < 63.0) {
	        if (lat < 35.5) {
	         return 367;
	        } else {
	         return 76;
	        }
	       } else {
	        if (lat < 35.5) {
	         return 367;
	        } else {
	         if (lng < 63.75) {
	          if (lat < 36.0) {
	           if (lng < 63.25) {
	            return 76;
	           } else {
	            return 367;
	           }
	          } else {
	           return 76;
	          }
	         } else {
	          if (lat < 36.0) {
	           return 367;
	          } else {
	           if (lng < 64.0) {
	            return 76;
	           } else {
	            if (lng < 64.25) {
	             if (lat < 36.25) {
	              return 367;
	             } else {
	              return 76;
	             }
	            } else {
	             if (lat < 36.25) {
	              return 367;
	             } else {
	              return 76;
	             }
	            }
	           }
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < 64.25) {
	        return 76;
	       } else {
	        if (lat < 39.0) {
	         return 76;
	        } else {
	         return 168;
	        }
	       }
	      }
	     } else {
	      if (lat < 36.5) {
	       return 367;
	      } else {
	       if (lng < 66.0) {
	        if (lat < 37.75) {
	         if (lng < 65.25) {
	          if (lat < 37.0) {
	           if (lng < 64.75) {
	            return 76;
	           } else {
	            return 367;
	           }
	          } else {
	           if (lng < 65.0) {
	            return 76;
	           } else {
	            if (lat < 37.25) {
	             return 367;
	            } else {
	             return 76;
	            }
	           }
	          }
	         } else {
	          if (lat < 37.25) {
	           return 367;
	          } else {
	           if (lng < 65.75) {
	            return 76;
	           } else {
	            return 367;
	           }
	          }
	         }
	        } else {
	         if (lng < 65.25) {
	          if (lat < 38.75) {
	           return 76;
	          } else {
	           if (lng < 64.75) {
	            if (lat < 39.0) {
	             return 76;
	            } else {
	             return 168;
	            }
	           } else {
	            return 168;
	           }
	          }
	         } else {
	          if (lat < 38.5) {
	           if (lng < 65.75) {
	            return 76;
	           } else {
	            if (lat < 38.25) {
	             return 76;
	            } else {
	             return 168;
	            }
	           }
	          } else {
	           return 168;
	          }
	         }
	        }
	       } else {
	        if (lat < 37.75) {
	         if (lng < 66.75) {
	          if (lat < 37.5) {
	           return 367;
	          } else {
	           return 76;
	          }
	         } else {
	          if (lat < 37.25) {
	           return 367;
	          } else {
	           if (lng < 67.0) {
	            if (lat < 37.5) {
	             return 367;
	            } else {
	             return 168;
	            }
	           } else {
	            if (lng < 67.25) {
	             if (lat < 37.5) {
	              return 367;
	             } else {
	              return 168;
	             }
	            } else {
	             return 168;
	            }
	           }
	          }
	         }
	        } else {
	         if (lng < 66.75) {
	          if (lat < 38.25) {
	           return 76;
	          } else {
	           return 168;
	          }
	         } else {
	          return 168;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     return kdLookup151(lat,lng);
	    }
	   }
	  }
	 }
	}

	private  int kdLookup153(double lat, double lng)
	{
	 if (lat < 28.0) {
	  if (lng < 70.25) {
	   if (lat < 24.5) {
	    if (lng < 68.75) {
	     if (lat < 23.5) {
	      return 372;
	     } else {
	      if (lng < 68.25) {
	       return 211;
	      } else {
	       if (lat < 24.0) {
	        return 372;
	       } else {
	        return 211;
	       }
	      }
	     }
	    } else {
	     if (lat < 24.0) {
	      return 372;
	     } else {
	      if (lng < 69.5) {
	       if (lng < 69.0) {
	        return 211;
	       } else {
	        if (lng < 69.25) {
	         if (lat < 24.25) {
	          return 372;
	         } else {
	          return 211;
	         }
	        } else {
	         return 372;
	        }
	       }
	      } else {
	       if (lng < 69.75) {
	        return 372;
	       } else {
	        if (lng < 70.0) {
	         if (lat < 24.25) {
	          return 372;
	         } else {
	          return 211;
	         }
	        } else {
	         if (lat < 24.25) {
	          return 372;
	         } else {
	          return 211;
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 26.75) {
	     return 211;
	    } else {
	     if (lng < 69.75) {
	      return 211;
	     } else {
	      if (lat < 27.5) {
	       return 372;
	      } else {
	       if (lng < 70.0) {
	        return 211;
	       } else {
	        if (lat < 27.75) {
	         return 372;
	        } else {
	         return 211;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 25.25) {
	    if (lng < 71.25) {
	     if (lat < 24.25) {
	      return 372;
	     } else {
	      if (lng < 70.75) {
	       if (lat < 24.5) {
	        return 372;
	       } else {
	        return 211;
	       }
	      } else {
	       if (lat < 24.75) {
	        if (lng < 71.0) {
	         return 211;
	        } else {
	         return 372;
	        }
	       } else {
	        if (lng < 71.0) {
	         return 211;
	        } else {
	         if (lat < 25.0) {
	          return 211;
	         } else {
	          return 372;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     return 372;
	    }
	   } else {
	    if (lng < 71.25) {
	     if (lat < 26.5) {
	      if (lat < 25.75) {
	       if (lng < 70.75) {
	        return 211;
	       } else {
	        if (lng < 71.0) {
	         if (lat < 25.5) {
	          return 211;
	         } else {
	          return 372;
	         }
	        } else {
	         return 372;
	        }
	       }
	      } else {
	       if (lng < 70.5) {
	        if (lat < 26.0) {
	         return 211;
	        } else {
	         return 372;
	        }
	       } else {
	        return 372;
	       }
	      }
	     } else {
	      if (lat < 27.75) {
	       return 372;
	      } else {
	       if (lng < 70.75) {
	        return 372;
	       } else {
	        return 211;
	       }
	      }
	     }
	    } else {
	     return 372;
	    }
	   }
	  }
	 } else {
	  if (lat < 30.75) {
	   if (lng < 70.5) {
	    return 211;
	   } else {
	    if (lat < 29.0) {
	     if (lng < 71.75) {
	      if (lng < 70.75) {
	       if (lat < 28.25) {
	        return 372;
	       } else {
	        return 211;
	       }
	      } else {
	       return 211;
	      }
	     } else {
	      if (lng < 72.25) {
	       if (lat < 28.25) {
	        if (lng < 72.0) {
	         return 211;
	        } else {
	         return 372;
	        }
	       } else {
	        return 211;
	       }
	      } else {
	       if (lat < 28.75) {
	        return 372;
	       } else {
	        if (lng < 72.5) {
	         return 211;
	        } else {
	         return 372;
	        }
	       }
	      }
	     }
	    } else {
	     return 211;
	    }
	   }
	  } else {
	   if (lng < 70.25) {
	    if (lat < 32.25) {
	     if (lng < 68.75) {
	      if (lat < 31.5) {
	       if (lng < 67.75) {
	        if (lat < 31.25) {
	         return 211;
	        } else {
	         return 367;
	        }
	       } else {
	        return 211;
	       }
	      } else {
	       if (lng < 68.0) {
	        if (lat < 31.75) {
	         if (lng < 67.75) {
	          return 367;
	         } else {
	          return 211;
	         }
	        } else {
	         return 367;
	        }
	       } else {
	        if (lng < 68.25) {
	         if (lat < 31.75) {
	          return 211;
	         } else {
	          return 367;
	         }
	        } else {
	         if (lat < 31.75) {
	          return 211;
	         } else {
	          if (lng < 68.5) {
	           if (lat < 32.0) {
	            return 211;
	           } else {
	            return 367;
	           }
	          } else {
	           return 367;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < 69.5) {
	       if (lat < 31.75) {
	        return 211;
	       } else {
	        if (lng < 69.25) {
	         return 367;
	        } else {
	         if (lat < 32.0) {
	          return 211;
	         } else {
	          return 367;
	         }
	        }
	       }
	      } else {
	       return 211;
	      }
	     }
	    } else {
	     if (lng < 69.5) {
	      return 367;
	     } else {
	      if (lat < 33.25) {
	       return 211;
	      } else {
	       return 367;
	      }
	     }
	    }
	   } else {
	    return 211;
	   }
	  }
	 }
	}

	private  int kdLookup154(double lat, double lng)
	{
	 if (lng < 70.25) {
	  if (lat < 36.5) {
	   if (lng < 70.0) {
	    return 367;
	   } else {
	    if (lat < 34.25) {
	     return 211;
	    } else {
	     return 367;
	    }
	   }
	  } else {
	   if (lng < 68.75) {
	    if (lat < 37.75) {
	     if (lng < 68.0) {
	      if (lat < 37.25) {
	       return 367;
	      } else {
	       if (lng < 67.75) {
	        if (lat < 37.5) {
	         return 367;
	        } else {
	         return 168;
	        }
	       } else {
	        return 168;
	       }
	      }
	     } else {
	      if (lat < 37.0) {
	       return 367;
	      } else {
	       if (lng < 68.25) {
	        return 209;
	       } else {
	        if (lat < 37.25) {
	         return 367;
	        } else {
	         return 209;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 38.5) {
	      if (lng < 68.25) {
	       return 168;
	      } else {
	       if (lat < 38.0) {
	        return 209;
	       } else {
	        if (lng < 68.5) {
	         return 168;
	        } else {
	         return 209;
	        }
	       }
	      }
	     } else {
	      if (lng < 68.25) {
	       return 168;
	      } else {
	       return 209;
	      }
	     }
	    }
	   } else {
	    if (lat < 37.75) {
	     if (lng < 69.5) {
	      if (lat < 37.25) {
	       return 367;
	      } else {
	       if (lng < 69.0) {
	        if (lat < 37.5) {
	         return 367;
	        } else {
	         return 209;
	        }
	       } else {
	        if (lng < 69.25) {
	         if (lat < 37.5) {
	          return 367;
	         } else {
	          return 209;
	         }
	        } else {
	         return 209;
	        }
	       }
	      }
	     } else {
	      return 367;
	     }
	    } else {
	     return 209;
	    }
	   }
	  }
	 } else {
	  if (lat < 36.5) {
	   if (lng < 71.5) {
	    if (lat < 35.0) {
	     if (lng < 70.75) {
	      if (lat < 34.0) {
	       return 211;
	      } else {
	       return 367;
	      }
	     } else {
	      if (lat < 34.25) {
	       if (lng < 71.0) {
	        if (lat < 34.0) {
	         return 211;
	        } else {
	         return 367;
	        }
	       } else {
	        return 211;
	       }
	      } else {
	       if (lng < 71.0) {
	        return 367;
	       } else {
	        if (lat < 34.5) {
	         if (lng < 71.25) {
	          return 367;
	         } else {
	          return 211;
	         }
	        } else {
	         if (lng < 71.25) {
	          if (lat < 34.75) {
	           return 211;
	          } else {
	           return 367;
	          }
	         } else {
	          return 211;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     return 367;
	    }
	   } else {
	    if (lat < 35.0) {
	     return 211;
	    } else {
	     if (lng < 71.75) {
	      if (lat < 35.75) {
	       return 367;
	      } else {
	       return 211;
	      }
	     } else {
	      return 211;
	     }
	    }
	   }
	  } else {
	   if (lng < 71.5) {
	    if (lat < 37.75) {
	     return 367;
	    } else {
	     if (lat < 38.5) {
	      if (lng < 70.75) {
	       if (lat < 38.0) {
	        if (lng < 70.5) {
	         return 209;
	        } else {
	         return 367;
	        }
	       } else {
	        if (lng < 70.5) {
	         return 209;
	        } else {
	         if (lat < 38.25) {
	          return 367;
	         } else {
	          return 209;
	         }
	        }
	       }
	      } else {
	       return 367;
	      }
	     } else {
	      return 209;
	     }
	    }
	   } else {
	    if (lat < 37.75) {
	     if (lng < 72.25) {
	      if (lat < 37.0) {
	       if (lng < 71.75) {
	        return 367;
	       } else {
	        if (lng < 72.0) {
	         if (lat < 36.75) {
	          return 367;
	         } else {
	          return 209;
	         }
	        } else {
	         if (lat < 36.75) {
	          return 211;
	         } else {
	          return 367;
	         }
	        }
	       }
	      } else {
	       if (lng < 71.75) {
	        if (lat < 37.5) {
	         return 209;
	        } else {
	         return 367;
	        }
	       } else {
	        return 209;
	       }
	      }
	     } else {
	      if (lat < 37.0) {
	       return 211;
	      } else {
	       if (lng < 72.75) {
	        return 209;
	       } else {
	        if (lat < 37.25) {
	         return 367;
	        } else {
	         return 209;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 71.75) {
	      if (lat < 38.0) {
	       return 367;
	      } else {
	       return 209;
	      }
	     } else {
	      return 209;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup155(double lat, double lng)
	{
	 if (lng < 71.5) {
	  if (lat < 40.5) {
	   if (lng < 70.75) {
	    if (lat < 39.75) {
	     return 209;
	    } else {
	     if (lat < 40.25) {
	      return 231;
	     } else {
	      return 209;
	     }
	    }
	   } else {
	    if (lat < 39.75) {
	     if (lng < 71.0) {
	      if (lat < 39.5) {
	       return 209;
	      } else {
	       return 231;
	      }
	     } else {
	      if (lng < 71.25) {
	       if (lat < 39.5) {
	        return 209;
	       } else {
	        return 231;
	       }
	      } else {
	       return 209;
	      }
	     }
	    } else {
	     if (lng < 71.0) {
	      if (lat < 40.25) {
	       return 231;
	      } else {
	       return 247;
	      }
	     } else {
	      return 231;
	     }
	    }
	   }
	  } else {
	   if (lat < 41.25) {
	    if (lng < 70.75) {
	     if (lat < 40.75) {
	      if (lng < 70.5) {
	       return 209;
	      } else {
	       return 247;
	      }
	     } else {
	      if (lng < 70.5) {
	       if (lat < 41.0) {
	        return 209;
	       } else {
	        return 247;
	       }
	      } else {
	       return 209;
	      }
	     }
	    } else {
	     if (lng < 71.0) {
	      if (lat < 40.75) {
	       return 247;
	      } else {
	       if (lat < 41.0) {
	        return 209;
	       } else {
	        return 247;
	       }
	      }
	     } else {
	      return 247;
	     }
	    }
	   } else {
	    if (lng < 70.75) {
	     if (lat < 41.5) {
	      return 247;
	     } else {
	      if (lng < 70.5) {
	       return 247;
	      } else {
	       if (lat < 41.75) {
	        return 231;
	       } else {
	        return 247;
	       }
	      }
	     }
	    } else {
	     if (lng < 71.0) {
	      if (lat < 41.5) {
	       return 247;
	      } else {
	       return 231;
	      }
	     } else {
	      return 231;
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 40.5) {
	   if (lng < 72.25) {
	    if (lat < 39.75) {
	     if (lng < 71.75) {
	      return 209;
	     } else {
	      if (lng < 72.0) {
	       if (lat < 39.5) {
	        return 209;
	       } else {
	        return 231;
	       }
	      } else {
	       if (lat < 39.5) {
	        return 209;
	       } else {
	        return 231;
	       }
	      }
	     }
	    } else {
	     if (lng < 71.75) {
	      if (lat < 40.25) {
	       return 231;
	      } else {
	       return 247;
	      }
	     } else {
	      if (lat < 40.25) {
	       return 231;
	      } else {
	       if (lng < 72.0) {
	        return 247;
	       } else {
	        return 231;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 39.5) {
	     if (lng < 72.5) {
	      return 231;
	     } else {
	      return 209;
	     }
	    } else {
	     return 231;
	    }
	   }
	  } else {
	   if (lng < 72.25) {
	    if (lat < 41.25) {
	     return 247;
	    } else {
	     if (lng < 71.75) {
	      if (lat < 41.5) {
	       return 247;
	      } else {
	       return 231;
	      }
	     } else {
	      if (lat < 41.5) {
	       if (lng < 72.0) {
	        return 247;
	       } else {
	        return 231;
	       }
	      } else {
	       return 231;
	      }
	     }
	    }
	   } else {
	    if (lat < 41.25) {
	     if (lng < 72.5) {
	      return 247;
	     } else {
	      if (lat < 40.75) {
	       return 231;
	      } else {
	       if (lng < 72.75) {
	        return 247;
	       } else {
	        if (lat < 41.0) {
	         return 247;
	        } else {
	         return 231;
	        }
	       }
	      }
	     }
	    } else {
	     return 231;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup156(double lat, double lng)
	{
	 if (lat < 42.0) {
	  if (lng < 70.25) {
	   if (lng < 68.75) {
	    if (lat < 40.5) {
	     if (lng < 68.0) {
	      if (lat < 39.75) {
	       return 209;
	      } else {
	       return 247;
	      }
	     } else {
	      if (lat < 39.75) {
	       return 209;
	      } else {
	       return 247;
	      }
	     }
	    } else {
	     if (lat < 41.25) {
	      return 247;
	     } else {
	      return 348;
	     }
	    }
	   } else {
	    if (lat < 40.5) {
	     if (lng < 69.5) {
	      if (lat < 40.0) {
	       return 209;
	      } else {
	       if (lng < 69.0) {
	        return 247;
	       } else {
	        if (lng < 69.25) {
	         if (lat < 40.25) {
	          return 209;
	         } else {
	          return 247;
	         }
	        } else {
	         if (lat < 40.25) {
	          return 209;
	         } else {
	          return 247;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 39.75) {
	       return 209;
	      } else {
	       if (lng < 69.75) {
	        if (lat < 40.0) {
	         return 231;
	        } else {
	         return 209;
	        }
	       } else {
	        if (lat < 40.25) {
	         return 231;
	        } else {
	         return 209;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 69.5) {
	      if (lat < 41.25) {
	       return 247;
	      } else {
	       if (lng < 69.0) {
	        return 348;
	       } else {
	        if (lat < 41.5) {
	         return 247;
	        } else {
	         return 348;
	        }
	       }
	      }
	     } else {
	      if (lat < 41.25) {
	       if (lng < 69.75) {
	        if (lat < 41.0) {
	         return 209;
	        } else {
	         return 247;
	        }
	       } else {
	        if (lat < 40.75) {
	         return 209;
	        } else {
	         return 247;
	        }
	       }
	      } else {
	       if (lng < 69.75) {
	        if (lat < 41.75) {
	         return 247;
	        } else {
	         return 348;
	        }
	       } else {
	        if (lat < 41.75) {
	         return 247;
	        } else {
	         if (lng < 70.0) {
	          return 348;
	         } else {
	          return 247;
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   return kdLookup155(lat,lng);
	  }
	 } else {
	  if (lng < 70.25) {
	   if (lat < 43.5) {
	    if (lng < 68.0) {
	     if (lat < 43.25) {
	      return 348;
	     } else {
	      return 240;
	     }
	    } else {
	     return 348;
	    }
	   } else {
	    if (lng < 68.25) {
	     if (lat < 44.25) {
	      if (lng < 67.75) {
	       return 240;
	      } else {
	       if (lat < 43.75) {
	        return 348;
	       } else {
	        if (lng < 68.0) {
	         return 240;
	        } else {
	         if (lat < 44.0) {
	          return 348;
	         } else {
	          return 240;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < 67.75) {
	       return 240;
	      } else {
	       if (lat < 44.5) {
	        if (lng < 68.0) {
	         return 240;
	        } else {
	         return 348;
	        }
	       } else {
	        return 348;
	       }
	      }
	     }
	    } else {
	     return 348;
	    }
	   }
	  } else {
	   if (lat < 43.0) {
	    if (lng < 71.5) {
	     if (lng < 70.75) {
	      if (lat < 42.25) {
	       if (lng < 70.5) {
	        return 348;
	       } else {
	        return 247;
	       }
	      } else {
	       return 348;
	      }
	     } else {
	      if (lat < 42.5) {
	       if (lng < 71.0) {
	        if (lat < 42.25) {
	         return 247;
	        } else {
	         return 348;
	        }
	       } else {
	        if (lng < 71.25) {
	         if (lat < 42.25) {
	          return 231;
	         } else {
	          return 247;
	         }
	        } else {
	         return 231;
	        }
	       }
	      } else {
	       if (lng < 71.25) {
	        return 348;
	       } else {
	        if (lat < 42.75) {
	         return 231;
	        } else {
	         return 348;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 72.5) {
	      return 231;
	     } else {
	      if (lat < 42.75) {
	       return 231;
	      } else {
	       return 348;
	      }
	     }
	    }
	   } else {
	    return 348;
	   }
	  }
	 }
	}

	private  int kdLookup157(double lat, double lng)
	{
	 if (lng < 75.75) {
	  if (lat < 36.5) {
	   if (lng < 74.25) {
	    if (lat < 34.75) {
	     if (lng < 74.0) {
	      return 211;
	     } else {
	      if (lat < 34.5) {
	       if (lat < 34.0) {
	        return 372;
	       } else {
	        if (lat < 34.25) {
	         return 211;
	        } else {
	         return 372;
	        }
	       }
	      } else {
	       return 372;
	      }
	     }
	    } else {
	     return 211;
	    }
	   } else {
	    if (lat < 35.0) {
	     if (lng < 75.0) {
	      if (lat < 34.75) {
	       if (lat < 34.5) {
	        if (lng < 74.5) {
	         if (lat < 34.0) {
	          return 372;
	         } else {
	          if (lat < 34.25) {
	           return 211;
	          } else {
	           return 372;
	          }
	         }
	        } else {
	         return 372;
	        }
	       } else {
	        return 372;
	       }
	      } else {
	       if (lng < 74.75) {
	        return 372;
	       } else {
	        return 211;
	       }
	      }
	     } else {
	      if (lat < 34.75) {
	       return 372;
	      } else {
	       return 211;
	      }
	     }
	    } else {
	     return 211;
	    }
	   }
	  } else {
	   if (lng < 74.25) {
	    if (lat < 37.75) {
	     if (lng < 73.5) {
	      if (lat < 37.0) {
	       return 211;
	      } else {
	       if (lat < 37.5) {
	        return 367;
	       } else {
	        return 209;
	       }
	      }
	     } else {
	      if (lat < 37.0) {
	       return 211;
	      } else {
	       if (lng < 73.75) {
	        if (lat < 37.5) {
	         return 367;
	        } else {
	         return 209;
	        }
	       } else {
	        if (lat < 37.25) {
	         return 367;
	        } else {
	         if (lng < 74.0) {
	          return 209;
	         } else {
	          if (lat < 37.5) {
	           return 367;
	          } else {
	           return 209;
	          }
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 38.75) {
	      return 209;
	     } else {
	      if (lng < 74.0) {
	       return 209;
	      } else {
	       return 4;
	      }
	     }
	    }
	   } else {
	    if (lat < 37.75) {
	     if (lng < 75.0) {
	      if (lat < 37.0) {
	       return 211;
	      } else {
	       if (lng < 74.5) {
	        if (lat < 37.5) {
	         return 367;
	        } else {
	         return 209;
	        }
	       } else {
	        if (lat < 37.25) {
	         if (lng < 74.75) {
	          return 367;
	         } else {
	          return 211;
	         }
	        } else {
	         if (lng < 74.75) {
	          if (lat < 37.5) {
	           return 367;
	          } else {
	           return 209;
	          }
	         } else {
	          if (lat < 37.5) {
	           return 4;
	          } else {
	           return 209;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 37.0) {
	       if (lng < 75.5) {
	        return 211;
	       } else {
	        if (lat < 36.75) {
	         return 211;
	        } else {
	         return 4;
	        }
	       }
	      } else {
	       if (lng < 75.25) {
	        if (lat < 37.5) {
	         return 4;
	        } else {
	         return 209;
	        }
	       } else {
	        return 4;
	       }
	      }
	     }
	    } else {
	     if (lng < 75.0) {
	      if (lat < 38.75) {
	       return 209;
	      } else {
	       return 4;
	      }
	     } else {
	      return 4;
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 36.5) {
	   if (lng < 77.25) {
	    if (lat < 35.0) {
	     if (lng < 76.5) {
	      if (lat < 34.75) {
	       return 372;
	      } else {
	       return 211;
	      }
	     } else {
	      return 372;
	     }
	    } else {
	     if (lng < 76.5) {
	      if (lat < 36.0) {
	       return 211;
	      } else {
	       if (lng < 76.25) {
	        return 211;
	       } else {
	        return 4;
	       }
	      }
	     } else {
	      if (lat < 35.75) {
	       return 211;
	      } else {
	       if (lng < 76.75) {
	        if (lat < 36.0) {
	         return 211;
	        } else {
	         return 4;
	        }
	       } else {
	        return 4;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 35.0) {
	     if (lng < 78.25) {
	      return 372;
	     } else {
	      if (lat < 34.75) {
	       return 372;
	      } else {
	       return 4;
	      }
	     }
	    } else {
	     if (lng < 78.0) {
	      if (lat < 35.75) {
	       if (lng < 77.5) {
	        if (lat < 35.25) {
	         return 372;
	        } else {
	         return 211;
	        }
	       } else {
	        if (lat < 35.5) {
	         return 372;
	        } else {
	         if (lng < 77.75) {
	          return 4;
	         } else {
	          return 211;
	         }
	        }
	       }
	      } else {
	       return 4;
	      }
	     } else {
	      if (lat < 35.5) {
	       if (lng < 78.25) {
	        return 372;
	       } else {
	        return 4;
	       }
	      } else {
	       return 4;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 76.0) {
	    if (lat < 36.75) {
	     return 211;
	    } else {
	     return 4;
	    }
	   } else {
	    return 4;
	   }
	  }
	 }
	}

	private  int kdLookup158(double lat, double lng)
	{
	 if (lat < 33.75) {
	  if (lng < 73.0) {
	   return kdLookup153(lat,lng);
	  } else {
	   if (lat < 29.25) {
	    return 372;
	   } else {
	    if (lng < 75.75) {
	     if (lat < 31.5) {
	      if (lng < 74.25) {
	       if (lat < 30.25) {
	        if (lng < 73.5) {
	         if (lat < 29.75) {
	          if (lng < 73.25) {
	           return 211;
	          } else {
	           return 372;
	          }
	         } else {
	          return 211;
	         }
	        } else {
	         if (lat < 30.0) {
	          return 372;
	         } else {
	          if (lng < 73.75) {
	           return 211;
	          } else {
	           return 372;
	          }
	         }
	        }
	       } else {
	        if (lng < 74.0) {
	         return 211;
	        } else {
	         if (lat < 30.75) {
	          return 372;
	         } else {
	          return 211;
	         }
	        }
	       }
	      } else {
	       if (lat < 30.75) {
	        return 372;
	       } else {
	        if (lng < 74.75) {
	         if (lat < 31.0) {
	          if (lng < 74.5) {
	           return 211;
	          } else {
	           return 372;
	          }
	         } else {
	          return 211;
	         }
	        } else {
	         return 372;
	        }
	       }
	      }
	     } else {
	      if (lng < 74.25) {
	       return 211;
	      } else {
	       if (lat < 32.5) {
	        if (lng < 75.0) {
	         if (lat < 32.0) {
	          if (lng < 74.75) {
	           return 211;
	          } else {
	           return 372;
	          }
	         } else {
	          return 211;
	         }
	        } else {
	         if (lat < 32.25) {
	          return 372;
	         } else {
	          if (lng < 75.5) {
	           return 211;
	          } else {
	           return 372;
	          }
	         }
	        }
	       } else {
	        if (lng < 74.75) {
	         if (lat < 33.0) {
	          return 211;
	         } else {
	          if (lat < 33.25) {
	           if (lng < 74.5) {
	            return 211;
	           } else {
	            return 372;
	           }
	          } else {
	           return 372;
	          }
	         }
	        } else {
	         return 372;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 32.25) {
	      return 372;
	     } else {
	      if (lng < 78.5) {
	       return 372;
	      } else {
	       if (lat < 32.75) {
	        return 4;
	       } else {
	        return 372;
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 73.0) {
	   if (lat < 39.25) {
	    return kdLookup154(lat,lng);
	   } else {
	    return kdLookup156(lat,lng);
	   }
	  } else {
	   if (lat < 39.25) {
	    return kdLookup157(lat,lng);
	   } else {
	    if (lng < 75.75) {
	     if (lat < 42.0) {
	      if (lng < 74.25) {
	       if (lat < 40.25) {
	        if (lng < 73.5) {
	         if (lat < 39.5) {
	          return 209;
	         } else {
	          return 231;
	         }
	        } else {
	         if (lat < 39.75) {
	          if (lng < 73.75) {
	           if (lat < 39.5) {
	            return 209;
	           } else {
	            return 231;
	           }
	          } else {
	           if (lng < 74.0) {
	            if (lat < 39.5) {
	             return 4;
	            } else {
	             return 231;
	            }
	           } else {
	            return 4;
	           }
	          }
	         } else {
	          if (lng < 74.0) {
	           return 231;
	          } else {
	           return 4;
	          }
	         }
	        }
	       } else {
	        return 231;
	       }
	      } else {
	       if (lat < 40.5) {
	        if (lng < 74.75) {
	         if (lat < 40.25) {
	          return 4;
	         } else {
	          return 231;
	         }
	        } else {
	         return 4;
	        }
	       } else {
	        if (lng < 75.5) {
	         return 231;
	        } else {
	         if (lat < 40.75) {
	          return 4;
	         } else {
	          return 231;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 43.25) {
	       if (lng < 74.25) {
	        if (lng < 73.5) {
	         if (lat < 42.75) {
	          return 231;
	         } else {
	          return 348;
	         }
	        } else {
	         if (lat < 43.0) {
	          return 231;
	         } else {
	          if (lng < 73.75) {
	           return 348;
	          } else {
	           return 231;
	          }
	         }
	        }
	       } else {
	        if (lng < 75.0) {
	         if (lat < 43.0) {
	          return 231;
	         } else {
	          if (lng < 74.75) {
	           return 231;
	          } else {
	           return 348;
	          }
	         }
	        } else {
	         if (lat < 43.0) {
	          return 231;
	         } else {
	          return 348;
	         }
	        }
	       }
	      } else {
	       return 348;
	      }
	     }
	    } else {
	     if (lat < 42.0) {
	      if (lng < 77.25) {
	       if (lat < 40.5) {
	        return 4;
	       } else {
	        if (lng < 76.75) {
	         return 231;
	        } else {
	         if (lat < 41.25) {
	          if (lat < 41.0) {
	           return 4;
	          } else {
	           if (lng < 77.0) {
	            return 231;
	           } else {
	            return 4;
	           }
	          }
	         } else {
	          return 231;
	         }
	        }
	       }
	      } else {
	       if (lat < 41.25) {
	        return 4;
	       } else {
	        if (lng < 78.5) {
	         return 231;
	        } else {
	         if (lat < 41.5) {
	          return 4;
	         } else {
	          return 231;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < 77.25) {
	       if (lat < 43.0) {
	        return 231;
	       } else {
	        return 348;
	       }
	      } else {
	       if (lat < 43.0) {
	        return 231;
	       } else {
	        return 348;
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup159(double lat, double lng)
	{
	 if (lat < 30.75) {
	  if (lng < 81.5) {
	   if (lng < 80.25) {
	    return 372;
	   } else {
	    if (lat < 29.25) {
	     if (lng < 80.75) {
	      if (lat < 28.75) {
	       return 372;
	      } else {
	       if (lng < 80.5) {
	        if (lat < 29.0) {
	         return 372;
	        } else {
	         return 269;
	        }
	       } else {
	        return 269;
	       }
	      }
	     } else {
	      if (lat < 28.5) {
	       return 372;
	      } else {
	       if (lng < 81.0) {
	        if (lat < 28.75) {
	         return 372;
	        } else {
	         return 269;
	        }
	       } else {
	        return 269;
	       }
	      }
	     }
	    } else {
	     if (lat < 30.0) {
	      if (lng < 80.5) {
	       return 372;
	      } else {
	       return 269;
	      }
	     } else {
	      if (lng < 80.75) {
	       if (lat < 30.5) {
	        return 372;
	       } else {
	        if (lng < 80.5) {
	         return 372;
	        } else {
	         return 4;
	        }
	       }
	      } else {
	       if (lng < 81.0) {
	        if (lat < 30.25) {
	         return 269;
	        } else {
	         if (lat < 30.5) {
	          return 372;
	         } else {
	          return 4;
	         }
	        }
	       } else {
	        if (lat < 30.25) {
	         return 269;
	        } else {
	         if (lng < 81.25) {
	          if (lat < 30.5) {
	           return 372;
	          } else {
	           return 4;
	          }
	         } else {
	          return 4;
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 82.75) {
	    if (lat < 29.25) {
	     if (lng < 81.75) {
	      if (lat < 28.25) {
	       return 372;
	      } else {
	       return 269;
	      }
	     } else {
	      return 269;
	     }
	    } else {
	     if (lat < 30.25) {
	      return 269;
	     } else {
	      if (lng < 82.0) {
	       if (lng < 81.75) {
	        if (lat < 30.5) {
	         return 269;
	        } else {
	         return 4;
	        }
	       } else {
	        if (lat < 30.5) {
	         return 269;
	        } else {
	         return 4;
	        }
	       }
	      } else {
	       if (lng < 82.25) {
	        if (lat < 30.5) {
	         return 269;
	        } else {
	         return 4;
	        }
	       } else {
	        return 407;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 29.25) {
	     return 269;
	    } else {
	     if (lng < 83.5) {
	      if (lat < 30.0) {
	       if (lng < 83.0) {
	        return 269;
	       } else {
	        if (lat < 29.75) {
	         return 269;
	        } else {
	         return 407;
	        }
	       }
	      } else {
	       return 407;
	      }
	     } else {
	      if (lat < 29.5) {
	       if (lng < 84.0) {
	        return 407;
	       } else {
	        return 269;
	       }
	      } else {
	       return 407;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 81.5) {
	   if (lat < 32.25) {
	    if (lng < 80.0) {
	     if (lat < 31.5) {
	      if (lng < 79.25) {
	       if (lat < 31.25) {
	        return 372;
	       } else {
	        if (lng < 79.0) {
	         return 372;
	        } else {
	         return 4;
	        }
	       }
	      } else {
	       if (lng < 79.5) {
	        if (lat < 31.25) {
	         return 372;
	        } else {
	         return 4;
	        }
	       } else {
	        if (lat < 31.0) {
	         return 372;
	        } else {
	         if (lng < 79.75) {
	          if (lat < 31.25) {
	           return 372;
	          } else {
	           return 4;
	          }
	         } else {
	          return 4;
	         }
	        }
	       }
	      }
	     } else {
	      return 4;
	     }
	    } else {
	     if (lng < 80.25) {
	      if (lat < 31.0) {
	       return 372;
	      } else {
	       return 4;
	      }
	     } else {
	      return 4;
	     }
	    }
	   } else {
	    if (lng < 79.5) {
	     if (lat < 33.0) {
	      if (lng < 79.0) {
	       if (lat < 32.75) {
	        return 4;
	       } else {
	        return 372;
	       }
	      } else {
	       if (lat < 32.5) {
	        return 4;
	       } else {
	        if (lng < 79.25) {
	         return 372;
	        } else {
	         if (lat < 32.75) {
	          return 4;
	         } else {
	          return 372;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < 79.0) {
	       return 372;
	      } else {
	       if (lat < 33.25) {
	        if (lng < 79.25) {
	         return 372;
	        } else {
	         return 4;
	        }
	       } else {
	        if (lng < 79.25) {
	         if (lat < 33.5) {
	          return 372;
	         } else {
	          return 4;
	         }
	        } else {
	         return 4;
	        }
	       }
	      }
	     }
	    } else {
	     return 4;
	    }
	   }
	  } else {
	   if (lat < 32.25) {
	    if (lng < 82.75) {
	     if (lat < 31.0) {
	      if (lng < 82.5) {
	       return 4;
	      } else {
	       return 407;
	      }
	     } else {
	      return 4;
	     }
	    } else {
	     if (lng < 83.25) {
	      if (lat < 31.0) {
	       if (lng < 83.0) {
	        return 407;
	       } else {
	        return 4;
	       }
	      } else {
	       if (lat < 31.5) {
	        return 4;
	       } else {
	        if (lat < 31.75) {
	         if (lng < 83.0) {
	          return 4;
	         } else {
	          return 407;
	         }
	        } else {
	         if (lng < 83.0) {
	          if (lat < 32.0) {
	           return 407;
	          } else {
	           return 4;
	          }
	         } else {
	          if (lat < 32.0) {
	           return 407;
	          } else {
	           return 4;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      return 407;
	     }
	    }
	   } else {
	    if (lng < 83.0) {
	     return 4;
	    } else {
	     if (lat < 33.0) {
	      if (lng < 83.25) {
	       return 4;
	      } else {
	       return 407;
	      }
	     } else {
	      if (lng < 83.25) {
	       if (lat < 33.25) {
	        return 4;
	       } else {
	        return 407;
	       }
	      } else {
	       return 407;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup160(double lat, double lng)
	{
	 if (lng < 87.0) {
	  if (lat < 26.5) {
	   return 372;
	  } else {
	   if (lng < 85.5) {
	    if (lat < 27.25) {
	     if (lng < 85.0) {
	      return 372;
	     } else {
	      if (lat < 27.0) {
	       return 372;
	      } else {
	       return 269;
	      }
	     }
	    } else {
	     if (lng < 84.75) {
	      if (lat < 27.5) {
	       return 372;
	      } else {
	       return 269;
	      }
	     } else {
	      return 269;
	     }
	    }
	   } else {
	    if (lng < 86.25) {
	     if (lat < 27.0) {
	      if (lng < 85.75) {
	       return 372;
	      } else {
	       if (lng < 86.0) {
	        if (lat < 26.75) {
	         return 372;
	        } else {
	         return 269;
	        }
	       } else {
	        if (lat < 26.75) {
	         return 372;
	        } else {
	         return 269;
	        }
	       }
	      }
	     } else {
	      return 269;
	     }
	    } else {
	     if (lat < 26.75) {
	      if (lng < 86.75) {
	       return 372;
	      } else {
	       return 269;
	      }
	     } else {
	      return 269;
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 25.25) {
	   if (lng < 88.5) {
	    if (lat < 24.5) {
	     return 372;
	    } else {
	     if (lng < 88.25) {
	      return 372;
	     } else {
	      if (lat < 25.0) {
	       return 151;
	      } else {
	       return 372;
	      }
	     }
	    }
	   } else {
	    if (lat < 23.75) {
	     if (lng < 89.0) {
	      if (lat < 23.25) {
	       return 372;
	      } else {
	       if (lng < 88.75) {
	        return 372;
	       } else {
	        return 151;
	       }
	      }
	     } else {
	      return 151;
	     }
	    } else {
	     if (lng < 89.0) {
	      if (lat < 24.5) {
	       if (lat < 24.0) {
	        if (lng < 88.75) {
	         return 372;
	        } else {
	         return 151;
	        }
	       } else {
	        if (lng < 88.75) {
	         return 372;
	        } else {
	         if (lat < 24.25) {
	          return 372;
	         } else {
	          return 151;
	         }
	        }
	       }
	      } else {
	       return 151;
	      }
	     } else {
	      return 151;
	     }
	    }
	   }
	  } else {
	   if (lng < 88.5) {
	    if (lat < 26.5) {
	     if (lng < 88.25) {
	      return 372;
	     } else {
	      if (lat < 26.0) {
	       return 372;
	      } else {
	       if (lat < 26.25) {
	        return 151;
	       } else {
	        return 372;
	       }
	      }
	     }
	    } else {
	     if (lng < 87.75) {
	      if (lat < 26.75) {
	       if (lng < 87.25) {
	        return 372;
	       } else {
	        return 269;
	       }
	      } else {
	       return 269;
	      }
	     } else {
	      if (lat < 27.25) {
	       if (lng < 88.25) {
	        return 269;
	       } else {
	        return 372;
	       }
	      } else {
	       if (lng < 88.25) {
	        return 269;
	       } else {
	        return 372;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 26.5) {
	     if (lng < 89.25) {
	      if (lat < 25.75) {
	       if (lng < 88.75) {
	        return 372;
	       } else {
	        if (lng < 89.0) {
	         if (lat < 25.5) {
	          return 372;
	         } else {
	          return 151;
	         }
	        } else {
	         return 151;
	        }
	       }
	      } else {
	       if (lng < 89.0) {
	        return 151;
	       } else {
	        if (lat < 26.25) {
	         return 151;
	        } else {
	         return 372;
	        }
	       }
	      }
	     } else {
	      if (lat < 26.25) {
	       return 151;
	      } else {
	       return 372;
	      }
	     }
	    } else {
	     if (lng < 89.25) {
	      if (lat < 27.25) {
	       if (lng < 88.75) {
	        if (lat < 26.75) {
	         return 151;
	        } else {
	         return 372;
	        }
	       } else {
	        if (lat < 27.0) {
	         return 372;
	        } else {
	         if (lng < 89.0) {
	          return 372;
	         } else {
	          return 62;
	         }
	        }
	       }
	      } else {
	       if (lng < 89.0) {
	        return 372;
	       } else {
	        if (lat < 27.5) {
	         return 62;
	        } else {
	         return 407;
	        }
	       }
	      }
	     } else {
	      if (lat < 27.0) {
	       if (lng < 89.75) {
	        return 372;
	       } else {
	        if (lat < 26.75) {
	         return 372;
	        } else {
	         return 62;
	        }
	       }
	      } else {
	       return 62;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup161(double lat, double lng)
	{
	 if (lng < 81.5) {
	  if (lat < 43.5) {
	   if (lng < 80.0) {
	    if (lat < 42.75) {
	     if (lng < 79.5) {
	      return 231;
	     } else {
	      if (lat < 42.5) {
	       return 231;
	      } else {
	       return 348;
	      }
	     }
	    } else {
	     if (lng < 79.25) {
	      if (lat < 43.0) {
	       return 231;
	      } else {
	       return 348;
	      }
	     } else {
	      return 348;
	     }
	    }
	   } else {
	    if (lng < 80.75) {
	     if (lat < 42.75) {
	      if (lng < 80.25) {
	       if (lat < 42.25) {
	        return 4;
	       } else {
	        if (lat < 42.5) {
	         return 231;
	        } else {
	         return 348;
	        }
	       }
	      } else {
	       if (lat < 42.25) {
	        return 4;
	       } else {
	        if (lng < 80.5) {
	         return 348;
	        } else {
	         return 4;
	        }
	       }
	      }
	     } else {
	      if (lng < 80.25) {
	       return 348;
	      } else {
	       if (lat < 43.0) {
	        return 4;
	       } else {
	        if (lng < 80.5) {
	         return 348;
	        } else {
	         if (lat < 43.25) {
	          return 4;
	         } else {
	          return 348;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 43.25) {
	      return 4;
	     } else {
	      if (lng < 81.0) {
	       return 348;
	      } else {
	       return 4;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 80.5) {
	    return 348;
	   } else {
	    if (lat < 44.0) {
	     if (lng < 80.75) {
	      return 348;
	     } else {
	      return 4;
	     }
	    } else {
	     return 4;
	    }
	   }
	  }
	 } else {
	  if (lat < 43.5) {
	   if (lng < 82.75) {
	    return 4;
	   } else {
	    if (lng < 83.25) {
	     if (lat < 42.75) {
	      if (lat < 42.25) {
	       if (lng < 83.0) {
	        return 4;
	       } else {
	        return 407;
	       }
	      } else {
	       if (lng < 83.0) {
	        return 4;
	       } else {
	        return 407;
	       }
	      }
	     } else {
	      if (lat < 43.0) {
	       return 407;
	      } else {
	       if (lng < 83.0) {
	        return 4;
	       } else {
	        if (lat < 43.25) {
	         return 4;
	        } else {
	         return 407;
	        }
	       }
	      }
	     }
	    } else {
	     return 407;
	    }
	   }
	  } else {
	   if (lng < 82.75) {
	    if (lat < 43.75) {
	     if (lng < 82.5) {
	      return 4;
	     } else {
	      return 407;
	     }
	    } else {
	     if (lng < 82.0) {
	      return 4;
	     } else {
	      if (lat < 44.25) {
	       return 4;
	      } else {
	       if (lng < 82.25) {
	        if (lat < 44.5) {
	         return 4;
	        } else {
	         if (lat < 44.75) {
	          return 407;
	         } else {
	          return 4;
	         }
	        }
	       } else {
	        if (lat < 44.5) {
	         if (lng < 82.5) {
	          return 4;
	         } else {
	          return 407;
	         }
	        } else {
	         if (lng < 82.5) {
	          if (lat < 44.75) {
	           return 407;
	          } else {
	           return 4;
	          }
	         } else {
	          if (lat < 44.75) {
	           return 407;
	          } else {
	           return 4;
	          }
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 83.5) {
	     if (lat < 44.75) {
	      if (lat < 44.0) {
	       if (lng < 83.0) {
	        if (lat < 43.75) {
	         return 407;
	        } else {
	         return 4;
	        }
	       } else {
	        if (lng < 83.25) {
	         if (lat < 43.75) {
	          return 407;
	         } else {
	          return 4;
	         }
	        } else {
	         if (lat < 43.75) {
	          return 407;
	         } else {
	          return 4;
	         }
	        }
	       }
	      } else {
	       if (lng < 83.0) {
	        if (lat < 44.25) {
	         return 4;
	        } else {
	         return 407;
	        }
	       } else {
	        if (lat < 44.25) {
	         return 4;
	        } else {
	         return 407;
	        }
	       }
	      }
	     } else {
	      return 407;
	     }
	    } else {
	     if (lat < 44.75) {
	      if (lat < 44.0) {
	       if (lng < 83.75) {
	        if (lat < 43.75) {
	         return 407;
	        } else {
	         return 4;
	        }
	       } else {
	        if (lng < 84.0) {
	         if (lat < 43.75) {
	          return 407;
	         } else {
	          return 4;
	         }
	        } else {
	         if (lat < 43.75) {
	          return 407;
	         } else {
	          return 4;
	         }
	        }
	       }
	      } else {
	       if (lng < 83.75) {
	        if (lat < 44.25) {
	         return 4;
	        } else {
	         return 407;
	        }
	       } else {
	        return 407;
	       }
	      }
	     } else {
	      return 407;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup162(double lat, double lng)
	{
	 if (lat < 33.75) {
	  if (lng < 84.25) {
	   if (lat < 28.0) {
	    if (lng < 82.5) {
	     return 372;
	    } else {
	     if (lat < 27.5) {
	      return 372;
	     } else {
	      if (lng < 83.0) {
	       if (lng < 82.75) {
	        if (lat < 27.75) {
	         return 372;
	        } else {
	         return 269;
	        }
	       } else {
	        if (lat < 27.75) {
	         return 372;
	        } else {
	         return 269;
	        }
	       }
	      } else {
	       return 269;
	      }
	     }
	    }
	   } else {
	    return kdLookup159(lat,lng);
	   }
	  } else {
	   if (lat < 28.0) {
	    return kdLookup160(lat,lng);
	   } else {
	    if (lng < 87.0) {
	     if (lat < 29.0) {
	      if (lng < 85.5) {
	       if (lng < 84.75) {
	        if (lat < 28.75) {
	         return 269;
	        } else {
	         if (lng < 84.5) {
	          return 269;
	         } else {
	          return 407;
	         }
	        }
	       } else {
	        if (lat < 28.5) {
	         return 269;
	        } else {
	         if (lng < 85.0) {
	          if (lat < 28.75) {
	           return 269;
	          } else {
	           return 407;
	          }
	         } else {
	          if (lng < 85.25) {
	           if (lat < 28.75) {
	            return 269;
	           } else {
	            return 407;
	           }
	          } else {
	           return 407;
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < 86.25) {
	        if (lat < 28.5) {
	         if (lng < 85.75) {
	          return 269;
	         } else {
	          if (lng < 86.0) {
	           if (lat < 28.25) {
	            return 269;
	           } else {
	            return 407;
	           }
	          } else {
	           return 407;
	          }
	         }
	        } else {
	         return 407;
	        }
	       } else {
	        if (lat < 28.25) {
	         if (lng < 86.75) {
	          return 407;
	         } else {
	          return 269;
	         }
	        } else {
	         return 407;
	        }
	       }
	      }
	     } else {
	      return 407;
	     }
	    } else {
	     if (lat < 28.25) {
	      if (lng < 88.5) {
	       return 407;
	      } else {
	       if (lng < 89.25) {
	        if (lng < 89.0) {
	         return 372;
	        } else {
	         return 407;
	        }
	       } else {
	        if (lng < 89.5) {
	         return 407;
	        } else {
	         return 62;
	        }
	       }
	      }
	     } else {
	      return 407;
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 84.25) {
	   if (lat < 39.25) {
	    if (lng < 81.5) {
	     if (lat < 34.5) {
	      if (lng < 79.0) {
	       return 372;
	      } else {
	       return 4;
	      }
	     } else {
	      return 4;
	     }
	    } else {
	     if (lat < 36.5) {
	      if (lng < 82.75) {
	       if (lat < 35.0) {
	        if (lng < 82.25) {
	         return 4;
	        } else {
	         if (lat < 34.25) {
	          if (lng < 82.5) {
	           return 4;
	          } else {
	           return 407;
	          }
	         } else {
	          if (lat < 34.75) {
	           return 407;
	          } else {
	           if (lng < 82.5) {
	            return 4;
	           } else {
	            return 407;
	           }
	          }
	         }
	        }
	       } else {
	        if (lat < 35.75) {
	         if (lng < 82.25) {
	          return 4;
	         } else {
	          if (lat < 35.25) {
	           if (lng < 82.5) {
	            return 4;
	           } else {
	            return 407;
	           }
	          } else {
	           return 407;
	          }
	         }
	        } else {
	         if (lng < 82.5) {
	          return 4;
	         } else {
	          if (lat < 36.25) {
	           return 4;
	          } else {
	           return 407;
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < 35.75) {
	        return 407;
	       } else {
	        if (lng < 83.0) {
	         if (lat < 36.0) {
	          return 4;
	         } else {
	          return 407;
	         }
	        } else {
	         return 407;
	        }
	       }
	      }
	     } else {
	      if (lng < 82.75) {
	       if (lat < 37.75) {
	        if (lng < 82.5) {
	         return 4;
	        } else {
	         if (lat < 37.0) {
	          return 4;
	         } else {
	          return 407;
	         }
	        }
	       } else {
	        if (lat < 38.25) {
	         if (lng < 82.5) {
	          return 4;
	         } else {
	          return 407;
	         }
	        } else {
	         return 4;
	        }
	       }
	      } else {
	       return 407;
	      }
	     }
	    }
	   } else {
	    if (lat < 42.0) {
	     if (lng < 81.5) {
	      if (lng < 79.25) {
	       if (lat < 41.75) {
	        return 4;
	       } else {
	        return 231;
	       }
	      } else {
	       return 4;
	      }
	     } else {
	      if (lng < 82.75) {
	       if (lat < 40.5) {
	        if (lng < 82.25) {
	         return 4;
	        } else {
	         if (lat < 39.5) {
	          return 4;
	         } else {
	          return 407;
	         }
	        }
	       } else {
	        if (lat < 41.25) {
	         if (lng < 82.0) {
	          return 4;
	         } else {
	          return 407;
	         }
	        } else {
	         if (lng < 82.5) {
	          return 4;
	         } else {
	          if (lat < 41.5) {
	           return 407;
	          } else {
	           return 4;
	          }
	         }
	        }
	       }
	      } else {
	       return 407;
	      }
	     }
	    } else {
	     return kdLookup161(lat,lng);
	    }
	   }
	  } else {
	   if (lat < 43.5) {
	    return 407;
	   } else {
	    if (lng < 85.0) {
	     if (lat < 44.0) {
	      if (lng < 84.75) {
	       return 4;
	      } else {
	       if (lat < 43.75) {
	        return 4;
	       } else {
	        return 407;
	       }
	      }
	     } else {
	      return 407;
	     }
	    } else {
	     return 407;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup163(double lat, double lng)
	{
	 if (lng < 47.75) {
	  if (lat < 47.75) {
	   if (lng < 46.25) {
	    return 184;
	   } else {
	    if (lat < 46.25) {
	     if (lng < 47.0) {
	      return 184;
	     } else {
	      if (lat < 45.5) {
	       return 184;
	      } else {
	       if (lng < 47.25) {
	        if (lat < 45.75) {
	         return 184;
	        } else {
	         if (lat < 46.0) {
	          return 99;
	         } else {
	          return 184;
	         }
	        }
	       } else {
	        if (lat < 45.75) {
	         return 184;
	        } else {
	         return 99;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 47.0) {
	      if (lat < 47.25) {
	       return 184;
	      } else {
	       if (lng < 46.5) {
	        if (lat < 47.5) {
	         return 184;
	        } else {
	         return 99;
	        }
	       } else {
	        if (lng < 46.75) {
	         return 184;
	        } else {
	         return 99;
	        }
	       }
	      }
	     } else {
	      if (lat < 47.5) {
	       if (lat < 46.75) {
	        return 99;
	       } else {
	        if (lng < 47.25) {
	         if (lat < 47.0) {
	          return 184;
	         } else {
	          return 99;
	         }
	        } else {
	         return 99;
	        }
	       }
	      } else {
	       return 99;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 46.25) {
	    if (lat < 48.25) {
	     if (lng < 45.75) {
	      return 184;
	     } else {
	      if (lng < 46.0) {
	       if (lat < 48.0) {
	        return 184;
	       } else {
	        return 99;
	       }
	      } else {
	       return 99;
	      }
	     }
	    } else {
	     return 99;
	    }
	   } else {
	    if (lat < 49.0) {
	     if (lng < 47.0) {
	      if (lat < 48.5) {
	       return 99;
	      } else {
	       if (lng < 46.75) {
	        return 99;
	       } else {
	        return 115;
	       }
	      }
	     } else {
	      if (lat < 48.25) {
	       if (lng < 47.25) {
	        return 99;
	       } else {
	        if (lng < 47.5) {
	         return 120;
	        } else {
	         if (lat < 48.0) {
	          return 99;
	         } else {
	          return 120;
	         }
	        }
	       }
	      } else {
	       if (lng < 47.25) {
	        if (lat < 48.5) {
	         return 99;
	        } else {
	         return 115;
	        }
	       } else {
	        return 115;
	       }
	      }
	     }
	    } else {
	     if (lng < 47.0) {
	      return 99;
	     } else {
	      if (lat < 50.0) {
	       return 115;
	      } else {
	       if (lng < 47.5) {
	        return 99;
	       } else {
	        return 115;
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 47.75) {
	   if (lng < 49.0) {
	    if (lat < 46.25) {
	     return 99;
	    } else {
	     if (lat < 47.25) {
	      return 99;
	     } else {
	      if (lng < 48.5) {
	       return 99;
	      } else {
	       if (lng < 48.75) {
	        if (lat < 47.5) {
	         return 99;
	        } else {
	         return 120;
	        }
	       } else {
	        return 120;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 46.25) {
	     if (lng < 49.75) {
	      if (lat < 45.5) {
	       return 0;
	      } else {
	       if (lng < 49.25) {
	        return 99;
	       } else {
	        return 120;
	       }
	      }
	     } else {
	      return 120;
	     }
	    } else {
	     if (lng < 49.25) {
	      if (lat < 46.5) {
	       return 99;
	      } else {
	       return 120;
	      }
	     } else {
	      return 120;
	     }
	    }
	   }
	  } else {
	   if (lng < 49.0) {
	    if (lat < 49.0) {
	     if (lng < 48.25) {
	      if (lat < 48.25) {
	       if (lng < 48.0) {
	        return 120;
	       } else {
	        if (lat < 48.0) {
	         return 99;
	        } else {
	         return 120;
	        }
	       }
	      } else {
	       return 115;
	      }
	     } else {
	      if (lat < 48.25) {
	       return 120;
	      } else {
	       return 115;
	      }
	     }
	    } else {
	     if (lat < 50.0) {
	      return 115;
	     } else {
	      if (lng < 48.25) {
	       if (lng < 48.0) {
	        return 115;
	       } else {
	        if (lat < 50.25) {
	         return 115;
	        } else {
	         return 99;
	        }
	       }
	      } else {
	       if (lng < 48.75) {
	        return 99;
	       } else {
	        if (lat < 50.25) {
	         return 99;
	        } else {
	         return 115;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 48.5) {
	     if (lng < 49.75) {
	      if (lng < 49.25) {
	       if (lat < 48.25) {
	        return 120;
	       } else {
	        return 115;
	       }
	      } else {
	       if (lat < 48.25) {
	        return 120;
	       } else {
	        return 115;
	       }
	      }
	     } else {
	      if (lng < 50.0) {
	       if (lat < 48.25) {
	        return 120;
	       } else {
	        return 115;
	       }
	      } else {
	       if (lat < 48.25) {
	        return 120;
	       } else {
	        if (lng < 50.25) {
	         return 115;
	        } else {
	         return 120;
	        }
	       }
	      }
	     }
	    } else {
	     return 115;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup164(double lat, double lng)
	{
	 if (lat < 53.25) {
	  if (lng < 47.75) {
	   if (lng < 46.25) {
	    if (lat < 52.5) {
	     return 99;
	    } else {
	     if (lng < 46.0) {
	      return 184;
	     } else {
	      if (lat < 52.75) {
	       return 99;
	      } else {
	       return 184;
	      }
	     }
	    }
	   } else {
	    if (lat < 52.75) {
	     return 99;
	    } else {
	     return 184;
	    }
	   }
	  } else {
	   if (lng < 49.0) {
	    if (lat < 51.75) {
	     if (lng < 48.75) {
	      return 99;
	     } else {
	      if (lat < 50.75) {
	       return 115;
	      } else {
	       return 99;
	      }
	     }
	    } else {
	     if (lat < 52.75) {
	      return 99;
	     } else {
	      if (lng < 48.25) {
	       if (lng < 48.0) {
	        return 184;
	       } else {
	        if (lat < 53.0) {
	         return 99;
	        } else {
	         return 184;
	        }
	       }
	      } else {
	       if (lng < 48.5) {
	        if (lat < 53.0) {
	         return 99;
	        } else {
	         return 184;
	        }
	       } else {
	        return 158;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 51.75) {
	     if (lng < 49.75) {
	      if (lat < 51.0) {
	       if (lng < 49.25) {
	        if (lat < 50.75) {
	         return 115;
	        } else {
	         return 99;
	        }
	       } else {
	        return 115;
	       }
	      } else {
	       if (lng < 49.5) {
	        return 99;
	       } else {
	        if (lat < 51.25) {
	         return 115;
	        } else {
	         return 99;
	        }
	       }
	      }
	     } else {
	      if (lat < 51.25) {
	       return 115;
	      } else {
	       if (lng < 50.0) {
	        return 99;
	       } else {
	        if (lng < 50.25) {
	         if (lat < 51.5) {
	          return 115;
	         } else {
	          return 99;
	         }
	        } else {
	         if (lat < 51.5) {
	          return 115;
	         } else {
	          return 99;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 49.75) {
	      if (lat < 52.5) {
	       return 99;
	      } else {
	       if (lng < 49.25) {
	        return 158;
	       } else {
	        if (lat < 52.75) {
	         if (lng < 49.5) {
	          return 99;
	         } else {
	          return 158;
	         }
	        } else {
	         return 158;
	        }
	       }
	      }
	     } else {
	      if (lat < 52.5) {
	       if (lng < 50.0) {
	        return 99;
	       } else {
	        if (lat < 52.25) {
	         return 99;
	        } else {
	         return 158;
	        }
	       }
	      } else {
	       return 158;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 48.25) {
	   return 184;
	  } else {
	   if (lat < 54.25) {
	    if (lng < 49.25) {
	     if (lng < 48.75) {
	      if (lat < 53.75) {
	       if (lng < 48.5) {
	        if (lat < 53.5) {
	         return 158;
	        } else {
	         return 184;
	        }
	       } else {
	        return 158;
	       }
	      } else {
	       if (lng < 48.5) {
	        return 184;
	       } else {
	        if (lat < 54.0) {
	         return 158;
	        } else {
	         return 184;
	        }
	       }
	      }
	     } else {
	      if (lat < 53.75) {
	       return 158;
	      } else {
	       if (lng < 49.0) {
	        return 184;
	       } else {
	        if (lat < 54.0) {
	         return 158;
	        } else {
	         return 184;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 49.75) {
	      if (lat < 54.0) {
	       return 158;
	      } else {
	       return 184;
	      }
	     } else {
	      if (lat < 54.0) {
	       return 158;
	      } else {
	       if (lng < 50.25) {
	        return 184;
	       } else {
	        return 158;
	       }
	      }
	     }
	    }
	   } else {
	    return 184;
	   }
	  }
	 }
	}

	private  int kdLookup165(double lat, double lng)
	{
	 if (lng < 53.25) {
	  if (lat < 53.25) {
	   if (lng < 51.75) {
	    if (lat < 51.75) {
	     if (lng < 50.75) {
	      if (lat < 51.5) {
	       return 115;
	      } else {
	       return 99;
	      }
	     } else {
	      return 115;
	     }
	    } else {
	     if (lat < 52.5) {
	      if (lng < 51.0) {
	       if (lat < 52.0) {
	        if (lng < 50.75) {
	         return 99;
	        } else {
	         return 115;
	        }
	       } else {
	        return 158;
	       }
	      } else {
	       if (lng < 51.25) {
	        if (lat < 52.0) {
	         return 179;
	        } else {
	         return 158;
	        }
	       } else {
	        if (lat < 52.25) {
	         return 179;
	        } else {
	         if (lng < 51.5) {
	          return 158;
	         } else {
	          return 179;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < 51.5) {
	       return 158;
	      } else {
	       if (lat < 52.75) {
	        return 179;
	       } else {
	        return 158;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 51.75) {
	     if (lng < 53.0) {
	      return 115;
	     } else {
	      if (lat < 51.5) {
	       return 115;
	      } else {
	       return 179;
	      }
	     }
	    } else {
	     if (lng < 52.25) {
	      if (lat < 52.75) {
	       return 179;
	      } else {
	       if (lng < 52.0) {
	        return 158;
	       } else {
	        if (lat < 53.0) {
	         return 179;
	        } else {
	         return 158;
	        }
	       }
	      }
	     } else {
	      return 179;
	     }
	    }
	   }
	  } else {
	   if (lat < 54.75) {
	    if (lng < 51.75) {
	     if (lat < 54.5) {
	      return 158;
	     } else {
	      if (lng < 51.0) {
	       return 184;
	      } else {
	       return 158;
	      }
	     }
	    } else {
	     if (lng < 52.5) {
	      if (lat < 54.0) {
	       if (lng < 52.25) {
	        return 158;
	       } else {
	        if (lat < 53.75) {
	         return 179;
	        } else {
	         return 158;
	        }
	       }
	      } else {
	       if (lng < 52.0) {
	        return 158;
	       } else {
	        if (lat < 54.5) {
	         return 158;
	        } else {
	         return 184;
	        }
	       }
	      }
	     } else {
	      if (lat < 54.5) {
	       return 179;
	      } else {
	       return 184;
	      }
	     }
	    }
	   } else {
	    if (lng < 51.75) {
	     if (lat < 56.0) {
	      return 184;
	     } else {
	      if (lng < 51.5) {
	       return 184;
	      } else {
	       return 158;
	      }
	     }
	    } else {
	     if (lng < 52.25) {
	      if (lat < 56.0) {
	       return 184;
	      } else {
	       return 158;
	      }
	     } else {
	      return 184;
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 53.25) {
	   if (lng < 54.75) {
	    if (lat < 51.75) {
	     if (lng < 54.0) {
	      if (lat < 51.25) {
	       return 115;
	      } else {
	       if (lng < 53.5) {
	        return 115;
	       } else {
	        if (lng < 53.75) {
	         if (lat < 51.5) {
	          return 115;
	         } else {
	          return 179;
	         }
	        } else {
	         return 179;
	        }
	       }
	      }
	     } else {
	      if (lat < 51.0) {
	       if (lng < 54.5) {
	        return 115;
	       } else {
	        if (lat < 50.75) {
	         return 115;
	        } else {
	         return 179;
	        }
	       }
	      } else {
	       if (lng < 54.25) {
	        if (lat < 51.25) {
	         return 115;
	        } else {
	         return 179;
	        }
	       } else {
	        return 179;
	       }
	      }
	     }
	    } else {
	     return 179;
	    }
	   } else {
	    if (lat < 51.0) {
	     if (lng < 55.5) {
	      if (lng < 55.25) {
	       return 241;
	      } else {
	       if (lat < 50.75) {
	        return 241;
	       } else {
	        return 179;
	       }
	      }
	     } else {
	      if (lng < 55.75) {
	       if (lat < 50.75) {
	        return 241;
	       } else {
	        return 179;
	       }
	      } else {
	       if (lng < 56.0) {
	        if (lat < 50.75) {
	         return 241;
	        } else {
	         return 179;
	        }
	       } else {
	        if (lat < 50.75) {
	         return 241;
	        } else {
	         return 179;
	        }
	       }
	      }
	     }
	    } else {
	     return 179;
	    }
	   }
	  } else {
	   if (lng < 54.25) {
	    if (lat < 54.75) {
	     if (lat < 54.25) {
	      return 179;
	     } else {
	      if (lng < 53.5) {
	       return 184;
	      } else {
	       return 179;
	      }
	     }
	    } else {
	     if (lat < 55.5) {
	      if (lng < 53.75) {
	       if (lat < 55.0) {
	        return 184;
	       } else {
	        if (lng < 53.5) {
	         return 184;
	        } else {
	         if (lat < 55.25) {
	          return 179;
	         } else {
	          return 184;
	         }
	        }
	       }
	      } else {
	       return 179;
	      }
	     } else {
	      if (lng < 53.75) {
	       if (lat < 56.0) {
	        return 184;
	       } else {
	        if (lng < 53.5) {
	         return 184;
	        } else {
	         return 158;
	        }
	       }
	      } else {
	       if (lat < 55.75) {
	        if (lng < 54.0) {
	         return 184;
	        } else {
	         return 179;
	        }
	       } else {
	        if (lng < 54.0) {
	         if (lat < 56.0) {
	          return 184;
	         } else {
	          return 158;
	         }
	        } else {
	         if (lat < 56.0) {
	          return 184;
	         } else {
	          return 179;
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return 179;
	   }
	  }
	 }
	}

	private  int kdLookup166(double lat, double lng)
	{
	 if (lng < 47.75) {
	  if (lat < 58.5) {
	   if (lng < 46.5) {
	    return 184;
	   } else {
	    if (lat < 57.25) {
	     if (lng < 47.0) {
	      if (lat < 57.0) {
	       return 184;
	      } else {
	       if (lng < 46.75) {
	        return 184;
	       } else {
	        return 99;
	       }
	      }
	     } else {
	      if (lat < 57.0) {
	       return 184;
	      } else {
	       return 99;
	      }
	     }
	    } else {
	     if (lng < 47.0) {
	      if (lat < 58.25) {
	       return 184;
	      } else {
	       return 99;
	      }
	     } else {
	      if (lat < 57.75) {
	       if (lng < 47.25) {
	        if (lat < 57.5) {
	         return 99;
	        } else {
	         return 184;
	        }
	       } else {
	        return 99;
	       }
	      } else {
	       if (lng < 47.25) {
	        if (lat < 58.25) {
	         return 184;
	        } else {
	         return 99;
	        }
	       } else {
	        if (lat < 58.0) {
	         return 184;
	        } else {
	         if (lng < 47.5) {
	          if (lat < 58.25) {
	           return 184;
	          } else {
	           return 99;
	          }
	         } else {
	          return 99;
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 46.5) {
	    return 184;
	   } else {
	    if (lat < 59.75) {
	     if (lng < 47.0) {
	      if (lat < 58.75) {
	       if (lng < 46.75) {
	        return 184;
	       } else {
	        return 99;
	       }
	      } else {
	       return 184;
	      }
	     } else {
	      if (lat < 59.0) {
	       if (lng < 47.25) {
	        if (lat < 58.75) {
	         return 99;
	        } else {
	         return 184;
	        }
	       } else {
	        return 99;
	       }
	      } else {
	       if (lng < 47.25) {
	        return 184;
	       } else {
	        if (lat < 59.25) {
	         return 184;
	        } else {
	         return 99;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 47.0) {
	      if (lat < 60.25) {
	       return 184;
	      } else {
	       if (lat < 60.5) {
	        return 99;
	       } else {
	        return 184;
	       }
	      }
	     } else {
	      if (lat < 60.25) {
	       if (lng < 47.25) {
	        if (lat < 60.0) {
	         return 184;
	        } else {
	         return 99;
	        }
	       } else {
	        return 99;
	       }
	      } else {
	       if (lng < 47.25) {
	        if (lat < 60.75) {
	         return 99;
	        } else {
	         return 184;
	        }
	       } else {
	        return 99;
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 58.5) {
	   if (lng < 49.0) {
	    if (lat < 57.25) {
	     return 184;
	    } else {
	     return 99;
	    }
	   } else {
	    if (lat < 57.25) {
	     if (lng < 49.75) {
	      return 184;
	     } else {
	      if (lat < 56.75) {
	       return 184;
	      } else {
	       if (lng < 50.0) {
	        if (lat < 57.0) {
	         return 184;
	        } else {
	         return 99;
	        }
	       } else {
	        if (lng < 50.25) {
	         if (lat < 57.0) {
	          return 184;
	         } else {
	          return 99;
	         }
	        } else {
	         return 99;
	        }
	       }
	      }
	     }
	    } else {
	     return 99;
	    }
	   }
	  } else {
	   if (lng < 49.0) {
	    if (lat < 59.75) {
	     return 99;
	    } else {
	     if (lng < 48.5) {
	      return 99;
	     } else {
	      if (lat < 60.0) {
	       if (lng < 48.75) {
	        return 99;
	       } else {
	        return 184;
	       }
	      } else {
	       if (lat < 60.5) {
	        if (lng < 48.75) {
	         if (lat < 60.25) {
	          return 184;
	         } else {
	          return 99;
	         }
	        } else {
	         return 184;
	        }
	       } else {
	        if (lng < 48.75) {
	         if (lat < 60.75) {
	          return 99;
	         } else {
	          return 184;
	         }
	        } else {
	         return 184;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 59.75) {
	     if (lng < 49.75) {
	      if (lat < 59.5) {
	       return 99;
	      } else {
	       if (lng < 49.25) {
	        return 99;
	       } else {
	        return 184;
	       }
	      }
	     } else {
	      if (lat < 59.25) {
	       return 99;
	      } else {
	       if (lng < 50.0) {
	        return 184;
	       } else {
	        return 99;
	       }
	      }
	     }
	    } else {
	     if (lng < 50.0) {
	      return 184;
	     } else {
	      if (lat < 60.0) {
	       return 99;
	      } else {
	       return 184;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup167(double lat, double lng)
	{
	 if (lat < 58.75) {
	  if (lng < 51.75) {
	   if (lat < 57.5) {
	    if (lng < 51.0) {
	     if (lat < 56.75) {
	      if (lng < 50.75) {
	       return 184;
	      } else {
	       if (lat < 56.5) {
	        return 184;
	       } else {
	        return 99;
	       }
	      }
	     } else {
	      return 99;
	     }
	    } else {
	     if (lat < 56.75) {
	      if (lng < 51.25) {
	       return 99;
	      } else {
	       if (lng < 51.5) {
	        if (lat < 56.5) {
	         return 99;
	        } else {
	         return 158;
	        }
	       } else {
	        return 158;
	       }
	      }
	     } else {
	      if (lng < 51.25) {
	       return 99;
	      } else {
	       if (lat < 57.0) {
	        if (lng < 51.5) {
	         return 99;
	        } else {
	         return 158;
	        }
	       } else {
	        if (lng < 51.5) {
	         if (lat < 57.25) {
	          return 99;
	         } else {
	          return 158;
	         }
	        } else {
	         return 158;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return 99;
	   }
	  } else {
	   if (lat < 57.5) {
	    if (lng < 52.75) {
	     return 158;
	    } else {
	     if (lat < 56.75) {
	      if (lng < 53.0) {
	       if (lat < 56.5) {
	        return 184;
	       } else {
	        return 158;
	       }
	      } else {
	       if (lat < 56.5) {
	        return 158;
	       } else {
	        return 184;
	       }
	      }
	     } else {
	      return 158;
	     }
	    }
	   } else {
	    if (lng < 52.5) {
	     if (lat < 58.0) {
	      if (lng < 52.0) {
	       if (lat < 57.75) {
	        return 158;
	       } else {
	        return 99;
	       }
	      } else {
	       return 158;
	      }
	     } else {
	      if (lng < 52.0) {
	       if (lat < 58.25) {
	        return 99;
	       } else {
	        if (lat < 58.5) {
	         return 158;
	        } else {
	         return 99;
	        }
	       }
	      } else {
	       if (lat < 58.5) {
	        return 158;
	       } else {
	        return 99;
	       }
	      }
	     }
	    } else {
	     if (lat < 58.5) {
	      return 158;
	     } else {
	      if (lng < 53.0) {
	       return 99;
	      } else {
	       return 158;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 51.75) {
	   if (lat < 60.0) {
	    return 99;
	   } else {
	    if (lng < 51.0) {
	     return 184;
	    } else {
	     if (lat < 60.25) {
	      if (lng < 51.5) {
	       return 99;
	      } else {
	       return 184;
	      }
	     } else {
	      return 184;
	     }
	    }
	   }
	  } else {
	   if (lat < 60.25) {
	    return 99;
	   } else {
	    if (lng < 52.5) {
	     if (lat < 60.75) {
	      if (lng < 52.0) {
	       return 184;
	      } else {
	       if (lng < 52.25) {
	        if (lat < 60.5) {
	         return 99;
	        } else {
	         return 184;
	        }
	       } else {
	        if (lat < 60.5) {
	         return 99;
	        } else {
	         return 179;
	        }
	       }
	      }
	     } else {
	      if (lng < 52.0) {
	       return 184;
	      } else {
	       if (lng < 52.25) {
	        if (lat < 61.0) {
	         return 179;
	        } else {
	         return 184;
	        }
	       } else {
	        if (lat < 61.0) {
	         return 179;
	        } else {
	         return 184;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 61.0) {
	      return 179;
	     } else {
	      if (lng < 53.0) {
	       return 184;
	      } else {
	       return 179;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup168(double lat, double lng)
	{
	 if (lng < 50.5) {
	  if (lat < 67.25) {
	   if (lat < 67.0) {
	    if (lat < 66.75) {
	     if (lat < 66.5) {
	      if (lat < 66.25) {
	       if (lat < 66.0) {
	        if (lat < 61.0) {
	         return kdLookup166(lat,lng);
	        } else {
	         if (lng < 47.75) {
	          if (lat < 61.25) {
	           if (lng < 47.25) {
	            return 184;
	           } else {
	            return 99;
	           }
	          } else {
	           return 184;
	          }
	         } else {
	          if (lat < 61.25) {
	           if (lng < 48.0) {
	            return 99;
	           } else {
	            return 184;
	           }
	          } else {
	           return 184;
	          }
	         }
	        }
	       } else {
	        return 184;
	       }
	      } else {
	       return 184;
	      }
	     } else {
	      return 184;
	     }
	    } else {
	     return 184;
	    }
	   } else {
	    return 184;
	   }
	  } else {
	   return 184;
	  }
	 } else {
	  if (lat < 61.25) {
	   if (lng < 53.25) {
	    return kdLookup167(lat,lng);
	   } else {
	    if (lat < 58.75) {
	     if (lng < 54.5) {
	      if (lat < 57.5) {
	       if (lng < 54.25) {
	        if (lat < 56.5) {
	         if (lng < 54.0) {
	          if (lng < 53.5) {
	           return 158;
	          } else {
	           if (lng < 53.75) {
	            return 184;
	           } else {
	            return 158;
	           }
	          }
	         } else {
	          return 158;
	         }
	        } else {
	         return 158;
	        }
	       } else {
	        if (lat < 56.75) {
	         if (lat < 56.5) {
	          return 158;
	         } else {
	          return 179;
	         }
	        } else {
	         if (lat < 57.25) {
	          return 179;
	         } else {
	          return 158;
	         }
	        }
	       }
	      } else {
	       if (lng < 53.75) {
	        if (lat < 58.5) {
	         return 158;
	        } else {
	         return 99;
	        }
	       } else {
	        if (lat < 58.0) {
	         if (lng < 54.25) {
	          return 158;
	         } else {
	          return 179;
	         }
	        } else {
	         if (lng < 54.0) {
	          if (lat < 58.5) {
	           return 158;
	          } else {
	           return 99;
	          }
	         } else {
	          if (lat < 58.25) {
	           if (lng < 54.25) {
	            return 158;
	           } else {
	            return 179;
	           }
	          } else {
	           return 179;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      return 179;
	     }
	    } else {
	     if (lng < 54.75) {
	      if (lat < 60.0) {
	       if (lng < 54.0) {
	        if (lat < 59.25) {
	         return 99;
	        } else {
	         if (lng < 53.5) {
	          return 99;
	         } else {
	          if (lat < 59.75) {
	           return 179;
	          } else {
	           if (lng < 53.75) {
	            return 99;
	           } else {
	            return 179;
	           }
	          }
	         }
	        }
	       } else {
	        return 179;
	       }
	      } else {
	       if (lng < 54.0) {
	        if (lat < 60.5) {
	         if (lng < 53.5) {
	          if (lat < 60.25) {
	           return 99;
	          } else {
	           return 179;
	          }
	         } else {
	          if (lng < 53.75) {
	           if (lat < 60.25) {
	            return 99;
	           } else {
	            return 179;
	           }
	          } else {
	           return 179;
	          }
	         }
	        } else {
	         if (lng < 53.5) {
	          return 179;
	         } else {
	          if (lat < 61.0) {
	           return 179;
	          } else {
	           return 184;
	          }
	         }
	        }
	       } else {
	        if (lat < 61.0) {
	         return 179;
	        } else {
	         return 184;
	        }
	       }
	      }
	     } else {
	      if (lat < 61.0) {
	       return 179;
	      } else {
	       if (lng < 55.25) {
	        return 184;
	       } else {
	        return 179;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   return 184;
	  }
	 }
	}

	private  int kdLookup169(double lat, double lng)
	{
	 if (lat < 53.25) {
	  if (lng < 59.0) {
	   if (lng < 57.5) {
	    if (lat < 51.25) {
	     if (lng < 56.5) {
	      if (lat < 51.0) {
	       return 241;
	      } else {
	       return 179;
	      }
	     } else {
	      if (lng < 57.25) {
	       if (lng < 56.75) {
	        return 241;
	       } else {
	        if (lat < 51.0) {
	         return 241;
	        } else {
	         if (lng < 57.0) {
	          return 179;
	         } else {
	          return 241;
	         }
	        }
	       }
	      } else {
	       return 241;
	      }
	     }
	    } else {
	     return 179;
	    }
	   } else {
	    if (lat < 51.25) {
	     if (lng < 58.25) {
	      if (lng < 57.75) {
	       if (lat < 51.0) {
	        return 241;
	       } else {
	        return 179;
	       }
	      } else {
	       if (lat < 51.0) {
	        return 241;
	       } else {
	        if (lng < 58.0) {
	         return 179;
	        } else {
	         return 241;
	        }
	       }
	      }
	     } else {
	      if (lng < 58.5) {
	       return 241;
	      } else {
	       if (lat < 51.0) {
	        return 241;
	       } else {
	        return 179;
	       }
	      }
	     }
	    } else {
	     return 179;
	    }
	   }
	  } else {
	   if (lng < 60.25) {
	    if (lat < 51.0) {
	     if (lng < 59.5) {
	      if (lng < 59.25) {
	       if (lat < 50.75) {
	        return 241;
	       } else {
	        return 179;
	       }
	      } else {
	       if (lat < 50.75) {
	        return 241;
	       } else {
	        return 179;
	       }
	      }
	     } else {
	      if (lng < 59.75) {
	       if (lat < 50.75) {
	        return 241;
	       } else {
	        return 179;
	       }
	      } else {
	       if (lng < 60.0) {
	        if (lat < 50.75) {
	         return 241;
	        } else {
	         return 179;
	        }
	       } else {
	        return 241;
	       }
	      }
	     }
	    } else {
	     return 179;
	    }
	   } else {
	    if (lat < 51.75) {
	     if (lng < 61.0) {
	      if (lat < 50.75) {
	       return 241;
	      } else {
	       return 179;
	      }
	     } else {
	      if (lat < 51.0) {
	       if (lng < 61.25) {
	        if (lat < 50.75) {
	         return 241;
	        } else {
	         return 179;
	        }
	       } else {
	        if (lng < 61.5) {
	         if (lat < 50.75) {
	          return 241;
	         } else {
	          return 179;
	         }
	        } else {
	         return 241;
	        }
	       }
	      } else {
	       if (lng < 61.25) {
	        if (lat < 51.5) {
	         return 179;
	        } else {
	         return 240;
	        }
	       } else {
	        if (lat < 51.5) {
	         return 179;
	        } else {
	         return 240;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 61.0) {
	      if (lat < 52.25) {
	       if (lng < 60.5) {
	        if (lat < 52.0) {
	         return 179;
	        } else {
	         return 240;
	        }
	       } else {
	        return 240;
	       }
	      } else {
	       if (lat < 52.75) {
	        return 179;
	       } else {
	        if (lng < 60.75) {
	         return 179;
	        } else {
	         if (lat < 53.0) {
	          return 240;
	         } else {
	          return 179;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 53.0) {
	       return 240;
	      } else {
	       if (lng < 61.25) {
	        return 179;
	       } else {
	        return 240;
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 61.25) {
	   return 179;
	  } else {
	   if (lat < 54.25) {
	    if (lat < 53.75) {
	     if (lng < 61.5) {
	      return 179;
	     } else {
	      if (lat < 53.5) {
	       return 240;
	      } else {
	       return 179;
	      }
	     }
	    } else {
	     if (lng < 61.5) {
	      if (lat < 54.0) {
	       return 240;
	      } else {
	       return 179;
	      }
	     } else {
	      return 240;
	     }
	    }
	   } else {
	    return 179;
	   }
	  }
	 }
	}

	private  int kdLookup170(double lat, double lng)
	{
	 if (lng < 64.5) {
	  if (lat < 47.75) {
	   if (lng < 63.0) {
	    if (lat < 47.25) {
	     return 240;
	    } else {
	     if (lng < 62.5) {
	      return 240;
	     } else {
	      if (lng < 62.75) {
	       if (lat < 47.5) {
	        return 240;
	       } else {
	        return 241;
	       }
	      } else {
	       return 241;
	      }
	     }
	    }
	   } else {
	    if (lat < 46.75) {
	     return 240;
	    } else {
	     if (lng < 63.75) {
	      if (lat < 47.25) {
	       if (lng < 63.25) {
	        return 240;
	       } else {
	        if (lng < 63.5) {
	         if (lat < 47.0) {
	          return 240;
	         } else {
	          return 348;
	         }
	        } else {
	         if (lat < 47.0) {
	          return 240;
	         } else {
	          return 348;
	         }
	        }
	       }
	      } else {
	       if (lng < 63.25) {
	        return 241;
	       } else {
	        if (lng < 63.5) {
	         if (lat < 47.5) {
	          return 348;
	         } else {
	          return 241;
	         }
	        } else {
	         if (lat < 47.5) {
	          return 348;
	         } else {
	          return 241;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 47.0) {
	       if (lng < 64.25) {
	        return 240;
	       } else {
	        return 348;
	       }
	      } else {
	       return 348;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 63.0) {
	    if (lat < 49.0) {
	     if (lng < 62.25) {
	      if (lat < 48.0) {
	       return 240;
	      } else {
	       return 241;
	      }
	     } else {
	      return 241;
	     }
	    } else {
	     if (lat < 49.75) {
	      if (lng < 62.5) {
	       return 241;
	      } else {
	       if (lat < 49.25) {
	        if (lng < 62.75) {
	         return 241;
	        } else {
	         return 240;
	        }
	       } else {
	        return 240;
	       }
	      }
	     } else {
	      if (lng < 62.75) {
	       return 241;
	      } else {
	       if (lat < 50.0) {
	        return 240;
	       } else {
	        if (lat < 50.25) {
	         return 241;
	        } else {
	         return 240;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 49.0) {
	     if (lng < 63.75) {
	      if (lat < 48.75) {
	       return 241;
	      } else {
	       if (lng < 63.25) {
	        return 241;
	       } else {
	        return 240;
	       }
	      }
	     } else {
	      if (lat < 48.25) {
	       if (lng < 64.25) {
	        return 241;
	       } else {
	        return 348;
	       }
	      } else {
	       if (lng < 64.0) {
	        if (lat < 48.5) {
	         return 241;
	        } else {
	         return 240;
	        }
	       } else {
	        if (lat < 48.5) {
	         if (lng < 64.25) {
	          return 240;
	         } else {
	          return 348;
	         }
	        } else {
	         return 240;
	        }
	       }
	      }
	     }
	    } else {
	     return 240;
	    }
	   }
	  }
	 } else {
	  if (lat < 47.75) {
	   if (lng < 66.0) {
	    if (lat < 46.25) {
	     return 240;
	    } else {
	     if (lng < 65.25) {
	      if (lat < 46.75) {
	       if (lng < 65.0) {
	        return 240;
	       } else {
	        if (lat < 46.5) {
	         return 240;
	        } else {
	         return 348;
	        }
	       }
	      } else {
	       return 348;
	      }
	     } else {
	      if (lat < 46.5) {
	       if (lng < 65.5) {
	        return 240;
	       } else {
	        return 348;
	       }
	      } else {
	       return 348;
	      }
	     }
	    }
	   } else {
	    if (lat < 46.25) {
	     if (lng < 67.25) {
	      return 240;
	     } else {
	      if (lat < 45.25) {
	       return 240;
	      } else {
	       if (lat < 45.75) {
	        if (lat < 45.5) {
	         return 348;
	        } else {
	         return 240;
	        }
	       } else {
	        if (lat < 46.0) {
	         return 240;
	        } else {
	         return 348;
	        }
	       }
	      }
	     }
	    } else {
	     return 348;
	    }
	   }
	  } else {
	   if (lng < 66.0) {
	    if (lat < 49.0) {
	     if (lng < 64.75) {
	      if (lat < 48.5) {
	       return 348;
	      } else {
	       return 240;
	      }
	     } else {
	      return 348;
	     }
	    } else {
	     return 240;
	    }
	   } else {
	    if (lat < 49.25) {
	     return 348;
	    } else {
	     if (lng < 66.75) {
	      return 240;
	     } else {
	      if (lat < 49.75) {
	       return 348;
	      } else {
	       if (lng < 67.25) {
	        return 240;
	       } else {
	        if (lat < 50.0) {
	         return 348;
	        } else {
	         return 240;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup171(double lat, double lng)
	{
	 if (lng < 61.75) {
	  if (lat < 50.5) {
	   if (lng < 59.0) {
	    if (lat < 46.75) {
	     if (lng < 57.5) {
	      if (lat < 45.75) {
	       if (lng < 56.75) {
	        if (lat < 45.25) {
	         return 168;
	        } else {
	         return 120;
	        }
	       } else {
	        if (lng < 57.0) {
	         if (lat < 45.25) {
	          return 168;
	         } else {
	          return 241;
	         }
	        } else {
	         if (lat < 45.25) {
	          return 168;
	         } else {
	          if (lng < 57.25) {
	           return 241;
	          } else {
	           if (lat < 45.5) {
	            return 168;
	           } else {
	            return 241;
	           }
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < 56.75) {
	        if (lat < 46.25) {
	         return 120;
	        } else {
	         if (lng < 56.5) {
	          return 120;
	         } else {
	          return 241;
	         }
	        }
	       } else {
	        return 241;
	       }
	      }
	     } else {
	      if (lat < 45.75) {
	       if (lng < 58.25) {
	        if (lng < 57.75) {
	         if (lat < 45.5) {
	          return 168;
	         } else {
	          return 241;
	         }
	        } else {
	         if (lat < 45.5) {
	          return 168;
	         } else {
	          return 241;
	         }
	        }
	       } else {
	        return 168;
	       }
	      } else {
	       return 241;
	      }
	     }
	    } else {
	     return 241;
	    }
	   } else {
	    if (lat < 47.75) {
	     if (lng < 60.25) {
	      if (lat < 46.25) {
	       if (lng < 59.5) {
	        if (lat < 45.5) {
	         return 168;
	        } else {
	         return 240;
	        }
	       } else {
	        if (lat < 45.5) {
	         return 168;
	        } else {
	         return 240;
	        }
	       }
	      } else {
	       if (lat < 47.0) {
	        if (lng < 59.5) {
	         return 241;
	        } else {
	         if (lng < 59.75) {
	          if (lat < 46.5) {
	           return 240;
	          } else {
	           return 241;
	          }
	         } else {
	          if (lat < 46.75) {
	           return 240;
	          } else {
	           if (lng < 60.0) {
	            return 241;
	           } else {
	            return 240;
	           }
	          }
	         }
	        }
	       } else {
	        return 241;
	       }
	      }
	     } else {
	      if (lat < 47.0) {
	       return 240;
	      } else {
	       if (lng < 61.0) {
	        if (lng < 60.5) {
	         return 241;
	        } else {
	         if (lat < 47.25) {
	          return 240;
	         } else {
	          return 241;
	         }
	        }
	       } else {
	        if (lng < 61.25) {
	         if (lat < 47.5) {
	          return 240;
	         } else {
	          return 241;
	         }
	        } else {
	         return 240;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 61.5) {
	      return 241;
	     } else {
	      if (lat < 48.0) {
	       return 240;
	      } else {
	       return 241;
	      }
	     }
	    }
	   }
	  } else {
	   return kdLookup169(lat,lng);
	  }
	 } else {
	  if (lat < 50.5) {
	   return kdLookup170(lat,lng);
	  } else {
	   if (lng < 64.5) {
	    if (lat < 53.25) {
	     if (lng < 62.5) {
	      if (lat < 51.75) {
	       if (lat < 51.0) {
	        if (lng < 62.25) {
	         return 241;
	        } else {
	         if (lat < 50.75) {
	          return 241;
	         } else {
	          return 240;
	         }
	        }
	       } else {
	        if (lng < 62.0) {
	         if (lat < 51.5) {
	          return 241;
	         } else {
	          return 240;
	         }
	        } else {
	         if (lat < 51.25) {
	          if (lng < 62.25) {
	           return 241;
	          } else {
	           return 240;
	          }
	         } else {
	          return 240;
	         }
	        }
	       }
	      } else {
	       if (lat < 53.0) {
	        return 240;
	       } else {
	        if (lng < 62.25) {
	         return 179;
	        } else {
	         return 240;
	        }
	       }
	      }
	     } else {
	      return 240;
	     }
	    } else {
	     if (lat < 54.5) {
	      if (lng < 63.0) {
	       if (lng < 62.25) {
	        if (lat < 54.0) {
	         return 240;
	        } else {
	         if (lng < 62.0) {
	          if (lat < 54.25) {
	           return 240;
	          } else {
	           return 179;
	          }
	         } else {
	          return 179;
	         }
	        }
	       } else {
	        if (lat < 54.0) {
	         return 240;
	        } else {
	         if (lng < 62.5) {
	          if (lat < 54.25) {
	           return 240;
	          } else {
	           return 179;
	          }
	         } else {
	          if (lng < 62.75) {
	           return 179;
	          } else {
	           if (lat < 54.25) {
	            return 240;
	           } else {
	            return 179;
	           }
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < 63.75) {
	        if (lat < 54.25) {
	         return 240;
	        } else {
	         return 179;
	        }
	       } else {
	        return 240;
	       }
	      }
	     } else {
	      return 179;
	     }
	    }
	   } else {
	    if (lat < 53.25) {
	     if (lng < 66.25) {
	      return 240;
	     } else {
	      if (lat < 52.25) {
	       return 240;
	      } else {
	       if (lng < 66.75) {
	        if (lat < 52.5) {
	         return 240;
	        } else {
	         return 348;
	        }
	       } else {
	        if (lat < 52.5) {
	         if (lng < 67.0) {
	          return 240;
	         } else {
	          return 348;
	         }
	        } else {
	         return 348;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 66.0) {
	      if (lat < 54.75) {
	       if (lng < 65.25) {
	        if (lat < 54.5) {
	         return 240;
	        } else {
	         return 179;
	        }
	       } else {
	        return 240;
	       }
	      } else {
	       return 179;
	      }
	     } else {
	      if (lat < 54.75) {
	       if (lng < 66.5) {
	        if (lat < 54.0) {
	         if (lat < 53.5) {
	          return 240;
	         } else {
	          if (lng < 66.25) {
	           return 240;
	          } else {
	           return 348;
	          }
	         }
	        } else {
	         if (lat < 54.25) {
	          if (lng < 66.25) {
	           return 240;
	          } else {
	           return 348;
	          }
	         } else {
	          if (lng < 66.25) {
	           return 240;
	          } else {
	           return 348;
	          }
	         }
	        }
	       } else {
	        return 348;
	       }
	      } else {
	       if (lng < 67.0) {
	        return 179;
	       } else {
	        if (lat < 55.0) {
	         return 348;
	        } else {
	         return 179;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup172(double lat, double lng)
	{
	 if (lng < 61.75) {
	  if (lat < 61.75) {
	   if (lng < 57.25) {
	    if (lat < 61.25) {
	     return 179;
	    } else {
	     if (lng < 56.75) {
	      if (lng < 56.5) {
	       return 184;
	      } else {
	       if (lat < 61.5) {
	        return 179;
	       } else {
	        return 184;
	       }
	      }
	     } else {
	      if (lng < 57.0) {
	       return 179;
	      } else {
	       if (lat < 61.5) {
	        return 179;
	       } else {
	        return 184;
	       }
	      }
	     }
	    }
	   } else {
	    return 179;
	   }
	  } else {
	   if (lat < 64.5) {
	    if (lng < 59.5) {
	     return 184;
	    } else {
	     if (lat < 63.75) {
	      if (lng < 59.75) {
	       if (lat < 63.5) {
	        if (lat < 62.5) {
	         return 179;
	        } else {
	         if (lat < 62.75) {
	          return 184;
	         } else {
	          return 179;
	         }
	        }
	       } else {
	        return 179;
	       }
	      } else {
	       return 179;
	      }
	     } else {
	      if (lng < 60.0) {
	       if (lat < 64.0) {
	        if (lng < 59.75) {
	         return 184;
	        } else {
	         return 179;
	        }
	       } else {
	        if (lng < 59.75) {
	         return 184;
	        } else {
	         if (lat < 64.25) {
	          return 184;
	         } else {
	          return 179;
	         }
	        }
	       }
	      } else {
	       return 179;
	      }
	     }
	    }
	   } else {
	    if (lng < 59.5) {
	     return 184;
	    } else {
	     if (lat < 65.5) {
	      if (lng < 60.5) {
	       if (lng < 60.0) {
	        if (lat < 65.0) {
	         if (lng < 59.75) {
	          if (lat < 64.75) {
	           return 179;
	          } else {
	           return 184;
	          }
	         } else {
	          return 179;
	         }
	        } else {
	         return 184;
	        }
	       } else {
	        if (lat < 65.0) {
	         return 179;
	        } else {
	         if (lng < 60.25) {
	          return 184;
	         } else {
	          if (lat < 65.25) {
	           return 179;
	          } else {
	           return 184;
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < 61.0) {
	        if (lat < 65.0) {
	         return 179;
	        } else {
	         return 184;
	        }
	       } else {
	        if (lat < 65.25) {
	         return 179;
	        } else {
	         if (lng < 61.5) {
	          return 184;
	         } else {
	          return 179;
	         }
	        }
	       }
	      }
	     } else {
	      return 184;
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 65.75) {
	   return 179;
	  } else {
	   if (lng < 64.5) {
	    if (lng < 63.0) {
	     if (lat < 66.0) {
	      if (lng < 62.25) {
	       return 184;
	      } else {
	       return 179;
	      }
	     } else {
	      return 184;
	     }
	    } else {
	     if (lat < 66.5) {
	      if (lng < 63.5) {
	       if (lat < 66.25) {
	        return 179;
	       } else {
	        return 184;
	       }
	      } else {
	       return 179;
	      }
	     } else {
	      if (lng < 63.75) {
	       return 184;
	      } else {
	       if (lat < 66.75) {
	        return 179;
	       } else {
	        return 184;
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 65.75) {
	     if (lat < 66.75) {
	      return 179;
	     } else {
	      if (lng < 65.0) {
	       if (lat < 67.0) {
	        if (lng < 64.75) {
	         return 184;
	        } else {
	         return 179;
	        }
	       } else {
	        return 184;
	       }
	      } else {
	       if (lng < 65.25) {
	        if (lat < 67.0) {
	         return 179;
	        } else {
	         return 184;
	        }
	       } else {
	        if (lat < 67.25) {
	         return 179;
	        } else {
	         return 184;
	        }
	       }
	      }
	     }
	    } else {
	     return 179;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup173(double lat, double lng)
	{
	 if (lat < 67.5) {
	  if (lng < 56.25) {
	   if (lat < 56.25) {
	    if (lng < 50.5) {
	     if (lat < 50.5) {
	      return kdLookup163(lat,lng);
	     } else {
	      return kdLookup164(lat,lng);
	     }
	    } else {
	     if (lat < 50.5) {
	      if (lng < 53.25) {
	       if (lat < 47.75) {
	        return 120;
	       } else {
	        if (lng < 51.75) {
	         if (lat < 48.75) {
	          if (lng < 51.0) {
	           if (lat < 48.5) {
	            return 120;
	           } else {
	            return 115;
	           }
	          } else {
	           return 120;
	          }
	         } else {
	          return 115;
	         }
	        } else {
	         if (lat < 49.0) {
	          if (lng < 52.5) {
	           if (lat < 48.75) {
	            return 120;
	           } else {
	            return 115;
	           }
	          } else {
	           if (lat < 48.75) {
	            return 120;
	           } else {
	            if (lng < 52.75) {
	             return 115;
	            } else {
	             return 120;
	            }
	           }
	          }
	         } else {
	          if (lng < 53.0) {
	           return 115;
	          } else {
	           if (lat < 49.25) {
	            return 120;
	           } else {
	            return 115;
	           }
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < 47.75) {
	        if (lng < 55.25) {
	         return 120;
	        } else {
	         if (lat < 46.25) {
	          if (lat < 45.25) {
	           if (lng < 56.0) {
	            return 120;
	           } else {
	            return 168;
	           }
	          } else {
	           return 120;
	          }
	         } else {
	          if (lat < 47.0) {
	           if (lng < 55.75) {
	            if (lat < 46.75) {
	             return 120;
	            } else {
	             if (lng < 55.5) {
	              return 120;
	             } else {
	              return 241;
	             }
	            }
	           } else {
	            if (lat < 46.75) {
	             return 120;
	            } else {
	             return 241;
	            }
	           }
	          } else {
	           return 241;
	          }
	         }
	        }
	       } else {
	        if (lng < 54.75) {
	         if (lat < 49.0) {
	          if (lng < 54.25) {
	           return 120;
	          } else {
	           if (lat < 48.75) {
	            return 120;
	           } else {
	            return 241;
	           }
	          }
	         } else {
	          if (lng < 54.0) {
	           if (lat < 49.75) {
	            if (lng < 53.5) {
	             if (lat < 49.25) {
	              return 120;
	             } else {
	              return 115;
	             }
	            } else {
	             if (lat < 49.25) {
	              return 120;
	             } else {
	              if (lng < 53.75) {
	               return 115;
	              } else {
	               return 241;
	              }
	             }
	            }
	           } else {
	            return 115;
	           }
	          } else {
	           if (lat < 49.75) {
	            return 241;
	           } else {
	            if (lng < 54.5) {
	             return 115;
	            } else {
	             if (lat < 50.0) {
	              return 241;
	             } else {
	              if (lat < 50.25) {
	               return 115;
	              } else {
	               return 241;
	              }
	             }
	            }
	           }
	          }
	         }
	        } else {
	         if (lat < 49.0) {
	          if (lng < 55.25) {
	           if (lat < 48.25) {
	            return 120;
	           } else {
	            if (lat < 48.5) {
	             if (lng < 55.0) {
	              return 120;
	             } else {
	              return 241;
	             }
	            } else {
	             if (lng < 55.0) {
	              return 120;
	             } else {
	              return 241;
	             }
	            }
	           }
	          } else {
	           return 241;
	          }
	         } else {
	          return 241;
	         }
	        }
	       }
	      }
	     } else {
	      return kdLookup165(lat,lng);
	     }
	    }
	   } else {
	    return kdLookup168(lat,lng);
	   }
	  } else {
	   if (lat < 56.25) {
	    return kdLookup171(lat,lng);
	   } else {
	    return kdLookup172(lat,lng);
	   }
	  }
	 } else {
	  if (lng < 56.25) {
	   return 184;
	  } else {
	   if (lat < 78.75) {
	    if (lng < 61.75) {
	     return 184;
	    } else {
	     if (lat < 73.0) {
	      if (lng < 64.5) {
	       return 184;
	      } else {
	       if (lat < 70.25) {
	        if (lng < 66.0) {
	         if (lat < 68.75) {
	          if (lng < 65.5) {
	           return 184;
	          } else {
	           if (lat < 68.0) {
	            return 184;
	           } else {
	            if (lat < 68.5) {
	             return 179;
	            } else {
	             if (lng < 65.75) {
	              return 184;
	             } else {
	              return 179;
	             }
	            }
	           }
	          }
	         } else {
	          if (lng < 65.25) {
	           if (lat < 69.5) {
	            if (lng < 64.75) {
	             return 184;
	            } else {
	             if (lat < 69.0) {
	              return 184;
	             } else {
	              if (lng < 65.0) {
	               if (lat < 69.25) {
	                return 179;
	               } else {
	                return 184;
	               }
	              } else {
	               return 179;
	              }
	             }
	            }
	           } else {
	            if (lng < 64.75) {
	             return 184;
	            } else {
	             if (lat < 69.75) {
	              if (lng < 65.0) {
	               return 184;
	              } else {
	               return 179;
	              }
	             } else {
	              return 0;
	             }
	            }
	           }
	          } else {
	           if (lat < 69.5) {
	            if (lng < 65.5) {
	             if (lat < 69.0) {
	              return 184;
	             } else {
	              return 179;
	             }
	            } else {
	             return 179;
	            }
	           } else {
	            return 0;
	           }
	          }
	         }
	        } else {
	         if (lat < 68.0) {
	          if (lng < 66.25) {
	           return 184;
	          } else {
	           return 179;
	          }
	         } else {
	          return 179;
	         }
	        }
	       } else {
	        return 179;
	       }
	      }
	     } else {
	      return 184;
	     }
	    }
	   } else {
	    return 184;
	   }
	  }
	 }
	}

	private  int kdLookup174(double lat, double lng)
	{
	 if (lng < 73.0) {
	  if (lat < 50.5) {
	   if (lng < 67.75) {
	    if (lat < 47.75) {
	     if (lat < 45.25) {
	      return 240;
	     } else {
	      return 348;
	     }
	    } else {
	     if (lat < 50.25) {
	      return 348;
	     } else {
	      return 240;
	     }
	    }
	   } else {
	    return 348;
	   }
	  } else {
	   if (lat < 53.25) {
	    if (lng < 68.25) {
	     if (lat < 51.75) {
	      if (lat < 51.0) {
	       if (lng < 68.0) {
	        return 240;
	       } else {
	        if (lat < 50.75) {
	         return 240;
	        } else {
	         return 348;
	        }
	       }
	      } else {
	       if (lng < 68.0) {
	        return 240;
	       } else {
	        return 348;
	       }
	      }
	     } else {
	      if (lat < 52.5) {
	       if (lng < 67.75) {
	        return 240;
	       } else {
	        if (lat < 52.0) {
	         if (lng < 68.0) {
	          return 240;
	         } else {
	          return 348;
	         }
	        } else {
	         if (lng < 68.0) {
	          if (lat < 52.25) {
	           return 240;
	          } else {
	           return 348;
	          }
	         } else {
	          return 348;
	         }
	        }
	       }
	      } else {
	       return 348;
	      }
	     }
	    } else {
	     return 348;
	    }
	   } else {
	    if (lng < 70.25) {
	     if (lat < 55.0) {
	      return 348;
	     } else {
	      if (lng < 68.75) {
	       if (lng < 68.25) {
	        return 179;
	       } else {
	        if (lat < 55.25) {
	         return 348;
	        } else {
	         return 179;
	        }
	       }
	      } else {
	       if (lng < 69.5) {
	        if (lat < 55.5) {
	         return 348;
	        } else {
	         return 179;
	        }
	       } else {
	        if (lat < 55.5) {
	         if (lng < 70.0) {
	          return 348;
	         } else {
	          if (lat < 55.25) {
	           return 348;
	          } else {
	           return 179;
	          }
	         }
	        } else {
	         return 179;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 54.75) {
	      if (lng < 71.5) {
	       if (lat < 54.25) {
	        return 348;
	       } else {
	        if (lng < 71.25) {
	         return 348;
	        } else {
	         return 380;
	        }
	       }
	      } else {
	       if (lng < 72.25) {
	        if (lat < 54.25) {
	         return 348;
	        } else {
	         return 380;
	        }
	       } else {
	        if (lat < 54.0) {
	         return 348;
	        } else {
	         if (lng < 72.5) {
	          if (lat < 54.5) {
	           return 348;
	          } else {
	           return 380;
	          }
	         } else {
	          if (lat < 54.25) {
	           if (lng < 72.75) {
	            return 380;
	           } else {
	            return 348;
	           }
	          } else {
	           return 380;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < 71.25) {
	       if (lat < 55.5) {
	        if (lng < 70.75) {
	         if (lat < 55.25) {
	          return 348;
	         } else {
	          if (lng < 70.5) {
	           return 179;
	          } else {
	           return 348;
	          }
	         }
	        } else {
	         if (lat < 55.0) {
	          return 348;
	         } else {
	          if (lng < 71.0) {
	           return 348;
	          } else {
	           return 380;
	          }
	         }
	        }
	       } else {
	        if (lng < 70.75) {
	         return 179;
	        } else {
	         if (lat < 56.0) {
	          return 380;
	         } else {
	          if (lng < 71.0) {
	           return 179;
	          } else {
	           return 380;
	          }
	         }
	        }
	       }
	      } else {
	       return 380;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 52.75) {
	   return 348;
	  } else {
	   if (lng < 75.75) {
	    if (lat < 54.25) {
	     if (lng < 74.25) {
	      if (lat < 53.5) {
	       return 348;
	      } else {
	       if (lng < 73.5) {
	        if (lat < 54.0) {
	         return 348;
	        } else {
	         if (lng < 73.25) {
	          return 348;
	         } else {
	          return 380;
	         }
	        }
	       } else {
	        if (lng < 73.75) {
	         return 380;
	        } else {
	         if (lat < 53.75) {
	          return 348;
	         } else {
	          return 380;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < 75.0) {
	       if (lat < 53.75) {
	        return 348;
	       } else {
	        return 380;
	       }
	      } else {
	       if (lat < 54.0) {
	        return 348;
	       } else {
	        if (lng < 75.5) {
	         return 380;
	        } else {
	         return 348;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 75.5) {
	      return 380;
	     } else {
	      if (lat < 55.25) {
	       if (lat < 55.0) {
	        return 380;
	       } else {
	        return 95;
	       }
	      } else {
	       if (lat < 56.0) {
	        return 95;
	       } else {
	        return 380;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 54.5) {
	     if (lng < 77.25) {
	      if (lat < 54.0) {
	       return 348;
	      } else {
	       if (lng < 76.5) {
	        if (lng < 76.0) {
	         if (lat < 54.25) {
	          return 348;
	         } else {
	          return 95;
	         }
	        } else {
	         if (lng < 76.25) {
	          if (lat < 54.25) {
	           return 348;
	          } else {
	           return 95;
	          }
	         } else {
	          return 348;
	         }
	        }
	       } else {
	        if (lng < 76.75) {
	         return 348;
	        } else {
	         if (lng < 77.0) {
	          if (lat < 54.25) {
	           return 95;
	          } else {
	           return 348;
	          }
	         } else {
	          return 95;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 53.5) {
	       if (lng < 78.0) {
	        return 348;
	       } else {
	        if (lng < 78.25) {
	         if (lat < 53.25) {
	          return 348;
	         } else {
	          return 380;
	         }
	        } else {
	         if (lat < 53.0) {
	          if (lng < 78.5) {
	           return 348;
	          } else {
	           return 380;
	          }
	         } else {
	          return 380;
	         }
	        }
	       }
	      } else {
	       if (lng < 78.0) {
	        if (lat < 53.75) {
	         if (lng < 77.75) {
	          return 348;
	         } else {
	          return 95;
	         }
	        } else {
	         return 95;
	        }
	       } else {
	        if (lat < 53.75) {
	         if (lng < 78.25) {
	          return 380;
	         } else {
	          if (lng < 78.5) {
	           return 95;
	          } else {
	           return 380;
	          }
	         }
	        } else {
	         return 95;
	        }
	       }
	      }
	     }
	    } else {
	     return 95;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup175(double lat, double lng)
	{
	 if (lng < 73.0) {
	  if (lat < 61.75) {
	   if (lng < 70.5) {
	    return 179;
	   } else {
	    if (lat < 58.75) {
	     if (lng < 71.75) {
	      if (lat < 57.5) {
	       if (lng < 71.0) {
	        if (lat < 57.25) {
	         return 179;
	        } else {
	         if (lng < 70.75) {
	          return 179;
	         } else {
	          return 380;
	         }
	        }
	       } else {
	        if (lat < 56.75) {
	         return 380;
	        } else {
	         if (lng < 71.25) {
	          return 179;
	         } else {
	          if (lat < 57.25) {
	           return 179;
	          } else {
	           return 380;
	          }
	         }
	        }
	       }
	      } else {
	       if (lng < 71.0) {
	        if (lat < 58.0) {
	         if (lng < 70.75) {
	          if (lat < 57.75) {
	           return 179;
	          } else {
	           return 380;
	          }
	         } else {
	          return 380;
	         }
	        } else {
	         if (lat < 58.25) {
	          return 380;
	         } else {
	          if (lng < 70.75) {
	           return 179;
	          } else {
	           if (lat < 58.5) {
	            return 380;
	           } else {
	            return 179;
	           }
	          }
	         }
	        }
	       } else {
	        if (lat < 58.25) {
	         return 380;
	        } else {
	         if (lng < 71.25) {
	          return 380;
	         } else {
	          return 179;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 58.25) {
	       return 380;
	      } else {
	       return 179;
	      }
	     }
	    } else {
	     return 179;
	    }
	   }
	  } else {
	   return 179;
	  }
	 } else {
	  if (lat < 61.0) {
	   if (lng < 75.75) {
	    if (lat < 58.5) {
	     if (lng < 74.25) {
	      if (lat < 58.25) {
	       return 380;
	      } else {
	       return 179;
	      }
	     } else {
	      if (lat < 58.0) {
	       return 380;
	      } else {
	       if (lng < 75.0) {
	        if (lng < 74.5) {
	         if (lat < 58.25) {
	          return 380;
	         } else {
	          return 179;
	         }
	        } else {
	         return 380;
	        }
	       } else {
	        if (lng < 75.25) {
	         return 380;
	        } else {
	         if (lng < 75.5) {
	          if (lat < 58.25) {
	           return 380;
	          } else {
	           return 95;
	          }
	         } else {
	          return 95;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 75.0) {
	      return 179;
	     } else {
	      if (lat < 59.0) {
	       if (lng < 75.25) {
	        if (lat < 58.75) {
	         return 380;
	        } else {
	         return 179;
	        }
	       } else {
	        if (lng < 75.5) {
	         if (lat < 58.75) {
	          return 380;
	         } else {
	          return 179;
	         }
	        } else {
	         return 95;
	        }
	       }
	      } else {
	       return 179;
	      }
	     }
	    }
	   } else {
	    if (lat < 58.5) {
	     if (lng < 76.5) {
	      if (lat < 57.25) {
	       if (lat < 57.0) {
	        if (lng < 76.0) {
	         return 380;
	        } else {
	         if (lat < 56.5) {
	          return 380;
	         } else {
	          if (lng < 76.25) {
	           if (lat < 56.75) {
	            return 95;
	           } else {
	            return 380;
	           }
	          } else {
	           if (lat < 56.75) {
	            return 95;
	           } else {
	            return 380;
	           }
	          }
	         }
	        }
	       } else {
	        if (lng < 76.25) {
	         return 380;
	        } else {
	         return 95;
	        }
	       }
	      } else {
	       if (lat < 57.75) {
	        if (lng < 76.0) {
	         return 380;
	        } else {
	         if (lng < 76.25) {
	          if (lat < 57.5) {
	           return 380;
	          } else {
	           return 95;
	          }
	         } else {
	          return 95;
	         }
	        }
	       } else {
	        return 95;
	       }
	      }
	     } else {
	      return 95;
	     }
	    } else {
	     if (lng < 77.25) {
	      if (lat < 59.75) {
	       if (lng < 76.25) {
	        if (lat < 59.5) {
	         return 95;
	        } else {
	         return 179;
	        }
	       } else {
	        return 95;
	       }
	      } else {
	       if (lng < 76.75) {
	        return 179;
	       } else {
	        if (lat < 60.25) {
	         if (lng < 77.0) {
	          if (lat < 60.0) {
	           return 95;
	          } else {
	           return 179;
	          }
	         } else {
	          return 95;
	         }
	        } else {
	         if (lat < 60.5) {
	          if (lng < 77.0) {
	           return 179;
	          } else {
	           return 95;
	          }
	         } else {
	          if (lng < 77.0) {
	           return 179;
	          } else {
	           if (lat < 60.75) {
	            return 95;
	           } else {
	            return 179;
	           }
	          }
	         }
	        }
	       }
	      }
	     } else {
	      return 95;
	     }
	    }
	   }
	  } else {
	   return 179;
	  }
	 }
	}

	private  int kdLookup176(double lat, double lng)
	{
	 if (lat < 53.25) {
	  if (lng < 81.5) {
	   if (lng < 80.0) {
	    if (lat < 51.75) {
	     if (lng < 79.75) {
	      return 348;
	     } else {
	      if (lat < 51.25) {
	       return 348;
	      } else {
	       return 380;
	      }
	     }
	    } else {
	     if (lat < 52.5) {
	      if (lng < 79.25) {
	       if (lat < 52.25) {
	        return 348;
	       } else {
	        if (lng < 79.0) {
	         return 348;
	        } else {
	         return 380;
	        }
	       }
	      } else {
	       if (lng < 79.5) {
	        if (lat < 52.0) {
	         return 348;
	        } else {
	         return 380;
	        }
	       } else {
	        return 380;
	       }
	      }
	     } else {
	      return 380;
	     }
	    }
	   } else {
	    if (lat < 51.5) {
	     if (lng < 80.75) {
	      if (lat < 51.0) {
	       return 348;
	      } else {
	       if (lng < 80.5) {
	        return 380;
	       } else {
	        if (lat < 51.25) {
	         return 348;
	        } else {
	         return 380;
	        }
	       }
	      }
	     } else {
	      if (lat < 51.0) {
	       return 348;
	      } else {
	       if (lng < 81.0) {
	        return 348;
	       } else {
	        if (lng < 81.25) {
	         if (lat < 51.25) {
	          return 348;
	         } else {
	          return 380;
	         }
	        } else {
	         return 380;
	        }
	       }
	      }
	     }
	    } else {
	     return 380;
	    }
	   }
	  } else {
	   if (lng < 82.75) {
	    if (lat < 51.0) {
	     if (lng < 82.5) {
	      return 348;
	     } else {
	      if (lat < 50.75) {
	       return 348;
	      } else {
	       return 380;
	      }
	     }
	    } else {
	     return 380;
	    }
	   } else {
	    if (lat < 51.25) {
	     if (lng < 83.5) {
	      if (lng < 83.0) {
	       if (lat < 51.0) {
	        return 348;
	       } else {
	        return 380;
	       }
	      } else {
	       if (lat < 51.0) {
	        return 348;
	       } else {
	        if (lng < 83.25) {
	         return 380;
	        } else {
	         return 348;
	        }
	       }
	      }
	     } else {
	      if (lng < 83.75) {
	       if (lat < 51.0) {
	        return 348;
	       } else {
	        return 380;
	       }
	      } else {
	       if (lat < 50.75) {
	        return 348;
	       } else {
	        if (lng < 84.0) {
	         if (lat < 51.0) {
	          return 348;
	         } else {
	          return 380;
	         }
	        } else {
	         return 380;
	        }
	       }
	      }
	     }
	    } else {
	     return 380;
	    }
	   }
	  }
	 } else {
	  if (lng < 81.5) {
	   if (lat < 54.5) {
	    if (lng < 80.0) {
	     if (lng < 79.25) {
	      if (lat < 53.75) {
	       return 380;
	      } else {
	       return 95;
	      }
	     } else {
	      if (lat < 53.75) {
	       return 380;
	      } else {
	       if (lng < 79.75) {
	        return 95;
	       } else {
	        if (lat < 54.0) {
	         return 380;
	        } else {
	         return 95;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 80.75) {
	      if (lat < 54.0) {
	       return 380;
	      } else {
	       if (lng < 80.25) {
	        return 95;
	       } else {
	        if (lng < 80.5) {
	         if (lat < 54.25) {
	          return 380;
	         } else {
	          return 95;
	         }
	        } else {
	         if (lat < 54.25) {
	          return 380;
	         } else {
	          return 95;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 54.25) {
	       return 380;
	      } else {
	       if (lng < 81.25) {
	        return 380;
	       } else {
	        return 95;
	       }
	      }
	     }
	    }
	   } else {
	    return 95;
	   }
	  } else {
	   if (lat < 54.25) {
	    if (lng < 82.75) {
	     if (lng < 82.0) {
	      if (lat < 54.0) {
	       return 380;
	      } else {
	       if (lng < 81.75) {
	        return 380;
	       } else {
	        return 95;
	       }
	      }
	     } else {
	      if (lat < 53.75) {
	       if (lng < 82.5) {
	        return 380;
	       } else {
	        if (lat < 53.5) {
	         return 380;
	        } else {
	         return 95;
	        }
	       }
	      } else {
	       return 95;
	      }
	     }
	    } else {
	     if (lng < 83.25) {
	      if (lat < 53.75) {
	       return 380;
	      } else {
	       if (lng < 83.0) {
	        return 95;
	       } else {
	        if (lat < 54.0) {
	         return 380;
	        } else {
	         return 95;
	        }
	       }
	      }
	     } else {
	      if (lng < 84.0) {
	       if (lat < 54.0) {
	        return 380;
	       } else {
	        if (lng < 83.5) {
	         return 380;
	        } else {
	         if (lng < 83.75) {
	          return 95;
	         } else {
	          return 380;
	         }
	        }
	       }
	      } else {
	       return 380;
	      }
	     }
	    }
	   } else {
	    return 95;
	   }
	  }
	 }
	}

	private  int kdLookup177(double lat, double lng)
	{
	 if (lng < 87.0) {
	  if (lat < 47.75) {
	   if (lng < 85.5) {
	    if (lat < 47.0) {
	     return 407;
	    } else {
	     if (lng < 85.25) {
	      return 348;
	     } else {
	      if (lat < 47.25) {
	       return 407;
	      } else {
	       return 348;
	      }
	     }
	    }
	   } else {
	    if (lat < 47.25) {
	     return 407;
	    } else {
	     if (lng < 85.75) {
	      return 348;
	     } else {
	      return 407;
	     }
	    }
	   }
	  } else {
	   if (lng < 85.5) {
	    if (lat < 49.75) {
	     return 348;
	    } else {
	     if (lng < 84.75) {
	      if (lat < 50.25) {
	       return 348;
	      } else {
	       if (lng < 84.5) {
	        return 348;
	       } else {
	        return 380;
	       }
	      }
	     } else {
	      if (lng < 85.0) {
	       if (lat < 50.25) {
	        return 348;
	       } else {
	        return 380;
	       }
	      } else {
	       if (lat < 50.0) {
	        if (lng < 85.25) {
	         return 348;
	        } else {
	         return 380;
	        }
	       } else {
	        return 380;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 49.0) {
	     if (lng < 86.25) {
	      if (lat < 48.25) {
	       if (lng < 85.75) {
	        return 348;
	       } else {
	        return 407;
	       }
	      } else {
	       if (lng < 85.75) {
	        return 348;
	       } else {
	        if (lat < 48.5) {
	         return 407;
	        } else {
	         return 348;
	        }
	       }
	      }
	     } else {
	      if (lat < 48.5) {
	       return 407;
	      } else {
	       if (lng < 86.5) {
	        return 348;
	       } else {
	        if (lng < 86.75) {
	         if (lat < 48.75) {
	          return 407;
	         } else {
	          return 348;
	         }
	        } else {
	         if (lat < 48.75) {
	          return 407;
	         } else {
	          return 348;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 86.25) {
	      if (lat < 49.75) {
	       if (lng < 86.0) {
	        return 348;
	       } else {
	        if (lat < 49.5) {
	         return 348;
	        } else {
	         return 380;
	        }
	       }
	      } else {
	       return 380;
	      }
	     } else {
	      if (lat < 49.75) {
	       if (lng < 86.75) {
	        return 348;
	       } else {
	        if (lat < 49.25) {
	         return 407;
	        } else {
	         return 348;
	        }
	       }
	      } else {
	       if (lng < 86.75) {
	        return 380;
	       } else {
	        if (lat < 50.0) {
	         return 348;
	        } else {
	         return 380;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 48.0) {
	   return 407;
	  } else {
	   if (lng < 88.5) {
	    if (lat < 49.25) {
	     if (lng < 88.0) {
	      return 407;
	     } else {
	      if (lat < 48.75) {
	       return 407;
	      } else {
	       if (lng < 88.25) {
	        if (lat < 49.0) {
	         return 407;
	        } else {
	         return 121;
	        }
	       } else {
	        return 121;
	       }
	      }
	     }
	    } else {
	     if (lng < 87.75) {
	      if (lat < 49.5) {
	       if (lng < 87.25) {
	        return 348;
	       } else {
	        return 380;
	       }
	      } else {
	       return 380;
	      }
	     } else {
	      if (lat < 49.5) {
	       if (lng < 88.25) {
	        return 380;
	       } else {
	        return 121;
	       }
	      } else {
	       return 380;
	      }
	     }
	    }
	   } else {
	    if (lat < 49.25) {
	     if (lng < 89.25) {
	      if (lat < 48.5) {
	       if (lng < 88.75) {
	        return 407;
	       } else {
	        if (lng < 89.0) {
	         if (lat < 48.25) {
	          return 407;
	         } else {
	          return 121;
	         }
	        } else {
	         if (lat < 48.25) {
	          return 407;
	         } else {
	          return 121;
	         }
	        }
	       }
	      } else {
	       return 121;
	      }
	     } else {
	      if (lat < 48.25) {
	       if (lng < 89.5) {
	        return 121;
	       } else {
	        if (lng < 89.75) {
	         return 407;
	        } else {
	         return 121;
	        }
	       }
	      } else {
	       return 121;
	      }
	     }
	    } else {
	     if (lng < 89.25) {
	      if (lat < 49.5) {
	       return 121;
	      } else {
	       return 380;
	      }
	     } else {
	      if (lat < 49.75) {
	       return 121;
	      } else {
	       if (lng < 89.5) {
	        return 380;
	       } else {
	        if (lat < 50.0) {
	         if (lng < 89.75) {
	          return 380;
	         } else {
	          return 121;
	         }
	        } else {
	         if (lng < 89.75) {
	          if (lat < 50.25) {
	           return 380;
	          } else {
	           return 290;
	          }
	         } else {
	          return 290;
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup178(double lat, double lng)
	{
	 if (lat < 53.25) {
	  if (lng < 88.5) {
	   if (lat < 51.75) {
	    if (lng < 88.0) {
	     return 380;
	    } else {
	     if (lat < 51.5) {
	      return 380;
	     } else {
	      return 290;
	     }
	    }
	   } else {
	    if (lng < 87.75) {
	     if (lat < 52.5) {
	      return 380;
	     } else {
	      if (lng < 87.25) {
	       return 380;
	      } else {
	       if (lat < 52.75) {
	        if (lng < 87.5) {
	         return 380;
	        } else {
	         return 87;
	        }
	       } else {
	        return 87;
	       }
	      }
	     }
	    } else {
	     if (lat < 52.5) {
	      if (lng < 88.25) {
	       return 380;
	      } else {
	       if (lat < 52.25) {
	        return 290;
	       } else {
	        return 380;
	       }
	      }
	     } else {
	      if (lng < 88.0) {
	       return 87;
	      } else {
	       if (lat < 52.75) {
	        if (lng < 88.25) {
	         return 380;
	        } else {
	         return 87;
	        }
	       } else {
	        return 87;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 51.75) {
	    if (lng < 89.25) {
	     if (lat < 51.25) {
	      return 380;
	     } else {
	      if (lng < 88.75) {
	       if (lat < 51.5) {
	        return 380;
	       } else {
	        return 290;
	       }
	      } else {
	       if (lng < 89.0) {
	        return 380;
	       } else {
	        return 290;
	       }
	      }
	     }
	    } else {
	     if (lat < 51.0) {
	      if (lng < 89.75) {
	       return 380;
	      } else {
	       if (lat < 50.75) {
	        return 380;
	       } else {
	        return 290;
	       }
	      }
	     } else {
	      return 290;
	     }
	    }
	   } else {
	    if (lng < 89.25) {
	     if (lat < 52.5) {
	      if (lng < 88.75) {
	       if (lat < 52.25) {
	        return 290;
	       } else {
	        return 87;
	       }
	      } else {
	       return 290;
	      }
	     } else {
	      if (lng < 89.0) {
	       return 87;
	      } else {
	       if (lat < 52.75) {
	        return 290;
	       } else {
	        if (lat < 53.0) {
	         return 87;
	        } else {
	         return 290;
	        }
	       }
	      }
	     }
	    } else {
	     return 290;
	    }
	   }
	  }
	 } else {
	  if (lng < 88.5) {
	   if (lat < 56.0) {
	    if (lat < 55.75) {
	     if (lat < 55.5) {
	      if (lat < 55.25) {
	       if (lat < 55.0) {
	        if (lat < 54.75) {
	         if (lng < 87.25) {
	          if (lat < 54.5) {
	           if (lat < 54.25) {
	            if (lat < 54.0) {
	             if (lat < 53.5) {
	              return 87;
	             } else {
	              if (lat < 53.75) {
	               return 380;
	              } else {
	               return 87;
	              }
	             }
	            } else {
	             return 87;
	            }
	           } else {
	            return 87;
	           }
	          } else {
	           return 87;
	          }
	         } else {
	          return 87;
	         }
	        } else {
	         return 87;
	        }
	       } else {
	        return 87;
	       }
	      } else {
	       return 87;
	      }
	     } else {
	      return 87;
	     }
	    } else {
	     return 87;
	    }
	   } else {
	    return 87;
	   }
	  } else {
	   if (lat < 54.75) {
	    if (lng < 89.25) {
	     if (lat < 54.5) {
	      if (lat < 54.25) {
	       if (lat < 54.0) {
	        if (lng < 89.0) {
	         return 87;
	        } else {
	         if (lat < 53.5) {
	          return 87;
	         } else {
	          if (lat < 53.75) {
	           return 290;
	          } else {
	           return 87;
	          }
	         }
	        }
	       } else {
	        return 87;
	       }
	      } else {
	       return 87;
	      }
	     } else {
	      if (lng < 88.75) {
	       return 87;
	      } else {
	       return 290;
	      }
	     }
	    } else {
	     return 290;
	    }
	   } else {
	    if (lng < 89.25) {
	     if (lat < 55.5) {
	      if (lng < 88.75) {
	       if (lat < 55.25) {
	        return 87;
	       } else {
	        return 290;
	       }
	      } else {
	       return 290;
	      }
	     } else {
	      if (lng < 88.75) {
	       return 87;
	      } else {
	       if (lat < 55.75) {
	        return 290;
	       } else {
	        return 87;
	       }
	      }
	     }
	    } else {
	     if (lat < 56.0) {
	      return 290;
	     } else {
	      if (lng < 89.5) {
	       return 87;
	      } else {
	       return 290;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup179(double lat, double lng)
	{
	 if (lng < 87.0) {
	  if (lat < 59.0) {
	   if (lng < 85.75) {
	    return 95;
	   } else {
	    if (lat < 56.75) {
	     if (lng < 86.25) {
	      if (lng < 86.0) {
	       if (lat < 56.5) {
	        return 87;
	       } else {
	        return 95;
	       }
	      } else {
	       if (lat < 56.5) {
	        return 87;
	       } else {
	        return 95;
	       }
	      }
	     } else {
	      return 87;
	     }
	    } else {
	     return 95;
	    }
	   }
	  } else {
	   if (lng < 85.5) {
	    if (lat < 60.25) {
	     if (lng < 84.75) {
	      return 95;
	     } else {
	      if (lat < 60.0) {
	       return 95;
	      } else {
	       return 290;
	      }
	     }
	    } else {
	     if (lat < 61.0) {
	      if (lng < 84.75) {
	       if (lat < 60.75) {
	        return 95;
	       } else {
	        if (lng < 84.5) {
	         return 95;
	        } else {
	         return 290;
	        }
	       }
	      } else {
	       return 290;
	      }
	     } else {
	      if (lng < 84.75) {
	       return 179;
	      } else {
	       if (lng < 85.0) {
	        if (lat < 61.25) {
	         return 290;
	        } else {
	         return 179;
	        }
	       } else {
	        if (lat < 61.25) {
	         return 290;
	        } else {
	         return 179;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 60.25) {
	     if (lng < 86.25) {
	      if (lat < 60.0) {
	       return 95;
	      } else {
	       return 290;
	      }
	     } else {
	      if (lat < 60.0) {
	       return 95;
	      } else {
	       return 290;
	      }
	     }
	    } else {
	     if (lng < 86.0) {
	      if (lat < 61.25) {
	       return 290;
	      } else {
	       if (lng < 85.75) {
	        return 179;
	       } else {
	        if (lat < 61.5) {
	         return 290;
	        } else {
	         return 179;
	        }
	       }
	      }
	     } else {
	      return 290;
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 59.0) {
	   if (lng < 88.5) {
	    if (lat < 57.5) {
	     if (lng < 87.75) {
	      if (lat < 56.75) {
	       return 87;
	      } else {
	       return 95;
	      }
	     } else {
	      if (lat < 56.75) {
	       return 87;
	      } else {
	       if (lng < 88.25) {
	        return 95;
	       } else {
	        if (lat < 57.0) {
	         return 87;
	        } else {
	         return 95;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 88.0) {
	      return 95;
	     } else {
	      if (lat < 58.25) {
	       return 95;
	      } else {
	       if (lat < 58.5) {
	        if (lng < 88.25) {
	         return 95;
	        } else {
	         return 290;
	        }
	       } else {
	        if (lng < 88.25) {
	         if (lat < 58.75) {
	          return 290;
	         } else {
	          return 95;
	         }
	        } else {
	         return 290;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 57.5) {
	     if (lng < 89.25) {
	      if (lat < 56.75) {
	       if (lng < 88.75) {
	        return 87;
	       } else {
	        if (lng < 89.0) {
	         if (lat < 56.5) {
	          return 87;
	         } else {
	          return 290;
	         }
	        } else {
	         if (lat < 56.5) {
	          return 87;
	         } else {
	          return 290;
	         }
	        }
	       }
	      } else {
	       if (lng < 88.75) {
	        if (lat < 57.0) {
	         return 87;
	        } else {
	         return 95;
	        }
	       } else {
	        if (lat < 57.25) {
	         return 290;
	        } else {
	         if (lng < 89.0) {
	          return 95;
	         } else {
	          return 290;
	         }
	        }
	       }
	      }
	     } else {
	      return 290;
	     }
	    } else {
	     if (lng < 89.25) {
	      if (lat < 58.25) {
	       if (lng < 89.0) {
	        return 95;
	       } else {
	        if (lat < 58.0) {
	         return 95;
	        } else {
	         return 290;
	        }
	       }
	      } else {
	       return 290;
	      }
	     } else {
	      if (lat < 58.75) {
	       if (lat < 58.5) {
	        if (lat < 58.25) {
	         if (lng < 89.5) {
	          if (lat < 57.75) {
	           return 290;
	          } else {
	           if (lat < 58.0) {
	            return 95;
	           } else {
	            return 290;
	           }
	          }
	         } else {
	          return 290;
	         }
	        } else {
	         return 290;
	        }
	       } else {
	        return 290;
	       }
	      } else {
	       return 290;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 88.5) {
	    if (lat < 60.0) {
	     if (lng < 87.75) {
	      if (lat < 59.75) {
	       return 95;
	      } else {
	       if (lng < 87.25) {
	        return 95;
	       } else {
	        return 290;
	       }
	      }
	     } else {
	      if (lat < 59.5) {
	       return 95;
	      } else {
	       return 290;
	      }
	     }
	    } else {
	     return 290;
	    }
	   } else {
	    if (lat < 59.5) {
	     if (lng < 89.0) {
	      if (lng < 88.75) {
	       return 95;
	      } else {
	       if (lat < 59.25) {
	        return 95;
	       } else {
	        return 290;
	       }
	      }
	     } else {
	      return 290;
	     }
	    } else {
	     return 290;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup180(double lat, double lng)
	{
	 if (lng < 84.25) {
	  if (lat < 61.75) {
	   if (lng < 81.5) {
	    if (lat < 60.75) {
	     return 95;
	    } else {
	     if (lng < 79.5) {
	      if (lat < 61.0) {
	       return 95;
	      } else {
	       return 179;
	      }
	     } else {
	      if (lng < 80.5) {
	       return 179;
	      } else {
	       if (lng < 81.0) {
	        if (lat < 61.0) {
	         return 95;
	        } else {
	         return 179;
	        }
	       } else {
	        if (lat < 61.0) {
	         if (lng < 81.25) {
	          return 95;
	         } else {
	          return 179;
	         }
	        } else {
	         return 179;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 60.75) {
	     return 95;
	    } else {
	     if (lng < 82.75) {
	      if (lng < 82.5) {
	       return 179;
	      } else {
	       if (lat < 61.0) {
	        return 95;
	       } else {
	        return 179;
	       }
	      }
	     } else {
	      if (lng < 83.5) {
	       if (lat < 61.25) {
	        if (lng < 83.0) {
	         if (lat < 61.0) {
	          return 95;
	         } else {
	          return 179;
	         }
	        } else {
	         if (lng < 83.25) {
	          if (lat < 61.0) {
	           return 95;
	          } else {
	           return 179;
	          }
	         } else {
	          return 95;
	         }
	        }
	       } else {
	        return 179;
	       }
	      } else {
	       if (lat < 61.25) {
	        if (lng < 83.75) {
	         return 95;
	        } else {
	         if (lng < 84.0) {
	          if (lat < 61.0) {
	           return 95;
	          } else {
	           return 179;
	          }
	         } else {
	          if (lat < 61.0) {
	           return 95;
	          } else {
	           return 179;
	          }
	         }
	        }
	       } else {
	        return 179;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 66.0) {
	    return 179;
	   } else {
	    if (lng < 82.25) {
	     return 179;
	    } else {
	     if (lng < 83.25) {
	      if (lat < 67.0) {
	       return 179;
	      } else {
	       if (lng < 82.75) {
	        if (lng < 82.5) {
	         if (lat < 67.25) {
	          return 179;
	         } else {
	          return 290;
	         }
	        } else {
	         if (lat < 67.25) {
	          return 179;
	         } else {
	          return 290;
	         }
	        }
	       } else {
	        return 290;
	       }
	      }
	     } else {
	      if (lat < 66.5) {
	       if (lng < 83.5) {
	        return 179;
	       } else {
	        return 290;
	       }
	      } else {
	       return 290;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 61.75) {
	   return kdLookup179(lat,lng);
	  } else {
	   if (lng < 86.25) {
	    if (lat < 64.5) {
	     if (lat < 63.0) {
	      if (lng < 85.25) {
	       if (lat < 62.25) {
	        if (lng < 84.75) {
	         return 179;
	        } else {
	         if (lng < 85.0) {
	          if (lat < 62.0) {
	           return 179;
	          } else {
	           return 290;
	          }
	         } else {
	          if (lat < 62.0) {
	           return 179;
	          } else {
	           return 290;
	          }
	         }
	        }
	       } else {
	        if (lng < 84.75) {
	         return 179;
	        } else {
	         if (lat < 62.5) {
	          return 290;
	         } else {
	          if (lng < 85.0) {
	           return 179;
	          } else {
	           if (lat < 62.75) {
	            return 290;
	           } else {
	            return 179;
	           }
	          }
	         }
	        }
	       }
	      } else {
	       return 290;
	      }
	     } else {
	      if (lng < 85.5) {
	       return 179;
	      } else {
	       if (lat < 63.75) {
	        if (lng < 85.75) {
	         if (lat < 63.5) {
	          return 179;
	         } else {
	          return 290;
	         }
	        } else {
	         return 290;
	        }
	       } else {
	        if (lng < 85.75) {
	         if (lat < 64.0) {
	          return 290;
	         } else {
	          return 179;
	         }
	        } else {
	         if (lat < 64.25) {
	          return 290;
	         } else {
	          return 179;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 65.75) {
	      if (lng < 85.25) {
	       if (lat < 65.0) {
	        return 179;
	       } else {
	        if (lng < 84.75) {
	         if (lat < 65.25) {
	          if (lng < 84.5) {
	           return 179;
	          } else {
	           return 290;
	          }
	         } else {
	          return 179;
	         }
	        } else {
	         return 290;
	        }
	       }
	      } else {
	       if (lat < 65.0) {
	        if (lng < 86.0) {
	         return 179;
	        } else {
	         return 290;
	        }
	       } else {
	        return 290;
	       }
	      }
	     } else {
	      return 290;
	     }
	    }
	   } else {
	    return 290;
	   }
	  }
	 }
	}

	private  int kdLookup181(double lat, double lng)
	{
	 if (lng < 81.5) {
	  if (lat < 70.25) {
	   if (lng < 80.0) {
	    if (lat < 69.5) {
	     return 179;
	    } else {
	     if (lng < 79.25) {
	      return 179;
	     } else {
	      if (lng < 79.5) {
	       if (lat < 69.75) {
	        return 179;
	       } else {
	        return 290;
	       }
	      } else {
	       if (lat < 69.75) {
	        if (lng < 79.75) {
	         return 179;
	        } else {
	         return 290;
	        }
	       } else {
	        return 290;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 69.25) {
	     return 179;
	    } else {
	     if (lng < 80.75) {
	      if (lat < 69.5) {
	       return 179;
	      } else {
	       return 290;
	      }
	     } else {
	      if (lat < 69.5) {
	       if (lng < 81.0) {
	        return 179;
	       } else {
	        if (lng < 81.25) {
	         return 290;
	        } else {
	         return 179;
	        }
	       }
	      } else {
	       return 290;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 80.0) {
	    if (lat < 71.5) {
	     if (lng < 79.75) {
	      return 179;
	     } else {
	      if (lat < 70.75) {
	       if (lat < 70.5) {
	        return 290;
	       } else {
	        return 179;
	       }
	      } else {
	       if (lat < 71.25) {
	        return 179;
	       } else {
	        return 290;
	       }
	      }
	     }
	    } else {
	     if (lat < 72.25) {
	      if (lng < 79.5) {
	       return 179;
	      } else {
	       if (lat < 71.75) {
	        return 290;
	       } else {
	        return 179;
	       }
	      }
	     } else {
	      return 290;
	     }
	    }
	   } else {
	    if (lat < 71.75) {
	     if (lng < 80.75) {
	      if (lat < 71.0) {
	       if (lng < 80.25) {
	        if (lat < 70.5) {
	         return 290;
	        } else {
	         return 179;
	        }
	       } else {
	        if (lat < 70.5) {
	         return 290;
	        } else {
	         return 179;
	        }
	       }
	      } else {
	       if (lng < 80.25) {
	        if (lat < 71.25) {
	         return 179;
	        } else {
	         return 290;
	        }
	       } else {
	        if (lat < 71.25) {
	         return 179;
	        } else {
	         return 290;
	        }
	       }
	      }
	     } else {
	      return 290;
	     }
	    } else {
	     if (lng < 80.75) {
	      if (lat < 72.25) {
	       if (lng < 80.25) {
	        if (lat < 72.0) {
	         return 179;
	        } else {
	         return 290;
	        }
	       } else {
	        return 290;
	       }
	      } else {
	       return 290;
	      }
	     } else {
	      return 290;
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 69.5) {
	   if (lng < 82.75) {
	    if (lat < 68.5) {
	     if (lng < 82.0) {
	      return 179;
	     } else {
	      if (lat < 68.0) {
	       if (lng < 82.25) {
	        if (lat < 67.75) {
	         return 179;
	        } else {
	         return 290;
	        }
	       } else {
	        if (lng < 82.5) {
	         if (lat < 67.75) {
	          return 179;
	         } else {
	          return 290;
	         }
	        } else {
	         return 290;
	        }
	       }
	      } else {
	       if (lng < 82.5) {
	        return 179;
	       } else {
	        if (lat < 68.25) {
	         return 290;
	        } else {
	         return 179;
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 82.0) {
	      return 179;
	     } else {
	      if (lat < 69.0) {
	       return 179;
	      } else {
	       if (lng < 82.25) {
	        if (lat < 69.25) {
	         return 179;
	        } else {
	         return 290;
	        }
	       } else {
	        if (lng < 82.5) {
	         if (lat < 69.25) {
	          return 179;
	         } else {
	          return 290;
	         }
	        } else {
	         return 290;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 68.75) {
	     return 290;
	    } else {
	     if (lng < 83.0) {
	      if (lat < 69.0) {
	       return 179;
	      } else {
	       return 290;
	      }
	     } else {
	      return 290;
	     }
	    }
	   }
	  } else {
	   return 290;
	  }
	 }
	}

	private  int kdLookup182(double lat, double lng)
	{
	 if (lat < 67.5) {
	  if (lng < 78.75) {
	   if (lat < 56.25) {
	    return kdLookup174(lat,lng);
	   } else {
	    return kdLookup175(lat,lng);
	   }
	  } else {
	   if (lat < 56.25) {
	    if (lng < 84.25) {
	     if (lat < 50.5) {
	      if (lng < 81.5) {
	       if (lat < 45.25) {
	        if (lng < 80.25) {
	         return 348;
	        } else {
	         return 4;
	        }
	       } else {
	        return 348;
	       }
	      } else {
	       if (lat < 47.25) {
	        if (lng < 82.75) {
	         if (lat < 46.0) {
	          if (lng < 82.0) {
	           if (lat < 45.5) {
	            return 4;
	           } else {
	            return 348;
	           }
	          } else {
	           if (lat < 45.5) {
	            if (lng < 82.25) {
	             if (lat < 45.25) {
	              return 4;
	             } else {
	              return 348;
	             }
	            } else {
	             if (lng < 82.5) {
	              if (lat < 45.25) {
	               return 4;
	              } else {
	               return 348;
	              }
	             } else {
	              if (lat < 45.25) {
	               return 4;
	              } else {
	               return 348;
	              }
	             }
	            }
	           } else {
	            if (lng < 82.5) {
	             return 348;
	            } else {
	             return 407;
	            }
	           }
	          }
	         } else {
	          return 348;
	         }
	        } else {
	         if (lat < 46.75) {
	          return 407;
	         } else {
	          if (lng < 83.5) {
	           if (lng < 83.0) {
	            return 348;
	           } else {
	            return 407;
	           }
	          } else {
	           if (lng < 84.0) {
	            return 407;
	           } else {
	            if (lat < 47.0) {
	             return 407;
	            } else {
	             return 348;
	            }
	           }
	          }
	         }
	        }
	       } else {
	        return 348;
	       }
	      }
	     } else {
	      return kdLookup176(lat,lng);
	     }
	    } else {
	     if (lat < 50.5) {
	      return kdLookup177(lat,lng);
	     } else {
	      if (lng < 87.0) {
	       if (lat < 53.25) {
	        return 380;
	       } else {
	        if (lat < 54.75) {
	         if (lng < 85.5) {
	          if (lat < 54.25) {
	           return 380;
	          } else {
	           if (lng < 84.75) {
	            return 95;
	           } else {
	            if (lng < 85.0) {
	             if (lat < 54.5) {
	              return 380;
	             } else {
	              return 95;
	             }
	            } else {
	             if (lng < 85.25) {
	              if (lat < 54.5) {
	               return 380;
	              } else {
	               return 95;
	              }
	             } else {
	              if (lat < 54.5) {
	               return 380;
	              } else {
	               return 87;
	              }
	             }
	            }
	           }
	          }
	         } else {
	          if (lng < 86.25) {
	           if (lat < 54.25) {
	            return 380;
	           } else {
	            return 87;
	           }
	          } else {
	           if (lat < 54.0) {
	            if (lng < 86.5) {
	             return 380;
	            } else {
	             if (lat < 53.5) {
	              if (lng < 86.75) {
	               return 380;
	              } else {
	               return 87;
	              }
	             } else {
	              if (lng < 86.75) {
	               if (lat < 53.75) {
	                return 380;
	               } else {
	                return 87;
	               }
	              } else {
	               return 87;
	              }
	             }
	            }
	           } else {
	            return 87;
	           }
	          }
	         }
	        } else {
	         if (lng < 85.0) {
	          if (lat < 55.5) {
	           return 95;
	          } else {
	           if (lng < 84.75) {
	            return 95;
	           } else {
	            return 87;
	           }
	          }
	         } else {
	          return 87;
	         }
	        }
	       }
	      } else {
	       return kdLookup178(lat,lng);
	      }
	     }
	    }
	   } else {
	    return kdLookup180(lat,lng);
	   }
	  }
	 } else {
	  if (lng < 78.75) {
	   if (lat < 78.75) {
	    if (lng < 73.0) {
	     if (lat < 73.0) {
	      return 179;
	     } else {
	      if (lat < 75.75) {
	       return 179;
	      } else {
	       return 184;
	      }
	     }
	    } else {
	     return 179;
	    }
	   } else {
	    return 290;
	   }
	  } else {
	   if (lat < 78.75) {
	    if (lng < 84.25) {
	     if (lat < 73.0) {
	      return kdLookup181(lat,lng);
	     } else {
	      return 290;
	     }
	    } else {
	     return 290;
	    }
	   } else {
	    return 0;
	   }
	  }
	 }
	}

	private  int kdLookup183(double lat, double lng)
	{
	 if (lat < -11.25) {
	  if (lng < 129.25) {
	   if (lat < -17.0) {
	    if (lng < 129.0) {
	     return 35;
	    } else {
	     if (lat < -20.0) {
	      return 35;
	     } else {
	      return 10;
	     }
	    }
	   } else {
	    if (lat < -14.25) {
	     if (lng < 126.5) {
	      return 35;
	     } else {
	      if (lng < 128.25) {
	       return 35;
	      } else {
	       if (lat < -15.75) {
	        if (lat < -16.75) {
	         if (lng < 129.0) {
	          return 35;
	         } else {
	          return 10;
	         }
	        } else {
	         return 35;
	        }
	       } else {
	        if (lat < -15.0) {
	         if (lng < 129.0) {
	          return 35;
	         } else {
	          if (lat < -15.25) {
	           return 35;
	          } else {
	           return 10;
	          }
	         }
	        } else {
	         if (lng < 128.75) {
	          return 35;
	         } else {
	          if (lat < -14.75) {
	           if (lng < 129.0) {
	            return 35;
	           } else {
	            return 10;
	           }
	          } else {
	           if (lng < 129.0) {
	            return 35;
	           } else {
	            return 10;
	           }
	          }
	         }
	        }
	       }
	      }
	     }
	    } else {
	     return 35;
	    }
	   }
	  } else {
	   return 10;
	  }
	 } else {
	  if (lng < 129.25) {
	   if (lat < -5.75) {
	    if (lng < 126.5) {
	     if (lat < -8.5) {
	      if (lng < 125.0) {
	       if (lat < -10.0) {
	        return 384;
	       } else {
	        if (lat < -9.25) {
	         return 384;
	        } else {
	         if (lng < 124.25) {
	          return 0;
	         } else {
	          if (lng < 124.5) {
	           return 346;
	          } else {
	           return 384;
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < -10.0) {
	        return 0;
	       } else {
	        if (lng < 125.25) {
	         if (lat < -9.25) {
	          return 384;
	         } else {
	          return 346;
	         }
	        } else {
	         return 346;
	        }
	       }
	      }
	     } else {
	      if (lng < 125.0) {
	       return 384;
	      } else {
	       if (lat < -7.25) {
	        if (lng < 125.75) {
	         if (lat < -8.0) {
	          if (lng < 125.25) {
	           return 384;
	          } else {
	           if (lng < 125.5) {
	            if (lat < -8.25) {
	             return 346;
	            } else {
	             return 384;
	            }
	           } else {
	            return 346;
	           }
	          }
	         } else {
	          if (lng < 125.25) {
	           return 384;
	          } else {
	           return 132;
	          }
	         }
	        } else {
	         if (lat < -8.0) {
	          if (lng < 126.0) {
	           if (lat < -8.25) {
	            return 346;
	           } else {
	            return 132;
	           }
	          } else {
	           return 346;
	          }
	         } else {
	          return 132;
	         }
	        }
	       } else {
	        return 0;
	       }
	      }
	     }
	    } else {
	     if (lat < -8.5) {
	      return 346;
	     } else {
	      if (lng < 127.75) {
	       if (lat < -7.25) {
	        if (lng < 127.0) {
	         if (lat < -8.0) {
	          return 346;
	         } else {
	          return 132;
	         }
	        } else {
	         return 346;
	        }
	       } else {
	        return 0;
	       }
	      } else {
	       return 132;
	      }
	     }
	    }
	   } else {
	    if (lat < -3.0) {
	     if (lng < 126.0) {
	      return 384;
	     } else {
	      return 132;
	     }
	    } else {
	     return 132;
	    }
	   }
	  } else {
	   if (lat < -5.75) {
	    if (lng < 132.0) {
	     if (lat < -8.5) {
	      return 10;
	     } else {
	      return 132;
	     }
	    } else {
	     if (lat < -8.5) {
	      return 10;
	     } else {
	      return 132;
	     }
	    }
	   } else {
	    return 132;
	   }
	  }
	 }
	}

	private  int kdLookup184(double lat, double lng)
	{
	 if (lat < -22.5) {
	  if (lng < 123.75) {
	   return 35;
	  } else {
	   if (lat < -33.75) {
	    return 35;
	   } else {
	    if (lng < 129.25) {
	     if (lat < -28.25) {
	      if (lng < 126.5) {
	       if (lat < -31.25) {
	        if (lng < 125.5) {
	         return 35;
	        } else {
	         return 79;
	        }
	       } else {
	        return 35;
	       }
	      } else {
	       if (lat < -31.0) {
	        if (lng < 127.75) {
	         if (lat < -32.5) {
	          return 0;
	         } else {
	          if (lat < -31.75) {
	           return 79;
	          } else {
	           if (lng < 127.0) {
	            if (lat < -31.25) {
	             return 79;
	            } else {
	             return 35;
	            }
	           } else {
	            if (lng < 127.25) {
	             if (lat < -31.25) {
	              return 79;
	             } else {
	              return 35;
	             }
	            } else {
	             if (lat < -31.25) {
	              return 79;
	             } else {
	              return 35;
	             }
	            }
	           }
	          }
	         }
	        } else {
	         if (lat < -32.5) {
	          return 0;
	         } else {
	          if (lng < 128.5) {
	           if (lat < -31.75) {
	            return 79;
	           } else {
	            if (lng < 128.0) {
	             if (lat < -31.25) {
	              return 79;
	             } else {
	              return 35;
	             }
	            } else {
	             if (lat < -31.25) {
	              return 79;
	             } else {
	              return 35;
	             }
	            }
	           }
	          } else {
	           if (lat < -31.75) {
	            return 79;
	           } else {
	            if (lng < 128.75) {
	             if (lat < -31.25) {
	              return 79;
	             } else {
	              return 35;
	             }
	            } else {
	             if (lat < -31.5) {
	              return 79;
	             } else {
	              if (lng < 129.0) {
	               if (lat < -31.25) {
	                return 79;
	               } else {
	                return 35;
	               }
	              } else {
	               return 67;
	              }
	             }
	            }
	           }
	          }
	         }
	        }
	       } else {
	        if (lng < 129.0) {
	         return 35;
	        } else {
	         if (lat < -29.75) {
	          if (lat < -30.25) {
	           return 67;
	          } else {
	           return 35;
	          }
	         } else {
	          if (lat < -28.75) {
	           return 35;
	          } else {
	           return 67;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < -25.5) {
	       if (lng < 129.0) {
	        return 35;
	       } else {
	        if (lat < -25.75) {
	         return 67;
	        } else {
	         return 10;
	        }
	       }
	      } else {
	       if (lng < 129.0) {
	        return 35;
	       } else {
	        if (lat < -24.25) {
	         return 10;
	        } else {
	         return 35;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < -28.25) {
	      return 67;
	     } else {
	      if (lng < 132.0) {
	       if (lat < -26.0) {
	        return 67;
	       } else {
	        return 10;
	       }
	      } else {
	       if (lat < -26.0) {
	        return 67;
	       } else {
	        return 10;
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 123.75) {
	   if (lat < -11.25) {
	    return 35;
	   } else {
	    if (lng < 118.0) {
	     if (lat < -5.75) {
	      if (lng < 115.25) {
	       if (lat < -8.5) {
	        if (lng < 113.75) {
	         return 0;
	        } else {
	         if (lat < -10.0) {
	          return 0;
	         } else {
	          if (lng < 115.0) {
	           return 26;
	          } else {
	           return 384;
	          }
	         }
	        }
	       } else {
	        if (lng < 113.75) {
	         return 26;
	        } else {
	         if (lat < -7.25) {
	          if (lng < 114.5) {
	           return 26;
	          } else {
	           if (lat < -7.75) {
	            return 384;
	           } else {
	            return 26;
	           }
	          }
	         } else {
	          return 26;
	         }
	        }
	       }
	      } else {
	       return 384;
	      }
	     } else {
	      if (lat < -3.0) {
	       if (lng < 115.25) {
	        if (lng < 114.5) {
	         return 321;
	        } else {
	         return 384;
	        }
	       } else {
	        return 384;
	       }
	      } else {
	       if (lng < 115.25) {
	        if (lat < -2.25) {
	         if (lng < 114.75) {
	          return 321;
	         } else {
	          if (lat < -2.75) {
	           return 384;
	          } else {
	           if (lng < 115.0) {
	            return 321;
	           } else {
	            return 384;
	           }
	          }
	         }
	        } else {
	         return 321;
	        }
	       } else {
	        if (lat < -1.5) {
	         if (lng < 116.5) {
	          if (lat < -2.0) {
	           return 384;
	          } else {
	           if (lng < 115.5) {
	            return 321;
	           } else {
	            return 384;
	           }
	          }
	         } else {
	          return 384;
	         }
	        } else {
	         if (lng < 116.5) {
	          if (lat < -0.75) {
	           if (lng < 115.75) {
	            if (lat < -1.25) {
	             if (lng < 115.5) {
	              return 321;
	             } else {
	              return 384;
	             }
	            } else {
	             return 321;
	            }
	           } else {
	            if (lng < 116.0) {
	             if (lat < -1.25) {
	              return 384;
	             } else {
	              if (lat < -1.0) {
	               return 321;
	              } else {
	               return 384;
	              }
	             }
	            } else {
	             return 384;
	            }
	           }
	          } else {
	           if (lng < 115.5) {
	            if (lat < -0.25) {
	             return 321;
	            } else {
	             return 384;
	            }
	           } else {
	            return 384;
	           }
	          }
	         } else {
	          return 384;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     return 384;
	    }
	   }
	  } else {
	   return kdLookup183(lat,lng);
	  }
	 }
	}

	private  int kdLookup185(double lat, double lng)
	{
	 if (lat < -33.75) {
	  if (lng < 140.5) {
	   return 67;
	  } else {
	   if (lat < -39.5) {
	    if (lng < 143.25) {
	     return 0;
	    } else {
	     if (lat < -42.25) {
	      return 113;
	     } else {
	      if (lng < 144.75) {
	       if (lat < -41.0) {
	        return 113;
	       } else {
	        if (lng < 144.0) {
	         return 43;
	        } else {
	         if (lat < -40.25) {
	          return 113;
	         } else {
	          return 43;
	         }
	        }
	       }
	      } else {
	       return 113;
	      }
	     }
	    }
	   } else {
	    if (lng < 143.25) {
	     if (lat < -36.75) {
	      if (lng < 141.0) {
	       return 67;
	      } else {
	       return 127;
	      }
	     } else {
	      if (lat < -35.25) {
	       if (lng < 141.0) {
	        return 67;
	       } else {
	        return 127;
	       }
	      } else {
	       if (lng < 141.75) {
	        if (lat < -34.5) {
	         if (lng < 141.0) {
	          return 67;
	         } else {
	          return 127;
	         }
	        } else {
	         if (lng < 141.0) {
	          return 67;
	         } else {
	          if (lng < 141.25) {
	           if (lat < -34.0) {
	            return 127;
	           } else {
	            return 208;
	           }
	          } else {
	           if (lat < -34.0) {
	            return 127;
	           } else {
	            return 208;
	           }
	          }
	         }
	        }
	       } else {
	        if (lng < 142.5) {
	         if (lat < -34.25) {
	          return 127;
	         } else {
	          if (lng < 142.0) {
	           if (lat < -34.0) {
	            return 127;
	           } else {
	            return 208;
	           }
	          } else {
	           if (lng < 142.25) {
	            if (lat < -34.0) {
	             return 127;
	            } else {
	             return 208;
	            }
	           } else {
	            return 208;
	           }
	          }
	         }
	        } else {
	         if (lat < -34.5) {
	          return 127;
	         } else {
	          return 208;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < -36.75) {
	      if (lng < 146.0) {
	       if (lng < 145.75) {
	        if (lat < -38.25) {
	         if (lng < 144.75) {
	          if (lng < 144.0) {
	           return 127;
	          } else {
	           if (lat < -39.0) {
	            return 43;
	           } else {
	            return 127;
	           }
	          }
	         } else {
	          return 127;
	         }
	        } else {
	         return 127;
	        }
	       } else {
	        return 127;
	       }
	      } else {
	       return 127;
	      }
	     } else {
	      if (lng < 144.75) {
	       if (lat < -35.25) {
	        if (lng < 144.0) {
	         return 127;
	        } else {
	         if (lat < -35.75) {
	          return 127;
	         } else {
	          if (lng < 144.25) {
	           if (lat < -35.5) {
	            return 127;
	           } else {
	            return 208;
	           }
	          } else {
	           if (lng < 144.5) {
	            if (lat < -35.5) {
	             return 127;
	            } else {
	             return 208;
	            }
	           } else {
	            return 208;
	           }
	          }
	         }
	        }
	       } else {
	        if (lng < 143.75) {
	         if (lat < -34.75) {
	          if (lng < 143.5) {
	           return 127;
	          } else {
	           if (lat < -35.0) {
	            return 127;
	           } else {
	            return 208;
	           }
	          }
	         } else {
	          return 208;
	         }
	        } else {
	         return 208;
	        }
	       }
	      } else {
	       if (lat < -35.75) {
	        if (lng < 145.0) {
	         if (lat < -36.0) {
	          return 127;
	         } else {
	          return 208;
	         }
	        } else {
	         return 127;
	        }
	       } else {
	        return 208;
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 140.5) {
	   if (lat < -26.0) {
	    return 67;
	   } else {
	    if (lng < 138.0) {
	     if (lat < -25.75) {
	      if (lng < 136.5) {
	       if (lng < 136.0) {
	        return 10;
	       } else {
	        return 67;
	       }
	      } else {
	       if (lng < 136.75) {
	        return 67;
	       } else {
	        return 10;
	       }
	      }
	     } else {
	      return 10;
	     }
	    } else {
	     if (lat < -24.25) {
	      if (lng < 139.25) {
	       if (lat < -25.25) {
	        return 236;
	       } else {
	        if (lng < 138.25) {
	         return 10;
	        } else {
	         return 236;
	        }
	       }
	      } else {
	       if (lat < -25.75) {
	        return 67;
	       } else {
	        return 236;
	       }
	      }
	     } else {
	      if (lng < 138.25) {
	       if (lat < -23.75) {
	        return 10;
	       } else {
	        return 236;
	       }
	      } else {
	       return 236;
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < -28.25) {
	    if (lng < 143.25) {
	     if (lat < -31.0) {
	      if (lng < 141.75) {
	       if (lat < -32.5) {
	        if (lng < 141.0) {
	         return 67;
	        } else {
	         return 208;
	        }
	       } else {
	        if (lat < -31.75) {
	         if (lng < 141.0) {
	          return 67;
	         } else {
	          if (lng < 141.25) {
	           if (lat < -32.0) {
	            return 208;
	           } else {
	            return 129;
	           }
	          } else {
	           if (lat < -32.25) {
	            return 208;
	           } else {
	            if (lng < 141.5) {
	             if (lat < -32.0) {
	              return 208;
	             } else {
	              return 129;
	             }
	            } else {
	             return 129;
	            }
	           }
	          }
	         }
	        } else {
	         if (lng < 141.0) {
	          return 67;
	         } else {
	          if (lng < 141.25) {
	           if (lat < -31.25) {
	            return 129;
	           } else {
	            return 208;
	           }
	          } else {
	           if (lat < -31.5) {
	            return 129;
	           } else {
	            return 208;
	           }
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < -32.25) {
	        return 208;
	       } else {
	        if (lng < 142.0) {
	         if (lat < -31.5) {
	          return 129;
	         } else {
	          return 208;
	         }
	        } else {
	         return 208;
	        }
	       }
	      }
	     } else {
	      if (lng < 141.75) {
	       if (lat < -29.75) {
	        if (lng < 141.0) {
	         return 67;
	        } else {
	         return 208;
	        }
	       } else {
	        if (lat < -29.0) {
	         if (lng < 141.25) {
	          return 67;
	         } else {
	          return 208;
	         }
	        } else {
	         if (lng < 141.25) {
	          return 67;
	         } else {
	          return 236;
	         }
	        }
	       }
	      } else {
	       if (lat < -29.0) {
	        return 208;
	       } else {
	        return 236;
	       }
	      }
	     }
	    } else {
	     if (lat < -29.0) {
	      return 208;
	     } else {
	      return 236;
	     }
	    }
	   } else {
	    if (lng < 141.25) {
	     if (lat < -26.0) {
	      return 67;
	     } else {
	      return 236;
	     }
	    } else {
	     return 236;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup186(double lat, double lng)
	{
	 if (lat < -33.75) {
	  if (lng < 151.75) {
	   if (lat < -39.5) {
	    return 113;
	   } else {
	    if (lat < -36.75) {
	     if (lng < 149.0) {
	      if (lng < 148.75) {
	       return 127;
	      } else {
	       if (lat < -38.25) {
	        return 0;
	       } else {
	        if (lat < -37.5) {
	         return 127;
	        } else {
	         if (lat < -37.0) {
	          return 127;
	         } else {
	          return 208;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < 150.25) {
	       if (lat < -38.25) {
	        return 0;
	       } else {
	        if (lat < -37.5) {
	         return 127;
	        } else {
	         if (lng < 149.5) {
	          if (lat < -37.0) {
	           return 127;
	          } else {
	           return 208;
	          }
	         } else {
	          if (lng < 149.75) {
	           if (lat < -37.25) {
	            return 127;
	           } else {
	            return 208;
	           }
	          } else {
	           return 208;
	          }
	         }
	        }
	       }
	      } else {
	       return 0;
	      }
	     }
	    } else {
	     if (lng < 148.25) {
	      if (lat < -35.75) {
	       if (lng < 147.25) {
	        if (lng < 146.75) {
	         if (lat < -36.0) {
	          return 127;
	         } else {
	          if (lng < 146.5) {
	           return 208;
	          } else {
	           return 127;
	          }
	         }
	        } else {
	         if (lat < -36.0) {
	          return 127;
	         } else {
	          return 208;
	         }
	        }
	       } else {
	        if (lng < 147.75) {
	         if (lat < -36.0) {
	          return 127;
	         } else {
	          if (lng < 147.5) {
	           return 208;
	          } else {
	           return 127;
	          }
	         }
	        } else {
	         if (lat < -36.0) {
	          return 127;
	         } else {
	          if (lng < 148.0) {
	           return 127;
	          } else {
	           return 208;
	          }
	         }
	        }
	       }
	      } else {
	       return 208;
	      }
	     } else {
	      return 208;
	     }
	    }
	   }
	  } else {
	   return 0;
	  }
	 } else {
	  if (lng < 151.75) {
	   if (lat < -28.25) {
	    if (lng < 149.0) {
	     if (lat < -29.0) {
	      return 208;
	     } else {
	      return 236;
	     }
	    } else {
	     if (lat < -29.0) {
	      return 208;
	     } else {
	      if (lng < 150.25) {
	       if (lng < 149.5) {
	        if (lat < -28.75) {
	         return 208;
	        } else {
	         if (lng < 149.25) {
	          return 236;
	         } else {
	          if (lat < -28.5) {
	           return 208;
	          } else {
	           return 236;
	          }
	         }
	        }
	       } else {
	        if (lng < 149.75) {
	         if (lat < -28.5) {
	          return 208;
	         } else {
	          return 236;
	         }
	        } else {
	         if (lat < -28.5) {
	          return 208;
	         } else {
	          return 236;
	         }
	        }
	       }
	      } else {
	       if (lng < 151.0) {
	        if (lng < 150.5) {
	         if (lat < -28.5) {
	          return 208;
	         } else {
	          return 236;
	         }
	        } else {
	         if (lat < -28.5) {
	          return 208;
	         } else {
	          return 236;
	         }
	        }
	       } else {
	        if (lng < 151.25) {
	         if (lat < -28.5) {
	          return 208;
	         } else {
	          return 236;
	         }
	        } else {
	         if (lat < -28.75) {
	          if (lng < 151.5) {
	           return 208;
	          } else {
	           return 236;
	          }
	         } else {
	          return 236;
	         }
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return 236;
	   }
	  } else {
	   if (lat < -28.25) {
	    if (lng < 154.5) {
	     if (lat < -31.0) {
	      return 208;
	     } else {
	      if (lng < 153.0) {
	       if (lat < -28.75) {
	        return 208;
	       } else {
	        if (lng < 152.25) {
	         return 236;
	        } else {
	         return 208;
	        }
	       }
	      } else {
	       return 208;
	      }
	     }
	    } else {
	     return 0;
	    }
	   } else {
	    if (lng < 154.5) {
	     if (lat < -25.5) {
	      if (lng < 153.25) {
	       return 236;
	      } else {
	       if (lat < -27.0) {
	        if (lng < 153.75) {
	         if (lat < -27.75) {
	          if (lng < 153.5) {
	           return 236;
	          } else {
	           return 208;
	          }
	         } else {
	          return 236;
	         }
	        } else {
	         if (lat < -27.75) {
	          return 208;
	         } else {
	          return 236;
	         }
	        }
	       } else {
	        return 236;
	       }
	      }
	     } else {
	      return 236;
	     }
	    } else {
	     return 0;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup187(double lat, double lng)
	{
	 if (lng < 146.25) {
	  if (lat < -11.25) {
	   if (lng < 140.5) {
	    if (lat < -17.0) {
	     if (lng < 138.0) {
	      return 10;
	     } else {
	      return 236;
	     }
	    } else {
	     if (lat < -14.25) {
	      if (lng < 137.75) {
	       return 10;
	      } else {
	       if (lng < 139.0) {
	        if (lat < -15.75) {
	         if (lng < 138.25) {
	          if (lat < -16.5) {
	           if (lng < 138.0) {
	            return 10;
	           } else {
	            return 236;
	           }
	          } else {
	           return 10;
	          }
	         } else {
	          return 236;
	         }
	        } else {
	         return 0;
	        }
	       } else {
	        return 236;
	       }
	      }
	     } else {
	      return 10;
	     }
	    }
	   } else {
	    return 236;
	   }
	  } else {
	   if (lng < 140.5) {
	    return 132;
	   } else {
	    if (lat < -5.75) {
	     if (lng < 143.25) {
	      if (lat < -8.5) {
	       if (lng < 141.75) {
	        if (lat < -10.0) {
	         return 0;
	        } else {
	         if (lat < -9.25) {
	          return 0;
	         } else {
	          if (lng < 141.25) {
	           return 132;
	          } else {
	           return 171;
	          }
	         }
	        }
	       } else {
	        if (lat < -10.0) {
	         return 236;
	        } else {
	         return 171;
	        }
	       }
	      } else {
	       if (lng < 141.25) {
	        if (lat < -6.75) {
	         return 132;
	        } else {
	         if (lat < -6.25) {
	          if (lng < 141.0) {
	           return 132;
	          } else {
	           return 171;
	          }
	         } else {
	          return 132;
	         }
	        }
	       } else {
	        return 171;
	       }
	      }
	     } else {
	      return 171;
	     }
	    } else {
	     if (lng < 143.25) {
	      if (lat < -3.0) {
	       if (lng < 141.25) {
	        if (lat < -4.5) {
	         if (lat < -5.0) {
	          return 132;
	         } else {
	          if (lng < 141.0) {
	           return 132;
	          } else {
	           return 171;
	          }
	         }
	        } else {
	         if (lat < -3.75) {
	          if (lng < 141.0) {
	           return 132;
	          } else {
	           return 171;
	          }
	         } else {
	          if (lng < 141.0) {
	           return 132;
	          } else {
	           return 171;
	          }
	         }
	        }
	       } else {
	        return 171;
	       }
	      } else {
	       if (lat < -1.5) {
	        if (lng < 141.75) {
	         if (lat < -2.25) {
	          if (lng < 141.0) {
	           return 132;
	          } else {
	           return 171;
	          }
	         } else {
	          return 132;
	         }
	        } else {
	         return 171;
	        }
	       } else {
	        return 0;
	       }
	      }
	     } else {
	      return 171;
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < -11.25) {
	   if (lng < 151.75) {
	    return 236;
	   } else {
	    return 171;
	   }
	  } else {
	   if (lng < 151.75) {
	    return 171;
	   } else {
	    if (lat < -5.75) {
	     if (lng < 154.5) {
	      return 171;
	     } else {
	      if (lat < -8.5) {
	       return 0;
	      } else {
	       if (lng < 156.0) {
	        if (lat < -7.25) {
	         return 0;
	        } else {
	         if (lng < 155.25) {
	          return 171;
	         } else {
	          if (lat < -6.5) {
	           if (lng < 155.75) {
	            return 171;
	           } else {
	            if (lat < -6.75) {
	             return 305;
	            } else {
	             return 171;
	            }
	           }
	          } else {
	           return 171;
	          }
	         }
	        }
	       } else {
	        if (lat < -7.0) {
	         return 305;
	        } else {
	         if (lng < 156.75) {
	          if (lat < -6.5) {
	           if (lng < 156.25) {
	            if (lat < -6.75) {
	             return 305;
	            } else {
	             return 171;
	            }
	           } else {
	            return 305;
	           }
	          } else {
	           if (lng < 156.25) {
	            return 171;
	           } else {
	            return 305;
	           }
	          }
	         } else {
	          return 305;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     return 171;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup188(double lat, double lng)
	{
	 if (lng < 98.25) {
	  if (lat < 19.5) {
	   if (lng < 97.75) {
	    return 40;
	   } else {
	    if (lat < 18.0) {
	     if (lat < 17.75) {
	      return 40;
	     } else {
	      if (lng < 98.0) {
	       return 40;
	      } else {
	       return 271;
	      }
	     }
	    } else {
	     if (lat < 18.75) {
	      return 271;
	     } else {
	      if (lat < 19.0) {
	       if (lng < 98.0) {
	        return 40;
	       } else {
	        return 271;
	       }
	      } else {
	       if (lng < 98.0) {
	        if (lat < 19.25) {
	         return 271;
	        } else {
	         return 40;
	        }
	       } else {
	        return 271;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 19.75) {
	    if (lng < 98.0) {
	     return 40;
	    } else {
	     return 271;
	    }
	   } else {
	    return 40;
	   }
	  }
	 } else {
	  if (lat < 19.5) {
	   if (lng < 99.75) {
	    if (lat < 17.25) {
	     if (lng < 98.5) {
	      return 40;
	     } else {
	      return 271;
	     }
	    } else {
	     return 271;
	    }
	   } else {
	    if (lat < 19.25) {
	     if (lat < 19.0) {
	      if (lat < 17.75) {
	       return 271;
	      } else {
	       if (lng < 101.0) {
	        return 271;
	       } else {
	        if (lat < 18.0) {
	         return 266;
	        } else {
	         return 271;
	        }
	       }
	      }
	     } else {
	      return 271;
	     }
	    } else {
	     return 271;
	    }
	   }
	  } else {
	   if (lng < 99.75) {
	    if (lat < 21.0) {
	     if (lng < 99.0) {
	      if (lat < 20.0) {
	       if (lng < 98.5) {
	        if (lat < 19.75) {
	         return 271;
	        } else {
	         return 40;
	        }
	       } else {
	        if (lng < 98.75) {
	         if (lat < 19.75) {
	          return 271;
	         } else {
	          return 40;
	         }
	        } else {
	         return 271;
	        }
	       }
	      } else {
	       return 40;
	      }
	     } else {
	      if (lat < 20.25) {
	       if (lng < 99.25) {
	        if (lat < 20.0) {
	         return 271;
	        } else {
	         return 40;
	        }
	       } else {
	        return 271;
	       }
	      } else {
	       return 40;
	      }
	     }
	    } else {
	     if (lng < 99.25) {
	      return 40;
	     } else {
	      if (lat < 22.25) {
	       return 40;
	      } else {
	       return 404;
	      }
	     }
	    }
	   } else {
	    if (lat < 21.0) {
	     if (lng < 100.5) {
	      if (lat < 20.5) {
	       return 271;
	      } else {
	       if (lng < 100.25) {
	        return 40;
	       } else {
	        if (lat < 20.75) {
	         return 266;
	        } else {
	         return 40;
	        }
	       }
	      }
	     } else {
	      if (lat < 20.25) {
	       if (lng < 100.75) {
	        if (lat < 19.75) {
	         return 271;
	        } else {
	         if (lat < 20.0) {
	          return 266;
	         } else {
	          return 271;
	         }
	        }
	       } else {
	        if (lat < 19.75) {
	         return 271;
	        } else {
	         return 266;
	        }
	       }
	      } else {
	       return 266;
	      }
	     }
	    } else {
	     if (lng < 100.5) {
	      if (lat < 21.75) {
	       if (lng < 100.25) {
	        return 40;
	       } else {
	        if (lat < 21.5) {
	         return 40;
	        } else {
	         return 404;
	        }
	       }
	      } else {
	       if (lng < 100.0) {
	        if (lat < 22.25) {
	         return 40;
	        } else {
	         return 404;
	        }
	       } else {
	        return 404;
	       }
	      }
	     } else {
	      if (lat < 21.75) {
	       if (lng < 100.75) {
	        if (lat < 21.5) {
	         return 40;
	        } else {
	         return 404;
	        }
	       } else {
	        if (lat < 21.5) {
	         return 266;
	        } else {
	         return 40;
	        }
	       }
	      } else {
	       return 404;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup189(double lat, double lng)
	{
	 if (lat < 11.25) {
	  if (lng < 95.5) {
	   if (lat < 5.5) {
	    return 26;
	   } else {
	    if (lat < 8.25) {
	     if (lng < 92.75) {
	      return 0;
	     } else {
	      if (lng < 94.0) {
	       return 372;
	      } else {
	       if (lat < 6.75) {
	        return 26;
	       } else {
	        return 372;
	       }
	      }
	     }
	    } else {
	     return 372;
	    }
	   }
	  } else {
	   if (lat < 5.5) {
	    if (lng < 98.25) {
	     return 26;
	    } else {
	     if (lat < 2.75) {
	      return 26;
	     } else {
	      if (lng < 99.75) {
	       return 26;
	      } else {
	       if (lat < 3.5) {
	        if (lng < 100.5) {
	         return 26;
	        } else {
	         return 328;
	        }
	       } else {
	        return 328;
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 98.25) {
	     if (lat < 8.25) {
	      return 26;
	     } else {
	      if (lat < 9.75) {
	       return 271;
	      } else {
	       return 40;
	      }
	     }
	    } else {
	     if (lat < 8.25) {
	      if (lng < 99.75) {
	       return 271;
	      } else {
	       if (lat < 6.75) {
	        if (lng < 100.5) {
	         return 328;
	        } else {
	         if (lat < 6.5) {
	          return 328;
	         } else {
	          if (lng < 100.75) {
	           return 328;
	          } else {
	           return 271;
	          }
	         }
	        }
	       } else {
	        return 271;
	       }
	      }
	     } else {
	      if (lng < 99.75) {
	       if (lat < 9.75) {
	        return 271;
	       } else {
	        if (lng < 99.0) {
	         if (lat < 10.5) {
	          if (lng < 98.5) {
	           return 40;
	          } else {
	           return 271;
	          }
	         } else {
	          return 40;
	         }
	        } else {
	         if (lat < 10.5) {
	          return 271;
	         } else {
	          if (lng < 99.25) {
	           if (lat < 11.0) {
	            return 271;
	           } else {
	            return 40;
	           }
	          } else {
	           return 271;
	          }
	         }
	        }
	       }
	      } else {
	       return 271;
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 95.5) {
	   if (lat < 16.75) {
	    if (lng < 92.75) {
	     return 372;
	    } else {
	     if (lat < 14.0) {
	      return 372;
	     } else {
	      return 40;
	     }
	    }
	   } else {
	    if (lat < 21.0) {
	     return 40;
	    } else {
	     if (lng < 92.75) {
	      if (lng < 91.25) {
	       return 151;
	      } else {
	       if (lng < 92.0) {
	        return 151;
	       } else {
	        if (lat < 21.5) {
	         if (lng < 92.25) {
	          return 151;
	         } else {
	          if (lng < 92.5) {
	           if (lat < 21.25) {
	            return 151;
	           } else {
	            return 40;
	           }
	          } else {
	           return 40;
	          }
	         }
	        } else {
	         return 151;
	        }
	       }
	      }
	     } else {
	      if (lng < 93.25) {
	       if (lat < 22.0) {
	        return 40;
	       } else {
	        if (lng < 93.0) {
	         if (lat < 22.25) {
	          return 40;
	         } else {
	          return 372;
	         }
	        } else {
	         return 372;
	        }
	       }
	      } else {
	       return 40;
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 16.75) {
	    if (lng < 98.25) {
	     return 40;
	    } else {
	     if (lat < 14.0) {
	      if (lng < 99.75) {
	       if (lat < 12.5) {
	        if (lng < 99.5) {
	         return 40;
	        } else {
	         if (lat < 11.75) {
	          return 271;
	         } else {
	          if (lat < 12.25) {
	           return 40;
	          } else {
	           return 271;
	          }
	         }
	        }
	       } else {
	        if (lng < 99.0) {
	         return 40;
	        } else {
	         if (lat < 13.25) {
	          if (lng < 99.25) {
	           return 40;
	          } else {
	           if (lat < 12.75) {
	            if (lng < 99.5) {
	             return 40;
	            } else {
	             return 271;
	            }
	           } else {
	            return 271;
	           }
	          }
	         } else {
	          if (lng < 99.25) {
	           return 40;
	          } else {
	           return 271;
	          }
	         }
	        }
	       }
	      } else {
	       return 271;
	      }
	     } else {
	      if (lng < 99.25) {
	       if (lat < 15.25) {
	        if (lat < 14.5) {
	         if (lng < 98.75) {
	          return 40;
	         } else {
	          if (lng < 99.0) {
	           if (lat < 14.25) {
	            return 40;
	           } else {
	            return 271;
	           }
	          } else {
	           if (lat < 14.25) {
	            return 40;
	           } else {
	            return 271;
	           }
	          }
	         }
	        } else {
	         if (lng < 98.75) {
	          if (lat < 14.75) {
	           return 40;
	          } else {
	           if (lng < 98.5) {
	            if (lat < 15.0) {
	             return 40;
	            } else {
	             return 271;
	            }
	           } else {
	            return 271;
	           }
	          }
	         } else {
	          return 271;
	         }
	        }
	       } else {
	        if (lat < 16.0) {
	         if (lng < 98.75) {
	          if (lat < 15.5) {
	           if (lng < 98.5) {
	            return 40;
	           } else {
	            return 271;
	           }
	          } else {
	           return 40;
	          }
	         } else {
	          return 271;
	         }
	        } else {
	         if (lng < 98.75) {
	          return 40;
	         } else {
	          if (lat < 16.25) {
	           return 271;
	          } else {
	           if (lng < 99.0) {
	            if (lat < 16.5) {
	             return 40;
	            } else {
	             return 271;
	            }
	           } else {
	            return 271;
	           }
	          }
	         }
	        }
	       }
	      } else {
	       return 271;
	      }
	     }
	    }
	   } else {
	    return kdLookup188(lat,lng);
	   }
	  }
	 }
	}

	private  int kdLookup190(double lat, double lng)
	{
	 if (lat < 5.5) {
	  if (lng < 104.0) {
	   if (lat < 2.75) {
	    if (lng < 102.5) {
	     if (lat < 1.75) {
	      return 26;
	     } else {
	      if (lng < 101.75) {
	       if (lat < 2.5) {
	        return 26;
	       } else {
	        return 328;
	       }
	      } else {
	       if (lat < 2.25) {
	        if (lng < 102.25) {
	         return 26;
	        } else {
	         if (lat < 2.0) {
	          return 26;
	         } else {
	          return 328;
	         }
	        }
	       } else {
	        if (lng < 102.0) {
	         if (lat < 2.5) {
	          return 26;
	         } else {
	          return 328;
	         }
	        } else {
	         return 328;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 1.25) {
	      return 26;
	     } else {
	      if (lng < 103.25) {
	       if (lat < 1.75) {
	        if (lng < 102.75) {
	         return 26;
	        } else {
	         if (lng < 103.0) {
	          return 26;
	         } else {
	          if (lat < 1.5) {
	           return 26;
	          } else {
	           return 328;
	          }
	         }
	        }
	       } else {
	        return 328;
	       }
	      } else {
	       return 328;
	      }
	     }
	    }
	   } else {
	    return 328;
	   }
	  } else {
	   if (lat < 2.75) {
	    if (lng < 105.25) {
	     if (lat < 1.25) {
	      return 26;
	     } else {
	      if (lat < 2.0) {
	       if (lng < 104.5) {
	        return 328;
	       } else {
	        if (lng < 104.75) {
	         if (lat < 1.5) {
	          return 26;
	         } else {
	          return 328;
	         }
	        } else {
	         return 0;
	        }
	       }
	      } else {
	       return 328;
	      }
	     }
	    } else {
	     return 0;
	    }
	   } else {
	    return 26;
	   }
	  }
	 } else {
	  if (lat < 8.25) {
	   if (lng < 104.0) {
	    if (lng < 102.5) {
	     if (lat < 6.75) {
	      if (lng < 101.75) {
	       if (lat < 6.0) {
	        if (lng < 101.5) {
	         if (lat < 5.75) {
	          return 328;
	         } else {
	          return 271;
	         }
	        } else {
	         return 328;
	        }
	       } else {
	        return 271;
	       }
	      } else {
	       if (lat < 6.0) {
	        return 328;
	       } else {
	        if (lng < 102.0) {
	         return 271;
	        } else {
	         if (lat < 6.25) {
	          return 328;
	         } else {
	          return 271;
	         }
	        }
	       }
	      }
	     } else {
	      return 271;
	     }
	    } else {
	     return 328;
	    }
	   } else {
	    return 0;
	   }
	  } else {
	   if (lng < 104.0) {
	    if (lat < 9.75) {
	     return 0;
	    } else {
	     if (lng < 102.5) {
	      return 0;
	     } else {
	      if (lng < 103.25) {
	       return 252;
	      } else {
	       if (lat < 10.5) {
	        return 24;
	       } else {
	        return 252;
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 9.75) {
	     return 24;
	    } else {
	     if (lng < 105.25) {
	      if (lat < 10.5) {
	       return 24;
	      } else {
	       if (lng < 104.75) {
	        return 252;
	       } else {
	        if (lat < 10.75) {
	         return 24;
	        } else {
	         return 252;
	        }
	       }
	      }
	     } else {
	      if (lng < 105.75) {
	       if (lat < 11.0) {
	        return 24;
	       } else {
	        return 252;
	       }
	      } else {
	       if (lat < 11.0) {
	        return 24;
	       } else {
	        if (lng < 106.5) {
	         if (lng < 106.0) {
	          return 24;
	         } else {
	          if (lng < 106.25) {
	           return 252;
	          } else {
	           return 24;
	          }
	         }
	        } else {
	         return 24;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup191(double lat, double lng)
	{
	 if (lat < 19.5) {
	  if (lng < 104.0) {
	   if (lng < 102.5) {
	    if (lat < 18.0) {
	     if (lng < 101.75) {
	      if (lat < 17.75) {
	       return 271;
	      } else {
	       return 266;
	      }
	     } else {
	      return 271;
	     }
	    } else {
	     if (lat < 18.75) {
	      if (lng < 102.0) {
	       return 266;
	      } else {
	       if (lat < 18.25) {
	        return 271;
	       } else {
	        return 266;
	       }
	      }
	     } else {
	      if (lng < 101.5) {
	       if (lat < 19.0) {
	        return 266;
	       } else {
	        return 271;
	       }
	      } else {
	       return 266;
	      }
	     }
	    }
	   } else {
	    if (lat < 18.0) {
	     return 271;
	    } else {
	     if (lng < 103.25) {
	      return 266;
	     } else {
	      if (lat < 18.5) {
	       return 271;
	      } else {
	       return 266;
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 105.25) {
	    if (lat < 18.0) {
	     if (lng < 104.5) {
	      return 271;
	     } else {
	      if (lat < 17.25) {
	       if (lng < 104.75) {
	        return 271;
	       } else {
	        if (lng < 105.0) {
	         if (lat < 17.0) {
	          return 271;
	         } else {
	          return 266;
	         }
	        } else {
	         return 266;
	        }
	       }
	      } else {
	       if (lng < 104.75) {
	        if (lat < 17.75) {
	         return 271;
	        } else {
	         return 266;
	        }
	       } else {
	        if (lat < 17.5) {
	         if (lng < 105.0) {
	          return 271;
	         } else {
	          return 266;
	         }
	        } else {
	         return 266;
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 18.75) {
	      if (lng < 104.25) {
	       if (lat < 18.5) {
	        return 271;
	       } else {
	        return 266;
	       }
	      } else {
	       return 266;
	      }
	     } else {
	      if (lng < 104.5) {
	       if (lat < 19.25) {
	        return 266;
	       } else {
	        return 24;
	       }
	      } else {
	       if (lng < 104.75) {
	        if (lat < 19.25) {
	         return 266;
	        } else {
	         return 24;
	        }
	       } else {
	        if (lat < 19.0) {
	         if (lng < 105.0) {
	          return 266;
	         } else {
	          return 24;
	         }
	        } else {
	         return 24;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    if (lat < 18.0) {
	     if (lng < 106.0) {
	      if (lat < 17.75) {
	       return 266;
	      } else {
	       if (lng < 105.75) {
	        return 266;
	       } else {
	        return 24;
	       }
	      }
	     } else {
	      if (lat < 17.25) {
	       if (lng < 106.5) {
	        return 266;
	       } else {
	        if (lat < 17.0) {
	         return 266;
	        } else {
	         return 24;
	        }
	       }
	      } else {
	       if (lng < 106.25) {
	        if (lat < 17.5) {
	         return 266;
	        } else {
	         return 24;
	        }
	       } else {
	        return 24;
	       }
	      }
	     }
	    } else {
	     if (lng < 106.0) {
	      if (lat < 18.5) {
	       if (lng < 105.5) {
	        return 266;
	       } else {
	        if (lng < 105.75) {
	         if (lat < 18.25) {
	          return 266;
	         } else {
	          return 24;
	         }
	        } else {
	         return 24;
	        }
	       }
	      } else {
	       return 24;
	      }
	     } else {
	      return 24;
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 104.0) {
	   if (lat < 21.0) {
	    if (lng < 102.5) {
	     if (lat < 19.75) {
	      if (lng < 101.5) {
	       return 271;
	      } else {
	       return 266;
	      }
	     } else {
	      return 266;
	     }
	    } else {
	     if (lng < 103.75) {
	      return 266;
	     } else {
	      if (lat < 20.75) {
	       return 266;
	      } else {
	       return 24;
	      }
	     }
	    }
	   } else {
	    if (lng < 102.5) {
	     if (lat < 21.75) {
	      if (lng < 101.75) {
	       if (lat < 21.25) {
	        return 266;
	       } else {
	        return 404;
	       }
	      } else {
	       if (lng < 102.0) {
	        if (lat < 21.25) {
	         return 266;
	        } else {
	         return 404;
	        }
	       } else {
	        return 266;
	       }
	      }
	     } else {
	      if (lng < 101.75) {
	       return 404;
	      } else {
	       if (lng < 102.0) {
	        if (lat < 22.0) {
	         return 404;
	        } else {
	         return 266;
	        }
	       } else {
	        if (lat < 22.25) {
	         return 266;
	        } else {
	         if (lng < 102.25) {
	          return 266;
	         } else {
	          return 24;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lng < 103.25) {
	      if (lat < 21.75) {
	       if (lng < 103.0) {
	        return 266;
	       } else {
	        if (lat < 21.25) {
	         return 266;
	        } else {
	         return 24;
	        }
	       }
	      } else {
	       if (lng < 102.75) {
	        if (lat < 22.0) {
	         return 266;
	        } else {
	         return 24;
	        }
	       } else {
	        return 24;
	       }
	      }
	     } else {
	      return 24;
	     }
	    }
	   }
	  } else {
	   if (lat < 21.0) {
	    if (lng < 105.0) {
	     if (lat < 20.25) {
	      if (lng < 104.5) {
	       if (lat < 19.75) {
	        if (lng < 104.25) {
	         return 266;
	        } else {
	         return 24;
	        }
	       } else {
	        return 266;
	       }
	      } else {
	       if (lat < 19.75) {
	        return 24;
	       } else {
	        if (lng < 104.75) {
	         return 266;
	        } else {
	         if (lat < 20.0) {
	          return 24;
	         } else {
	          return 266;
	         }
	        }
	       }
	      }
	     } else {
	      if (lng < 104.5) {
	       return 266;
	      } else {
	       if (lat < 20.5) {
	        if (lng < 104.75) {
	         return 266;
	        } else {
	         return 24;
	        }
	       } else {
	        return 24;
	       }
	      }
	     }
	    } else {
	     return 24;
	    }
	   } else {
	    return 24;
	   }
	  }
	 }
	}

	private  int kdLookup192(double lat, double lng)
	{
	 if (lat < 16.75) {
	  if (lng < 109.5) {
	   if (lat < 14.0) {
	    if (lng < 107.75) {
	     if (lat < 12.5) {
	      if (lat < 12.25) {
	       return 24;
	      } else {
	       if (lng < 107.25) {
	        return 252;
	       } else {
	        return 24;
	       }
	      }
	     } else {
	      if (lat < 13.75) {
	       if (lat < 13.0) {
	        return 252;
	       } else {
	        if (lng < 107.5) {
	         return 252;
	        } else {
	         if (lat < 13.25) {
	          return 24;
	         } else {
	          return 252;
	         }
	        }
	       }
	      } else {
	       return 252;
	      }
	     }
	    } else {
	     return 24;
	    }
	   } else {
	    if (lng < 108.0) {
	     if (lat < 15.25) {
	      if (lng < 107.25) {
	       if (lat < 14.5) {
	        return 252;
	       } else {
	        return 266;
	       }
	      } else {
	       if (lat < 14.5) {
	        if (lng < 107.5) {
	         return 252;
	        } else {
	         return 24;
	        }
	       } else {
	        if (lng < 107.5) {
	         return 266;
	        } else {
	         if (lat < 14.75) {
	          if (lng < 107.75) {
	           return 252;
	          } else {
	           return 24;
	          }
	         } else {
	          if (lng < 107.75) {
	           if (lat < 15.0) {
	            return 266;
	           } else {
	            return 24;
	           }
	          } else {
	           return 24;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 16.0) {
	       if (lng < 107.25) {
	        return 266;
	       } else {
	        if (lng < 107.5) {
	         if (lat < 15.75) {
	          return 266;
	         } else {
	          return 24;
	         }
	        } else {
	         if (lat < 15.5) {
	          if (lng < 107.75) {
	           return 266;
	          } else {
	           return 24;
	          }
	         } else {
	          return 24;
	         }
	        }
	       }
	      } else {
	       if (lng < 107.25) {
	        if (lat < 16.5) {
	         return 266;
	        } else {
	         return 24;
	        }
	       } else {
	        if (lng < 107.5) {
	         if (lat < 16.25) {
	          return 266;
	         } else {
	          return 24;
	         }
	        } else {
	         return 24;
	        }
	       }
	      }
	     }
	    } else {
	     return 24;
	    }
	   }
	  } else {
	   return 24;
	  }
	 } else {
	  if (lng < 109.5) {
	   if (lat < 18.25) {
	    return 24;
	   } else {
	    if (lat < 20.25) {
	     return 404;
	    } else {
	     if (lng < 108.0) {
	      if (lat < 21.25) {
	       return 24;
	      } else {
	       if (lng < 107.25) {
	        if (lat < 22.0) {
	         return 24;
	        } else {
	         if (lng < 107.0) {
	          if (lat < 22.25) {
	           return 24;
	          } else {
	           return 404;
	          }
	         } else {
	          return 404;
	         }
	        }
	       } else {
	        if (lat < 21.75) {
	         return 24;
	        } else {
	         return 404;
	        }
	       }
	      }
	     } else {
	      if (lat < 21.25) {
	       return 24;
	      } else {
	       if (lng < 108.25) {
	        if (lat < 21.75) {
	         return 24;
	        } else {
	         return 404;
	        }
	       } else {
	        return 404;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 19.5) {
	    return 404;
	   } else {
	    if (lng < 111.0) {
	     if (lat < 21.0) {
	      if (lng < 110.25) {
	       if (lat < 20.25) {
	        return 404;
	       } else {
	        return 407;
	       }
	      } else {
	       if (lat < 20.5) {
	        return 404;
	       } else {
	        return 407;
	       }
	      }
	     } else {
	      if (lng < 110.25) {
	       if (lat < 21.75) {
	        if (lng < 109.75) {
	         if (lat < 21.25) {
	          return 407;
	         } else {
	          return 404;
	         }
	        } else {
	         if (lat < 21.5) {
	          return 407;
	         } else {
	          if (lng < 110.0) {
	           return 404;
	          } else {
	           return 407;
	          }
	         }
	        }
	       } else {
	        if (lng < 110.0) {
	         return 404;
	        } else {
	         if (lat < 22.0) {
	          return 407;
	         } else {
	          return 404;
	         }
	        }
	       }
	      } else {
	       if (lat < 22.0) {
	        return 407;
	       } else {
	        if (lng < 110.5) {
	         return 404;
	        } else {
	         if (lng < 110.75) {
	          if (lat < 22.25) {
	           return 407;
	          } else {
	           return 404;
	          }
	         } else {
	          return 407;
	         }
	        }
	       }
	      }
	     }
	    } else {
	     if (lat < 21.0) {
	      return 404;
	     } else {
	      if (lng < 111.5) {
	       return 407;
	      } else {
	       return 404;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup193(double lat, double lng)
	{
	 if (lat < 11.25) {
	  if (lng < 106.75) {
	   return kdLookup190(lat,lng);
	  } else {
	   if (lat < 5.5) {
	    if (lng < 109.5) {
	     if (lat < 2.75) {
	      return 321;
	     } else {
	      return 26;
	     }
	    } else {
	     if (lat < 2.75) {
	      if (lng < 111.0) {
	       if (lat < 1.25) {
	        if (lng < 110.5) {
	         return 321;
	        } else {
	         if (lat < 1.0) {
	          return 321;
	         } else {
	          return 287;
	         }
	        }
	       } else {
	        if (lng < 110.25) {
	         if (lat < 2.0) {
	          if (lng < 109.75) {
	           return 321;
	          } else {
	           if (lat < 1.5) {
	            return 321;
	           } else {
	            if (lng < 110.0) {
	             if (lat < 1.75) {
	              return 321;
	             } else {
	              return 287;
	             }
	            } else {
	             return 287;
	            }
	           }
	          }
	         } else {
	          if (lng < 109.75) {
	           return 321;
	          } else {
	           return 287;
	          }
	         }
	        } else {
	         return 287;
	        }
	       }
	      } else {
	       if (lat < 1.25) {
	        return 321;
	       } else {
	        if (lng < 111.75) {
	         return 287;
	        } else {
	         if (lat < 1.5) {
	          if (lng < 112.25) {
	           return 287;
	          } else {
	           return 321;
	          }
	         } else {
	          return 287;
	         }
	        }
	       }
	      }
	     } else {
	      return 287;
	     }
	    }
	   } else {
	    return 24;
	   }
	  }
	 } else {
	  if (lng < 106.75) {
	   if (lat < 16.75) {
	    if (lng < 104.0) {
	     if (lat < 14.0) {
	      if (lng < 102.5) {
	       return 271;
	      } else {
	       if (lat < 12.5) {
	        if (lng < 102.75) {
	         return 271;
	        } else {
	         return 252;
	        }
	       } else {
	        if (lng < 102.75) {
	         if (lat < 13.25) {
	          return 271;
	         } else {
	          if (lat < 13.75) {
	           return 252;
	          } else {
	           return 271;
	          }
	         }
	        } else {
	         return 252;
	        }
	       }
	      }
	     } else {
	      if (lng < 103.0) {
	       return 271;
	      } else {
	       if (lat < 14.5) {
	        if (lng < 103.25) {
	         if (lat < 14.25) {
	          return 252;
	         } else {
	          return 271;
	         }
	        } else {
	         return 252;
	        }
	       } else {
	        return 271;
	       }
	      }
	     }
	    } else {
	     if (lat < 14.0) {
	      if (lng < 106.0) {
	       return 252;
	      } else {
	       if (lat < 12.0) {
	        if (lng < 106.25) {
	         if (lat < 11.75) {
	          return 24;
	         } else {
	          return 252;
	         }
	        } else {
	         if (lat < 11.75) {
	          return 24;
	         } else {
	          if (lng < 106.5) {
	           return 252;
	          } else {
	           return 24;
	          }
	         }
	        }
	       } else {
	        return 252;
	       }
	      }
	     } else {
	      if (lng < 105.25) {
	       if (lat < 15.25) {
	        if (lng < 104.5) {
	         if (lat < 14.5) {
	          return 252;
	         } else {
	          return 271;
	         }
	        } else {
	         if (lat < 14.5) {
	          return 252;
	         } else {
	          return 271;
	         }
	        }
	       } else {
	        if (lat < 16.5) {
	         return 271;
	        } else {
	         if (lng < 105.0) {
	          return 271;
	         } else {
	          return 266;
	         }
	        }
	       }
	      } else {
	       if (lat < 15.25) {
	        if (lng < 106.0) {
	         if (lat < 14.5) {
	          if (lng < 105.5) {
	           if (lat < 14.25) {
	            return 252;
	           } else {
	            return 266;
	           }
	          } else {
	           if (lng < 105.75) {
	            if (lat < 14.25) {
	             return 252;
	            } else {
	             return 266;
	            }
	           } else {
	            if (lat < 14.25) {
	             return 252;
	            } else {
	             return 266;
	            }
	           }
	          }
	         } else {
	          if (lng < 105.5) {
	           return 271;
	          } else {
	           if (lat < 14.75) {
	            return 266;
	           } else {
	            if (lng < 105.75) {
	             return 271;
	            } else {
	             return 266;
	            }
	           }
	          }
	         }
	        } else {
	         if (lat < 14.5) {
	          if (lng < 106.25) {
	           return 266;
	          } else {
	           return 252;
	          }
	         } else {
	          if (lng < 106.5) {
	           return 266;
	          } else {
	           if (lat < 14.75) {
	            return 252;
	           } else {
	            return 266;
	           }
	          }
	         }
	        }
	       } else {
	        if (lng < 105.75) {
	         if (lat < 16.0) {
	          return 271;
	         } else {
	          if (lat < 16.25) {
	           if (lng < 105.5) {
	            return 271;
	           } else {
	            return 266;
	           }
	          } else {
	           return 266;
	          }
	         }
	        } else {
	         return 266;
	        }
	       }
	      }
	     }
	    }
	   } else {
	    return kdLookup191(lat,lng);
	   }
	  } else {
	   return kdLookup192(lat,lng);
	  }
	 }
	}

	private  int kdLookup194(double lat, double lng)
	{
	 if (lng < 92.75) {
	  if (lat < 25.25) {
	   if (lng < 91.25) {
	    return 151;
	   } else {
	    if (lat < 23.75) {
	     if (lng < 92.0) {
	      if (lat < 23.25) {
	       return 151;
	      } else {
	       if (lng < 91.5) {
	        if (lat < 23.5) {
	         return 151;
	        } else {
	         return 372;
	        }
	       } else {
	        return 372;
	       }
	      }
	     } else {
	      if (lat < 23.0) {
	       if (lng < 92.5) {
	        return 151;
	       } else {
	        if (lat < 22.75) {
	         return 151;
	        } else {
	         return 372;
	        }
	       }
	      } else {
	       if (lng < 92.5) {
	        return 151;
	       } else {
	        return 372;
	       }
	      }
	     }
	    } else {
	     if (lng < 92.0) {
	      if (lat < 24.25) {
	       if (lng < 91.5) {
	        if (lat < 24.0) {
	         return 372;
	        } else {
	         return 151;
	        }
	       } else {
	        return 372;
	       }
	      } else {
	       return 151;
	      }
	     } else {
	      if (lat < 24.5) {
	       return 372;
	      } else {
	       if (lng < 92.25) {
	        return 151;
	       } else {
	        if (lat < 24.75) {
	         return 372;
	        } else {
	         if (lng < 92.5) {
	          return 151;
	         } else {
	          return 372;
	         }
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 91.25) {
	    if (lat < 26.5) {
	     if (lng < 90.25) {
	      if (lat < 25.5) {
	       return 151;
	      } else {
	       return 372;
	      }
	     } else {
	      return 372;
	     }
	    } else {
	     if (lat < 27.0) {
	      if (lng < 90.25) {
	       if (lat < 26.75) {
	        return 372;
	       } else {
	        return 62;
	       }
	      } else {
	       return 372;
	      }
	     } else {
	      return 62;
	     }
	    }
	   } else {
	    if (lat < 27.0) {
	     return 372;
	    } else {
	     if (lng < 92.0) {
	      if (lat < 27.5) {
	       return 62;
	      } else {
	       if (lng < 91.75) {
	        return 62;
	       } else {
	        return 372;
	       }
	      }
	     } else {
	      if (lat < 27.5) {
	       if (lng < 92.25) {
	        return 62;
	       } else {
	        return 372;
	       }
	      } else {
	       if (lng < 92.25) {
	        if (lat < 27.75) {
	         return 372;
	        } else {
	         return 407;
	        }
	       } else {
	        return 372;
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 25.25) {
	   if (lng < 94.0) {
	    if (lat < 23.75) {
	     if (lng < 93.25) {
	      return 372;
	     } else {
	      if (lat < 23.25) {
	       return 40;
	      } else {
	       if (lng < 93.5) {
	        return 372;
	       } else {
	        return 40;
	       }
	      }
	     }
	    } else {
	     if (lat < 24.0) {
	      if (lng < 93.5) {
	       return 372;
	      } else {
	       return 40;
	      }
	     } else {
	      return 372;
	     }
	    }
	   } else {
	    if (lat < 24.0) {
	     return 40;
	    } else {
	     if (lng < 94.75) {
	      if (lat < 24.5) {
	       if (lng < 94.25) {
	        return 372;
	       } else {
	        if (lng < 94.5) {
	         if (lat < 24.25) {
	          return 40;
	         } else {
	          return 372;
	         }
	        } else {
	         return 40;
	        }
	       }
	      } else {
	       if (lng < 94.5) {
	        return 372;
	       } else {
	        if (lat < 24.75) {
	         return 40;
	        } else {
	         return 372;
	        }
	       }
	      }
	     } else {
	      return 40;
	     }
	    }
	   }
	  } else {
	   if (lng < 94.75) {
	    return 372;
	   } else {
	    if (lat < 26.5) {
	     if (lat < 25.75) {
	      if (lng < 95.0) {
	       if (lat < 25.5) {
	        return 40;
	       } else {
	        return 372;
	       }
	      } else {
	       return 40;
	      }
	     } else {
	      if (lng < 95.25) {
	       return 372;
	      } else {
	       return 40;
	      }
	     }
	    } else {
	     if (lat < 26.75) {
	      if (lng < 95.25) {
	       return 372;
	      } else {
	       return 40;
	      }
	     } else {
	      return 372;
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup195(double lat, double lng)
	{
	 if (lat < 28.0) {
	  if (lng < 98.25) {
	   if (lat < 25.25) {
	    if (lng < 97.75) {
	     return 40;
	    } else {
	     if (lat < 24.0) {
	      return 40;
	     } else {
	      if (lat < 24.5) {
	       if (lng < 98.0) {
	        if (lat < 24.25) {
	         return 404;
	        } else {
	         return 40;
	        }
	       } else {
	        if (lat < 24.25) {
	         return 40;
	        } else {
	         return 404;
	        }
	       }
	      } else {
	       return 404;
	      }
	     }
	    }
	   } else {
	    if (lng < 96.75) {
	     if (lat < 27.0) {
	      return 40;
	     } else {
	      if (lng < 96.0) {
	       return 372;
	      } else {
	       if (lat < 27.5) {
	        if (lng < 96.25) {
	         if (lat < 27.25) {
	          return 40;
	         } else {
	          return 372;
	         }
	        } else {
	         return 40;
	        }
	       } else {
	        return 372;
	       }
	      }
	     }
	    } else {
	     if (lat < 26.5) {
	      if (lng < 98.0) {
	       return 40;
	      } else {
	       if (lat < 25.5) {
	        return 404;
	       } else {
	        return 40;
	       }
	      }
	     } else {
	      if (lng < 97.25) {
	       if (lat < 27.25) {
	        return 40;
	       } else {
	        if (lat < 27.5) {
	         if (lng < 97.0) {
	          return 40;
	         } else {
	          return 372;
	         }
	        } else {
	         if (lng < 97.0) {
	          return 372;
	         } else {
	          if (lat < 27.75) {
	           return 40;
	          } else {
	           return 372;
	          }
	         }
	        }
	       }
	      } else {
	       return 40;
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 24.25) {
	    if (lng < 99.75) {
	     if (lat < 23.25) {
	      if (lng < 99.5) {
	       return 40;
	      } else {
	       if (lat < 23.0) {
	        return 404;
	       } else {
	        return 40;
	       }
	      }
	     } else {
	      if (lng < 99.0) {
	       return 40;
	      } else {
	       return 404;
	      }
	     }
	    } else {
	     return 404;
	    }
	   } else {
	    if (lat < 26.0) {
	     if (lng < 98.5) {
	      if (lat < 25.75) {
	       return 404;
	      } else {
	       return 40;
	      }
	     } else {
	      return 404;
	     }
	    } else {
	     if (lng < 99.0) {
	      if (lat < 27.0) {
	       if (lat < 26.5) {
	        if (lng < 98.75) {
	         return 40;
	        } else {
	         return 404;
	        }
	       } else {
	        if (lng < 98.75) {
	         return 40;
	        } else {
	         if (lat < 26.75) {
	          return 404;
	         } else {
	          return 40;
	         }
	        }
	       }
	      } else {
	       if (lat < 27.5) {
	        if (lng < 98.75) {
	         return 40;
	        } else {
	         return 404;
	        }
	       } else {
	        if (lng < 98.5) {
	         if (lat < 27.75) {
	          return 40;
	         } else {
	          return 404;
	         }
	        } else {
	         if (lng < 98.75) {
	          if (lat < 27.75) {
	           return 40;
	          } else {
	           return 404;
	          }
	         } else {
	          return 404;
	         }
	        }
	       }
	      }
	     } else {
	      return 404;
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 98.25) {
	   if (lat < 30.75) {
	    if (lng < 96.75) {
	     if (lat < 29.25) {
	      if (lng < 96.25) {
	       return 372;
	      } else {
	       if (lat < 28.5) {
	        return 372;
	       } else {
	        if (lat < 28.75) {
	         if (lng < 96.5) {
	          return 372;
	         } else {
	          return 407;
	         }
	        } else {
	         if (lng < 96.5) {
	          if (lat < 29.0) {
	           return 372;
	          } else {
	           return 407;
	          }
	         } else {
	          if (lat < 29.0) {
	           return 372;
	          } else {
	           return 407;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 29.5) {
	       if (lng < 96.0) {
	        if (lng < 95.75) {
	         return 407;
	        } else {
	         return 372;
	        }
	       } else {
	        if (lng < 96.25) {
	         return 372;
	        } else {
	         return 407;
	        }
	       }
	      } else {
	       return 407;
	      }
	     }
	    } else {
	     if (lat < 28.5) {
	      if (lng < 97.5) {
	       return 372;
	      } else {
	       return 40;
	      }
	     } else {
	      return 407;
	     }
	    }
	   } else {
	    if (lat < 32.5) {
	     return 407;
	    } else {
	     if (lng < 97.5) {
	      return 407;
	     } else {
	      if (lat < 33.0) {
	       if (lng < 97.75) {
	        if (lat < 32.75) {
	         return 407;
	        } else {
	         return 404;
	        }
	       } else {
	        if (lng < 98.0) {
	         if (lat < 32.75) {
	          return 407;
	         } else {
	          return 404;
	         }
	        } else {
	         return 404;
	        }
	       }
	      } else {
	       if (lng < 97.75) {
	        return 407;
	       } else {
	        return 404;
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lat < 30.75) {
	    if (lng < 99.25) {
	     if (lat < 29.25) {
	      if (lat < 28.5) {
	       if (lng < 98.75) {
	        if (lng < 98.5) {
	         if (lat < 28.25) {
	          return 404;
	         } else {
	          return 407;
	         }
	        } else {
	         if (lat < 28.25) {
	          return 404;
	         } else {
	          return 407;
	         }
	        }
	       } else {
	        return 404;
	       }
	      } else {
	       if (lng < 98.75) {
	        return 407;
	       } else {
	        if (lat < 29.0) {
	         return 404;
	        } else {
	         if (lng < 99.0) {
	          return 407;
	         } else {
	          return 404;
	         }
	        }
	       }
	      }
	     } else {
	      if (lat < 30.25) {
	       return 407;
	      } else {
	       if (lng < 99.0) {
	        return 407;
	       } else {
	        return 404;
	       }
	      }
	     }
	    } else {
	     return 404;
	    }
	   } else {
	    if (lng < 99.0) {
	     if (lat < 32.25) {
	      if (lat < 31.5) {
	       if (lng < 98.75) {
	        return 407;
	       } else {
	        if (lat < 31.25) {
	         return 407;
	        } else {
	         return 404;
	        }
	       }
	      } else {
	       if (lng < 98.5) {
	        return 407;
	       } else {
	        if (lat < 31.75) {
	         if (lng < 98.75) {
	          return 407;
	         } else {
	          return 404;
	         }
	        } else {
	         if (lng < 98.75) {
	          if (lat < 32.0) {
	           return 407;
	          } else {
	           return 404;
	          }
	         } else {
	          return 404;
	         }
	        }
	       }
	      }
	     } else {
	      return 404;
	     }
	    } else {
	     return 404;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup196(double lat, double lng)
	{
	 if (lat < 42.0) {
	  if (lng < 96.75) {
	   return 407;
	  } else {
	   if (lat < 40.5) {
	    if (lng < 97.5) {
	     if (lat < 39.75) {
	      return 407;
	     } else {
	      if (lng < 97.0) {
	       if (lat < 40.25) {
	        return 404;
	       } else {
	        return 407;
	       }
	      } else {
	       if (lat < 40.0) {
	        if (lng < 97.25) {
	         return 407;
	        } else {
	         return 404;
	        }
	       } else {
	        return 404;
	       }
	      }
	     }
	    } else {
	     if (lat < 39.5) {
	      if (lng < 97.75) {
	       return 407;
	      } else {
	       return 404;
	      }
	     } else {
	      return 404;
	     }
	    }
	   } else {
	    if (lng < 97.5) {
	     if (lat < 41.0) {
	      if (lng < 97.0) {
	       return 407;
	      } else {
	       return 404;
	      }
	     } else {
	      return 407;
	     }
	    } else {
	     if (lat < 41.25) {
	      if (lng < 97.75) {
	       if (lat < 40.75) {
	        return 404;
	       } else {
	        return 407;
	       }
	      } else {
	       if (lat < 41.0) {
	        return 404;
	       } else {
	        return 407;
	       }
	      }
	     } else {
	      if (lng < 97.75) {
	       return 407;
	      } else {
	       if (lat < 41.5) {
	        if (lng < 98.0) {
	         return 407;
	        } else {
	         return 404;
	        }
	       } else {
	        if (lng < 98.0) {
	         if (lat < 41.75) {
	          return 404;
	         } else {
	          return 407;
	         }
	        } else {
	         return 404;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lat < 43.5) {
	   if (lng < 96.75) {
	    if (lat < 42.75) {
	     return 407;
	    } else {
	     if (lng < 96.0) {
	      return 407;
	     } else {
	      if (lng < 96.25) {
	       if (lat < 43.25) {
	        return 407;
	       } else {
	        return 121;
	       }
	      } else {
	       if (lat < 43.0) {
	        if (lng < 96.5) {
	         return 407;
	        } else {
	         return 121;
	        }
	       } else {
	        return 121;
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 97.5) {
	     if (lat < 42.75) {
	      return 407;
	     } else {
	      if (lng < 97.25) {
	       return 121;
	      } else {
	       if (lat < 43.0) {
	        return 404;
	       } else {
	        return 121;
	       }
	      }
	     }
	    } else {
	     if (lat < 42.75) {
	      if (lng < 97.75) {
	       if (lat < 42.25) {
	        return 407;
	       } else {
	        return 404;
	       }
	      } else {
	       return 404;
	      }
	     } else {
	      if (lng < 97.75) {
	       return 121;
	      } else {
	       if (lat < 43.0) {
	        if (lng < 98.0) {
	         return 121;
	        } else {
	         return 124;
	        }
	       } else {
	        if (lng < 98.0) {
	         if (lat < 43.25) {
	          return 121;
	         } else {
	          return 124;
	         }
	        } else {
	         return 124;
	        }
	       }
	      }
	     }
	    }
	   }
	  } else {
	   if (lng < 96.75) {
	    if (lat < 44.25) {
	     if (lng < 96.0) {
	      if (lat < 43.75) {
	       return 407;
	      } else {
	       if (lng < 95.75) {
	        return 407;
	       } else {
	        return 121;
	       }
	      }
	     } else {
	      return 121;
	     }
	    } else {
	     return 121;
	    }
	   } else {
	    if (lng < 97.75) {
	     return 121;
	    } else {
	     if (lat < 44.25) {
	      if (lat < 44.0) {
	       return 124;
	      } else {
	       if (lng < 98.0) {
	        return 121;
	       } else {
	        return 124;
	       }
	      }
	     } else {
	      if (lat < 44.5) {
	       if (lng < 98.0) {
	        return 121;
	       } else {
	        return 124;
	       }
	      } else {
	       return 121;
	      }
	     }
	    }
	   }
	  }
	 }
	}

	private  int kdLookup197(double lat, double lng)
	{
	 if (lng < 95.5) {
	  if (lat < 44.5) {
	   return 407;
	  } else {
	   if (lng < 94.25) {
	    return 407;
	   } else {
	    if (lng < 94.5) {
	     if (lat < 44.75) {
	      return 407;
	     } else {
	      return 121;
	     }
	    } else {
	     return 121;
	    }
	   }
	  }
	 } else {
	  if (lat < 39.25) {
	   if (lng < 98.25) {
	    if (lat < 36.5) {
	     if (lng < 97.5) {
	      return 407;
	     } else {
	      if (lat < 34.0) {
	       return 404;
	      } else {
	       return 407;
	      }
	     }
	    } else {
	     if (lng < 97.75) {
	      return 407;
	     } else {
	      if (lat < 39.0) {
	       return 407;
	      } else {
	       return 404;
	      }
	     }
	    }
	   } else {
	    if (lat < 36.5) {
	     if (lng < 99.5) {
	      if (lat < 35.0) {
	       if (lng < 98.75) {
	        if (lat < 34.25) {
	         if (lng < 98.5) {
	          if (lat < 34.0) {
	           return 404;
	          } else {
	           return 407;
	          }
	         } else {
	          return 404;
	         }
	        } else {
	         return 407;
	        }
	       } else {
	        if (lat < 34.25) {
	         return 404;
	        } else {
	         if (lng < 99.0) {
	          return 407;
	         } else {
	          if (lat < 34.5) {
	           if (lng < 99.25) {
	            return 407;
	           } else {
	            return 404;
	           }
	          } else {
	           if (lng < 99.25) {
	            if (lat < 34.75) {
	             return 404;
	            } else {
	             return 407;
	            }
	           } else {
	            if (lat < 34.75) {
	             return 404;
	            } else {
	             return 407;
	            }
	           }
	          }
	         }
	        }
	       }
	      } else {
	       if (lat < 35.75) {
	        if (lng < 99.25) {
	         return 407;
	        } else {
	         if (lat < 35.5) {
	          return 404;
	         } else {
	          return 407;
	         }
	        }
	       } else {
	        if (lng < 99.0) {
	         return 407;
	        } else {
	         if (lat < 36.0) {
	          if (lng < 99.25) {
	           return 407;
	          } else {
	           return 404;
	          }
	         } else {
	          if (lng < 99.25) {
	           if (lat < 36.25) {
	            return 407;
	           } else {
	            return 404;
	           }
	          } else {
	           return 404;
	          }
	         }
	        }
	       }
	      }
	     } else {
	      return 404;
	     }
	    } else {
	     if (lng < 99.75) {
	      if (lat < 37.75) {
	       if (lng < 99.25) {
	        return 407;
	       } else {
	        if (lat < 37.25) {
	         if (lat < 36.75) {
	          return 404;
	         } else {
	          if (lng < 99.5) {
	           if (lat < 37.0) {
	            return 407;
	           } else {
	            return 404;
	           }
	          } else {
	           return 404;
	          }
	         }
	        } else {
	         if (lng < 99.5) {
	          return 407;
	         } else {
	          return 404;
	         }
	        }
	       }
	      } else {
	       if (lng < 99.0) {
	        if (lat < 38.5) {
	         return 407;
	        } else {
	         if (lng < 98.5) {
	          if (lat < 38.75) {
	           return 407;
	          } else {
	           return 404;
	          }
	         } else {
	          if (lat < 38.75) {
	           if (lng < 98.75) {
	            return 407;
	           } else {
	            return 404;
	           }
	          } else {
	           return 404;
	          }
	         }
	        }
	       } else {
	        if (lat < 38.5) {
	         if (lng < 99.5) {
	          return 407;
	         } else {
	          if (lat < 38.0) {
	           return 407;
	          } else {
	           if (lat < 38.25) {
	            return 404;
	           } else {
	            return 407;
	           }
	          }
	         }
	        } else {
	         return 404;
	        }
	       }
	      }
	     } else {
	      return 404;
	     }
	    }
	   }
	  } else {
	   if (lng < 98.25) {
	    return kdLookup196(lat,lng);
	   } else {
	    if (lat < 42.75) {
	     return 404;
	    } else {
	     return 124;
	    }
	   }
	  }
	 }
	}

	private  int kdLookup198(double lat, double lng)
	{
	 if (lat < 25.25) {
	  if (lng < 111.0) {
	   if (lat < 22.75) {
	    if (lng < 110.75) {
	     return 404;
	    } else {
	     return 407;
	    }
	   } else {
	    return 404;
	   }
	  } else {
	   if (lat < 23.75) {
	    if (lng < 111.75) {
	     if (lat < 23.0) {
	      if (lng < 111.25) {
	       if (lat < 22.75) {
	        return 407;
	       } else {
	        return 404;
	       }
	      } else {
	       if (lng < 111.5) {
	        if (lat < 22.75) {
	         return 407;
	        } else {
	         return 404;
	        }
	       } else {
	        return 404;
	       }
	      }
	     } else {
	      if (lng < 111.5) {
	       return 404;
	      } else {
	       if (lat < 23.5) {
	        return 404;
	       } else {
	        return 292;
	       }
	      }
	     }
	    } else {
	     if (lat < 23.25) {
	      return 404;
	     } else {
	      if (lng < 112.0) {
	       if (lat < 23.5) {
	        return 404;
	       } else {
	        return 292;
	       }
	      } else {
	       if (lng < 112.25) {
	        if (lat < 23.5) {
	         return 404;
	        } else {
	         return 292;
	        }
	       } else {
	        return 292;
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 111.75) {
	     if (lat < 24.75) {
	      return 404;
	     } else {
	      if (lng < 111.25) {
	       if (lat < 25.0) {
	        return 404;
	       } else {
	        return 292;
	       }
	      } else {
	       if (lng < 111.5) {
	        return 404;
	       } else {
	        return 292;
	       }
	      }
	     }
	    } else {
	     if (lat < 24.5) {
	      if (lng < 112.0) {
	       if (lat < 24.0) {
	        return 292;
	       } else {
	        return 404;
	       }
	      } else {
	       return 292;
	      }
	     } else {
	      if (lng < 112.0) {
	       if (lat < 25.0) {
	        return 404;
	       } else {
	        return 292;
	       }
	      } else {
	       if (lat < 24.75) {
	        return 292;
	       } else {
	        if (lng < 112.25) {
	         if (lat < 25.0) {
	          return 404;
	         } else {
	          return 292;
	         }
	        } else {
	         return 292;
	        }
	       }
	      }
	     }
	    }
	   }
	  }
	 } else {
	  if (lng < 111.0) {
	   if (lat < 26.5) {
	    if (lng < 110.25) {
	     if (lat < 26.0) {
	      return 404;
	     } else {
	      if (lng < 110.0) {
	       return 292;
	      } else {
	       if (lat < 26.25) {
	        return 404;
	       } else {
	        return 292;
	       }
	      }
	     }
	    } else {
	     if (lat < 26.0) {
	      return 404;
	     } else {
	      if (lng < 110.5) {
	       return 292;
	      } else {
	       if (lng < 110.75) {
	        if (lat < 26.25) {
	         return 404;
	        } else {
	         return 292;
	        }
	       } else {
	        return 404;
	       }
	      }
	     }
	    }
	   } else {
	    if (lng < 109.75) {
	     if (lat < 27.75) {
	      if (lat < 27.0) {
	       return 292;
	      } else {
	       if (lat < 27.25) {
	        return 404;
	       } else {
	        return 292;
	       }
	      }
	     } else {
	      return 292;
	     }
	    } else {
	     return 292;
	    }
	   }
	  } else {
	   if (lat < 26.5) {
	    if (lng < 111.5) {
	     if (lat < 25.5) {
	      if (lng < 111.25) {
	       return 404;
	      } else {
	       return 292;
	      }
	     } else {
	      if (lat < 26.0) {
	       return 404;
	      } else {
	       if (lng < 111.25) {
	        return 404;
	       } else {
	        if (lat < 26.25) {
	         return 292;
	        } else {
	         return 404;
	        }
	       }
	      }
	     }
	    } else {
	     return 292;
	    }
	   } else {
	    return 292;
	   }
	  }
	 }
	}

	private  int kdLookup199(double lat, double lng)
	{
	 if (lat < 28.0) {
	  if (lng < 109.5) {
	   if (lat < 27.25) {
	    if (lat < 27.0) {
	     if (lat < 26.75) {
	      if (lat < 26.5) {
	       if (lat < 26.25) {
	        if (lat < 26.0) {
	         if (lat < 25.75) {
	          if (lat < 25.5) {
	           if (lat < 25.25) {
	            if (lng < 107.0) {
	             if (lat < 25.0) {
	              if (lat < 24.75) {
	               if (lat < 24.5) {
	                if (lat < 24.25) {
	                 if (lat < 24.0) {
	                  if (lat < 23.75) {
	                   if (lat < 23.5) {
	                    if (lat < 23.25) {
	                     if (lat < 22.75) {
	                      return 404;
	                     } else {
	                      if (lat < 23.0) {
	                       return 24;
	                      } else {
	                       return 404;
	                      }
	                     }
	                    } else {
	                     return 404;
	                    }
	                   } else {
	                    return 404;
	                   }
	                  } else {
	                   return 404;
	                  }
	                 } else {
	                  return 404;
	                 }
	                } else {
	                 return 404;
	                }
	               } else {
	                return 404;
	               }
	              } else {
	               return 404;
	              }
	             } else {
	              return 404;
	             }
	            } else {
	             return 404;
	            }
	           } else {
	            return 404;
	           }
	          } else {
	           return 404;
	          }
	         } else {
	          return 404;
	         }
	        } else {
	         return 404;
	        }
	       } else {
	        return 404;
	       }
	      } else {
	       return 404;
	      }
	     } else {
	      return 404;
	     }
	    } else {
	     return 404;
	    }
	   } else {
	    if (lng < 109.0) {
	     return 404;
	    } else {
	     if (lat < 27.5) {
	      return 292;
