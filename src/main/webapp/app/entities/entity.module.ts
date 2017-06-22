import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { PentahorestAssetModule } from './asset/asset.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        PentahorestAssetModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PentahorestEntityModule {}
