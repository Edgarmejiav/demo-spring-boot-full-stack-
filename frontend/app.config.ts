import {ApplicationConfig} from "@angular/core";
import {provideHttpClient, withJsonpSupport} from "@angular/common/http";

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient(
      withJsonpSupport(),
    ),
  ]
};
