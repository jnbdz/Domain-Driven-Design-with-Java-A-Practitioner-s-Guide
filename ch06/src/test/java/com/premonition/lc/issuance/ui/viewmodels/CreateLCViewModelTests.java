//package com.premonition.lc.issuance.ui.viewmodels;
//
//import com.premonition.lc.issuance.ui.services.CreateLCService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.verifyNoInteractions;
//
//@ExtendWith(MockitoExtension.class)
//class CreateLCViewModelTests {
//
//    @Mock
//    private CreateLCService service;
//
//    @Test
//    void shouldNotEnableCreateIfClientReferenceLesserThanMinimumLength() {
//        final CreateLCViewModel viewModel = new CreateLCViewModel(4);
//        viewModel.setClientReference("123");
//        assertThat(viewModel.getCreateDisabled()).isTrue();
//    }
//
//    @Test
//    void shouldEnableCreateIfClientReferenceEqualToMinimumLength() {
//        final CreateLCViewModel viewModel = new CreateLCViewModel(4);
//        viewModel.setClientReference("1234");
//        assertThat(viewModel.getCreateDisabled()).isFalse();
//    }
//
//    @Test
//    void shouldEnableCreateIfClientReferenceGreaterThanMinimumLength() {
//        final CreateLCViewModel viewModel = new CreateLCViewModel(4);
//        viewModel.setClientReference("12345");
//        assertThat(viewModel.getCreateDisabled()).isFalse();
//    }
//
//    @Test
//    void shouldInvokeServiceOnSave() {
//        final CreateLCViewModel viewModel = new CreateLCViewModel(4);
//        final String clientReference = "My awesome LC";
//        viewModel.setClientReference(clientReference);
//
//        viewModel.createLC(service);
//
//        verify(service).createLC(clientReference);
//    }
//
//    @Test
//    void shouldNotInvokeServiceIfCreateDisabled() {
//        final CreateLCViewModel viewModel = new CreateLCViewModel(4);
//        final String clientReference = "123";
//        viewModel.setClientReference(clientReference);
//
//        assertThrows(IllegalStateException.class, () -> viewModel.createLC(service));
//        verifyNoInteractions(service);
//    }
//}